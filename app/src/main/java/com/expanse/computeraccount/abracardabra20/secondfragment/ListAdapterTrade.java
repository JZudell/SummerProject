package com.expanse.computeraccount.abracardabra20.secondfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.expanse.computeraccount.abracardabra20.CallBackToSecondFragment;
import com.expanse.computeraccount.abracardabra20.R;
import com.expanse.computeraccount.abracardabra20.pojo.Card;

import java.util.ArrayList;

public class ListAdapterTrade extends ArrayAdapter<Card> {

    private ArrayList<Card> listOfCards;
    private Context context;
    public int sideIndex;

    CallBackToSecondFragment mListener;
    public void setListener(CallBackToSecondFragment listener){
        mListener = listener;
    }

    public ListAdapterTrade(Context context, int resource, ArrayList<Card> listOfCards,int sideIndex){
        super(context,resource,listOfCards);
        this.listOfCards = listOfCards;
        this.context = context;
        this.sideIndex = sideIndex;
    }


    @NonNull
    @Override
    public View getView(int position, final View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.list_item_trade,null);
        }
//        String dataOfSingleCard = data.get(position);
//        String[] dataSplit = dataOfSingleCard.split("##");

        final Card card = listOfCards.get(position);


        //
        String finishType = card.getSubType();


        //--
        String name = card.getName();
        if (finishType.equals("Foil")){
            name = "Foil "+name;
        }
        String set = card.getSet();



        //
        String visualData = name+"\n"+set+"\n"+"Low: "+card.getLowPrice()+" Mrkt: "+card.getMarketPrice()+" Mid: "+card.getMidPrice();
        //
        final String image = card.getImageUrl();
        //
        String setReplace = set.replace(":","").replace(" ","-");

        //
        String nameReplace = name.replace(" ","-");


        //
        // final String urlToTcg = "https://shop.tcgplayer.com/magic/"+setReplace+"/"+nameReplace+"?pk=AbraCARDabrA";




        TextView textView =  v.findViewById(R.id.list_item_text);
        textView.setText(visualData);

        TextView totalOfCardTextView = v.findViewById(R.id.number_of_cards);
        totalOfCardTextView.setText(String.valueOf(card.getQuantity()));

        ImageButton imageButton = v.findViewById(R.id.plus_one_card);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.addCard(card,sideIndex);
            }
        });
        ImageButton imageButtonMinus = v.findViewById(R.id.minus_one_card);
        imageButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.subtractCard(card,sideIndex);
            }
        });


//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
////                ImageLoader.getInstance().init(config);
////                ImageLoader imageLoader = ImageLoader.getInstance();
////                final AlertDialog.Builder alertadd = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
////                LayoutInflater factory = LayoutInflater.from(context);
////                final View viewNew = factory.inflate(R.layout.card_image_layout, null);
////                ImageView viewer = viewNew.findViewById(R.id.dialog_imageview);
////                imageLoader.displayImage( image, viewer);
////                alertadd.setView(viewNew);
////                alertadd.setNeutralButton("Close", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dlg, int sumthin) {
////                    }
////                });
////                alertadd.show();
//
//                Log.v("CONTEXT aaz", String.valueOf(context));
//
//
//
//
//            }
//        });

        return v;
    }
}
