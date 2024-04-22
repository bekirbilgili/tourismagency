package view;

import business.UserManager;
import entity.Role;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private DefaultTableModel tmdl_user_table = new DefaultTableModel();
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
        loadUserTable();

        tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

        userMenu = new JPopupMenu();
        userMenu.add("Yeni").addActionListener(e -> {
            UserUpdateView userUpdateView = new UserUpdateView(null);
            userUpdateView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });

        userMenu.add("Güncelle").addActionListener(e -> {
            int selectedId = Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(), 0).toString());
            UserUpdateView userUpdateView = new UserUpdateView(this.userManager.getById(selectedId));
            userUpdateView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        userMenu.add("Sil");
        tbl_user.setComponentPopupMenu(userMenu);

        cmb_role.setModel(new DefaultComboBoxModel<>(Role.values()));
        cmb_search.setModel(new DefaultComboBoxModel<>(Role.values()));

    }

    public void loadUserTable(){
        Object[] col_user = {"Kullanıcı ID", "Kullanıcı Adı", "Kullanıcı Şifresi", "Kullanıcı Rolü"};
        ArrayList<Object[]> userList = userManager.getForTable(col_user.length);
        this.createTable(this.tmdl_user_table,this.tbl_user,col_user,userList);
    }
}
