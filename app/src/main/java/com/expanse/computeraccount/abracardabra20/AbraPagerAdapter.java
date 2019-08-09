package com.expanse.computeraccount.abracardabra20;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.expanse.computeraccount.abracardabra20.secondfragment.SecondFragment;
import com.expanse.computeraccount.abracardabra20.thirdfragment.ThirdFragment;

public class AbraPagerAdapter extends FragmentPagerAdapter {

    public AbraPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "Price Lookup";
            case 1:
                return "Trade!";
            case 2:
                return "Collections";
            default:
                return null;

        }
    }
}
