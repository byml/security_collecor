package net.ibyml.common.http.client;

public class GlobalSetting {

	// �����ļ����λ��
	private static String downloadFileHomeLocation = ".";

	public static String getDownloadFileHomeLocation() {
		return downloadFileHomeLocation;
	}

	public static void setDownloadFileHomeLocation(String downloadFileHomeLocation) {
		GlobalSetting.downloadFileHomeLocation = downloadFileHomeLocation;
	}

}
