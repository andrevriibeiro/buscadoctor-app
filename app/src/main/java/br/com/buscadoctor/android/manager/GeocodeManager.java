package br.com.buscadoctor.android.manager;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import br.com.buscadoctor.android.model.LatitudeLongitude;
import br.com.buscadoctor.android.service.GeocodeService;

/**
 * @author Andre Ribeiro
 */
public class GeocodeManager {

    private Context context;

    public GeocodeManager(Context context) {
        this.context = context;
    }

    public LatitudeLongitude parserLatLonge(String address) {

        String mAddressFormated = address.replace(" ", "+");

        LatitudeLongitude latitudeLongitude = new LatitudeLongitude();

        try {
            String response = new GeocodeService(context, address).execute().get();
            JSONObject jsonObject = new JSONObject(response);
            JSONObject geometryJson = jsonObject.getJSONObject("geometry");
            JSONObject locationJson = geometryJson.getJSONObject("location");

            latitudeLongitude.setLatitude(locationJson.getDouble("lat"));
            latitudeLongitude.setLongitude(locationJson.getDouble("lng"));
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("GeocoderManager", e.getMessage());
        }

        return latitudeLongitude;
    }
}