package com.expanse.computeraccount.abracardabra20.pojo;

import java.util.ArrayList;

public class CollectionListObject {

    long collectionId;
    String nameOfList;
    ArrayList<Card> cardList;


    public CollectionListObject(long collectionId, String nameOfList,ArrayList<Card> cardList){
        this.collectionId = collectionId;
        this.nameOfList = nameOfList;
        this.cardList = cardList;
    }

    public long getCollectionId(){
        return collectionId;
    }
    public String getNameOfList(){
        return nameOfList;
    }
    public ArrayList<Card> getCardList(){return cardList;}

    public void addCard(Card card){
        cardList.add(card);
    }
    public void deleteCard(int tcgId){
        for(Card card:cardList){
            if(card.id==tcgId){
                cardList.remove(card);
                break;
            }
        }
    }


}
