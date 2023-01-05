package model;

public class Checkout {
    private String name;
    private Integer keyCard;

    public Checkout(String name,Integer keyCard){
        this.name = name;
        this.keyCard = keyCard;
    }

    public String getName(){
        return name;
    }

    public Integer getKeyCard(){
        return keyCard;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setKeyCard(Integer keyCard){
        this.keyCard = keyCard;
    }

}
