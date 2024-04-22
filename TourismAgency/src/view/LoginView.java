package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JPanel container;
    private JPanel w_top;
    private JLabel w_welcome;
    private JLabel w_welcome2;
    private JPanel w_bottom;
    private JTextField fld_username;
    private JPasswordField fld_userpassword;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_userpassword;
    private final UserManager userManager;
    private User user;

    public LoginView() {
        this.userManager = new UserManager();
        this.user = new User();
        this.add(container);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("PATIKA TURIZM ACENTASI");
        this.setSize(400, 400);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", getSize()));
        this.setVisible(true);

        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username, this.fld_userpassword};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_userpassword.getText());
                if (loginUser == null) {
                    Helper.showMsg("notFound");
                } else {

                    if (userManager.whatIsMyRole(loginUser).equals("ADMIN")) {
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                    } else if ((userManager.whatIsMyRole(loginUser).equals("EMPLOYEE"))) {
                        EmployeeView employeeView = new EmployeeView(loginUser);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "TANIMLANAMAYAN KULLANICI TİPİ",
                                "HATA",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            }
        });
    }
}
