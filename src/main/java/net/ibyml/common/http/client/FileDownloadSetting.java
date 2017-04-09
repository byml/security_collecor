package net.ibyml.common.http.client;

/**
 * 文件下载设置
 * 
 * @author Byml
 *
 */
public interface FileDownloadSetting {
	// 下载文件的网址
	String getUrl();

	// 下载文件的存放目录位置
	String getDirLocation();

	// 下载文件的存放文件名
	String getFileName();
}
