package com.expanse.computeraccount.abracardabra20.dataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {CarDBObject.class,ListDBObject.class}, version = 6,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract CarDBObjectDao carModel();

    public abstract ListDBObjectDao listModel();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
//                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
//
//                            // To simplify the codelab, allow queries on the main thread.
//                            // Don't do this on a real app! See PersistenceBasicSample for an example.
//                            .allowMainThreadQueries()
//                            .build();
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"cardsTable")
                            .fallbackToDestructiveMigration()

                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            //.allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
