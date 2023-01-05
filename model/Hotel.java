package model;

public class Hotel {
    private Integer floor;
    private Integer roomPerFloor;

    public Hotel(Integer floor,Integer roomPerFloor){
        this.floor = floor;
        this.roomPerFloor = roomPerFloor;
    }

    public Integer getFloor(){
        return floor;
    }
    
    public Integer getRoomPerFloor(){
        return roomPerFloor;
    }

    public void setFloor(Integer floor){
        this.floor = floor;
    }
    
    public void setRoomPerFloor(Integer roomPerFloor){
        this.roomPerFloor = roomPerFloor;
    }
}
