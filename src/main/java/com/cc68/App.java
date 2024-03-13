package com.cc68;

import com.cc68.UI.LoginFrame;
import com.cc68.client.Client;
import com.cc68.manager.DaoManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
//        Client client = context.getBean("client", Client.class);
//        client.setContext(context);
//        client.action("login","user09","123456");
//        Thread.sleep(3000);
//        client.action("logout");
//        client.close();
//        client.action("logon","user11","9999");
        DaoManager daoManager = context.getBean("daoManager", DaoManager.class);
        Client client = context.getBean("client", Client.class);
        client.setDaoManager(daoManager);
        daoManager.init();
        LoginFrame loginFrame = context.getBean("loginFrame", LoginFrame.class);
        loginFrame.setVisible(true);
//        System.out.println("1710234651627".length());
//        System.out.println("10000000".length());
    }
}
