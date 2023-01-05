package model;

public class BookInfo {
    private Integer roomNo;
    private String customerName;
    private Integer customerAge;

    public BookInfo(Integer roomNo,String customerName,Integer customerAge){
        this.roomNo = roomNo;
        this.customerName = customerName;
        this.customerAge = customerAge;
    }

    public Integer getRoomNo(){
        return roomNo;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public Integer getCustomerAge(){
        return customerAge;
    }

    public void setRoomNo(Integer roomNo){
        this.roomNo = roomNo;
    }
    
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public void setCustomerAge(Integer customerAge){
        this.customerAge = customerAge;
    }
}
