import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.BookInfo;
import model.Checkout;
import model.Hotel;
import model.Room;
import service.HotelService;
import service.impl.HotelServiceImpl;
import util.Utility;

// Online Java Compiler
// Use this editor to write, compile and run your Java code online

//* f */
class Command{
    String name;
    List<String> params;
    Command(String name , List<String> params){
        this.name = name;
        this.params = params;
    }
}

class Main {

    public static void main(String[] args) throws IOException {

        final String fileName = "input.txt";

        //Service and Utility method
        HotelService hotelService = new HotelServiceImpl();

        // Initial variable
        List<Command> commands = getCommandsFromFileName(fileName);
        Room[][] hotelRoom = new Room[10][10];
        BookInfo bookInfo;
        Hotel hotel;
        Checkout checkout;
        List<Integer> usingKeyCard = new ArrayList<>();
        try{
            for(int i=0;i<commands.size();i++){
                switch(commands.get(i).name) {
                    case "create_hotel":
                        hotel = new Hotel(Utility.string2Integer(commands.get(i).params.get(0)) ,Utility.string2Integer(commands.get(i).params.get(1))  );
                        hotelRoom = hotelService.createHotel(hotel,hotelRoom);
                        break;
        
                    case "book":
                        bookInfo = new BookInfo(Utility.string2Integer(commands.get(i).params.get(0)), commands.get(i).params.get(1), Utility.string2Integer(commands.get(i).params.get(2)));
                        hotelService.book(bookInfo, hotelRoom,usingKeyCard);
                        break;
    
                    case "list_available_rooms":
                        hotelService.listAvailableRoom(hotelRoom);
                        break;
                    case "checkout":
                        checkout = new Checkout(commands.get(i).params.get(1), Utility.string2Integer(commands.get(i).params.get(0)));
                        hotelService.checkout(checkout, hotelRoom,usingKeyCard);
                        break;
    
                    case "list_guest":
                        hotelService.listGuest(hotelRoom);
                        break;
    
                    case "get_guest_in_room":
                        hotelService.getGuestInRoom(hotelRoom, Utility.string2Integer(commands.get(i).params.get(0)));
                        break;
    
                    case "list_guest_by_age":
                        hotelService.listGuestByAge(hotelRoom, commands.get(i).params.get(0),Utility.string2Integer(commands.get(i).params.get(1)));
                        break;
    
                    case "list_guest_by_floor":
                        hotelService.listGuestByFloor(hotelRoom, Utility.string2Integer(commands.get(i).params.get(0)));
                        break;
    
                    case "checkout_guest_by_floor":
                        hotelService.checkoutGuestByFloor(hotelRoom, Utility.string2Integer(commands.get(i).params.get(0)),usingKeyCard);
                        break;
    
                    case "book_by_floor":
                        bookInfo = new BookInfo(/*default value*/101, commands.get(i).params.get(1), Utility.string2Integer(commands.get(i).params.get(2)));
                        hotelService.bookByFloor(hotelRoom, Utility.string2Integer(commands.get(i).params.get(0)),bookInfo,usingKeyCard);
                        break;
                    default:
                        // nothing
                        System.out.println("not in service");
                  }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }


    /*
     * filename = "/folder/test.txt"
     */
    public static List<Command> getCommandsFromFileName(String fileName) throws IOException{
        List<Command> commands = new ArrayList<>();
        try{
            Path path = Paths.get(fileName);
            // text to List String
            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            int i=0;

            // seperate line to command/params with List<command>
            for(i=0;i<allLines.size();i++){
                List<String> params = new ArrayList<>(Arrays.asList(allLines.get(i).split(" ")));
                String name = params.get(0);
                params.remove(0);
                Command command = new Command(name, params);
                commands.add(command);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return commands;
    }
    
}