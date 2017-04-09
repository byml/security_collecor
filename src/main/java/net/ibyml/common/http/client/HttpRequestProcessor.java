package net.ibyml.common.http.client;

import org.apache.http.client.methods.HttpRequestBase;

public interface HttpRequestProcessor {
	void process(HttpRequestBase httpRequest);
}
