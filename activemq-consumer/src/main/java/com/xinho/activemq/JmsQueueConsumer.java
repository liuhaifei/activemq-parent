package com.xinho.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 生产者
 * @date 2018/7/516:16
 */
public class JmsQueueConsumer {

    public static void main(String[] args) {
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



            for (int i=0;i<10;i++){
                TextMessage message=(TextMessage)consumer.receive();
                System.out.println(message.getText());
                if (i==8){
                    message.acknowledge();//延迟确认机制-->确认当前之前的  之后的不会被提交
                }

            }
//            session.commit();

            session.close();

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
