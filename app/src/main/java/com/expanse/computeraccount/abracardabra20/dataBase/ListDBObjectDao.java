package com.expanse.computeraccount.abracardabra20.dataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(ArrayConverter.class)
public interface ListDBObjectDao {


    @Query("SELECT * FROM ListDBObject")
    LiveData<List<ListDBObject>> findAllListDBObjects();

    @Insert(onConflict = REPLACE)
    void insertListDBObject(ListDBObject listDBObject);

    @Query("DELETE FROM ListDBObject " +
            " WHERE ListDBObject.id LIKE :collection_id")
    void deleteOneList(long collection_id);


}
