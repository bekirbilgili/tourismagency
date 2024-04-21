package view;

import core.Helper;

import javax.swing.*;

public class Layout extends JFrame {
    public void guiInitializer(int width, int height) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("PATIKA TURIZM ACENTASI");
        this.setSize(width, height);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", getSize()));
        this.setVisible(true);
    }
}
