package com.expanse.computeraccount.abracardabra20;

import com.expanse.computeraccount.abracardabra20.dataBase.CarDBObject;
import com.expanse.computeraccount.abracardabra20.pojo.Card;

public interface CallBackToThirdFragment {
    void addCard(CarDBObject carDBObject,int view_id);
    void updateCard(CarDBObject carDBObject,int view_id);
    void addTab();
    void deleteTab(int position,long collectionId);
    void startCollectionDialog(long collectionId);

    void addCardToCollection(Card card, long collectionIndex);
}
