package com.expanse.computeraccount.abracardabra20.pojo;

import java.io.Serializable;

public class Card implements Serializable {
    int id;
    String name;
    String set;
    String lowPrice;
    String marketPrice;
    String midPrice;
    String subType;
    String imageUrl;
    public int quantity = 1;

    public Card(Card card){
        this.id = card.getId();
        this.name = card.getName();
        this.set = card.getSet();
        this.lowPrice = card.getLowPrice();
        this.midPrice = card.getMidPrice();
        this.marketPrice = card.getMarketPrice();
        this.subType = card.getSubType();
        this.imageUrl = card.getImageUrl();
        this.quantity = card.getQuantity()+1;
    }
    public Card(Card card,boolean subtraction){
        this.id = card.getId();
        this.name = card.getName();
        this.set = card.getSet();
        this.lowPrice = card.getLowPrice();
        this.midPrice = card.getMidPrice();
        this.marketPrice = card.getMarketPrice();
        this.subType = card.getSubType();
        this.imageUrl = card.getImageUrl();
        if(subtraction==true){
            this.quantity = card.getQuantity()-1;
        }
    }
    public Card(int id, String name, String set,String subType,String imageUrl){
        this.id = id;
        this.name = name;
        this.set = set;
        this.lowPrice = lowPrice;
        this.marketPrice = marketPrice;
        this.midPrice = midPrice;
        this.subType = subType;
        this.imageUrl = imageUrl;
        quantity = 1;
    }

    public Card(int id, String name, String set,String lowPrice,String marketPrice,String midPrice,String subType,String imageUrl){
        this.id = id;
        this.name = name;
        this.set = set;
        this.lowPrice = lowPrice;
        this.marketPrice = marketPrice;
        this.midPrice = midPrice;
        this.subType = subType;
        this.imageUrl = imageUrl;
        quantity = 1;
    }
    public Card(int id, String name, String set,String lowPrice,String marketPrice,String midPrice,String subType,String imageUrl,int quantity){
        this.id = id;
        this.name = name;
        this.set = set;
        this.lowPrice = lowPrice;
        this.marketPrice = marketPrice;
        this.midPrice = midPrice;
        this.subType = subType;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }
    public int getId(){ return id;}
    public String getName(){
        return name;
    }
    public String getSet(){
        return set;
    }
    public String getLowPrice(){
        return lowPrice;
    }
    public String getMarketPrice(){
        return marketPrice;
    }
    public String getMidPrice(){
        return midPrice;
    }
    public String getSubType(){
        return subType;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setLowPrice(String lowPrice){
        this.lowPrice = lowPrice;
    }
    public void setMarketPrice(String marketPrice){
        this.marketPrice = marketPrice;
    }
    public void setMidPrice(String midPrice){
        this.midPrice = midPrice;
    }


}
