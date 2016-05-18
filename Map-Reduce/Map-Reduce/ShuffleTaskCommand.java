package edu.cmu.cs.cs214.hw6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.cmu.cs.cs214.hw6.util.KeyValuePair;
import edu.cmu.cs.cs214.hw6.util.Log;

/**
 * In charge of giving disjoint words to each worker
 *
 */
public class ShuffleTaskCommand extends WorkerCommand {
	private static final long serialVersionUID = -5508887078090228896L;
	private final static String TAG = "ShuffleTaskCommand";
	private int rId;
	private int numWorkers;
	private WorkerInfo mWorker;

	public ShuffleTaskCommand(WorkerInfo worker, int id, int numWorkers) {
		mWorker = worker;
		rId = id;
		this.numWorkers = numWorkers;
	}
	
	@Override
	public void run() {
		Socket socket = getSocket();
		String fileIn = "worker_storage/" + mWorker.getName() + "/intermediate_results/inter.txt";
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			File file = new File(fileIn);
			BufferedReader read = new BufferedReader(new FileReader(file));
			String line;
			String[] holder = new String[2];
			//Read in from intermediate file
			while ((line = read.readLine()) != null) {
				holder = line.split(" ");
				String key = holder[0];
				String value = holder[1];
				//We are separating what words each worker will get
				if(Math.abs(key.hashCode() % numWorkers) == rId) {
					out.writeObject(new KeyValuePair(key, value));
					out.reset();
				}
			}
			out.writeObject(null); //done
		}
		catch (IOException e){
			Log.e(TAG, "I/O error", e);
		}
	}
}
