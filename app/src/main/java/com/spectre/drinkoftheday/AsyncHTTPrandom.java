package com.spectre.drinkoftheday;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class AsyncHTTPrandom extends AsyncTask<String, Integer, String> {

    private AppCompatActivity myActivity;

    public AsyncHTTPrandom(AppCompatActivity RandomDrinks) {
        myActivity = RandomDrinks;
    }

    @Override
    protected void onPreExecute() {
        Context context = myActivity.getApplicationContext();
        CharSequence text = "Wait!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        publishProgress(1);

        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            publishProgress(2);
            result = readStream(in);
        }

        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        finally { if (urlConnection != null)
            urlConnection.disconnect();  }

        publishProgress(4);
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        ProgressBar pb = (ProgressBar) myActivity.findViewById(R.id.progressBar);
        pb.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        TextView text = (TextView) myActivity.findViewById(R.id.text);
        String res = "";

        JSONObject jObject = null;
        try {
            jObject = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jArray = null;
        try {
            jArray = jObject.getJSONArray("drinks");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jArray == null ) {
            res = "error";
        } else {

            for (int i = 0; i < jArray.length(); i++) {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    String DrinkName = oneObject.getString("strDrink");
                    String Alcool = oneObject.getString("strAlcoholic");
                    String Instructions = oneObject.getString("strInstructions");
                    String Ingredient = oneObject.getString("strIngredient1");
                    String Measure = oneObject.getString("strMeasure1");
                    int x=1;
                    String xx;
                    String listeIngredrientsMeasure="";
                    while (Ingredient!="null") {
                        listeIngredrientsMeasure = listeIngredrientsMeasure + ";  " + Ingredient + ": " + Measure;
                        x = x + 1;
                        xx = String.valueOf(x);
                        Ingredient = oneObject.getString("strIngredient" + xx);
                        Measure = oneObject.getString("strMeasure" + xx);


                    }
                    res = res + "\n\n" + DrinkName + ": \nHas Alcohool ? " + Alcool +"\nIngredients: " + listeIngredrientsMeasure +"\nInstructions: "+Instructions;

                } catch (JSONException e) {
                }
            }
        }
        text.setText(res);
        ProgressBar pb = (ProgressBar) myActivity.findViewById(R.id.progressBar);
        pb.setProgress(5);
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
