package com.zy.socket;

import java.util.ArrayList;

public class ZYSocketAuthentication {

	//��֤�Ƿ��Ǻ�������ӡ�
	/*
	 * 1. �п����豸��һ����
	 * 2. �������ӷ����Socket Ҫ����У� clientID + �豸5λ����š� ���磺 2424242424@121214
	 * 3. 
	 * 		��֤�� �ӷ����������ݿ��У���ѯ�Ƿ� ���ڵ�ǰ��clientID,	 ������ڣ������������ӣ���������������
	 * */
	static ArrayList<String> testingCliectIds = new ArrayList<String>();
	public static boolean isValidClient(String clicentId){
		testingCliectIds.add("123456");
		testingCliectIds.add("123457");
		testingCliectIds.add("123458");
		testingCliectIds.add("123459");
		testingCliectIds.add("123450");
		if (testingCliectIds.contains(clicentId)) {
			System.out.println("********��Ч�� client �� ���ӡ�");
			return true;
		}
		System.out.println("---------��Ч�� client �� ���ӡ�");
		return false;
	}
	
	
	
	
}
