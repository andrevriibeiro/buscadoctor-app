package br.com.buscadoctor.android.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.buscadoctor.android.R;

/**
 * @author Andre Ribeiro
 */
public class GeocodeService extends AsyncTask<String, String, String> {

    private Context context;
    private String address;

    public GeocodeService(Context context, String address) {
        this.context = context;
        this.address = address;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        try {
            URL url = new URL("https://maps.google.com/maps/api/geocode/json?address=" + address + "+CA&sensor=true&key=AIzaSyA4cA9cj6z-e89e7Vv-PEEq7U-ykWJharM");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }

            result = response.toString();
            bufferedReader.close();
        } catch (Exception e) {
            Log.d("InputStream", e.getMessage());
        }

        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result.isEmpty()) {
            Toast.makeText(context, context.getString(R.string.conection_problem), Toast.LENGTH_LONG).show();
        }
    }
}