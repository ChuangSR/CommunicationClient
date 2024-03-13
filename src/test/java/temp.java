//
//import com.cc68.client.Client;
//import com.cc68.mapper.MessageMapper;
//import com.cc68.pojo.MessageDatabase;
//import com.cc68.pojo.Style;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Controller;
//
//import javax.swing.*;
//import javax.swing.GroupLayout;
//import javax.swing.border.*;
//import javax.swing.table.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
///*
// * Created by JFormDesigner on Wed Mar 06 20:32:52 CST 2024
// */
//
//
//
///**
// * @author 周智成
// */
//@Controller("chatRoomFrame")
// class ChatRoomFrame extends JFrame {
//    @Autowired
//    private ApplicationContext context;
//    //    private Object[][] users;
//    private JScrollPane scrollPane1;
//    private JTable table1;
//    private JScrollPane scrollPane2;
//    private JTextArea chatArea;
//    private JButton send;
//    private JButton logout;
//    private JScrollPane scrollPane3;
//    private JPanel displayMessageBox;
//    private JPanel panel2;
//    private JLabel label2;
//    private JPanel panel3;
//    private JLabel label3;
//    private JLabel userName;
//    private JTextField searchbar;
//
//    private ArrayList<String> loginUser;
//    private ArrayList<String> logoutUsers;
//
//    HashMap<String, Style> styles = new HashMap<>();
//
//
//    public ChatRoomFrame() {
//        initComponents();
//    }
//    public void setUsers(ArrayList<String> loginUsers,ArrayList<String> logoutUsers){
//        this.loginUser = loginUsers;
//        this.logoutUsers = logoutUsers;
//        Object[][] o = new Object[loginUsers.size()+logoutUsers.size()+2][1];
//        o[0][0] = "在线用户";
//        int index = 1;
//        for (String t : loginUsers){
//            o[index++][0] = t;
//        }
//        o[index++][0] = "离线用户";
//
//        for (String t:logoutUsers){
//            o[index++][0] = t;
//        }
//
//        DefaultTableModel defaultTableModel = new DefaultTableModel(
//                o,
//                new String[] {
//                        "\u597d\u53cb"
//                }
//        ) {
//            boolean[] columnEditable = new boolean[] {
//                    false
//            };
//            @Override
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return columnEditable[columnIndex];
//            }
//        };
//        table1.setModel(defaultTableModel);
//        rendering();
//    }
//
//    private void logout(ActionEvent e) {
//        Client client = context.getBean("client", Client.class);
//        try {
//            client.action("logout");
//            client.close();
//            client.flush();
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//        this.dispose();
//        LoginFrame loginFrame = context.getBean("loginFrame", LoginFrame.class);
//        loginFrame.setVisible(true);
//    }
//
//    private void click(String account){
//
//    }
//
//    private void send(ActionEvent e){
//        String text = chatArea.getText();
//        if ("".equals(userName.getText())){
//            return;
//        }
//        Client client = context.getBean("client", Client.class);
//        try {
//            client.action("send",userName.getText().trim(),text);
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//        chatArea.setText("");
//    }
//
//    public void rendering(){
//        Client client = context.getBean("client", Client.class);
//        MessageMapper messageMapper = client.getMessageMapper();
//        ArrayList<MessageDatabase> messageDatabases = messageMapper.selectAllMessage(client.getAccount());
//        for (MessageDatabase messageDatabase:messageDatabases){
//            if (!"send".equals(messageDatabase.getType())){
//                continue;
//            }
//            if (styles.get(messageDatabase.getPosition())==null){
//                styles.put(messageDatabase.getPosition(),new Style(displayMessageBox));
//            }
//
//            JPanel temp = new JPanel();
//            JLabel label = new JLabel();
//            label.setText(messageDatabase.getMessage());
//            Style style = styles.get(messageDatabase.getPosition());
//            GroupLayout.ParallelGroup group = style.getGroup();
//            GroupLayout.SequentialGroup sequentialGroup = style.getSequentialGroup();
//            if (messageDatabase.getOriginator().equals(client.getAccount())){
//                temp.setLayout(new FlowLayout(FlowLayout.RIGHT));
//                temp.add(label);
//                group.addGroup(style.getGroupLayout().createSequentialGroup()
//                        .addContainerGap()
//                        .addComponent(temp, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
//                        .addContainerGap());
//                sequentialGroup.addComponent(temp, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
//            }else {
//                temp.setLayout(new FlowLayout(FlowLayout.LEFT));
//                temp.add(label);
//                group.addComponent(temp, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE);
//                sequentialGroup.addComponent(temp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
//            }
//        }
//    }
//
//
//
//    private void initComponents() {
//        scrollPane1 = new JScrollPane();
//        table1 = new JTable();
//        scrollPane2 = new JScrollPane();
//        chatArea = new JTextArea();
//        send = new JButton();
//        logout = new JButton();
//        scrollPane3 = new JScrollPane();
//        displayMessageBox = new JPanel();
//        panel2 = new JPanel();
//        label2 = new JLabel();
//        panel3 = new JPanel();
//        label3 = new JLabel();
//        userName = new JLabel();
//        searchbar = new JTextField();
//
//        //======== this ========
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        Container contentPane = getContentPane();
//
//        //======== scrollPane1 ========
//        {
//            scrollPane1.setBorder(new BevelBorder(BevelBorder.LOWERED));
//
//            //---- table1 ----
//            scrollPane1.setViewportView(table1);
//        }
//
//        //======== scrollPane2 ========
//        {
//            scrollPane2.setViewportView(chatArea);
//        }
//
//        //---- send ----
//        send.setText("\u53d1   \u9001");
//
//        //---- button1 ----
//        logout.setText("\u9000\u51fa\u767b\u5f55");
//        logout.addActionListener(e -> logout(e));
//
//        //======== scrollPane3 ========
//        {
//
//            //======== panel1 ========
//            {
//
//                //======== panel2 ========
//                {
//                    panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
//
//                    //---- label2 ----
//                    label2.setText("hello!");
//                    panel2.add(label2);
//                }
//
//                //======== panel3 ========
//                {
//                    panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
//
//                    //---- label3 ----
//                    label3.setText("hello!");
//                    Label label = new Label();
//                    label.setText("1000");
//                    panel3.add(label3);
//                    panel3.add(label);
//                }
//
////                JPanel temp = new JPanel();
////                temp
//                JPanel temp = new JPanel();
//                JLabel label = new JLabel();
//                temp.setLayout(new FlowLayout(FlowLayout.LEFT));
//                label.setText("sda");
//                temp.add(label);
//                GroupLayout panel1Layout = new GroupLayout(displayMessageBox);
//                GroupLayout panel1Layout1 = new GroupLayout(displayMessageBox);
//                displayMessageBox.setLayout(panel1Layout1);
////                panel1Layout.setHorizontalGroup();
//                panel1Layout.setHorizontalGroup(
//                        panel1Layout.createParallelGroup()
//                                .addComponent(panel2, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
//                                .addGroup(panel1Layout.createSequentialGroup()
//                                        .addContainerGap()
//                                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
//                                        .addContainerGap())
//                                .addComponent(temp, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
//                );
//                panel1Layout.setVerticalGroup(
//                        panel1Layout.createParallelGroup()
//                                .addGroup(panel1Layout.createSequentialGroup()
//                                                .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                                .addComponent(panel3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
//                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                                .addComponent(temp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
////                                        .addGap(0, 191, Short.MAX_VALUE)
//                                )
//
//                );
//            }
////            scrollPane3.setViewportView(displayMessageBox);
//        }
//
//        //---- label1 ----
//
//        //---- textField1 ----
//        searchbar.setText("\u641c\u7d22......");
//        searchbar.setFont(new Font("Microsoft YaHei UI", Font.ITALIC, 12));
//
//        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
//        contentPane.setLayout(contentPaneLayout);
//        contentPaneLayout.setHorizontalGroup(
//                contentPaneLayout.createParallelGroup()
//                        .addGroup(contentPaneLayout.createSequentialGroup()
//                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
//                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
//                                        .addComponent(searchbar, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
//                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                .addGroup(contentPaneLayout.createParallelGroup()
//                                        .addGroup(contentPaneLayout.createSequentialGroup()
//                                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
//                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                                .addGroup(contentPaneLayout.createParallelGroup()
//                                                        .addComponent(send)
//                                                        .addComponent(logout)))
//                                        .addComponent(userName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
//                                .addContainerGap())
//        );
//        contentPaneLayout.setVerticalGroup(
//                contentPaneLayout.createParallelGroup()
//                        .addGroup(contentPaneLayout.createSequentialGroup()
//                                .addContainerGap()
//                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//                                        .addGroup(contentPaneLayout.createSequentialGroup()
//                                                .addComponent(userName, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
//                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                                .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
//                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
//                                                        .addGroup(contentPaneLayout.createSequentialGroup()
//                                                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
//                                                                .addContainerGap())
//                                                        .addGroup(contentPaneLayout.createSequentialGroup()
//                                                                .addComponent(send)
//                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                                                .addComponent(logout)
//                                                                .addGap(14, 14, 14))))
//                                        .addGroup(contentPaneLayout.createSequentialGroup()
//                                                .addGap(0, 4, Short.MAX_VALUE)
//                                                .addComponent(searchbar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE))))
//        );
//        pack();
//        setLocationRelativeTo(getOwner());
//        userName.setText("");
//        send.addActionListener(e -> send(e));
//        table1.addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent e) {
//            int r= table1.getSelectedRow();
//            int c= table1.getSelectedColumn();
//            //得到选中的单元格的值，表格中都是字符串
//            Object value= table1.getValueAt(r, c);
//            Client client = context.getBean("client", Client.class);
//            if ("在线用户".equals(value.toString())||"离线用户".equals(value.toString())||client.getAccount().equals(value.toString())){
//                return;
//            }
//            userName.setText("                                                                              "+value);
//            click(value.toString());
//        }});
//    }
//
//    public ApplicationContext getContext() {
//        return context;
//    }
//
//    public void setContext(ApplicationContext context) {
//        this.context = context;
//    }
//}
