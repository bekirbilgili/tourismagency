package view;

import business.UserManager;
import core.Helper;
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
    private UserManager userManager;

    public UserUpdateView(User user) {
        this.add(container);
        this.guiInitializer(500, 300);
        this.user = user;
        this.userManager = new UserManager();

        if (user != null) {
            fld_username.setText(user.getUsername());
            fld_password.setText(user.getPassword());
        }

        this.cmb_role.setModel(new DefaultComboBoxModel<>(Role.values()));

        btn_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_username) || Helper.isFieldEmpty(fld_password)) {
                Helper.showMsg("error");
            } else {
                boolean result;
                if (this.user == null) {
                    User obj = new User(fld_username.getText(), fld_password.getText(), (Role) cmb_role.getSelectedItem());
                    result = this.userManager.save(obj);
                } else {
                    this.user.setUsername(fld_username.getText());
                    this.user.setPassword(fld_password.getText());
                    this.user.setRole((Role) cmb_role.getSelectedItem());
                    result = this.userManager.update(this.user);
                }
                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}
