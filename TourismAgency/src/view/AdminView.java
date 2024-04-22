package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
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
    private JScrollPane scrl_userlist;
    private JTable tbl_user;
    private JComboBox<Role> cmb_search;
    private JButton btn_search;
    private JComboBox<Role> cmb_role;
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
        loadUserComponent();
        loadUserFilter();
    }

    public void loadUserTable() {
        Object[] col_user = {"Kullanıcı ID", "Kullanıcı Adı", "Kullanıcı Şifresi", "Kullanıcı Rolü"};
        ArrayList<Object[]> userList = userManager.getForTable(col_user.length);
        this.createTable(this.tmdl_user_table, this.tbl_user, col_user, userList);
    }
    public void loadUserComponent() {
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
            int selectedId = this.getTableSelectedRow(tbl_user, 0);
            UserUpdateView userUpdateView = new UserUpdateView(this.userManager.getById(selectedId));
            userUpdateView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        userMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectedRowId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectedRowId)) {
                    Helper.showMsg("done");
                    loadUserTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        tbl_user.setComponentPopupMenu(userMenu);

        this.btn_logout.addActionListener(e -> {
            System.exit(0);
        });

       // cmb_search.setModel(new DefaultComboBoxModel<>(Role.values()));
    }

    public void loadUserFilter (){
        cmb_search.setModel(new DefaultComboBoxModel<>(Role.values()));
        this.cmb_search.setSelectedItem(null);
    }



}
