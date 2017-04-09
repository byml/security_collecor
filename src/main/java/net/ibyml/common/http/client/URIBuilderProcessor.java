package net.ibyml.common.http.client;

import org.apache.http.client.utils.URIBuilder;

public interface URIBuilderProcessor {
	void process(URIBuilder uriBuilder);
}
