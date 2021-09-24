package br.com.buscadoctor.android.service.listener;

import com.google.gson.internal.LinkedTreeMap;

import br.com.buscadoctor.android.util.Constants;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by andreribeiro on 13/07/17.
 */

public interface FavoritosConsultorioServiceInterface {

    /**
     *
     * Este metodo retorna os consultorios favoritos do usuario
     *
     * @param id do usuario
     * @return favoritosConsultorio
     * @since 1.0.0
     *
     */
    @GET(Constants.FAVORITOS_CONSULTORIOS + "/")
    Observable<LinkedTreeMap> getFavoritosConsultorios(@Query("usuario") Integer id);

}
