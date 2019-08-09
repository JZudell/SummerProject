package com.expanse.computeraccount.abracardabra20.thirdfragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.expanse.computeraccount.abracardabra20.CallBackToPageAdapter;
import com.expanse.computeraccount.abracardabra20.CallBackToThirdFragment;
import com.expanse.computeraccount.abracardabra20.dataBase.CarDBObject;
import com.expanse.computeraccount.abracardabra20.pojo.CollectionListObject;

import java.util.ArrayList;

public class PageAdapter extends FragmentStatePagerAdapter implements CallBackToPageAdapter {

    ArrayList<CollectionListObject> data;

    public PagerFragment pagerFragment;


    public PageAdapter(FragmentManager fm, ArrayList<CollectionListObject> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        pagerFragment = PagerFragment.newInstance(data.get(position), position);
        pagerFragment.setListener(this);




        return pagerFragment;
    }

//    public void updateAddList(ArrayList<Card> cardList){
//        pagerFragment.updateCollectionAddDialog(cardList);
//    }
    @Override
    public int getCount() {
        Log.v("GET COUNT", String.valueOf(data.size()));
        return data.size();
    }

    @Override
    public void addCard(CarDBObject carDBObject, int view_id) {

    }

    @Override
    public void updateCard(CarDBObject carDBObject, int view_id) {

    }


    CallBackToThirdFragment mListener;
    public void setListener(CallBackToThirdFragment listener){
        mListener = listener;
    }
    @Override
    public void addTab() {

        mListener.addTab();
    }

    @Override
    public void startAddCardDialog(long collectionId) {
        mListener.startCollectionDialog(collectionId);
    }
}