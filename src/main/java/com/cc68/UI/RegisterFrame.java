package com.cc68.UI;

import com.cc68.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;
/*
 * Created by JFormDesigner on Wed Mar 06 20:18:25 CST 2024
 */



/**
 * @author 周智成
 */
@Controller("registerFrame")
public class RegisterFrame extends JFrame {
    @Autowired
    private ApplicationContext context;
    private JLabel title;
    private JLabel accountLabel;
    private JLabel passwordLabel;
    private JTextField account;
    private JButton register;
    private JButton goBefore;
    private JLabel confirmPasswordLabel;
    private JPasswordField password;
    private JPasswordField confirmPassword;

    public RegisterFrame() {
        initComponents();
    }

    private void goBefore(ActionEvent e) {
        this.dispose();
        context.getBean("loginFrame",LoginFrame.class).setVisible(true);
    }

    public void log(HashMap<String,Object> data){
        String code = data.get("code").toString();
        String message = data.get("message").toString();
        JOptionPane.showMessageDialog(null, "code:"+code+"\n"+message);
    }

    private void register(ActionEvent e){
        System.out.println("register");
        String account = this.account.getText();
        String password = this.password.getText();
        String confirmPassword = this.confirmPassword.getText();
        if ("".equals(account)){
            JOptionPane.showMessageDialog(null, "用户名不能为空！");
        }else if (!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
        }else {
            Client client = context.getBean("client", Client.class);
            try {
                client.action("register",account,password);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                client.getReceiveManager().close();
                client.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        title = new JLabel();
        accountLabel = new JLabel();
        passwordLabel = new JLabel();
        account = new JTextField();
        register = new JButton();
        goBefore = new JButton();
        confirmPasswordLabel = new JLabel();
        password = new JPasswordField();
        confirmPassword = new JPasswordField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- label1 ----
        title.setText("\u6b22\u8fce\u6ce8\u518c");
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 9f));

        //---- label2 ----
        accountLabel.setText("\u8d26\u53f7");
        accountLabel.setFont(accountLabel.getFont().deriveFont(accountLabel.getFont().getSize() + 4f));

        //---- label3 ----
        passwordLabel.setText("\u5bc6\u7801");
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(passwordLabel.getFont().getSize() + 4f));

        //---- register ----
        register.setText("\u6ce8\u518c");
        register.addActionListener(e -> register(e));

        //---- goBefore ----
        goBefore.setText("\u8fd4\u56de");
        goBefore.addActionListener(e -> goBefore(e));

        //---- label4 ----
        confirmPasswordLabel.setText("\u786e\u8ba4\u5bc6\u7801");

        //---- passwordField1 ----
        password.setEchoChar('*');

        //---- passwordField2 ----
        confirmPassword.setEchoChar('*');

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(confirmPasswordLabel)
                                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(passwordLabel)
                                                                .addComponent(accountLabel)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(account, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                        .addComponent(password, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                        .addComponent(confirmPassword, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                                .addGap(100, 100, 100))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(register)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(goBefore)
                                .addGap(66, 66, 66))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(title)
                                .addGap(46, 46, 46)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(accountLabel))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordLabel)
                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(confirmPasswordLabel)
                                        .addComponent(confirmPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(register)
                                        .addComponent(goBefore))
                                .addGap(30, 30, 30))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
