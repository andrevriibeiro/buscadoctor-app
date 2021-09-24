package br.com.buscadoctor.android.service;

import com.google.gson.internal.LinkedTreeMap;

import java.util.Map;

import br.com.buscadoctor.android.model.Usuario;
import br.com.buscadoctor.android.util.Constants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public interface UsuarioServiceInterface {

    @POST(Constants.USUARIO + "/doLogin")
    Observable<LinkedTreeMap> login(@QueryMap Map<String, String> query);

    @GET(Constants.USUARIO + "/{id}")
    Observable<LinkedTreeMap> getUsuario(@Path("id") Integer id);

    @POST(Constants.USUARIO)
    Call<LinkedTreeMap> create(@Body Usuario usuario);

    @POST(Constants.USUARIO + "/update")
    Call<LinkedTreeMap> update(@Body Usuario usuario);
}