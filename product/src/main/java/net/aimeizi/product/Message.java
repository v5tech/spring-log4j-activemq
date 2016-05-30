package net.aimeizi.product;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import javax.jms.*;

/**
 * 简单消息发送接收
 * Created by Administrator on 2016/5/30 0030.
 */
public class Message implements MessageListener {

    public Message() throws Exception {
        // create consumer and listen queue
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        //注意这里JMSAppender只支持TopicDestination//
        Destination topicDestination = session.createTopic("logTopic");
        MessageConsumer consumer = session.createConsumer(topicDestination);
        consumer.setMessageListener(this);

        // log a message
        Logger logger = Logger.getLogger(Message.class);
        logger.info("Info Log.");
        logger.warn("Warn Log");
        logger.error("Error Log.");

        // clean up
        Thread.sleep(1000);
        consumer.close();
        session.close();
        connection.close();
        System.exit(1);
    }

    public void onMessage(javax.jms.Message message) {
        try {
            // receive log event in your consumer
            LoggingEvent event = (LoggingEvent) ((ActiveMQObjectMessage) message).getObject();
            System.out.println("Received log [" + event.getLevel() + "]: " + event.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new Message();
    }

}
