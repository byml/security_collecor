package net.ibyml.common;

import java.net.URI;

import org.apache.http.client.methods.HttpRequestBase;

public interface I {
	//��ַ
	
	//��������趨
	
	//����header�趨
	void xxx(HttpRequestBase httpRequest);
	
	URI getURI();
	
	//Ŀ¼
	String getFileLocation();
	
	//�ļ���
	String getFileName();
}
