package com.expanse.computeraccount.abracardabra20.thirdfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.expanse.computeraccount.abracardabra20.CallBackToPageAdapter;
import com.expanse.computeraccount.abracardabra20.CallBackToPagerFragment;
import com.expanse.computeraccount.abracardabra20.CollectionAdapter;
import com.expanse.computeraccount.abracardabra20.R;
import com.expanse.computeraccount.abracardabra20.pojo.Card;
import com.expanse.computeraccount.abracardabra20.pojo.CollectionListObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PagerFragment extends Fragment implements CallBackToPagerFragment {

    private static final String KEY_TITLE = "key_titile";
    private static final String KEY_PAGE_ID = "key_page_id";
    private String title;
    private TextView titleView;
    private int page_id;

    public long collectionId;

    CallBackToPageAdapter mListener;
    public void setListener(CallBackToPageAdapter listener){
        mListener = listener;
    }

    private static final int[] colors = new int[]{
            Color.CYAN,
            Color.WHITE,
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.GRAY,
            Color.YELLOW
    };

    public static PagerFragment newInstance(CollectionListObject collectionListObject, int page_id) {

        Bundle args = new Bundle();
        args.putLong("key_collection",collectionListObject.getCollectionId());
        args.putString(KEY_TITLE, collectionListObject.getNameOfList());
        args.putSerializable("args_list", (Serializable) collectionListObject.getCardList());
        args.putInt(KEY_PAGE_ID, page_id);
        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listOfCards = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.title = bundle.getString(KEY_TITLE);
            this.page_id = bundle.getInt(KEY_PAGE_ID);
            this.collectionId = bundle.getLong("key_collection");
            this.listOfCards =(ArrayList<Card>) bundle.getSerializable("args_list");
        }
    }

    ArrayList<Card> listOfCards;
    CollectionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page, container, false);
        this.titleView = (TextView) view.findViewById(R.id.txt_title);
        titleView.setText(title);

       // view.setBackgroundColor(colors[page_id % colors.length]);


        Log.v("TEST listOfCards size","============="+listOfCards.size());



        convertCardList(listOfCards);



        Log.v("TEST corrected size","============="+correctedList.size());





        ListView collectionListView = view.findViewById(R.id.collection_listview);

        adapter = new CollectionAdapter(getContext(), R.layout.list_item_view,correctedList);
        adapter.setListener(this);
        collectionListView.setAdapter(adapter);




        TextView addCardTextView = view.findViewById(R.id.add_card_collection);
        addCardTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // ((MainActivity)getActivity()).startQuery(String.valueOf(editText.getText()),0);

                //mListener.addTab();
               // mListener.addCard


                mListener.startAddCardDialog(collectionId);


                //showAddToCollectionDialog(page_id);



                Log.v("PRESSED","PRESSED");
            }
        });

        return view;
    }

    ArrayList<Card> correctedList;
    ArrayList<Integer> carIds = new ArrayList<>();
    Map<String, Card> hm = new HashMap<String, Card>();
    public ArrayList<Card> convertCardList(ArrayList<Card> fullList){

        Log.v("TEST full size","============="+fullList.size());
        for (int x = 0;x<fullList.size();x++){


            if(hm.size()>0){
                Log.v("TEST hm has >0","========");


                String tempId = String.valueOf(fullList.get(x).getId());
                if(fullList.get(x).getSubType().equals("Foil")){
                    tempId = tempId+"F";
                }

                if(hm.containsKey(tempId)){


                    int quantityPrior = hm.get(tempId).quantity;
                    Card tempCard = hm.get(tempId);
                    tempCard.setQuantity(quantityPrior+1);

                    hm.put(tempId,tempCard);
//                    if(tempId.contains("F")){
//                        hm.put(fullList.get(x).getId()+"F",tempCard);
//                    }else{
//                        hm.put(String.valueOf(fullList.get(x).getId()),tempCard);
//                    }

                }else{
                    Card tempCard = fullList.get(x);
                    tempCard.setQuantity(1);
                    if(tempCard.getSubType().equals("Foil")){
                        hm.put((fullList.get(x).getId())+"F",tempCard);
                    }else{
                        hm.put(String.valueOf(fullList.get(x).getId()),tempCard);
                    }


                }
            }else{
                Log.v("TEST hm has none","=============");
                Card tempCard = fullList.get(x);
                tempCard.setQuantity(1);
                if(fullList.get(x).getSubType().equals("Foil")){
                    hm.put(fullList.get(x).getId()+"F",tempCard);
                }else{
                    hm.put(String.valueOf(fullList.get(x).getId()),tempCard);
                }

            }
        }

        Collection<Card> values = hm.values();
        Log.v("TEST hm size","============="+hm.size());
        correctedList = new ArrayList<>(values);


        return correctedList;
    }


    @Override
    public void sendBackTotal(Double total) {

        String totalString = title+" total: "+total;
        titleView.setText(totalString);
    }
}
