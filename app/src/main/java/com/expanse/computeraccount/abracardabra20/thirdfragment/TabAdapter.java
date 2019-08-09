package com.expanse.computeraccount.abracardabra20.thirdfragment;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.expanse.computeraccount.abracardabra20.CallBackToThirdFragment;
import com.expanse.computeraccount.abracardabra20.R;
import com.expanse.computeraccount.abracardabra20.pojo.CollectionListObject;

import java.util.ArrayList;

public class TabAdapter extends BaseAdapter implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    private final ArrayList<CollectionListObject> data;
    private final ListView listView;
    private OnItemClickListener listener;

    private int currentSelected = 0;

    CallBackToThirdFragment mListener;
    public void setListener(CallBackToThirdFragment listener){
        mListener = listener;
    }

    public TabAdapter(ArrayList<CollectionListObject> data, ListView listView) {
        this(data, listView, null);
    }


    public TabAdapter(ArrayList<CollectionListObject> data, ListView listView, OnItemClickListener listener){
        this.data = data;
        Log.v("DATALENGTH aaz", String.valueOf(this.data.size()));
        this.listView = listView;
        this.listener = listener;

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i).getNameOfList();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Currently not using viewHolder pattern cause there aren't too many tabs in the demo project

        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tab, viewGroup, false);
        }

        TextView tabTitle = (TextView)view.findViewById(R.id.txt_tab_title);
        tabTitle.setText((String)getItem(i));

        if(i == currentSelected){
            setTextViewToSelected(tabTitle);
        }else{
            setTextViewToUnSelected(tabTitle);
        }

        return view;
    }


    /**
     * Return item view at the given position or null if position is not visible.
     */
    public View getViewByPosition(int pos) {
        if(listView == null){
            return  null;
        }
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return null;
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }


    private void setTextViewToSelected(TextView tabTitle){
        tabTitle.setTextColor(Color.CYAN);
    }

    private void setTextViewToUnSelected(TextView tabTitle){
        tabTitle.setTextColor(Color.GRAY);
    }


    private void select(int position){
        if(currentSelected >= 0){
            deselect(currentSelected);
        }

        View targetView = getViewByPosition(position);
        if(targetView != null) {
            setTextViewToSelected((TextView)(targetView.findViewById(R.id.txt_tab_title)));
        }

        if(listener != null){
            listener.selectItem(position);
        }

        currentSelected = position;

    }

    private void deselect(int position) {
        if(getViewByPosition(position) != null){
            View targetView = getViewByPosition(position);
            if(targetView != null) {
                setTextViewToUnSelected((TextView)(targetView.findViewById(R.id.txt_tab_title)));
            }
        }

        currentSelected = -1;
    }


    // OnClick Events

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        select(i);
    }



    public void OnItemClickListener(TabAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    public void setCurrentSelected(int i) {
        select(i);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


        mListener.deleteTab(position,data.get(position).getCollectionId());

        return true;
    }

    public interface OnItemClickListener{
        void selectItem(int position);
    }



}
