package com.expanse.computeraccount.abracardabra20;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.expanse.computeraccount.abracardabra20.pojo.Card;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Card> {

    private ArrayList<Card> listOfCards;
    private Context context;

    public ListAdapter(Context context, int resource, ArrayList<Card> listOfCards){
        super(context,resource,listOfCards);
        this.listOfCards = listOfCards;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.list_item_view,null);
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



        //
        String visualData = name+"\n"+set+"\n"+"Low: "+card.getLowPrice()+" Market: "+card.getMarketPrice()+" Mid: "+card.getMidPrice();
        //
        final String image = card.getImageUrl();
        //
        String setReplace = set.replace(":","").replace(" ","-");

        //
        String nameReplace = name.replace(" ","-");
        String nameReplaceMinusFoil = nameReplace.replace("Foil-","");


        //
        final String urlToTcg = "https://shop.tcgplayer.com/magic/"+setReplace+"/"+nameReplaceMinusFoil+"?pk=AbraCARDabrA";




        TextView textView =  v.findViewById(R.id.list_item_text);
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
        ImageButton imageButton = v.findViewById(R.id.tcg_link);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToTcg));
                context.startActivity(browserIntent);
            }
        });
        return v;
    }
}
