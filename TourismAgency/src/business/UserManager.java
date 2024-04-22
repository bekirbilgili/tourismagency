package business;

import core.Helper;
import dao.UserDao;
import entity.Role;
import entity.User;

import javax.management.ObjectName;
import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);
    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size) {
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user : this.findAll()) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();
            userRowList.add(rowObject);
        }
        return userRowList;
    }

    public boolean save(User user) {
        if (user.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.userDao.save(user);
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return userDao.update(user);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notFound");
            return false;
        }
        return this.userDao.delete(id);
    }

    public ArrayList<User> searchForTable (Role role) {
        String query = "SELECT * FROM public.user";
        ArrayList<String> whereList = new ArrayList<>();

        if( role != null) {
            whereList.add("user_role = " + role.toString());
        }
        System.out.println(whereList);
        return this.userDao.findAll();
    }
}
