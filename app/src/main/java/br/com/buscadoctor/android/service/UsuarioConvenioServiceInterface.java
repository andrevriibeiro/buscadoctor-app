package br.com.buscadoctor.android.service;

import com.google.gson.internal.LinkedTreeMap;

import java.util.Map;

import br.com.buscadoctor.android.model.UsuarioConvenio;
import br.com.buscadoctor.android.util.Constants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author Andre
 * @since 1.0.0
 * @version 1.0.0
 */
public interface UsuarioConvenioServiceInterface {

    @POST(Constants.USUARIO_CONVENIOS)
    Call<LinkedTreeMap> create(@Body UsuarioConvenio usuarioConvenio);

    @GET(Constants.USUARIO_CONVENIOS)
    Observable<LinkedTreeMap> getUsuarioConvenios(@QueryMap Map<String, Integer> query);

    @POST(Constants.USUARIO_CONVENIOS)
    Call<LinkedTreeMap> editUsuarioConvenio(@Body UsuarioConvenio usuarioConvenio);

    @HTTP(method = "DELETE", path = Constants.USUARIO_CONVENIOS, hasBody = true)
    Call<Void> deleteUsuarioConvenio(@Body UsuarioConvenio usuarioConvenio);
}