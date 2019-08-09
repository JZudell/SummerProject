package com.expanse.computeraccount.abracardabra20.thirdfragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.expanse.computeraccount.abracardabra20.CallBackToThirdFragment;
import com.expanse.computeraccount.abracardabra20.CollectionListDialog;
import com.expanse.computeraccount.abracardabra20.R;
import com.expanse.computeraccount.abracardabra20.dataBase.AppDatabase;
import com.expanse.computeraccount.abracardabra20.dataBase.CarDBObject;
import com.expanse.computeraccount.abracardabra20.dataBase.CollectionViewModel;
import com.expanse.computeraccount.abracardabra20.dataBase.DatabaseInitializer;
import com.expanse.computeraccount.abracardabra20.dataBase.ListDBObject;
import com.expanse.computeraccount.abracardabra20.pojo.Card;
import com.expanse.computeraccount.abracardabra20.pojo.CollectionListObject;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment implements TabAdapter.OnItemClickListener, ViewPager.OnPageChangeListener, CallBackToThirdFragment {

    private AppDatabase mDb;
    private CollectionViewModel mViewModel;
    ArrayList<CarDBObject> listOfTotalCardsInCollection;

    View view;
    private ArrayList<CollectionListObject> data;







    ViewPager viewPager;
    ListView tabs;
    private TabAdapter tabAdapter;
    TextView addCollectionList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_third,container,false);
        viewPager = (ViewPager) view.findViewById(R.id.vertical_viewPager);
        tabs = (ListView) view.findViewById(R.id.lv_tabs);
        addCollectionList = view.findViewById(R.id.add_collection_list);
        data = new ArrayList<>();

        listOfTotalCardsInCollection = new ArrayList<>();
        mDb = AppDatabase.getInMemoryDatabase(getActivity());
        mViewModel = ViewModelProviders.of(this).get(CollectionViewModel.class);

//        CarDBObject replacementCarAddOne = new CarDBObject();
//        replacementCarAddOne.id = 1002;
//        replacementCarAddOne.name = "JackJohnson";
//        replacementCarAddOne.set = "Masters 25";
//        replacementCarAddOne.imageUrl = "www.msn.com";
//        replacementCarAddOne.subType = "Normal";
//        replacementCarAddOne.quantity = 1;
//        addCardToDb(replacementCarAddOne);


        addCollectionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTab();
            }
        });



       // mDb.carModel().insertCarDBObject(replacementCarAddOne);
            //    ( 1001, "Snapcaster Mage", "Innistrad", "www.google.com","FOil",1);
       // populateDb();

        //fetchData();

        subscribeUiCards();


        initData();
        initView();

        return view;

    }
    private void initData() {








        //data.add(new CollectionListObject(System.currentTimeMillis(),"Collection 1"));
    }

    @Override
    public void addCard(CarDBObject carDBObject, int view_id) {

    }

    @Override
    public void updateCard(CarDBObject carDBObject, int view_id) {

    }

    int dataSize;
    @Override
    public void addTab(){
        //int sizeTabs = data.size();
        String answer = "Collection "+(data.size()+1);
        ListDBObject listDBObject = new ListDBObject();
        listDBObject.id = System.currentTimeMillis();
        listDBObject.name = answer;
        listDBObject.cards = new ArrayList<>();



        addListToDb(listDBObject);

       // long collectionId = System.currentTimeMillis();
      //  data.add(new CollectionListObject(collectionId,answer));
        initView();
//        tabAdapter.notifyDataSetChanged();
//        viewPageAdapter.notifyDataSetChanged();

    }

    @Override
    public void deleteTab(int position,long collectionId) {

       // data.clear();
        removeListFromDb(collectionId);
      //  data.remove(position);
      //  initView();
//        tabAdapter.notifyDataSetChanged();
//        viewPageAdapter.notifyDataSetChanged();
    }

    @Override
    public void startCollectionDialog(long collectionId) {

        showAddToCollectionDialog(collectionId);

    }

    @Override
    public void addCardToCollection(Card card, long collectionIndex) {

        //selecting a card from dialog list triggers this method. Implement caching card with reference to collectionIndex.
        //implement UI (collection fragments) being populated from the db change listener.

        //Needs a way to cataloge lists of collections, becaus deleting restarts the name count.

        CarDBObject newCard = new CarDBObject();
        newCard.name = card.getName();
        newCard.set = card.getSet();
        newCard.subType = card.getSubType();
        newCard.imageUrl = card.getImageUrl();
        newCard.collectionId = collectionIndex;
        newCard.tcgId = card.getId();
        addCardToDb(newCard);


        Card newCard2 = new Card(card.getId(), card.getName(), card.getSet(),card.getSubType(),card.getImageUrl());

       // CollectionListObject newList = new CollectionListObject();

        for (CollectionListObject listObject:data){
            if(listObject.getCollectionId()==collectionIndex){

              //  newList = listObject;
                listObject.addCard(newCard2);

                ListDBObject listDBObject = new ListDBObject();

                listDBObject.name = listObject.getNameOfList();
                listDBObject.id = listObject.getCollectionId();
                listDBObject.cards = listObject.getCardList();

                addListToDb(listDBObject);

            }
        }
    }

    public CollectionListDialog cdd;
    public void showAddToCollectionDialog(long collectionId){

        cdd=new CollectionListDialog(getActivity(),collectionId);
        cdd.setListener(this);
        cdd.show();
    }

    public void updateCollectionAddDialog(ArrayList<Card> cardList){

        cdd.updateCardListInDialog(cardList);
    }

    PageAdapter viewPageAdapter;
    private void initView() {
        if (tabs != null) {
            // landscape mode
            Log.v("ACCESSED HERE","aaz ------------------");
            this.tabAdapter = new TabAdapter(data, tabs, this);
            tabAdapter.setListener(this);
            tabs.setAdapter(tabAdapter);
            tabs.setDivider(null);
        }
        viewPageAdapter = new PageAdapter(getChildFragmentManager(), data);
        viewPageAdapter.setListener(this);
        viewPager.setAdapter(viewPageAdapter);
        viewPager.addOnPageChangeListener(this);
        Log.v("ACCESSED HERE 2","aaz ------------------");




    }

    // Implements TabAdapter.OnItemClickListener
    @Override
    public void selectItem(int position) {

        viewPager.setCurrentItem(position, true);
    }


    // Implements ViewPager.OnPageChangeListener
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(tabAdapter != null){
            tabAdapter.setCurrentSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
    public void addCardToDb(CarDBObject carDBObject){
        DatabaseInitializer.populateAsyncAddOneCard(mDb,carDBObject);
    }
    public void addListToDb(ListDBObject listDBObject){
        DatabaseInitializer.populateAsyncAddOneList(mDb,listDBObject);
    }
    public void removeListFromDb(long collectionId){
        DatabaseInitializer.removeAsyncOneList(mDb,collectionId);
    }

    public void updateCardToDb(CarDBObject carDBObject){
        DatabaseInitializer.populateAsyncUpdateOneCard(mDb,carDBObject);
    }

    private void subscribeUiCards() {
        mViewModel.cardList.observe(this, new Observer<List<CarDBObject>>() {
            @Override
            public void onChanged(@NonNull final List<CarDBObject> cards) {
                listOfTotalCardsInCollection.clear();
                listOfTotalCardsInCollection.addAll(cards);

                Log.v("TABLE LENGTH", String.valueOf(cards.size()));
                for(CarDBObject carDBObject:cards){
                    Log.v("OBJECT SUBSCRIBE TEST",carDBObject.id+" "+carDBObject.name+" :X "+carDBObject.quantity);
                }



//                ArrayList<Long> listOfCollectionIds = new ArrayList<>();
//                for(CarDBObject carDBObject:listOfTotalCardsInCollection){
//                    if(!listOfCollectionIds.contains(carDBObject.collectionId)){
//                        listOfCollectionIds.add(carDBObject.collectionId);
//                    }
//                }
//                 c




                // do, updateCardLists for pageFragment. implement CallBackToPageFragment and update every fragment with lists, either
                //by haveing a function in each fragment search for their collectionId






            }
        });
        mViewModel.listList.observe(this, new Observer<List<ListDBObject>>() {
            @Override
            public void onChanged(@Nullable List<ListDBObject> listDBObjects) {




                data.clear();

                for(ListDBObject listDBObject:listDBObjects){
                    data.add(new CollectionListObject(listDBObject.id,listDBObject.name,listDBObject.cards));

                    if(listDBObject.cards!=null){
                        Log.v("cards test","====================== "+listDBObject.cards.size()+"==="+listDBObject.cards );

                    }else{
                        Log.v("cards test NULL","========NULLNULL============="+listDBObject.cards );

                    }
                }
                Log.v("LISTLIST CHANGED", String.valueOf(listDBObjects.size()));
                initView();
            }
        });
    }
}
