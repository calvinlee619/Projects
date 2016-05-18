package edu.cmu.cs.cs214.hw6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Callable;

import edu.cmu.cs.cs214.hw6.util.Log;

/**
 * Give MapTaskCommand to worker
 *
 */
public class MapCallable implements Callable<Void> {
	private final MapTask mTask;
    private final WorkerInfo mWorker;
    private List<String> mParts;
    private final static String TAG = "MapCallable";

    public MapCallable(MapTask task, WorkerInfo worker, List<String> parts) {
        mTask = task;
        mWorker = worker;
        mParts = parts;
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
            // Give MapTaskCommand to a worker
            out.writeObject(new MapTaskCommand(mTask, mParts, mWorker));
            out.reset();
            
            // Done
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            in.readObject();
            return null;
        }
        catch (IOException e) {
        	Log.e(TAG, "I/O error", e);
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
