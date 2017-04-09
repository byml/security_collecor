package net.ibyml.common.http.client;

public class SimpleFileDownloadSetting implements FileDownloadSetting {
	private String url;
	private String dirLocation;
	private String fileName;

	public SimpleFileDownloadSetting(String url, String dirLocation, String fileName) {
		super();
		this.url = url;
		this.dirLocation = dirLocation;
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public String getDirLocation() {
		return dirLocation;
	}

	public String getFileName() {
		return fileName;
	}

}
