package com.zy.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;

public class ZYSocket {

	final static int heartInterval = 60000;//6s
	
	public Socket socket;
	
	public String cliectId;//客户端的Id. 
	
	public String  descriptionStr;//Socket描述。
	
	public boolean isHeartWorking = false;
	
	public BufferedReader reader = null;
	
	public void gotoRun()
	{
		//启动心跳。
		heartRun();
		//接收信息。
		receiveMessage();
	}
	
	
	
	public void sentMessage(String message)
	{
		
		
	}
	
	
	private void receiveMessage()
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				try {
					
					//创建 stream buffered Reader.
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					//阻塞读取客户端发送的信息 。
					while (!Thread.currentThread().isInterrupted()) {
						String message = reader.readLine();
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		}).start();
	}
	
	
	/*
	 * 心跳机制。
	 * */
	
	private void heartRun() {
		
		if (!isHeartWorking) {
			return;
		}
		
	    Thread heartThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					
					
				}
				
			}
		});
	    heartThread.start();
	}
}


