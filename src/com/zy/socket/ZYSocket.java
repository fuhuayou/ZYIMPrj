package com.zy.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;

public class ZYSocket {

	final static int heartInterval = 60000;//6s
	
	public Socket socket;
	
	public String cliectId;//�ͻ��˵�Id. 
	
	public String  descriptionStr;//Socket������
	
	public boolean isHeartWorking = false;
	
	public BufferedReader reader = null;
	
	public void gotoRun()
	{
		//����������
		heartRun();
		//������Ϣ��
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
					
					//���� stream buffered Reader.
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					//������ȡ�ͻ��˷��͵���Ϣ ��
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
	 * �������ơ�
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


