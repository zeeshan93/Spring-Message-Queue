package com.springmq.dynamicconsumer;

import java.net.URI;
import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;

public class JmsProducer {

	public static void main(String[] args) throws URISyntaxException, Exception {
		Connection connection = null;
		
		BrokerService broker = BrokerFactory.createBroker(new URI(
				"broker:(vm://localhost?jms.useAsyncSend=false)"));
		broker.start();	

		try {
			// Producer
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"vm://localhost");
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("customerQueue");
			MessageProducer producer = session.createProducer(queue);

			/*
			 * ClassPathXmlApplicationContext context = new
			 * ClassPathXmlApplicationContext( "applicationContext.xml");
			 * SpringJmsPersonProducer springJmsProducer =
			 * (SpringJmsPersonProducer) context .getBean("jmsTemplate");
			 * 
			 * // Send a message with a POJO - the template reuse the message
			 * converter System.out.println("Sending an email message.");
			 * jmsTemplate.convertAndSend("mailbox", new Person("new",22));
			 */

			Person personobj = new Person("xyz", 20);
			Message msg1 = session.createObjectMessage(personobj);
			System.out.println("Sending text '" + personobj + "'");
			producer.send(msg1);
			
			Person personobj1 = new Person("xyz", 30);
			Message msg2 = session.createObjectMessage(personobj1);
			System.out.println("Sending text '" + personobj1 + "'");
			producer.send(msg2);

			// Consumer
			MessageConsumer consumer = session.createConsumer(queue);
			consumer.setMessageListener(new ExampleListener());
			connection.start();
			// Thread.sleep(1000);
			System.out.println("after");

			// session.close();
		} finally {/*
			if (connection != null) {
				connection.close();
			}
		*/}
	}
}
