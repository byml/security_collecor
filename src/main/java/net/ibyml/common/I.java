package net.ibyml.common;

import java.net.URI;

import org.apache.http.client.methods.HttpRequestBase;

public interface I {
	//网址
	
	//特殊参数设定
	
	//特殊header设定
	void xxx(HttpRequestBase httpRequest);
	
	URI getURI();
	
	//目录
	String getFileLocation();
	
	//文件名
	String getFileName();
}
