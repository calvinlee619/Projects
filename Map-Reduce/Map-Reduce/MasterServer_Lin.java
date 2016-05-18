package edu.cmu.cs.cs214.hw6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.cmu.cs.cs214.hw6.util.Log;
import edu.cmu.cs.cs214.hw6.util.StaffUtils;

/**
 * This class represents the "master server" in the distributed map/reduce
 * framework. The {@link MasterServer} is in charge of managing the entire
 * map/reduce computation from beginning to end. The {@link MasterServer}
 * listens for incoming client connections on a distinct host/port address, and
 * is passed an array of {@link WorkerInfo} objects when it is first initialized
 * that provides it with necessary information about each of the available
 * workers in the system (i.e. each worker's name, host address, port number,
 * and the set of {@link Partition}s it stores). A single map/reduce computation
 * managed by the {@link MasterServer} will typically behave as follows:
 *
 * <ol>
 * <li>Wait for the client to submit a map/reduce task.</li>
 * <li>Distribute the {@link MapTask} across a set of "map-workers" and wait for
 * all map-workers to complete.</li>
 * <li>Distribute the {@link ReduceTask} across a set of "reduce-workers" and
 * wait for all reduce-workers to complete.</li>
 * <li>Write the final key/value pair results of the computation back to the
 * client.</li>
 * </ol>
 */
public class MasterServer extends Thread {
	private final int mPort;
    private List<WorkerInfo> mWorkers;

    private static final String TAG = "MasterServer";
    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final ExecutorService mExecutor;
       
 
    /**
     * The {@link MasterServer} constructor.
     *
     * @param masterPort The port to listen on.
     * @param workers Information about each of the available workers in the
     *        system.
     */
    public MasterServer(int masterPort, List<WorkerInfo> workers) {
        mPort = masterPort;
        mWorkers = workers;
        mExecutor = Executors.newFixedThreadPool(POOL_SIZE);
    }
    
    @Override
    public void run() {
    		ServerSocket serverSocket = null;
    		try {
    			serverSocket = new ServerSocket(mPort);
    		}
    		catch (IOException e) {
    			Log.e(TAG, "Could not open server socket on port " + mPort + ".");
    			return;
    		}
    		
    		Log.i(TAG, "Listening for incoming commands on port " + mPort + ".");
    		
    		while(true) {
    			try {
    				// Receive client
    				Socket clientSocket = serverSocket.accept();
    				mExecutor.execute(new MasterHandler(clientSocket, mWorkers));
                } catch (IOException e) {
                    Log.e(TAG, "Error while listening for incoming connections.", e);
                    break;
                }
            }

            Log.i(TAG, "Shutting down...");

            try {
                serverSocket.close();
            } catch (IOException e) {
            	Log.e(TAG, "I/O error", e);
            }
            finally {
            	mExecutor.shutdown();
            }
    }
    	
    private static class MasterHandler implements Runnable {
    	private final Socket mSocket;
    	private final ExecutorService mExecutor;
    	private static final String TAG = "MasterHandler";
    	
    	private static Map<String, List<WorkerInfo>> giveWorkMap;
        private  static Map<WorkerInfo, List<String>> workPartsMap; 
        private final List<WorkerInfo> mWorkers;

    	public MasterHandler(Socket socket, List<WorkerInfo> workers) {
    		mSocket = socket;
            mExecutor = Executors.newFixedThreadPool(POOL_SIZE);
            giveWorkMap = new HashMap<String, List<WorkerInfo>>();
            workPartsMap = new HashMap<WorkerInfo, List<String>>();
            mWorkers = workers;
    	}
    	
    	 /**
         * Update the giveWorkMap mapping each partition to a list of
         * workers who have the partition
         */
        public void updatePartMap() {
        	for(WorkerInfo worker : mWorkers) {
        		for(Partition partition : worker.getPartitions()) {
        			if(giveWorkMap.containsKey(partition.getPartitionName())) {
        				giveWorkMap.get(partition.getPartitionName()).add(worker);
        			}
        			else {
        				List<WorkerInfo> temp = new ArrayList<WorkerInfo>();
        				temp.add(worker);
        				giveWorkMap.put(partition.getPartitionName(), temp);
        			}
        		}
        	}
        }
        
        /**
         * Updates the workParts hashMap
         * Assigns unique partitions to workers
         */
        public void updateWorkMap() {
        	for(String part : giveWorkMap.keySet()) {
    			 List<WorkerInfo> workers = giveWorkMap.get(part); 
    			 int ran = (int) (Math.random() * workers.size());
    			 WorkerInfo work = workers.get(ran);
    			 // Keep track which workers have what partitions
    			 if(workPartsMap.containsKey(work)) {
    				 workPartsMap.get(work).add(part);
    			 }
    			 else {
    				 ArrayList<String> newy = new ArrayList<String>();
    				 newy.add(part);
    				 workPartsMap.put(work, newy);
    			 }
    		 }
        }
        
    	@Override
    	public void run() {
    		updatePartMap(); 
    		updateWorkMap(); 
    		try {
    			List<MapCallable> mCallables = new ArrayList<MapCallable>();
    			List<ReduceCallable> rCallables = new ArrayList<ReduceCallable>();
    			
    			ObjectInputStream in = new ObjectInputStream(mSocket.getInputStream());
    			MapTask mapTask = (MapTask) in.readObject();  //Receive mapTask
    			ReduceTask reduceTask = (ReduceTask) in.readObject(); //Receive reduceTask
    			
    			// MAP
    			for (WorkerInfo worker : mWorkers) {
    				if(workPartsMap.get(worker) != null) {
    					mCallables.add(new MapCallable(mapTask, worker, workPartsMap.get(worker)));
    				}
    			}
    			
    			//REDUCE
    			int iterId = 0;
    			for (WorkerInfo rworker : mWorkers) {
    				rCallables.add(new ReduceCallable(reduceTask, rworker, mWorkers, iterId, mWorkers.size()));
    				iterId++;
    			}
    			try {
    				mExecutor.invokeAll(mCallables); //Execute Map Workers
    				mExecutor.invokeAll(rCallables); //Execute Reduce Workers
    			}
    			catch (InterruptedException e) {
    				Thread.currentThread().interrupt();
    			}
    			ObjectOutputStream out = new ObjectOutputStream(mSocket.getOutputStream());
    			out.writeObject("Done");
    		}
    		catch (ClassNotFoundException e) {
    			Log.e(TAG, "Class not found error", e);
    		}
    		catch (IOException e) {
    			Log.e(TAG, "I/O error", e);
    		}
    		finally {
    			try {
    				mSocket.close();
    			}
    			catch(IOException e) {
    				//Ignore
    			}
    		}
    	}
    }
        
    /********************************************************************/
    /***************** STAFF CODE BELOW. DO NOT MODIFY. *****************/
    /********************************************************************/

    /**
     * Starts the master server on a distinct port. Information about each
     * available worker in the distributed system is parsed and passed as an
     * argument to the {@link MasterServer} constructor. This information can be
     * either specified via command line arguments or via system properties
     * specified in the <code>master.properties</code> and
     * <code>workers.properties</code> file (if no command line arguments are
     * specified).
     */
    public static void main(String[] args) {
        StaffUtils.makeMasterServer(args).start();
    }

}
