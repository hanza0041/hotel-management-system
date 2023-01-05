package model;

public class Room {
    private String name;
    private Integer age;
    private Integer keyCard;
    private Integer roomNo;
    private boolean isCheckOut;
    private boolean isCheckIn;

    public Room(String name,Integer age,Integer keyCard,Integer roomNo){
        this.name = name;
        this.age = age;
        this.keyCard = keyCard;
        this.roomNo = roomNo;
        this.isCheckOut = false;
        this.isCheckIn = false;
    }

    public String getName(){
        return name;
    }
    
    public Integer getAge(){
        return age;
    }

    public Integer getKeyCard(){
        return keyCard;
    }

    public Integer getRoomNo(){
        return roomNo;
    }

    public boolean getIsCheckOut(){
        return isCheckOut;
    }

    public boolean getIsCheckIn(){
        return isCheckIn;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void setAge(Integer age){
        this.age = age;
    }

    public void setKeyCard(Integer keyCard){
        this.keyCard = keyCard;
    }

    public void setRoomNo(Integer roomNo){
        this.roomNo = roomNo;
    }
    
    public void setIsCheckOut(boolean isCheckOut){
        this.isCheckOut = isCheckOut;
    }

    public void setIsCheckIn(boolean isCheckIn){
        this.isCheckIn = isCheckIn;
    }
}
