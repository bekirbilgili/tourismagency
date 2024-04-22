package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import entity.Role;
import entity.User;

import java.util.ArrayList;

public class HotelManager {
    private HotelDao hotelDao;

    public HotelManager(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }
    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size) {
        ArrayList<Object[]> hotelRowList = new ArrayList<>();
        for (Hotel hotel : this.findAll()) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = hotel.getId();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = hotel.getAddress();
            rowObject[i++] = hotel.getMail();
            rowObject[i++] = hotel.getPhone();
            rowObject[i++] = hotel.getStar();
            rowObject[i++] = hotel.isCarPark();
            rowObject[i++] = hotel.isWifi();
            rowObject[i++] = hotel.isPool();
            rowObject[i++] = hotel.isFitness();
            rowObject[i++] = hotel.isConcierge();
            rowObject[i++] = hotel.isSpa();
            rowObject[i++] = hotel.isRoomService();
            hotelRowList.add(rowObject);
        }
        return hotelRowList;
    }

    public boolean save(Hotel hotel) {
        if (hotel.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.hotelDao.save(hotel);
    }

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    public boolean update(Hotel hotel) {
        if (this.getById(hotel.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return hotelDao.update(hotel);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notFound");
            return false;
        }
        return this.hotelDao.delete(id);
    }

    public ArrayList<Hotel> searchForTable (Hotel hotel) {
        String query = "SELECT * FROM public.hotel";
        ArrayList<String> whereList = new ArrayList<>();

        if( hotel != null) {
            whereList.add("user_role = " + hotel.toString());
        }
        System.out.println(whereList);
        return this.hotelDao.findAll();
    }
}
