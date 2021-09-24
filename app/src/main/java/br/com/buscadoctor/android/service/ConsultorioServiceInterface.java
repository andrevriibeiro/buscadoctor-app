package br.com.buscadoctor.android.service;

import com.google.gson.internal.LinkedTreeMap;

import br.com.buscadoctor.android.model.Consultorio;
import br.com.buscadoctor.android.util.Constants;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Esta classe e responsavel pela interacao com endpoint do consultorio
 *
 * @author Guilherme Mendes
 * @version 1.0.0
 * @see Consultorio
 * @since 1.0.0
 */
public interface ConsultorioServiceInterface {

    @GET(Constants.CONSULTORIO)
    Observable<LinkedTreeMap> getConsultorios();

    @GET(Constants.CONSULTORIO + "/{id}")
    Observable<LinkedTreeMap> getConsultorio(@Path("id") Integer id);

    /**
     * Este metodo pesquisa consultorio por tipo
     *
     * @param tipo do conausltorio
     * @return consultorios
     * @since 1.0.0
     */
    @GET(Constants.CONSULTORIO + "/search")
    Observable<LinkedTreeMap> getConsultoriosByTipo(@Query("tipo") Integer tipo);
}