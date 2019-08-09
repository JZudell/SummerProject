package com.expanse.computeraccount.abracardabra20;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.expanse.computeraccount.abracardabra20.pojo.Card;

import java.util.ArrayList;

public class CollectionListDialog extends Dialog implements
        android.view.View.OnClickListener ,CallBackToCollectionDialog{

    CallBackToThirdFragment mListener;

    public void setListener(CallBackToThirdFragment listener) {
        mListener = listener;
    }

    public Activity c;
    public Dialog d;
    public long collectionIndex;
    public ArrayList<Card> cardList;
    public ListView listView;

    ListAdapterDialogCollection adapter;

    public CollectionListDialog(Activity a) {
        super(a);
        this.c = a;
    }

    public CollectionListDialog(Activity a, long collectionIndex) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.collectionIndex = collectionIndex;
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
                if (editText.getText().length() > 0) {
                    ((MainActivity) c).startQuery(String.valueOf(editText.getText()), 2, collectionIndex);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    public void updateCardListInDialog(ArrayList<Card> cardList) {
        this.cardList = cardList;

        ListAdapterDialogCollection adapter = new ListAdapterDialogCollection(
                c,
                R.layout.list_item_view,
                cardList,
                collectionIndex);
        adapter.setListener(this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addCard(Card card, long collectionIndex) {

        mListener.addCardToCollection(card,collectionIndex);
    }
}
