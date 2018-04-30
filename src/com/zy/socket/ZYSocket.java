package com.zy.socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import com.zy.system.tools.ZYPrint;

public class ZYSocket {

	final static int heartInterval = 60000;//6s
	
	public Socket socket;
	
	public String cliectId;//�ͻ��˵�Id. 
	
	public String  descriptionStr;//Socket������
	
	public boolean isHeartWorking = false;//����Ĭ��Ϊ�ر�
	
	public BufferedReader reader = null;
	public PrintWriter writer = null;
	
	public void gotoRun()
	{
		//����������
		heartRun();
		//������Ϣ��
		receiveMessage();
	}
	

	public void sentMessage(String message)
	{
		// sent message. File.
		try {
			if (writer == null) {
				writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			}
			writer.print(message);
			writer.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	private void receiveMessage()
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				try {
					
					//���� stream buffered Reader.
					if (reader == null) {
						reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					}
					
					//������ȡ�ͻ��˷��͵���Ϣ ��
					while (!Thread.currentThread().isInterrupted()) {
						String message = reader.readLine();
						ZYPrint.println("�յ��ͻ��˵���Ϣ"+message + "\n");
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
				
				Timer timer = new Timer();
                TimerTask  task = new TimerTask (){
                public void run() {
                    sentMessage("Hello, I am heart.... ��������....");
                    System.out.println("Hello, I am heart.... ��������....");
                   }
                };
                timer.schedule (task, 0, 10000L);
			}
		});
	    heartThread.start();
	}
}


