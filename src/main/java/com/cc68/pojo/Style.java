package com.cc68.pojo;

import javax.swing.*;

public class Style {
    private JPanel displayMessageBox;
    private GroupLayout groupLayout;

    private GroupLayout.ParallelGroup group;

    GroupLayout.SequentialGroup sequentialGroup;

    public Style(JPanel displayMessageBox) {
        this.displayMessageBox = displayMessageBox;
        this.groupLayout = new GroupLayout(displayMessageBox);
        this.group = groupLayout.createParallelGroup();
        this.sequentialGroup = groupLayout.createSequentialGroup();
    }

    public GroupLayout getGroupLayout() {
        return groupLayout;
    }

    public void setGroupLayout(GroupLayout groupLayout) {
        this.groupLayout = groupLayout;
    }

    public GroupLayout.ParallelGroup getGroup() {
        return group;
    }

    public void setGroup(GroupLayout.ParallelGroup group) {
        this.group = group;
    }

    public GroupLayout.SequentialGroup getSequentialGroup() {
        return sequentialGroup;
    }

    public void setSequentialGroup(GroupLayout.SequentialGroup sequentialGroup) {
        this.sequentialGroup = sequentialGroup;
    }

    public JPanel getDisplayMessageBox() {
        return displayMessageBox;
    }

    public void setDisplayMessageBox(JPanel displayMessageBox) {
        this.displayMessageBox = displayMessageBox;
    }
}
