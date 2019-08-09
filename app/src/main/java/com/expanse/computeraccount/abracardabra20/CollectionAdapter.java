package com.expanse.computeraccount.abracardabra20;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.expanse.computeraccount.abracardabra20.pojo.Card;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CollectionAdapter extends ArrayAdapter<Card> implements CallBackToCollectionAdapter{

    private ArrayList<Card> listOfCards;
    private Context context;

    CallBackToPagerFragment mListener;

    public void setListener(CallBackToPagerFragment listener){
        mListener = listener;
    }

    public CollectionAdapter(Context context, int resource, ArrayList<Card> listOfCards){
        super(context,resource,listOfCards);
        this.listOfCards = listOfCards;
        this.context = context;
        String listAsString = "";
        for(int x=0;x<listOfCards.size();x++){
            if(x==listOfCards.size()-1){
                listAsString = listAsString+listOfCards.get(x).getId();
            }else{
                listAsString = listAsString+listOfCards.get(x).getId()+",";
            }

        }
        Log.v("COLLECTION LIST",listAsString);
        GetCollectionListTask getCollectionListTask = new GetCollectionListTask();

        getCollectionListTask.setListener(this);
        getCollectionListTask.execute(listAsString);
    }

    double totalDoubleLow = 0.0;
    double totalDoubleMrk = 0.0;
    double totalDoubleMid = 0.0;
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.list_item_collection,null);
        }
//        String dataOfSingleCard = data.get(position);
//        String[] dataSplit = dataOfSingleCard.split("##");

        Card card = listOfCards.get(position);


        //
        String finishType = card.getSubType();


        //--
        String name = card.getName();
        if (finishType.equals("Foil")){
            name = "Foil "+name;
        }
        String set = card.getSet();

        Double cardTotalLow = 0.0;
        Double cardTotalMrk = 0.0;
        Double cardTotalMid = 0.0;
        if(card.getLowPrice()==null||card.getMidPrice()==null||card.getMarketPrice()==null){

        }else{
             cardTotalLow = Double.valueOf(card.getLowPrice())*card.getQuantity();
             cardTotalMrk = Double.valueOf(card.getMarketPrice())*card.getQuantity();
             cardTotalMid = Double.valueOf(card.getMidPrice())*card.getQuantity();
        }



        //
        String visualData = card.quantity+"X: "+name+"\n"+set+"\n"+"Low: "+card.getLowPrice()+" Market: "+card.getMarketPrice()+" Mid: "+card.getMidPrice()+
                "\nTotals:"+cardTotalLow+", "+cardTotalMrk+", "+cardTotalMid;
        //
        final String image = card.getImageUrl();
        //
        String setReplace = set.replace(":","").replace(" ","-");

        //
        String nameReplace = name.replace(" ","-");


        //
        final String urlToTcg = "https://shop.tcgplayer.com/magic/"+setReplace+"/"+nameReplace+"?pk=AbraCARDabrA";




        TextView textView =  v.findViewById(R.id.collection_list_item);
        textView.setText(visualData);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
                ImageLoader.getInstance().init(config);
                ImageLoader imageLoader = ImageLoader.getInstance();
                final AlertDialog.Builder alertadd = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
                LayoutInflater factory = LayoutInflater.from(context);
                final View viewNew = factory.inflate(R.layout.card_image_layout, null);
                ImageView viewer = viewNew.findViewById(R.id.dialog_imageview);
                imageLoader.displayImage( image, viewer);
                alertadd.setView(viewNew);
                alertadd.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {
                    }
                });
                alertadd.show();
            }
        });
        return v;
    }

    @Override
    public void jsonResponseList(String stringReturn) {

        Log.v("string return",stringReturn);
        try {
            JSONObject response = new JSONObject(stringReturn);
            JSONArray results = response.getJSONArray("results");
            Log.v("results size", String.valueOf(results.length()));
            Double total = 0.0;
            for(int x = 0;x<listOfCards.size();x++){
                for(int y = 0;y<results.length();y++){

                    JSONObject card = results.getJSONObject(y);



                    Log.v("RECHED HEREERE","DDDDDDDDDDDDDDDDD");
                    if(card.getString("productId").equals(String.valueOf(listOfCards.get(x).getId()))&&card.getString("subTypeName").equals(listOfCards.get(x).getSubType())){

                        Log.v("RECHED HEREERE","KKKkkKKKKKKKKKKKKK");
                        listOfCards.get(x).setLowPrice(card.getString("lowPrice"));
                        listOfCards.get(x).setMarketPrice(card.getString("marketPrice"));
                        listOfCards.get(x).setMidPrice(card.getString("midPrice"));
                        total = total+ (Double.valueOf(listOfCards.get(x).getMarketPrice())*listOfCards.get(x).getQuantity());

                        notifyDataSetChanged();
                        break;


                    }
                }
            }
            mListener.sendBackTotal(total);







        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
