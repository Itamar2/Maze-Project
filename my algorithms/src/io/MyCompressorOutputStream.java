package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	private int currentByte;
	private int byteCounter;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
		byteCounter = 0;
	}
	
	@Override
	public void write(int arg0) throws IOException {
		
		//new byte to write
		if(byteCounter==0){
			currentByte = arg0;
			byteCounter++;
		}
		//write the last byte
		else if(currentByte==arg0){
			if(byteCounter <Byte.MAX_VALUE){
				byteCounter++; // we store it
			}
			//last byte but we got max value
			else{
				out.write(currentByte);
				out.write(byteCounter);
				byteCounter=1;
			}
		} //write diffrent char
		else{
			out.write(currentByte);
			out.write(byteCounter);
			currentByte=arg0;
			byteCounter=1;
		}	
	}

	@Override
	public void flush() throws IOException {
		out.write(currentByte);
		out.write(byteCounter);
		byteCounter=0;
	}

	@Override
	public void close() throws IOException {
		super.close();
		out.close();
	}
	
	
}
