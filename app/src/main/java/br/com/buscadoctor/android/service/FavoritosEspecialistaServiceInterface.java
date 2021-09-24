package br.com.buscadoctor.android.service;

import com.google.gson.internal.LinkedTreeMap;

import br.com.buscadoctor.android.util.Constants;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Andre
 * @version 1.0.0
 * @since 1.0.0
 */

public interface FavoritosEspecialistaServiceInterface {

    /**
     * Este metodo retorna os especialistas favoritos do usuario
     *
     * @param id do usuario
     * @return favoritosEspecialistas
     * @since 1.0.0
     */
    @GET(Constants.FAVORITOS_ESPECIALISTAS + "/")
    Observable<LinkedTreeMap> getFavoritosEspecialistas(@Query("usuario") Integer id);
}