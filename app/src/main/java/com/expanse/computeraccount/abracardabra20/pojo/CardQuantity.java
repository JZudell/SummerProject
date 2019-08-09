package com.expanse.computeraccount.abracardabra20.pojo;

public class CardQuantity {
    Card card;


    public CardQuantity(Card card,int quantity) {
        this.card = card;
        this.card.setQuantity(quantity);
    }
    public void setQuantity(int quantity){
        this.card.setQuantity(quantity);
    }
    public int getQuantity(){
        return this.card.quantity;
    }
    public Card getCard(){
        return card;
    }
}
