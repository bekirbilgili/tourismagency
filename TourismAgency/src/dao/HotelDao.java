package dao;

import core.Db;
import entity.Hotel;
import entity.Role;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection connection;

    public HotelDao() {
        this.connection = Db.getInstance();
    }

   /* public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }*/

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel ORDER BY hotel_id ASC";

        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel" +
                "("
                + "hotel_name, "
                + "hotel_address, "
                + "hotel_mail"
                + "hotel_phone"
                + "hotel_star"
                + "car_park"
                + "wifi"
                + "pool"
                + "fitness"
                + "concierge"
                + "spa"
                + "room_service"
                + ")" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getAddress());
            pr.setString(3, hotel.getMail());
            pr.setString(4, hotel.getPhone());
            pr.setString(5, hotel.getStar());
            pr.setBoolean(6, hotel.isCarPark());
            pr.setBoolean(7, hotel.isWifi());
            pr.setBoolean(8, hotel.isPool());
            pr.setBoolean(9, hotel.isFitness());
            pr.setBoolean(10, hotel.isConcierge());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isRoomService());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Hotel hotel) {
        String query = "UPDATE public.hotel SET "
                + "hotel_name = ?, "
                + "hotel_address = ?, "
                + "hotel_mail = ?, "
                + "hotel_phone = ?, "
                + "hotel_star = ?, "
                + "car_park = ?, "
                + "wifi = ?, "
                + "pool = ?, "
                + "fitness = ?, "
                + "concierge = ?, "
                + "spa = ?, "
                + "room_service = ?"
                + " WHERE hotel_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getAddress());
            pr.setString(3, hotel.getMail());
            pr.setString(4, hotel.getPhone());
            pr.setString(5, hotel.getStar());
            pr.setBoolean(6, hotel.isCarPark());
            pr.setBoolean(7, hotel.isWifi());
            pr.setBoolean(8, hotel.isPool());
            pr.setBoolean(9, hotel.isFitness());
            pr.setBoolean(10, hotel.isConcierge());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isRoomService());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj = new Hotel();
        obj.setId(rs.getInt("hotel_id"));
        obj.setName(rs.getString("hotel_name"));
        obj.setMail(rs.getString("hotel_mail"));
        obj.setPhone(rs.getString("hotel_phone"));
        obj.setStar(rs.getString("hotel_star"));
        obj.setCarPark(rs.getBoolean("car_park"));
        obj.setWifi(rs.getBoolean("wifi"));
        obj.setPool(rs.getBoolean("pool"));
        obj.setFitness(rs.getBoolean("fitness"));
        obj.setConcierge(rs.getBoolean("concierge"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setRoomService(rs.getBoolean("room_service"));

        return obj;
    }
}
