package com.expanse.computeraccount.abracardabra20.dataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.expanse.computeraccount.abracardabra20.pojo.Card;

import java.util.ArrayList;

@Entity
@TypeConverters(ArrayConverter.class)
public class ListDBObject {

    @PrimaryKey
    @NonNull
    public long id;

    public String name;

    public ArrayList<Card> cards;
}
