package com.me.remoteproxy;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpResourceClient implements ResourceIF {

	private static final URI URL_SECURED_BY_BASIC_AUTHENTICATION = URI.create("http://localhost:9080/");

	private ResponseHandler<String> handler = new BasicResponseHandler();

	@Override
	public String sayHello() {
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("user", "password");
		provider.setCredentials(AuthScope.ANY, credentials);
		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();

		String result = null;
		HttpResponse response;
		try {
			response = client.execute(new HttpGet(URL_SECURED_BY_BASIC_AUTHENTICATION));
			int statusCode = response.getStatusLine().getStatusCode();
			// assertThat(statusCode, equalTo(HttpStatus.SC_OK));
			result = this.handler.handleResponse(response);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
