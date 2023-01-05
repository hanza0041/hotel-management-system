package service.impl;

import java.util.ArrayList;
import java.util.List;

import model.BookInfo;
import model.Checkout;
import model.Hotel;
import model.Room;
import service.HotelService;
import util.Utility;

public class HotelServiceImpl implements HotelService{

    @Override
    public void book(BookInfo bookInfo, Room[][] hotelRoom,List<Integer> usingKeyCard) {
        Integer floor = 0;
        Integer roomNoOnFloor = 0;
        Integer keyCard = 0;
        try{
            // find room to floor 
            floor = Utility.findFloor2Index(bookInfo.getRoomNo());
            roomNoOnFloor = Utility.findRoomNoOnFloor2Index(bookInfo.getRoomNo());
            keyCard = Utility.findKeyCard2Customer(usingKeyCard);
            if(Utility.checkRoomAvailable(hotelRoom,bookInfo)){
                usingKeyCard.add(keyCard);
                hotelRoom[floor][roomNoOnFloor].setName(bookInfo.getCustomerName());
                hotelRoom[floor][roomNoOnFloor].setAge(bookInfo.getCustomerAge());
                hotelRoom[floor][roomNoOnFloor].setIsCheckIn(true);
                hotelRoom[floor][roomNoOnFloor].setIsCheckOut(false);
                hotelRoom[floor][roomNoOnFloor].setKeyCard(keyCard);
                System.out.println("Room "+ bookInfo.getRoomNo() + " is booked by " + hotelRoom[floor][roomNoOnFloor].getName() + " with keycard number " +hotelRoom[floor][roomNoOnFloor].getKeyCard()+".");
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }


    @Override
    public Room[][] createHotel(Hotel hotel, Room[][] hotelRoom) {
        try{
            // create all room in hotel with null variable;
            hotelRoom = new Room[hotel.getFloor()][hotel.getRoomPerFloor()];
            for(int z=0;z<hotelRoom.length;z++){
                for(int y=0;y<hotelRoom[z].length;y++){
                    hotelRoom[z][y] = new Room(null, null, null, Utility.getRoomNo(z,y));
                }
            }
            System.out.println("Hotel created with "+hotel.getFloor() +" floor(s), "+hotel.getRoomPerFloor() + " room(s) per floor.");
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return hotelRoom;
    }


    @Override
    public void listAvailableRoom(Room[][] hotelRoom) {
        try{
            for(int i=0;i<hotelRoom.length;i++){
                for(int y=0;y<hotelRoom[i].length;y++){
                    if((hotelRoom[i][y].getName() == null)){
                        System.out.print(hotelRoom[i][y].getRoomNo() + " ");
                    }
                }
            }
            System.out.print("\n");
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        
    }


    @Override
    public void checkout(Checkout checkOut, Room[][] hotelRoom,List<Integer> usingKeyCard) {
        boolean isWrongKeyCard = false;
        boolean isCheckOut = false;
        Integer floorNotAvailableIndex = 0;
        Integer roomNoNotAvailableIndex = 0;
        // check checkout
        try{
            for(int i=0;i<hotelRoom.length;i++){
                for(int y=0;y<hotelRoom[i].length;y++){
                    if(hotelRoom[i][y].getName() == null ){
                        continue;
                    }else if((hotelRoom[i][y].getName().equals(checkOut.getName()) && hotelRoom[i][y].getKeyCard().equals(checkOut.getKeyCard()) )){
                        usingKeyCard.remove(checkOut.getKeyCard());
                        floorNotAvailableIndex = i;
                        roomNoNotAvailableIndex = y;
                        isCheckOut = true;

                        hotelRoom[i][y].setName(null);
                        hotelRoom[i][y].setAge(null);
                        hotelRoom[i][y].setKeyCard(null);

                        hotelRoom[i][y].setIsCheckOut(true);
                        hotelRoom[i][y].setIsCheckIn(false);
                        break;
                    }
                }
                if(isCheckOut){
                    break;
                }
            }
            // can not checkout (check wrong key card or not?)
            if(!isCheckOut ){
                isWrongKeyCard = Utility.checkWrongKeyCard(hotelRoom, checkOut);
            }
            if(isWrongKeyCard){
                System.out.println("Only "+Utility.findRoomFromKeyCard(checkOut.getKeyCard(),hotelRoom)+" can checkout with keycard number "+checkOut.getKeyCard()+".");
            }else{
                System.out.println("Room "+hotelRoom[floorNotAvailableIndex][roomNoNotAvailableIndex].getRoomNo()+ " is checkout.");
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }

    }


    @Override
    public void listGuest(Room[][] hotelRoom) {
    
        List<Room> allGuest = new ArrayList<>();
        List<String> allGuestSorted = new ArrayList<>();
        List<Integer> allKeyCardCheckin = new ArrayList<>();

        try{
            for(int i=hotelRoom.length-1;i>=0;i--){
                for(int y=hotelRoom[i].length-1;y>=0;y--){
                    if(hotelRoom[i][y].getIsCheckIn() && (!hotelRoom[i][y].getIsCheckOut())){
                        allGuest.add(hotelRoom[i][y]);
                        allKeyCardCheckin.add(hotelRoom[i][y].getKeyCard());
                    }
                    
                }
            }
            allGuestSorted = Utility.sortByRoomNoAsc(allKeyCardCheckin, allGuest);
            System.out.println(allGuestSorted.toString().replace("[", "").replace("]", ""));
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void listGuestByAge(Room[][] hotelRoom,String operation, Integer age) {
        List<Room> allGuest = new ArrayList<>();
        List<String> allGuestSorted = new ArrayList<>();
        List<Integer> allKeyCardCheckin = new ArrayList<>();

        try{
            for(int i=hotelRoom.length-1;i>=0;i--){
                for(int y=hotelRoom[i].length-1;y>=0;y--){
                    if(hotelRoom[i][y].getIsCheckIn() && (!hotelRoom[i][y].getIsCheckOut())){
                        switch(operation) {
                            case "<" : //code here
                                if(hotelRoom[i][y].getAge() < age){
                                    allGuest.add(hotelRoom[i][y]);
                                    allKeyCardCheckin.add(hotelRoom[i][y].getKeyCard());
                                }
                                break;
                            case ">" : //code here
                                if(hotelRoom[i][y].getAge() > age){
                                    allGuest.add(hotelRoom[i][y]);
                                    allKeyCardCheckin.add(hotelRoom[i][y].getKeyCard());
                                }
                                break;
                            case "==" : //code here
                            if(hotelRoom[i][y].getAge().equals(age)){
                                allGuest.add(hotelRoom[i][y]);
                                allKeyCardCheckin.add(hotelRoom[i][y].getKeyCard());
                            }
                                break;
                        default:
                            System.out.println("Wrong operation");
                        }
                    }
                    
                }
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }

        allGuestSorted = Utility.sortByRoomNoAsc(allKeyCardCheckin, allGuest);
        System.out.println(allGuestSorted.toString().replace("[", "").replace("]", ""));
    }


    @Override
    public void listGuestByFloor(Room[][] hotelRoom, Integer floor) {
        // floorIndex
        floor -= 1;
        List<Room> allGuest = new ArrayList<>();
        List<String> allGuestSorted = new ArrayList<>();
        List<Integer> allKeyCardCheckin = new ArrayList<>();
        try{
            for(int y=hotelRoom[floor].length-1;y>=0;y--){
                if(hotelRoom[floor][y].getIsCheckIn() && (!hotelRoom[floor][y].getIsCheckOut())){
                    allGuest.add(hotelRoom[floor][y]);
                    allKeyCardCheckin.add(hotelRoom[floor][y].getKeyCard());
                }
                
            }
            allGuestSorted = Utility.sortByRoomNoAsc(allKeyCardCheckin, allGuest);
            System.out.println(allGuestSorted.toString().replace("[", "").replace("]", ""));
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void getGuestInRoom(Room[][] hotelRoom, Integer roomNo) {
        try{
            for (int i = 0; i < hotelRoom.length; i++) {     
                for (int y = 0; y < hotelRoom[i].length; y++) {   
                    if(hotelRoom[i][y].getRoomNo().equals(roomNo)){
                        System.out.println(hotelRoom[i][y].getName());
                        break;
                    }
                }
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void checkoutGuestByFloor(Room[][] hotelRoom, Integer floor,List<Integer> usingKeyCard) {
        List<Integer> listRoom2CheckOut = new ArrayList<>();
        // floor index
        floor -= 1;
        try{
            for(int y=0;y<hotelRoom[floor].length;y++){
                if(hotelRoom[floor][y].getIsCheckIn()  && (!hotelRoom[floor][y].getIsCheckOut()) ){
                    usingKeyCard.remove(hotelRoom[floor][y].getKeyCard());
                    listRoom2CheckOut.add(hotelRoom[floor][y].getRoomNo());
                    hotelRoom[floor][y].setName(null);
                    hotelRoom[floor][y].setAge(null);
                    hotelRoom[floor][y].setKeyCard(null);

                    hotelRoom[floor][y].setIsCheckOut(true);
                    hotelRoom[floor][y].setIsCheckIn(false);
                }
            }
            System.out.println("Room "+(listRoom2CheckOut.toString().replace("[", "").replace("]", ""))+" are checkout.");
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        

        
    }


    @Override
    public void bookByFloor(Room[][] hotelRoom, Integer floor, BookInfo bookInfo,List<Integer> usingKeyCard) {
        // floor index
        Integer floorIndex = floor-1;
        Integer keyCard = 0;
        List<Integer> listRoom2CheckIn = new ArrayList<>();
        List<Integer> listKeyCard2CheckIn = new ArrayList<>();
        boolean availableOnThisFloor = true;

        try{
            for(int y=0;y<hotelRoom[floorIndex].length;y++){
                if(!((hotelRoom[floorIndex][y].getIsCheckIn()  && (hotelRoom[floorIndex][y].getIsCheckOut())) || (!hotelRoom[floorIndex][y].getIsCheckIn()) )){
                    availableOnThisFloor = false;
                }
            }
            if(availableOnThisFloor){
                for(int y=0;y<hotelRoom[floorIndex].length;y++){                        
                        keyCard = Utility.findKeyCard2Customer(usingKeyCard);
                        hotelRoom[floorIndex][y].setName(bookInfo.getCustomerName());
                        hotelRoom[floorIndex][y].setAge(bookInfo.getCustomerAge());
                        hotelRoom[floorIndex][y].setIsCheckIn(true);
                        usingKeyCard.add(keyCard);
                        hotelRoom[floorIndex][y].setKeyCard(keyCard);
                        hotelRoom[floorIndex][y].setIsCheckIn(true);
                        hotelRoom[floorIndex][y].setIsCheckIn(false);
    
                        listRoom2CheckIn.add(hotelRoom[floorIndex][y].getRoomNo());
                        listKeyCard2CheckIn.add(hotelRoom[floorIndex][y].getKeyCard());
                }
                System.out.println("Room "+(listRoom2CheckIn.toString().replace("[", "").replace("]", ""))+ " are booked with keycard number "+(listKeyCard2CheckIn.toString().replace("[", "").replace("]", "")));
            }else{
                System.out.println("Cannot book floor "+floor+" for "+bookInfo.getCustomerName()+".");
            }
            
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        
    }

}
