package br.com.buscadoctor.android.manager;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;

import java.util.List;
import java.util.Map;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.UsuarioConvenio;
import br.com.buscadoctor.android.service.ServiceGenerator;
import br.com.buscadoctor.android.service.UsuarioConvenioServiceInterface;
import br.com.buscadoctor.android.service.listener.UsuarioConvenioServiceListener;
import br.com.buscadoctor.android.util.JsonUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Andre
 * @version 1.0.0
 * @since 1.0.0
 */
public class UsuarioConvenioManager {

    private Context context;

    public UsuarioConvenioManager(Context context) {
        this.context = context;
    }

    /**
     * Este metodo cria um usuarioConvenio
     *
     * @param usuarioConvenio
     * @param listener
     * @since 1.0.0
     */
    public void createUsuarioConvenio(final UsuarioConvenio usuarioConvenio, final UsuarioConvenioServiceListener listener) {
        UsuarioConvenioServiceInterface usuarioConvenioServiceInterface = ServiceGenerator.createService(UsuarioConvenioServiceInterface.class);
        Call<LinkedTreeMap> call = usuarioConvenioServiceInterface.create(usuarioConvenio);
        call.enqueue(new Callback<LinkedTreeMap>() {
            @Override
            public void onResponse(Call<LinkedTreeMap> call, Response<LinkedTreeMap> response) {
                try {
                    UsuarioConvenio u = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToObject(response.body(), "usuarioConvenio"), new TypeReference<UsuarioConvenio>() {
                    });
                    listener.onSuccess(u);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFail(new Throwable(context.getString(R.string.error_usuario_convenio_create)));
                }
            }

            @Override
            public void onFailure(Call<LinkedTreeMap> call, Throwable t) {
                listener.onFail(new Throwable(context.getString(R.string.error_usuario_convenio_create)));
            }
        });
    }

    /**
     * Este metodo retorna todos os convenios da base
     *
     * @return convenios
     * @since 1.0.0
     */
    public Subscription getUsuarioConvenios(Map<String, Integer> map, final UsuarioConvenioServiceListener listener) {
        final UsuarioConvenioServiceInterface usuarioConvenioServiceInterface = ServiceGenerator.createService(UsuarioConvenioServiceInterface.class);

        Observable<LinkedTreeMap> o = usuarioConvenioServiceInterface.getUsuarioConvenios(map);
        return o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LinkedTreeMap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        //listener.onFail(new Throwable(context.getString(R.string.error_connection)));
                    }

                    @Override
                    public void onNext(LinkedTreeMap linkedTreeMap) {
                        try {
                            List<UsuarioConvenio> usuarioConvenioList = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToArray(linkedTreeMap, "usuarioConvenios"), new TypeReference<List<UsuarioConvenio>>() {
                            });
                            listener.onSuccess(usuarioConvenioList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail(new Throwable(context.getString(R.string.error_usuario_convenio_load)));
                        }
                    }
                });
    }


    /**
     * Este metodo edita um usuarioConvenio
     *
     * @param usuarioConvenio
     * @param listener
     * @since 1.0.0
     */
    public void editUsuarioConvenio(final UsuarioConvenio usuarioConvenio, final UsuarioConvenioServiceListener listener) {
        UsuarioConvenioServiceInterface usuarioConvenioServiceInterface = ServiceGenerator.createService(UsuarioConvenioServiceInterface.class);
        Call<LinkedTreeMap> call = usuarioConvenioServiceInterface.editUsuarioConvenio(usuarioConvenio);
        call.enqueue(new Callback<LinkedTreeMap>() {
            @Override
            public void onResponse(Call<LinkedTreeMap> call, Response<LinkedTreeMap> response) {
                try {
                    UsuarioConvenio u = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToObject(response.body(), "usuarioConvenio"), new TypeReference<UsuarioConvenio>() {
                    });
                    listener.onSuccess(u);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFail(new Throwable(context.getString(R.string.error_usuario_convenio_create)));
                }
            }

            @Override
            public void onFailure(Call<LinkedTreeMap> call, Throwable t) {
                listener.onFail(new Throwable(context.getString(R.string.error_usuario_convenio_create)));
            }
        });
    }


    /**
     * Este metodo deleta um usuarioConvenio do servidor
     *
     * @param listener
     * @since 1.0.0
     */
    public void deleteUsuarioConvenio(UsuarioConvenio usuarioConvenio, final UsuarioConvenioServiceListener listener) {
        UsuarioConvenioServiceInterface usuarioConvenioServiceInterface = ServiceGenerator.createService(UsuarioConvenioServiceInterface.class);
        Call<Void> call = usuarioConvenioServiceInterface.deleteUsuarioConvenio(usuarioConvenio);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onSuccess(response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail(new Throwable(context.getString(R.string.error_usuario_convenio_delete)));
            }
        });
    }
}