package com.zy.main;

import com.zy.socket.ZYSocketMgr;

public class ZYMain {

	

	public static void main(String[] args) {
		System.out.println("**************��ӭ��ʹ�����ڹ������ܵ�����ʱ������**************");

	   ZYSocketMgr socketMgr =	new ZYSocketMgr();
	   socketMgr.startListening();
//		ZYSocketMgr socketMgr =new ZYSocketMgr();
//		socketMgr.listen();
		
	}
}
