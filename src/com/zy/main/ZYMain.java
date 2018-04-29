package com.zy.main;

import com.zy.socket.ZYSocketMgr;

public class ZYMain {

	

	public static void main(String[] args) {
		System.out.println("**************欢迎来使用兆亿光年智能电气即时服务器**************");

	   ZYSocketMgr socketMgr =	new ZYSocketMgr();
	   socketMgr.startListening();
//		ZYSocketMgr socketMgr =new ZYSocketMgr();
//		socketMgr.listen();
		
	}
}
