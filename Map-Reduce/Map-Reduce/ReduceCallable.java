package edu.cmu.cs.cs214.hw6;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Callable;

import edu.cmu.cs.cs214.hw6.util.Log;

/**
 * Give the ReduceTaskCommand to Worker 
 * 
 */
public class ReduceCallable implements Callable<Void>{
	private final ReduceTask mTask;
	private final WorkerInfo mWorker;
	private int numWorkers;
	private final List<WorkerInfo> mMapWorkers;
	private final int rId;
	
	private final static String TAG = "ReduceCallable";

	public ReduceCallable(ReduceTask task, WorkerInfo worker, List<WorkerInfo> mapWorkers, int id, int numWorkers) {
		mTask = task;
		mWorker = worker;
		mMapWorkers = mapWorkers;
		rId = id;
		this.numWorkers = numWorkers;
    }

	@Override
	public Void call() {
		Socket socket = null;
		try {
			socket = new Socket(mWorker.getHost(), mWorker.getPort());

			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new ReduceTaskCommand(mTask, mWorker, mMapWorkers, rId, numWorkers));
			out.reset();

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			in.readObject();
			// Read and return the worker's final result.
			return null;
		}
		catch(EOFException e) {
			Log.e(TAG, "EOF error", e);
			return null;
		}
		catch(IOException e) {
			Log.e(TAG, "I/O error in call", e);
			return null;
		}
		catch (ClassNotFoundException e) {
			Log.e(TAG, "Class not found error", e);
			return null;
		}
		finally {
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				Log.e(TAG, "I/O error", e);
			}
		}
	}
}
