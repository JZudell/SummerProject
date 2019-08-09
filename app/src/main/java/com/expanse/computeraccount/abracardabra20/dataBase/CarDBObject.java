package com.expanse.computeraccount.abracardabra20.dataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class CarDBObject {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public String name;

    public String set;

    public String imageUrl;

    //foil finish or normal finish
    public String subType;

    public int quantity;

    public long collectionId;

    public int tcgId;
}
