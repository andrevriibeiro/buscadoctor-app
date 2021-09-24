package br.com.buscadoctor.android.manager;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;

import java.util.Map;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.Usuario;
import br.com.buscadoctor.android.service.ServiceGenerator;
import br.com.buscadoctor.android.service.UsuarioServiceInterface;
import br.com.buscadoctor.android.service.listener.UsuarioServiceListener;
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
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class UsuarioManager {

    private Context context;

    public UsuarioManager(Context context) {
        this.context = context;
    }

    public Subscription login(Map<String, String> map, final UsuarioServiceListener listener) {
        final UsuarioServiceInterface usuarioService = ServiceGenerator.createService(UsuarioServiceInterface.class);
        Observable<LinkedTreeMap> o = usuarioService.login(map);
        return o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LinkedTreeMap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(new Throwable(context.getString(R.string.error_username_password)));
                    }

                    @Override
                    public void onNext(LinkedTreeMap linkedTreeMap) {
                        try {
                            Usuario usuario = JsonUtil.getEntity(JsonUtil
                                    .convertLinkedTreeToObject(linkedTreeMap, "usuario"), new TypeReference<Usuario>() {
                            });
                            listener.onSuccess(usuario);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail(new Throwable(context.getString(R.string.error_usuario_load)));
                        }
                    }
                });
    }

    /**
     * Este metodo cria um usuario
     *
     * @param usuario
     * @param listener
     * @since 1.0.0
     */
    public void createUser(Usuario usuario, final UsuarioServiceListener listener) {
        UsuarioServiceInterface usuarioServiceInterface = ServiceGenerator.createService(UsuarioServiceInterface.class);
        Call<LinkedTreeMap> call = usuarioServiceInterface.create(usuario);
        call.enqueue(new Callback<LinkedTreeMap>() {
            @Override
            public void onResponse(Call<LinkedTreeMap> call, Response<LinkedTreeMap> response) {
                try {
                    Usuario u = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToObject(response.body(), "usuario"), new TypeReference<Usuario>() {
                    });
                    listener.onSuccess(u);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFail(new Throwable(context.getString(R.string.error_usuario_create)));

                }
            }

            @Override
            public void onFailure(Call<LinkedTreeMap> call, Throwable t) {
                listener.onFail(new Throwable(context.getString(R.string.error_usuario_create)));
            }
        });
    }

    /**
     * Este metodo faz o update do usuario
     *
     * @param usuario
     * @param listener
     * @since 1.0.0
     */
    public void updateUsuario(Usuario usuario, final UsuarioServiceListener listener) {
        UsuarioServiceInterface usuarioServiceInterface = ServiceGenerator.createService(UsuarioServiceInterface.class);
        Call<LinkedTreeMap> call = usuarioServiceInterface.update(usuario);
        call.enqueue(new Callback<LinkedTreeMap>() {
            @Override
            public void onResponse(Call<LinkedTreeMap> call, Response<LinkedTreeMap> response) {
                try {
                    Usuario u = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToObject(response.body(), "usuario"), new TypeReference<Usuario>() {
                    });
                    listener.onSuccess(u);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFail(new Throwable(context.getString(R.string.error_usuario_update)));
                }
            }

            @Override
            public void onFailure(Call<LinkedTreeMap> call, Throwable t) {
                listener.onFail(new Throwable(context.getString(R.string.error_usuario_update)));
            }
        });
    }

    /**
     * Este metodo retorna o usuario por id
     *
     * @param id
     * @param listener
     * @since 1.0.0
     */
    public Subscription getUsuario(Integer id, final UsuarioServiceListener listener) {
        final UsuarioServiceInterface usuarioServiceInterface = ServiceGenerator.createService(UsuarioServiceInterface.class);

        Observable<LinkedTreeMap> o = usuarioServiceInterface.getUsuario(id);
        return o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LinkedTreeMap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(new Throwable(context.getString(R.string.error_connection)));
                    }

                    @Override
                    public void onNext(LinkedTreeMap linkedTreeMap) {
                        try {
                            Usuario usuario = JsonUtil.getEntity(JsonUtil
                                    .convertLinkedTreeToObject(linkedTreeMap, "usuario"), new TypeReference<Usuario>() {
                            });
                            listener.onSuccess(usuario);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail(new Throwable(context.getString(R.string.error_usuario_load)));
                        }
                    }
                });
    }
}