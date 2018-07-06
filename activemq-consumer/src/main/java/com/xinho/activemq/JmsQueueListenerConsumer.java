package com.xinho.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;


/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 生产者
 * @date 2018/7/516:16
 */
public class JmsQueueListenerConsumer {

    public static void main(String[] args) throws IOException {
        //1.创建连接工程
        ConnectionFactory connectionFactory=
                    new ActiveMQConnectionFactory("tcp://47.96.119.178:61616");
        //2.创建连接
        Connection connection=null;
        try {
            connection=connectionFactory.createConnection();
            connection.start();
            //3.创建会话session
            Session session=connection
                    .createSession(Boolean.FALSE,Session.DUPS_OK_ACKNOWLEDGE);//延迟确认

            //4.创建目的地destination
            Destination destination=session.createQueue("myQueue");
            //5.创建发送者
            MessageConsumer consumer=session.createConsumer(destination);


            MessageListener messageListener=new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println(((TextMessage)message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            };

           consumer.setMessageListener(messageListener);


            session.commit();

//            session.close();


            System.in.read();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
