package view;

import entity.User;

import javax.swing.*;

public class EmployeeView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JButton btn_exit;
    private JLabel lbl_welcome;
    private JTabbedPane tab_hotelPanel;
    private JScrollPane scrl_hotelpanel;
    private JTable tbl_hotel;
    private JButton btn_hotel_add;
    private JPanel pnl_pension;
    private JScrollPane scrl_pension;
    private JTable tbl_pension;
    private JButton btn_pension;

    public EmployeeView (User user) {
        this.add(container);
        this.guiInitializer(1000,1000);
    }
}
