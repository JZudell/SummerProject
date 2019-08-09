package com.expanse.computeraccount.abracardabra20;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.expanse.computeraccount.abracardabra20.pojo.Card;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    View view;
    ListView listView;
    ArrayList<String> simpleCardList;
    ArrayList<Card> listOfCards;
    ListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_first,container,false);

        ImageButton searchButton = view.findViewById(R.id.search_button);

        listOfCards = new ArrayList<>();

        final EditText editText = view.findViewById(R.id.edit_query);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).startQuery(String.valueOf(editText.getText()),0,-1);
            }
        });

        simpleCardList = new ArrayList<>();
        simpleCardList.add("STARTING");
        listView = view.findViewById(R.id.list_view_id);

       // listOfCards.add(new Card("testname","testSet","$1.00","$2.00","$3.00","Foil","www.google.com"));
        adapter = new ListAdapter(
                getActivity(),
                R.layout.list_item_price,
                listOfCards);
        listView.setAdapter(adapter);

        return view;

    }

    public void setListView(ArrayList<Card> cardList){
        listOfCards.clear();
        for(Card card:cardList){
            listOfCards.add(card);
        }
        adapter.notifyDataSetChanged();

    }





}
