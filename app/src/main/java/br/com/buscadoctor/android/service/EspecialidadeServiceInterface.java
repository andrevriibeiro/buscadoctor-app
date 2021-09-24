package br.com.buscadoctor.android.service;

import com.google.gson.internal.LinkedTreeMap;

import br.com.buscadoctor.android.util.Constants;
import retrofit2.http.GET;
import rx.Observable;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public interface EspecialidadeServiceInterface {

    @GET(Constants.ESPECIALIDADE)
    Observable<LinkedTreeMap> getEspecialidades();
}