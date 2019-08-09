package com.expanse.computeraccount.abracardabra20;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetCollectionListTask extends AsyncTask<String, Void, String> {
    int whichVersionToCall;
    long collectionIndex;

    private final String LOG_TAG = GetPrintingsAndPriceTask.class.getSimpleName();
    public GetCollectionListTask() {
        this.whichVersionToCall = whichVersionToCall;
        this.collectionIndex = collectionIndex;
    }
    CallBackToCollectionAdapter mListener;

    public void setListener(CallBackToCollectionAdapter listener){
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String apiJsonStr = null;

        try {
            String baseUrl = "https://us-central1-tcginitiation.cloudfunctions.net/ANCIENTSECRETS";
            String listAsString = params[0];
            // String baseUrl = "https://us-central1-tcginitiation.cloudfunctions.net/ANCIENTSECRETS?funactcode=5&cardName=";

            String uri = Uri.parse(baseUrl).buildUpon().appendQueryParameter("funactcode","3").appendQueryParameter("idlist",listAsString).build().toString();


            //   String finalUrl = baseUrl + id;
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            apiJsonStr = buffer.toString();
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
            }
        }
        return apiJsonStr;
    }

    @Override
    protected void onPostExecute(String stringReturn) {
        super.onPostExecute(stringReturn);
        Log.v("JSONRETURN aaz",stringReturn);

        mListener.jsonResponseList(stringReturn);
//        try {
//            mListener.callBackPrintingsAndPrice(stringReturn,whichVersionToCall,collectionIndex);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }
}
