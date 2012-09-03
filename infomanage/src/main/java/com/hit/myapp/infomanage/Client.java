package com.hit.myapp.infomanage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
	private ExecutorService es = null;

	public static void main(String[] args) {
		new Client().execute();
	}

	public void execute() {
		es = Executors.newCachedThreadPool();
		es.execute(new UserThread());
		es.shutdown();
	}

}
