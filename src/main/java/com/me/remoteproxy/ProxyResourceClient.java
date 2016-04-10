package com.me.remoteproxy;

public class ProxyResourceClient implements ResourceIF {
	private ResourceIF targetResource = new HttpResourceClient();

	@Override
	public String sayHello() {
		return this.targetResource.sayHello();
	}

}
