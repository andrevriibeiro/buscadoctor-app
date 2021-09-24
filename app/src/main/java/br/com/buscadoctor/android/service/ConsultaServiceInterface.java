package br.com.buscadoctor.android.service;


import com.google.gson.internal.LinkedTreeMap;

import java.util.Map;

import br.com.buscadoctor.android.model.Consulta;
import br.com.buscadoctor.android.model.dto.ConsultaDTO;
import br.com.buscadoctor.android.util.Constants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Esta classe e responsavel pela interacao com endpoint do consulta
 *
 * @author Guilherme Mendes
 * @version 1.0.0
 * @see Consulta
 * @since 1.0.0
 */
public interface ConsultaServiceInterface {

    @GET(Constants.CONSULTA + "/{id}")
    Observable<Consulta> getConsulta(@Path("id") Integer id);

    @GET(Constants.CONSULTA + "/searchHorarios")
    Observable<LinkedTreeMap> searchHorarios(@QueryMap Map<String, String> query);

    @POST(Constants.CONSULTA + "/create")
    Call<LinkedTreeMap> createConsulta(@Body ConsultaDTO consulta);
}