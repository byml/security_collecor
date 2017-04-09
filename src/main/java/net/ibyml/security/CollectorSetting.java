package net.ibyml.security;

public class CollectorSetting {
	public static String HOST_SZSE = "www.szse.cn";
	public static String HOST_SSE = "yunhq.sse.com.cn:32041";

	private String host;
	private String fileLocation;

	public CollectorSetting(String host, String fileLocation) {
		super();
		this.host = host;
		this.fileLocation = fileLocation;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getHost() {
		return host;
	}

	public String getFileLocation() {
		return fileLocation;
	}

}
