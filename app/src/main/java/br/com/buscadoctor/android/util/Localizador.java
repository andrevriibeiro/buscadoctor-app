package br.com.buscadoctor.android.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;
import java.io.IOException;

import java.util.List;

/**
 * Created by andreribeiro on 01/09/17.
 */

public class Localizador {

    private Geocoder geo;

    public Localizador(Context context) {
        geo = new Geocoder(context);
    }

    public LatLng getCoordenada(String endereco) {

        /*GeocodeManager geocodeManager = new GeocodeManager(context);

        LatitudeLongitude lt = geocodeManager.parserLatLonge(endereco);

        if (lt != null) {
            return new LatLng(lt.getLatitude(), lt.getLongitude());
        } else {
            return null;
        }*/

        try {
            List<Address> listaEnderecos;
            listaEnderecos = geo.getFromLocationName(endereco, 1);

            if (!listaEnderecos.isEmpty()) {
                Address address = listaEnderecos.get(0);
                return new LatLng(address.getLatitude(), address.getLongitude());
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
}
