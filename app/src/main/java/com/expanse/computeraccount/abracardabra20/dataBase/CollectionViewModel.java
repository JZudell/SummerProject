package com.expanse.computeraccount.abracardabra20.dataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class CollectionViewModel extends AndroidViewModel {

    public final LiveData<List<CarDBObject>> cardList;

    public final LiveData<List<ListDBObject>> listList;

    private AppDatabase mDb;

    public CollectionViewModel(Application application) {
        super(application);
        createDb();


        // TODO: Assign books to the 'findBooksBorrowedByName' query.
        cardList = mDb.carModel().findAllCarDBObjects();
        listList = mDb.listModel().findAllListDBObjects();
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

        // Populate it with initial data
       // DatabaseInitializer.populateAsync(mDb);
    }

}
