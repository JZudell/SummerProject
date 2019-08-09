package com.expanse.computeraccount.abracardabra20;

import org.json.JSONException;

public interface CallBackToMainListener {
    void callBackPrintingsAndPrice(String s,int whichVersionToCall,long collectionIndex) throws JSONException;
    void startQuery(String S,int whichVersionToCall,long collectionIndex);
}
