package view;

import business.UserManager;
import entity.Role;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JPanel pnl_ribbon;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tabbedPane1;
    private JPanel pnl_userlist;
    private JPanel pnl_useradd;
    private JScrollPane scrl_userlist;
    private JTable tbl_user;
    private JComboBox<Role> cmb_search;
    private JButton btn_search;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_password;
    private JLabel lbl_role;
    private JComboBox<Role> cmb_role;
    private JButton btn_save;
    private JPasswordField fld_password;
    private JLabel lbl_header;
    private User user;
    private DefaultTableModel tmdl_user_table= new DefaultTableModel();
    private UserManager userManager;
    private JPopupMenu userMenu;

    public AdminView(User user) {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitializer(700, 500);
        this.user = user;
        if (user == null) {
            dispose();
        }

        this.lbl_welcome.setText("Hoşgeldiniz: " + user.getUsername());

        Object[] col_user = {"Kullanıcı ID", "Kullanıcı Adı", "Kullanıcı Şifresi", "Kullanıcı Rolü"};
        ArrayList<User> userList = userManager.findAll();
        tmdl_user_table.setColumnIdentifiers(col_user);

        for (User u: userList) {
            Object[] obj = {u.getId(),u.getUsername(),u.getPassword(),u.getRole()};
            tmdl_user_table.addRow(obj);
        }

        tbl_user.setModel(tmdl_user_table);
        tbl_user.getTableHeader().setReorderingAllowed(false);
        tbl_user.setEnabled(false);

        tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selectedRow,selectedRow);
            }
        });

        userMenu = new JPopupMenu();
        userMenu.add("Yeni").addActionListener(e -> {
            UserUpdateView userUpdateView = new UserUpdateView(null);
        });

        userMenu.add("Güncelle");
        userMenu.add("Sil");
        tbl_user.setComponentPopupMenu(userMenu);

        cmb_role.setModel(new DefaultComboBoxModel<>(Role.values()));
        cmb_search.setModel(new DefaultComboBoxModel<>(Role.values()));

    }
}
