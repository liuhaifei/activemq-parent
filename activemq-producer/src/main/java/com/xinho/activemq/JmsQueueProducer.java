package com.xinho.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;


/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 生产者
 * @date 2018/7/516:16
 */
public class JmsQueueProducer {

    public static void main(String[] args) {
        //1.创建连接工程
        ConnectionFactory connectionFactory=
                    new ActiveMQConnectionFactory("tcp:47.96.119.178:61616");
        //2.创建连接
        Connection connection=null;
        try {
            connection=connectionFactory.createConnection();
            connection.start();
            //3.创建会话session
            Session session=connection
                    .createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
