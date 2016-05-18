package edu.cmu.cs.cs214.hw6;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.cmu.cs.cs214.hw6.util.Log;
import edu.cmu.cs.cs214.hw6.util.WorkerStorage;

public class ReduceTaskCommand extends WorkerCommand {

	private static final long serialVersionUID = -4477004487249798921L;

	private static final String TAG = "ReduceTaskCommand";
	
	private final ReduceTask mTask;
	private final int rId;
	private int mNumWorkers;
	private final WorkerInfo mWorker;
	private final List<WorkerInfo> mMapWorkers;
	private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
	
	public ReduceTaskCommand(ReduceTask task, WorkerInfo worker, List<WorkerInfo> mapWorkers, int id, int numWorkers) {
		mTask = task;
		rId = id;
		mMapWorkers = mapWorkers;
		mWorker = worker;
		mNumWorkers = numWorkers;
	}
	
	@Override
	public void run() {
		Socket socket = getSocket();
		ExecutorService mExecutor = Executors.newFixedThreadPool(POOL_SIZE);
		try {
			Map<String, List<String>> conMap = new HashMap<String, List<String>>();
			List<ShuffleCallable> sCallables = new ArrayList<ShuffleCallable>();
			
			File file = new File(WorkerStorage.getFinalResultsDirectory(mWorker.getName()), "final.txt");
			file.delete();
			
			EmitterImpl emit = new EmitterImpl(file);
			// Give all Shuffle mapWorkers
			for(WorkerInfo worker : mMapWorkers) {
				sCallables.add(new ShuffleCallable(worker, conMap, rId, mNumWorkers));
			}
			try {
				mExecutor.invokeAll(sCallables); //execute
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			// When Shuffle is done then we execute the reduceTask
			for(String key : conMap.keySet()) {
					mTask.execute(key, conMap.get(key).iterator(), emit);
			}
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject("done");
		}
		catch(IOException e) {
			Log.e(TAG, "I/O Error", e);
		}
	}
}
