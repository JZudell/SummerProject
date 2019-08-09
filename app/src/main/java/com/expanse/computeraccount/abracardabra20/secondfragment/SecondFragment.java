package com.expanse.computeraccount.abracardabra20.secondfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.expanse.computeraccount.abracardabra20.CallBackToSecondFragment;
import com.expanse.computeraccount.abracardabra20.R;
import com.expanse.computeraccount.abracardabra20.pojo.Card;

import java.text.NumberFormat;
import java.util.ArrayList;

public class SecondFragment extends Fragment implements CallBackToSecondFragment {

    View view;
    TextView totalLeft;
    TextView totalRight;
    TextView addLeftTextView;
    TextView addRightTextView;
    ListView tradeListViewLeft;
    ListView tradeListViewRight;

    ArrayList<Card> leftList;
    ArrayList<Card> rightList;

    ListAdapterTrade adapterLeft;
    ListAdapterTrade adapterRight;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_second,container,false);

        addLeftTextView = view.findViewById(R.id.add_card_trade_left);
        addRightTextView = view.findViewById(R.id.add_card_trade_right);
        tradeListViewLeft = view.findViewById(R.id.trade_list_left);
        tradeListViewRight = view.findViewById(R.id.trade_list_right);
        leftList = new ArrayList<>();
        rightList = new ArrayList<>();
        totalLeft = view.findViewById(R.id.total_left);
        totalRight = view.findViewById(R.id.total_right);

        calculateTotalLeft();
        calculateTotalRight();

        adapterLeft = new ListAdapterTrade(
                getActivity(),
                R.layout.list_item_trade,
                leftList,0);
        adapterLeft.setListener(this);
        tradeListViewLeft.setAdapter(adapterLeft);

        adapterRight = new ListAdapterTrade(
                getActivity(),
                R.layout.list_item_trade,
                rightList,1);
        adapterRight.setListener(this);
        tradeListViewRight.setAdapter(adapterRight);

        addLeftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTradeDialog(0);
            }
        });
        addRightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTradeDialog(1);
            }
        });

        return view;
    }

    public TradeListDialog cdd;
    public void showTradeDialog(int sideIndex){

        cdd=new TradeListDialog(getActivity(),sideIndex);
        cdd.setListener(this);
        cdd.show();
    }
    public void updateDialogWithList(ArrayList<Card> cardList) {
        cdd.updateCardListInDialog(cardList);
    }

    public void updateLists(Card card,int sideIndex) {
        if(sideIndex==0){
            leftList.add(card);
            adapterLeft.notifyDataSetChanged();
            calculateTotalLeft();
        }else if(sideIndex==1){
            rightList.add(card);
            adapterRight.notifyDataSetChanged();
            calculateTotalRight();
        }
    }

    public void calculateTotalLeft(){
        double lowTotal = 0.00;
        double midTotal = 0.00;
        double mrktTotal = 0.00;
        for (int x = 0;x<leftList.size();x++){

            Card card = leftList.get(x);

            Double lowDbl = Double.valueOf(card.getLowPrice().replace("$",""));
            Double mrktDble = Double.valueOf(card.getMarketPrice().replace("$",""));
            Double midDble = Double.valueOf(card.getMidPrice().replace("$",""));

            lowTotal = lowTotal+ (lowDbl*card.getQuantity());
            midTotal = midTotal+(midDble*card.getQuantity());
            mrktTotal = mrktTotal+(mrktDble*card.getQuantity());
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        totalLeft.setText("Mrkt:"+formatter.format(mrktTotal)+"\nLow:"+formatter.format(lowTotal)+"\nMid:"+formatter.format(midTotal));
    }
    public void calculateTotalRight(){
        double lowTotal = 0.00;
        double midTotal = 0.00;
        double mrktTotal = 0.00;
        for (int x = 0;x<rightList.size();x++){

            Card card = rightList.get(x);

            Double lowDbl = Double.valueOf(card.getLowPrice().replace("$",""));
            Double mrktDble = Double.valueOf(card.getMarketPrice().replace("$",""));
            Double midDble = Double.valueOf(card.getMidPrice().replace("$",""));

            lowTotal = lowTotal+ (lowDbl*card.getQuantity());
            midTotal = midTotal+(midDble*card.getQuantity());
            mrktTotal = mrktTotal+(mrktDble*card.getQuantity());
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        totalRight.setText("Mrkt:"+formatter.format(mrktTotal)+"\nLow:"+formatter.format(lowTotal)+"\nMid:"+formatter.format(midTotal));
    }

    @Override
    public void addCard(Card card,int sideIndex) {
        int id = card.getId();
        Card cardChosen;
        if(sideIndex==0){

            boolean found = false;
            int count = 0;
            while(!found){
                cardChosen = leftList.get(count);
                if(id==cardChosen.getId()){
                    leftList.set(count,new Card(card));
                    found = true;
                }
                count++;
            }
            if(!found){
                leftList.add(card);
            }

            adapterLeft.notifyDataSetChanged();

            calculateTotalLeft();

        }else if(sideIndex==1){

            boolean found = false;
            int count = 0;
            while(!found){
                cardChosen = rightList.get(count);
                if(id==cardChosen.getId()){
                    rightList.set(count,new Card(card));
                    found = true;
                }
                count++;
            }
            if(!found){
                rightList.add(card);
            }
            adapterRight.notifyDataSetChanged();
            calculateTotalRight();
        }
    }

    @Override
    public void subtractCard(Card card, int sideIndex) {
        int id = card.getId();
        Card cardChosen;
        if(sideIndex==0){

            boolean found = false;
            int count = 0;
            while(!found||count<leftList.size()){
                cardChosen = leftList.get(count);
                if(id==cardChosen.getId()){
                    if(cardChosen.getQuantity()==1){
                        leftList.remove(count);
                    }else{
                        leftList.set(count,new Card(card,true));
                    }

                    found = true;
                }
                count++;
            }
            adapterLeft.notifyDataSetChanged();
            calculateTotalLeft();

        }else if(sideIndex==1){

            boolean found = false;
            int count = 0;
            while(!found||count<rightList.size()){
                cardChosen = rightList.get(count);
                if(id==cardChosen.getId()){
                    if(cardChosen.getQuantity()==1){
                        rightList.remove(count);
                    }else{
                        rightList.set(count,new Card(card,true));
                    }

                    found = true;
                }
                count++;
            }
            adapterRight.notifyDataSetChanged();
            calculateTotalRight();
        }
    }
}
