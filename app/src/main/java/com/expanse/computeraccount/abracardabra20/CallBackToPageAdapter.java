package com.expanse.computeraccount.abracardabra20;

import com.expanse.computeraccount.abracardabra20.dataBase.CarDBObject;

public interface CallBackToPageAdapter {
    void addCard(CarDBObject carDBObject,int view_id);
    void updateCard(CarDBObject carDBObject,int view_id);
    void addTab();
    void startAddCardDialog(long collectionId);
}
