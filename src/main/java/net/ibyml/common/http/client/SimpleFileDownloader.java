package net.ibyml.common.http.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class SimpleFileDownloader implements FileDownloader {
	private FileDownloadSetting setting;
	private URIBuilderProcessor uriBuilderProcessor;
	private HttpRequestProcessor httpRequestProcessor;
	private URIBuilder uriBuilder;

	public SimpleFileDownloader(FileDownloadSetting setting) throws URISyntaxException {
		this(setting, null, null);
	}

	public SimpleFileDownloader(FileDownloadSetting setting, URIBuilderProcessor uriBuilderProcessor,
			HttpRequestProcessor httpRequestProcessor) throws URISyntaxException {
		this.setting = setting;
		this.uriBuilderProcessor = uriBuilderProcessor;
		this.httpRequestProcessor = httpRequestProcessor;
		init();
	}

	private void init() throws URISyntaxException {
		uriBuilder = new URIBuilder(setting.getUrl());
	}

	public void download() {
		if (uriBuilder == null) {
			throw new RuntimeException("uriBuilder null");
		} else {
			try {
				if (uriBuilderProcessor != null) {
					uriBuilderProcessor.process(uriBuilder);
				}
				URI uri = uriBuilder.build();
				HttpGet httpget = new HttpGet(uri);
				if (httpRequestProcessor != null) {
					httpRequestProcessor.process(httpget);
				}

				CloseableHttpClient httpclient = HttpClients.createDefault();
				CloseableHttpResponse response = httpclient.execute(httpget);

				mm(response);
				
				response.close();
				httpclient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void mm(HttpResponse response) throws IOException {
		String dirLocation = setting.getDirLocation();
		String fileLocation = GlobalSetting.getDownloadFileHomeLocation() + File.separator + dirLocation;

		File fileLocaltionDir = new File(fileLocation);
		if (!fileLocaltionDir.exists()) {
			fileLocaltionDir.mkdirs();
		}

		String fileName = setting.getFileName();
		if (fileName == null) {
			fileName = getFileName(response);
		} else if (fileName.lastIndexOf(".") == -1) {
			fileName += getFileNameExtension(response);
		}

		String fullFileName = fileLocation + File.separator + fileName;
		OutputStream os = new FileOutputStream(fullFileName);
		response.getEntity().writeTo(os);

		os.flush();
		os.close();

		System.out.println(response.getFirstHeader("Content-type").getValue());
		System.out.println("________________________________fullFileName:" + fullFileName);
	}

	public String getFileNameExtension(HttpResponse response) {
		Header headerContentDisposition = response.getFirstHeader("Content-Disposition");
		if (headerContentDisposition != null) {
			return getFileNameExtension(headerContentDisposition.getValue());
		} else {
			return "";
		}
	}

	protected String getFileNameExtension(String contentDisposition) {
		int lastIndexOfDot = contentDisposition.lastIndexOf(".");
		if (lastIndexOfDot > 0) {
			return contentDisposition.substring(lastIndexOfDot, contentDisposition.length() - 1);
		} else {
			return "";
		}
	}

	public String getFileName(HttpResponse response) {
		Header headerContentDisposition = response.getFirstHeader("Content-Disposition");
		if (headerContentDisposition != null) {
			return getFileName(headerContentDisposition.getValue());
		} else {
			return "file";
		}
	}

	protected String getFileName(String contentDisposition) {
		if (contentDisposition != null) {
			int pos = contentDisposition.indexOf("=\"");
			if (pos > 0) {
				try {
					String fileName = new String(contentDisposition.substring(pos + 2, contentDisposition.length() - 1)
							.getBytes("ISO-8859-1"), "utf-8");
					return fileName;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return "file";
	}
}
