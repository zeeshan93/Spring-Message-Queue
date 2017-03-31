package com.springmq.dynamicconsumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

public class ExampleListener implements MessageListener {
//	@Autowired
//	TestService service;

	public void onMessage(Message message) {
		
		if (message instanceof ObjectMessage) {
			/*try {
				Thread.sleep(3000);
				System.out.println("After delayyy");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
            ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				Person someObject = (Person)objectMessage.getObject();
				System.out.println(someObject);
				//service.exec(someObject);
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
       

	}
}
