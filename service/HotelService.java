package service;

import java.util.List;

import model.BookInfo;
import model.Checkout;
import model.Hotel;
import model.Room;

public interface HotelService {
    public void book(BookInfo bookInfo,Room[][] hotelRoom,List<Integer> usingKeyCard);
    public Room[][] createHotel(Hotel hotel,Room[][] hotelRoom);
    public void listAvailableRoom(Room[][] hotelRoom);
    public void checkout(Checkout checkOut,Room[][] hotelRoom,List<Integer> usingKeyCard);
    public void listGuest( Room[][] hotelRoom);
    public void listGuestByAge(Room[][] hotelRoom,String operation,Integer age);
    public void listGuestByFloor(Room[][] hotelRoom,Integer floor);
    public void getGuestInRoom(Room[][] hotelRoom,Integer roomNo);
    public void checkoutGuestByFloor(Room[][] hotelRoom,Integer floor,List<Integer> usingKeyCard);
    public void bookByFloor(Room[][] hotelRoom,Integer floor,BookInfo bookInfo,List<Integer> usingKeyCard);

}
