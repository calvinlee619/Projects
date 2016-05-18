package edu.cmu.cs.cs214.hw6;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class EmitterImpl implements Emitter, Closeable {
	
	private final PrintWriter writer;
	
	public EmitterImpl(File file) throws FileNotFoundException {
		writer = new PrintWriter(new FileOutputStream(file), true);
	}

	@Override
	public void emit(String key, String value) {
		writer.println(key + " " + value);
	}

	@Override
	public void close() {
		writer.close();		
	}

}
