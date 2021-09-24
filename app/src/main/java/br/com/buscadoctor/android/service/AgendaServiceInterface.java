package br.com.buscadoctor.android.service;

import com.google.gson.internal.LinkedTreeMap;

import java.util.Map;

import br.com.buscadoctor.android.util.Constants;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AgendaServiceInterface {

    @GET(Constants.AGENDA + "/nonWorkDays")
    Observable<LinkedTreeMap> getNonWordays(@QueryMap Map<String, String> query);
}