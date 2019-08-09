package com.expanse.computeraccount.abracardabra20;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.expanse.computeraccount.abracardabra20.pojo.Card;
import com.expanse.computeraccount.abracardabra20.secondfragment.SecondFragment;
import com.expanse.computeraccount.abracardabra20.thirdfragment.ThirdFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBackToMainListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter =
                new AbraPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    ArrayList<Card> cardList = new ArrayList<>();
    @Override
    public void callBackPrintingsAndPrice(String s,int whichVersionToCall,long collectionIndex) throws JSONException {
        hideKeyboard(this);

        cardList.clear();
        Log.v("JSON RETURN",s);
        JSONArray cardArray = new JSONArray(s);
        for (int x = 0;x < cardArray.length();x++){
            JSONObject cardData = cardArray.getJSONObject(x);
            JSONObject priceArray = cardData.getJSONObject("price");
            Card card = new Card(cardData.getInt("id"),cardData.getString("name"),
                    cardData.getString("set"),
                    priceArray.getString("low"),
                    priceArray.getString("market"),
                    priceArray.getString("mid"),
                    cardData.getString("subTypeName"),
                    cardData.getString("image"));
            cardList.add(card);
        }
        updateFragmentIndex(whichVersionToCall,collectionIndex);
    }
    @Override
    public void startQuery(String s,int whichVersionToCall,long collectionIndex) {

        GetPrintingsAndPriceTask getPrintingsAndPriceTask = new GetPrintingsAndPriceTask(whichVersionToCall, collectionIndex);
        getPrintingsAndPriceTask.setListener(this);
        getPrintingsAndPriceTask.execute(s);


    }

    public void updateFragmentIndex(int whichVersionToCall,long collectionIndex){
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        if (allFragments != null) {
            int count = 1;
            for (Fragment fragment : allFragments) {

                Log.v("fragment type "+count,fragment.getClass().toString());

                if(fragment.getClass().toString().contains("FirstFragment")){

                    if(whichVersionToCall==0){
                        FirstFragment firstFragment = (FirstFragment) fragment;
                        firstFragment.setListView(cardList);
                    }

                }else if(fragment.getClass().toString().contains("SecondFragment")){
                    if(whichVersionToCall==1){
                        SecondFragment secondFragment = (SecondFragment) fragment;
                        secondFragment.updateDialogWithList(cardList);
                    }

                }else if(fragment.getClass().toString().contains("ThirdFragment")){

                    if(whichVersionToCall==2){
                        ThirdFragment thirdFragment = (ThirdFragment) fragment;
                        thirdFragment.updateCollectionAddDialog(cardList);
                    }

                }

                count++;
            }
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
