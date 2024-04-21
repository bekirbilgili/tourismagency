package view;

import entity.Role;
import entity.User;

import javax.swing.*;

public class UserUpdateView extends Layout {
    private JPanel container;
    private JLabel lbl_header;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_password;
    private JPasswordField fld_password;
    private JComboBox<Role> cmb_role;
    private JLabel lbl_role;
    private JButton btn_save;
    private User user;

    public UserUpdateView(User user) {
        this.add(container);
        this.guiInitializer(500, 300);
        this.user = user;

        this.cmb_role.setModel(new DefaultComboBoxModel<>(Role.values()));
    }
}
