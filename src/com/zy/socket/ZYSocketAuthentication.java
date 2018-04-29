package com.zy.socket;

import java.util.ArrayList;

public class ZYSocketAuthentication {

	//验证是否是合理的链接。
	/*
	 * 1. 有可能设备不一样。
	 * 2. 所有连接服务的Socket 要求带有， clientID + 设备5位随机号。 比如： 2424242424@121214
	 * 3. 
	 * 		验证： 从服务器的数据库中，查询是否 存在当前的clientID,	 如果存在，则允许创建链接，不存在则抛弃。
	 * */
	static ArrayList<String> testingCliectIds = new ArrayList<String>();
	public static boolean isValidClient(String clicentId){
		testingCliectIds.add("123456");
		testingCliectIds.add("123457");
		testingCliectIds.add("123458");
		testingCliectIds.add("123459");
		testingCliectIds.add("123450");
		if (testingCliectIds.contains(clicentId)) {
			System.out.println("********有效的 client 端 连接。");
			return true;
		}
		System.out.println("---------无效的 client 端 连接。");
		return false;
	}
	
	
	
	
}
