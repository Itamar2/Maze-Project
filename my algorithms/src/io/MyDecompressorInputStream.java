package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

	private InputStream in;
	private int currentByte;
	private int byteCounter;
	
	public MyDecompressorInputStream(InputStream in) {
		this.in=in;
		byteCounter=0;
	}
	@Override
	public int read() throws IOException {
		
		if(byteCounter==0){
			currentByte=in.read();
			byteCounter=in.read();	
		}
		byteCounter--;
		return currentByte;
	}
}
