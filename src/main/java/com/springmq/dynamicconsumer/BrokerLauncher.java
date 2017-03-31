package com.springmq.dynamicconsumer;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class BrokerLauncher {
	public static void main(String[] args) throws URISyntaxException, Exception {
		BrokerService broker = BrokerFactory.createBroker(new URI(
				"broker:(vm://localhost?jms.useAsyncSend=true)"));
		broker.start();		
	}
}
