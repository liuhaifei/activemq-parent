package com.xinho.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/7/610:36
 */
public class JMSTopicProducer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory=
                new ActiveMQConnectionFactory("tcp://47.96.119.178:61616");
        Connection connection=null;
        try {
             connection=connectionFactory.createConnection();

             connection.start();

             Session session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

             Destination destination=session.createTopic("myTopic");

             MessageProducer producer=session.createProducer(destination);
//             producer.setDeliveryMode(DeliveryMode.PERSISTENT);

             //创建需要发送的消息
            TextMessage textMessage=session.createTextMessage("this是一个test");

            producer.send(textMessage);

            session.commit();
//            session.rollback();
            session.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
