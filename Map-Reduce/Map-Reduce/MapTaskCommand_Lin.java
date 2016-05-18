
package edu.cmu.cs.cs214.hw6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import edu.cmu.cs.cs214.hw6.util.Log;
import edu.cmu.cs.cs214.hw6.util.WorkerStorage;

public class MapTaskCommand extends WorkerCommand {
	private static final long serialVersionUID = -8379182882403165888L;

	private static final String TAG = "MapTaskCommand";
	
	private final MapTask mTask;
	private final List<String> mParts;
	private final WorkerInfo mWorker;
	
	public MapTaskCommand(MapTask task, List<String> parts, WorkerInfo worker) {
		mTask = task;
		mParts = parts;
		mWorker = worker;
	}
	
	@Override
	public void run() {
		Socket socket = getSocket();
		FileInputStream in = null;
		File fileOut = new File(WorkerStorage.getIntermediateResultsDirectory(mWorker.getName()), "inter.txt");
		fileOut.delete();
		EmitterImpl emitter;
		try {
			emitter = new EmitterImpl(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("This shouldn't happen... bad!");
		}
		try {
			for(String partition : mParts) {
				Partition newPart = new Partition(partition, mWorker.getName());
				for(File file : newPart) {
					in = new FileInputStream(file);
					//Execute the mapTask for each file
					mTask.execute(in, emitter);
					in.close();
				}
			}
			
			// Done
			ObjectOutputStream out =  new ObjectOutputStream(socket.getOutputStream());
			out.writeObject("done");
		}
		catch (IOException e) {
			Log.e(TAG, "I/O error while executing task.", e);
		}
	}
}
