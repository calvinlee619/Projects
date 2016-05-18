package edu.cmu.cs.cs214.hw6;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import edu.cmu.cs.cs214.hw6.util.KeyValuePair;
import edu.cmu.cs.cs214.hw6.util.Log;

public class ShuffleCallable implements Callable<Void> {
	private static final String TAG = "ShuffleCallable";
	private WorkerInfo mWorker;
	private final int rId;
	private Map<String, List<String>> mConMap;
	private int numWorkers;
	
	public ShuffleCallable(WorkerInfo worker, Map<String, List<String>> conMap, int id, int numWorkers) {
		mWorker = worker;
		mConMap = conMap; 
		rId = id;
		this.numWorkers = numWorkers;
	}
	
	public WorkerInfo getWorker() {
		return mWorker;
	}
	
	@Override
	public Void call() {
		Socket socket = null;
		try {
			socket = new Socket(mWorker.getHost(), mWorker.getPort());
			
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new ShuffleTaskCommand(mWorker, rId, numWorkers));
			
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			// Get input from Shuffle Worker and safe on a hashmap
			while(true) {
				KeyValuePair pair = (KeyValuePair) in.readObject();
				if(pair == null) {
					break;
				}
				synchronized (mConMap) {
					if (mConMap.containsKey(pair.getKey())) {
						mConMap.get(pair.getKey()).add(pair.getValue());
					} else {
						List<String> temp = new ArrayList<String>();
						temp.add(pair.getValue());
						mConMap.put(pair.getKey(), temp);
					}
				}
			}
			return null;
		}
		catch(EOFException e) {
			Log.e(TAG, "EOF error", e);
			return null;
		}
		catch (FileNotFoundException e) {
			Log.e(TAG, "File not found", e);
			return null;
		}
		catch (IOException e) {
			Log.e(TAG, "I/O error in call", e);
			return null;
		}
		catch(ClassNotFoundException e) {
			Log.e(TAG, "Class not found error", e);
			return null;
		}
		finally {
			try {
				if (socket != null) {
					socket.close();
				}
			}
			catch (IOException e) {
				Log.e(TAG, "I/O error in socket", e);
			}
		}
	}
}
