package com.zy.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.zy.system.tools.ZYHandler;
import com.zy.system.tools.ZYPrint;

public class ZYSocketMgr {

	public ArrayList<ZYSocket> zySockets;
	public ServerSocket serverSocket;
	
	public ZYSocketMgr(){
		super();
		try {
			zySockets = new ArrayList<ZYSocket>();
			serverSocket = new ServerSocket(5500);
		} catch (Exception e) {
			e.printStackTrace();
			ZYPrint.print("ZY: Create server socket error." + e.getLocalizedMessage());
		} 	
	}
	
	/*
	 * ����������Client�˵����ӡ�
	 * */
	public void startListening()
	{
		 new Thread(new Runnable() {
             public void run() {
            	while (true) {
					try {
						//�����ȴ����ӡ�
						Socket socket = serverSocket.accept();
						ZYPrint.println("���ܵ���������.......");
						dealWithSocket(socket);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				} 
             }
         }).start();
	}
	
	void dealWithSocket(Socket socket) {
		
		new Thread(new Runnable() {
			public void run() {
					ZYPrint.println("����Socket ���󡣡�����");
				ZYHandler handler = new ZYHandler();
				Thread dealWithThread = new Thread(new Runnable() {
					@Override
					public void run() {
						BufferedReader reader = null;
						handler.obj = reader;
						try {
							socket.setSoTimeout(10000);
							reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							// While for get the message.
							String socketMessage = reader.readLine();
							System.out.println("Waiting cliecnt Idmessage.---had received.");
							if (socketMessage != null) {
								boolean isValid = ZYSocketAuthentication.isValidClient(socketMessage);
								if (isValid) {
									ZYSocket zySocket = new ZYSocket();
									socket.setSoTimeout(0);
									zySocket.socket = socket;
									zySocket.cliectId = socketMessage;
									zySocket.descriptionStr = "this is an validsocket.";
									zySockets.add(zySocket);
									zySocket.isHeartWorking = true;
									zySocket.gotoRun();
								} else {
									socket.close();
								}
							}
							else{
								socket.close();
							}
						} catch (Exception e) {
							
							try {
								socket.close();//ȡ��socket��
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							ZYPrint.println("�ڹ涨��ʱ����,δ���յ�Client��Ϣ���Ѿ�ȡ���ͻ�������Socket");
						}
					}
				});
				dealWithThread.start();
			}
		}).start();
		
	}
}
