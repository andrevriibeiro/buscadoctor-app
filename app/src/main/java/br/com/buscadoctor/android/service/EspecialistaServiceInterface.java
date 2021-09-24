package br.com.buscadoctor.android.service;

import com.google.gson.internal.LinkedTreeMap;

import br.com.buscadoctor.android.model.Especialista;
import br.com.buscadoctor.android.util.Constants;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Esta classe e responsavel pela interacao com endpoint do especialista
 *
 * @author Guilherme Mendes
 * @version 1.0.0
 * @see Especialista
 * @since 1.0.0
 */
public interface EspecialistaServiceInterface {

    @GET(Constants.ESPECIALISTA + "/search")
    Observable<LinkedTreeMap> getEspecialistasByEspecialidade(@Query("especialidade") Integer especialidade);
}