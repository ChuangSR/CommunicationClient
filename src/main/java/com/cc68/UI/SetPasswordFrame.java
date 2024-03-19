package com.cc68.UI;

import com.cc68.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;
@Controller("setPasswordFrame")
public class SetPasswordFrame extends JFrame {
    @Autowired
    private ApplicationContext context;
    public SetPasswordFrame() {
        initComponents();
    }

    public void log(HashMap<String,Object> data){
        String code = (String) data.get("code");
        JOptionPane.showMessageDialog(null, "code:"+code+"\n"+data.get("message"));
        back(null);
    }

    private void setPassword(ActionEvent e){
        String passwordField1Text = passwordField1.getText();
        String passwordField2Text = passwordField2.getText();
        if (!passwordField1Text.equals(passwordField2Text)){
            JOptionPane.showMessageDialog(null, "输入的两次密码不一致");
            return;
        }
        Client client = context.getBean("client", Client.class);
        try {
            client.action("setPassword",passwordField1Text);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void back(ActionEvent e){
        this.setVisible(false);
        context.getBean("chatRoomFrame", ChatRoomFrame.class).setVisible(true);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();
        this.addWindowListener(
                new WindowAdapter() {@Override public void windowClosing(WindowEvent e) {
                    back(null);
                    }
                }
        );
        //======== this ========
        setTitle("setPassword");
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u4fee\u6539\u5bc6\u7801");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 7f));

        //---- label2 ----
        label2.setText("\u65b0\u5bc6\u7801");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 4f));

        //---- label3 ----
        label3.setText("\u786e\u8ba4\u5bc6\u7801");
        label3.setIconTextGap(5);
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 4f));

        //---- passwordField2 ----
        passwordField2.setEchoChar('*');

        //---- button1 ----
        button1.setText("\u786e\u8ba4\u4fee\u6539");
        button1.addActionListener(this::setPassword);

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.addActionListener(this::back);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label2)
                                .addComponent(label3))
                            .addGap(28, 28, 28)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                .addComponent(passwordField2, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(91, 91, 91)
                            .addComponent(button1)
                            .addGap(91, 91, 91)
                            .addComponent(button2)))
                    .addContainerGap(68, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap(208, Short.MAX_VALUE)
                            .addComponent(button2))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(36, 36, 36)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label3)
                                .addComponent(passwordField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                            .addComponent(button1)))
                    .addGap(28, 28, 28))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton button1;
    private JButton button2;
    public ApplicationContext getContext() {
        return context;
    }
    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
