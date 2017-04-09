package net.ibyml.common.http.client;

public class GlobalSetting {

	// 下载文件存放位置
	private static String downloadFileHomeLocation = ".";

	public static String getDownloadFileHomeLocation() {
		return downloadFileHomeLocation;
	}

	public static void setDownloadFileHomeLocation(String downloadFileHomeLocation) {
		GlobalSetting.downloadFileHomeLocation = downloadFileHomeLocation;
	}

}
