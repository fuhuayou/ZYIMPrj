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
	
	public String cliectId;//客户端的Id. 
	
	public String  descriptionStr;//Socket描述。
	
	public boolean isHeartWorking = false;//心跳默认为关闭
	
	public BufferedReader reader = null;
	public PrintWriter writer = null;
	
	public void gotoRun()
	{
		//启动心跳。
		heartRun();
		//接收信息。
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
					
					//创建 stream buffered Reader.
					if (reader == null) {
						reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					}
					
					//阻塞读取客户端发送的信息 。
					while (!Thread.currentThread().isInterrupted()) {
						String message = reader.readLine();
						ZYPrint.println("收到客户端的信息"+message + "\n");
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
				
				Timer timer = new Timer();
                TimerTask  task = new TimerTask (){
                public void run() {
                    sentMessage("Hello, I am heart.... 噗呲噗呲....");
                    System.out.println("Hello, I am heart.... 噗呲噗呲....");
                   }
                };
                timer.schedule (task, 0, 10000L);
			}
		});
	    heartThread.start();
	}
}


