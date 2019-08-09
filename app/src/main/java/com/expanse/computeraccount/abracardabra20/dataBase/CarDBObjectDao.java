package com.expanse.computeraccount.abracardabra20.dataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CarDBObjectDao {

    @Query("select * from CarDBObject where id = :id")
    CarDBObject loadCarDBObjectById(int id);

//    @Query("SELECT * FROM CarDBObject " +
//            "INNER JOIN Loan ON Loan.carDBObject_id = CarDBObject.id " +
//            "INNER JOIN User on User.id = Loan.user_id " +
//            "WHERE User.name LIKE :userName"
//    )
//    LiveData<List<CarDBObject>> findCarDBObjectsBorrowedByName(String userName);
//
//    @Query("SELECT * FROM CarDBObject " +
//            "INNER JOIN Loan ON Loan.carDBObject_id = CarDBObject.id " +
//            "INNER JOIN User on User.id = Loan.user_id " +
//            "WHERE User.name LIKE :userName " +
//            "AND Loan.endTime > :after "
//    )
//    LiveData<List<CarDBObject>> findCarDBObjectsBorrowedByNameAfter(String userName, Date after);
//
//    @Query("SELECT * FROM CarDBObject " +
//            "INNER JOIN Loan ON Loan.carDBObject_id = CarDBObject.id " +
//            "INNER JOIN User on User.id = Loan.user_id " +
//            "WHERE User.name LIKE :userName"
//    )
//    List<CarDBObject> findCarDBObjectsBorrowedByNameSync(String userName);
//    @Query("SELECT * FROM CarDBObject " +
//            "INNER JOIN Loan ON Loan.carDBObject_id = CarDBObject.id " +
//            "INNER JOIN User on User.id = Loan.user_id " +
//            "WHERE User.name LIKE :userName"
//    )
//    LiveData<List<CarDBObject>> findCarDBObjectsBorrowedByNameSyncLiveData(String userName);
//
//    @Query("SELECT * FROM CarDBObject " +
//            "INNER JOIN Loan ON Loan.carDBObject_id LIKE CarDBObject.id " +
//            "WHERE Loan.user_id LIKE :userId "
//    )
//    LiveData<List<CarDBObject>> findCarDBObjectsBorrowedByUser(String userId);
//
//    @Query("SELECT * FROM CarDBObject " +
//            "INNER JOIN Loan ON Loan.carDBObject_id LIKE CarDBObject.id " +
//            "WHERE Loan.user_id LIKE :userId " +
//            "AND Loan.endTime > :after "
//    )
//    LiveData<List<CarDBObject>> findCarDBObjectsBorrowedByUserAfter(String userId, Date after);
//
//    @Query("SELECT * FROM CarDBObject " +
//            "INNER JOIN Loan ON Loan.carDBObject_id LIKE CarDBObject.id " +
//            "WHERE Loan.user_id LIKE :userId "
//    )
//    List<CarDBObject> findCarDBObjectsBorrowedByUserSync(String userId);

    @Query("SELECT * FROM CarDBObject")
    LiveData<List<CarDBObject>> findAllCarDBObjects();




    @Query("SELECT * FROM CarDBObject " +
           "WHERE CarDBObject.collectionId LIKE :collection_id")
    List<CarDBObject> findAllCarDBObjectsWithCollectionId(long collection_id);

    @Insert(onConflict = REPLACE)
    void insertCarDBObject(CarDBObject carDBObject);

    @Update(onConflict = REPLACE)
    void updateCarDBObject(CarDBObject carDBObject);

    @Query("DELETE FROM CarDBObject")
    void deleteAll();
}