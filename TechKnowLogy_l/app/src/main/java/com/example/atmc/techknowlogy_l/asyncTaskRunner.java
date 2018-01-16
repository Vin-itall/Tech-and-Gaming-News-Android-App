package com.example.atmc.techknowlogy_l;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



class ServiceHandler {

    // Create Http connection And find Json

    public static String findJSONFromUrl(String url) {
        String result = "";
        try {
            URL urls = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
            conn.setReadTimeout(150000); //milliseconds
            conn.setConnectTimeout(15000); // milliseconds
            conn.setRequestMethod("GET");

            conn.connect();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");

                }
                result = sb.toString();
            } else {

                return "error";
            }


        } catch (Exception e) {
            // System.out.println("exception in jsonparser class ........");
            e.printStackTrace();
            return "error";
        }

        return result;
    } // method ends
}


public class asyncTaskRunner extends AsyncTask<String, String, String>
{
    interface Listener {
        void onResult(String result);
    }
    private Listener mListener;

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(mListener != null)
        {
            mListener.onResult(s);
        }
    }

    public asyncTaskRunner() {

    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String response = "";

        response = ServiceHandler.findJSONFromUrl(params[0]);
//        data = response;
        return response;
    }

    private String readInputStreamToString(HttpURLConnection connection) {
        String result = null;
        StringBuilder sb = new StringBuilder();
        InputStream is = null;

        try {
            is = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            result = sb.toString();
        }
        catch (Exception e) {
            Log.i("Error", "Error reading InputStream");
            result = null;
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (Throwable t) {
                    Log.i("error", "Error closing InputStream");
                }
            }
        }

        return result;
    }
}
