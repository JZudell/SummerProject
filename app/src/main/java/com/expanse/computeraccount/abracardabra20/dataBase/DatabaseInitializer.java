package com.expanse.computeraccount.abracardabra20.dataBase;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

public class DatabaseInitializer {

    // Simulate a blocking operation delaying each Loan insertion with a delay:
    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }
    public static void populateAsyncAddOneCard(final AppDatabase db, CarDBObject carDBObject) {

        PopulateDbAsyncAddOneCard task = new PopulateDbAsyncAddOneCard(db,carDBObject);
        task.execute();
    }

    public static void populateAsyncUpdateOneCard(final AppDatabase db,CarDBObject carDBObject) {

        PopulateDbAsyncUpdateOneCard task = new PopulateDbAsyncUpdateOneCard(db,carDBObject);
        task.execute();
    }

    public static void populateAsyncAddOneList(final AppDatabase db,ListDBObject listDBObject) {

        PopulateDbAsyncAddOneList task = new PopulateDbAsyncAddOneList(db,listDBObject);
        task.execute();
    }

    public static void removeAsyncOneList(final AppDatabase db, long collectionId) {

        RemoveDbAsyncOneList removeDbAsyncOneList = new RemoveDbAsyncOneList(db,collectionId);
        removeDbAsyncOneList.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

//    private static void addLoan(final AppDatabase db, final String id,
//                                final User user, final Book book, Date from, Date to) {
//        Loan loan = new Loan();
//        loan.id = id;
//        loan.bookId = book.id;
//        loan.userId = user.id;
//        loan.startTime = from;
//        loan.endTime = to;
//        db.loanModel().insertLoan(loan);
//    }

//    private static Book addBook(final AppDatabase db, final String id, final String title) {
//        Book book = new Book();
//        book.id = id;
//        book.title = title;
//        db.bookModel().insertBook(book);
//        return book;
//    }


    //subType is if the card is foil or not. "Normal" and "Foil" are the 2 possible
    private static CarDBObject addCarDBObject(final AppDatabase db, final int id, final String name,
                                              final String set, final String imageUrl,
                                              final String subType, final int quantity) {
        CarDBObject carDBObject = new CarDBObject();
        carDBObject.id = id;
        carDBObject.set = set;
        carDBObject.name = name;
        carDBObject.imageUrl = imageUrl;
        carDBObject.subType = subType;
        carDBObject.quantity = quantity;
        db.carModel().insertCarDBObject(carDBObject);
        return carDBObject;
    }
    private static CarDBObject addCarDBObject(final AppDatabase db, CarDBObject carDBObject) {
        db.carModel().insertCarDBObject(carDBObject);
        return carDBObject;
    }
    private static CarDBObject updateCarDBObject(final AppDatabase db, CarDBObject carDBObject) {

        db.carModel().updateCarDBObject(carDBObject);
        return carDBObject;
    }

    private static ListDBObject addListDBObject(final AppDatabase db, ListDBObject listDBObject) {
        db.listModel().insertListDBObject(listDBObject);
        return listDBObject;
    }
    private static void deleteOneList(final AppDatabase db, long collectionId){
        Log.v("DELETE CALL","----------"+collectionId+"-----------");
        db.listModel().deleteOneList(collectionId);
    }











    private static void populateWithTestData(AppDatabase db) {
        //db.carModel().deleteAll();

        addCarDBObject(db, 1001, "Snapcaster Mage", "Innistrad", "www.google.com","FOil",1);
        addCarDBObject(db, 5555, "Nicol Bolas", "Legends", "www.yahoo.com","Normal",3);

    }



//    private static Date getTodayPlusDays(int daysAgo) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, daysAgo);
//        return calendar.getTime();
//    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
    private static class PopulateDbAsyncAddOneCard extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        private final CarDBObject carDBObject;

        PopulateDbAsyncAddOneCard(AppDatabase db,CarDBObject carDBObject) {
            mDb = db;
            this.carDBObject = carDBObject;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            addCarDBObject(mDb,carDBObject);
            return null;
        }

    }

    private static class PopulateDbAsyncUpdateOneCard extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        private final CarDBObject carDBObject;

        PopulateDbAsyncUpdateOneCard(AppDatabase db,CarDBObject carDBObject) {
            mDb = db;
            this.carDBObject = carDBObject;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            updateCarDBObject(mDb,carDBObject);
            return null;
        }

    }


    private static class PopulateDbAsyncAddOneList extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        private final ListDBObject listDBObject;

        PopulateDbAsyncAddOneList(AppDatabase db,ListDBObject listDBObject) {
            mDb = db;
            this.listDBObject = listDBObject;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            addListDBObject(mDb,listDBObject);
            return null;
        }

    }
    private static class RemoveDbAsyncOneList extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        private final long collectionId;

        RemoveDbAsyncOneList(AppDatabase db,long collectionId) {
            mDb = db;
            this.collectionId = collectionId;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            deleteOneList(mDb,collectionId);
            return null;
        }

    }






}
