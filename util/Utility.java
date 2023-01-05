package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import model.BookInfo;
import model.Checkout;
import model.Room;

public class Utility {
    public static final String FILE_NAME = "input.txt";
    

    /*
     * string = "1234"
     */
    public static Integer string2Integer(String string){
        int number = 0;
        try{
            number = Integer.parseInt(string);
            // System.out.println(number); // output = 25             
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        return number;
    }
    
    /*
     * find missing number / find max number of keycard
     */
    public static Integer findKeyCard2Customer(List<Integer> allKeyCard){
        
        Integer max = 1;
        try{
            if(allKeyCard.size()==0){
                return max;
            }else{
                HashSet<Integer> set = new HashSet<Integer>();
                for(int i=0; i<allKeyCard.size();i++){
                    set.add(allKeyCard.get(i));
                }
                int n = allKeyCard.size() + 1;
                for(int i=1; i<n;i++){
                    if(!set.contains(i)){
                        return i;
                    }
                }
                max = allKeyCard.stream().mapToInt(v->v).max().orElseThrow(NoSuchElementException::new);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return max + 1;
    }


    public static String findRoomFromKeyCard(Integer keyCard,Room[][] hotelRoom){
        String name = "unknow";
        try{
            for(int i=0;i<hotelRoom.length;i++){
                for(int y=0;y<hotelRoom[i].length;y++){
                    if(hotelRoom[i][y].getName() == null ){
                        continue;
                    }else if(hotelRoom[i][y].getKeyCard().equals(keyCard)){
                        name = hotelRoom[i][y].getName();
                    }
                }
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return name;
    }
        
    public static Integer findFloor2Index(Integer Room){
        return (Room/100) - 1;
    }

    public static Integer findRoomNoOnFloor2Index(Integer Room){
        String roomStr = String.valueOf(Room);
        return Utility.string2Integer(roomStr.substring(1)) - 1;
    }

    /*
     * check Room is Available ???
     */
    public static boolean checkRoomAvailable(Room[][] hotelRoom,BookInfo bookInfo){
        boolean isAvailable = true;
        Integer floorNotAvailableIndex = 0;
        Integer roomNoNotAvailableIndex = 0;
        boolean isFull = true;
        try{
            for(int i=0;i<hotelRoom.length;i++){
                for(int y=0;y<hotelRoom[i].length;y++){
                    if((hotelRoom[i][y].getName() != null) && (hotelRoom[i][y].getRoomNo().equals(bookInfo.getRoomNo()))){
                        isAvailable = false;
                        floorNotAvailableIndex = i;
                        roomNoNotAvailableIndex = y;
                    }else if(!hotelRoom[i][y].getIsCheckIn()){
                        isFull = false;
                    }
                    
                }
            }
            if(isFull){
                System.out.println("Hotel is fully booked.");
            }else if(!isAvailable){
                System.out.println("Cannot book room "+bookInfo.getRoomNo()+" for "+bookInfo.getCustomerName()+", The room is currently booked by "+hotelRoom[floorNotAvailableIndex][roomNoNotAvailableIndex].getName()+".");
            }else{
                //nothing
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return isAvailable;

    }


    public static Integer getRoomNo(Integer floor , Integer roomNoOnFloor){
        floor += 1;
        roomNoOnFloor += 1;
        String roomNo = "";
        String roomNoOnFloorStr = "";
        try{
            roomNoOnFloorStr = String.format("%02d", roomNoOnFloor);
            roomNo = String.valueOf(floor)+roomNoOnFloorStr;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return string2Integer(roomNo);

    }


    /*Sort RoomNo ASC */
    public static List<String> sortByRoomNoAsc(List<Integer> allKeyCardCheckin ,List<Room> allGuest){
        List<String> allGuestSorted = new ArrayList<>();
        List<Room> allGuestTemp = allGuest;
        // asc sorting
        Collections.sort(allKeyCardCheckin);
        //Sort the array in asc order    
        try{
            for (int i = 0; i < allKeyCardCheckin.size(); i++) {     
                for (int j = 0; j < allGuestTemp.size(); j++) {   
                    if(allKeyCardCheckin.get(i).equals(allGuestTemp.get(j).getKeyCard())) {
                        allGuestSorted.add(allGuest.get(j).getName());
                        allGuestTemp.remove(j);
                    }
                }     
            }    
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        return allGuestSorted;
    }

    /*If customer send wrong keycard */
    public static boolean checkWrongKeyCard(Room[][] hotelRoom ,Checkout checkOut){
        boolean isWrongKeyCard = false;
        try{
            for(int i=0;i<hotelRoom.length;i++){
                for(int y=0;y<hotelRoom[i].length;y++){
                    if(hotelRoom[i][y].getName() == null ){
                        continue;
                    }else if((hotelRoom[i][y].getName().equals(checkOut.getName()) && (!hotelRoom[i][y].getKeyCard().equals(checkOut.getKeyCard())))){
                        isWrongKeyCard = true;
                        break;
                    }
                }
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return isWrongKeyCard;
    }
    
}

