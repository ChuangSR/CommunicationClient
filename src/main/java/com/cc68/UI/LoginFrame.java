package com.cc68.UI;

import com.cc68.client.Client;
import com.cc68.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
/*
 * Created by JFormDesigner on Wed Mar 06 20:09:10 CST 2024
 */



@Controller("loginFrame")
public class LoginFrame extends JFrame {
    @Autowired
    private ApplicationContext context;
    private JLabel title;
    private JLabel accountLabel;
    private JLabel passwordLabel;
    private JTextField account;
    private JPasswordField password;
    private JButton login;
    private JButton register;

    public LoginFrame() {
        initComponents();
    }

    private void register(ActionEvent e) {
        this.dispose();
        context.getBean("registerFrame", RegisterFrame.class).setVisible(true);
    }
    public void log(Message message){
        HashMap<String, Object> data = message.getData();
        String code = data.get("code").toString();
        String type = message.getType();
        if ("418".equals(code)||"500".equals(code)){
            JOptionPane.showMessageDialog(null, "code:"+code+"\n"+data.get("message"));
        }else if ("connect".equals(type)){
            this.dispose();
            ChatRoomFrame chatRoomFrame = context.getBean("chatRoomFrame", ChatRoomFrame.class);
            chatRoomFrame.setVisible(true);
            Client client = context.getBean("client", Client.class);
            try {
                client.action("getUser");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void login(ActionEvent e){
        String account = this.account.getText();
        String password = this.password.getText();
        Client client = context.getBean("client", Client.class);
        try {
            client.action("login",account,password);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void passwordTxtKeyPressed(KeyEvent e) {
        keyPressedToChatRoom(e);
    }

    private void keyPressedToChatRoom(KeyEvent evt){
        int code = evt.getKeyCode();
        if(code==KeyEvent.VK_ENTER){
            this.login(null);
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        title = new JLabel();
        accountLabel = new JLabel();
        passwordLabel = new JLabel();
        account = new JTextField();
        password = new JPasswordField();
        login = new JButton();
        register = new JButton();

        //======== this ========
        setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- label1 ----
        title.setText("Java\u804a\u5929\u5ba4");
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 9f));
        title.setIcon(new ImageIcon("\\image\\icons8-chat-room-30.png"));


        //---- label2 ----
        accountLabel.setText("\u8d26\u53f7");
        accountLabel.setFont(accountLabel.getFont().deriveFont(accountLabel.getFont().getSize() + 4f));
        accountLabel.setIcon(new ImageIcon("\\image\\icons8-user-30(1).png"));

        //---- label3 ----
        passwordLabel.setText("\u5bc6\u7801");
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(passwordLabel.getFont().getSize() + 4f));
        passwordLabel.setIcon(new ImageIcon("\\image\\icons8-password-30(1).png"));

        //---- passwordTxt ----
        password.setEchoChar('*');
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                passwordTxtKeyPressed(e);
            }
        });

        //---- login ----
        login.setText("\u767b\u5f55");
        login.addActionListener(e -> login(e));
        login.setIcon(new ImageIcon("\\image\\icons8-login-30(1).png"));

        //---- register ----
        register.setText("\u6ce8\u518c");
        register.addActionListener(e -> register(e));
        register.setIcon(new ImageIcon("\\image\\icons8-register-30(1).png"));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(183, 183, 183)
                                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(101, 101, 101)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(passwordLabel, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                                        .addComponent(accountLabel, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(login)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(register))
                                                        .addComponent(account, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                                        .addComponent(password, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))))
                                .addContainerGap(168, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(accountLabel)
                                        .addComponent(account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(login, GroupLayout.Alignment.TRAILING)
                                        .addComponent(register, GroupLayout.Alignment.TRAILING))
                                .addGap(57, 57, 57))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
