package com.expanse.computeraccount.abracardabra20.dataBase;

import android.arch.persistence.room.TypeConverter;

import com.expanse.computeraccount.abracardabra20.pojo.Card;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ArrayConverter {
    @TypeConverter
    public static String toJSON(ArrayList<Card> cardList) {

        String json = new Gson().toJson(cardList);
        return json;
    }

    @TypeConverter
    public static ArrayList<Card> toCardObjectList(String jsonAsString) {

        Type listType =
                new TypeToken<ArrayList<Card>>(){}.getType();
        ArrayList<Card> yourClassList = new Gson().fromJson(jsonAsString, listType);

        return yourClassList;
    }
}
