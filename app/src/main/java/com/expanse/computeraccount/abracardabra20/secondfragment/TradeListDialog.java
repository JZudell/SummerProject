package com.expanse.computeraccount.abracardabra20.secondfragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.expanse.computeraccount.abracardabra20.CallBackToDialog;
import com.expanse.computeraccount.abracardabra20.CallBackToSecondFragment;
import com.expanse.computeraccount.abracardabra20.MainActivity;
import com.expanse.computeraccount.abracardabra20.R;
import com.expanse.computeraccount.abracardabra20.pojo.Card;

import java.util.ArrayList;

public class TradeListDialog extends Dialog implements
        android.view.View.OnClickListener, CallBackToDialog {

    CallBackToSecondFragment mListener;
    public void setListener(CallBackToSecondFragment listener){
        mListener = listener;
    }

    public Activity c;
    public Dialog d;
    public int sideIndex;
    public ArrayList<Card> cardList;
    public ListView listView;

    ListAdapterDialog adapter;

    public TradeListDialog(Activity a){
        super(a);
        this.c = a;
        this.sideIndex = -1;
    }

    public TradeListDialog(Activity a,int sideIndex) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.sideIndex = sideIndex;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_first);

        listView = findViewById(R.id.list_view_id);
        ImageButton searchButton = findViewById(R.id.search_button);
        final EditText editText = findViewById(R.id.edit_query);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().length()>0){
                    ((MainActivity)c).startQuery(String.valueOf(editText.getText()),1,-1);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    public void updateCardListInDialog(ArrayList<Card> cardList){
        this.cardList = cardList;

        adapter = new ListAdapterDialog(
                c,
                R.layout.list_item_view,
                cardList,
                sideIndex);
        adapter.setListener(this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateLists(Card card, int sideIndex) {

        mListener.updateLists(card,sideIndex);
        dismiss();

    }
}
