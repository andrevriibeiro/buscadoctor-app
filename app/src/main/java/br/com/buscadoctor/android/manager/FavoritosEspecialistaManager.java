package br.com.buscadoctor.android.manager;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.FavoritosEspecialista;
import br.com.buscadoctor.android.service.FavoritosEspecialistaServiceInterface;
import br.com.buscadoctor.android.service.ServiceGenerator;
import br.com.buscadoctor.android.service.listener.FavoritosEspecialistaServiceListener;
import br.com.buscadoctor.android.util.JsonUtil;
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
public class FavoritosEspecialistaManager {

    private Context context;

    public FavoritosEspecialistaManager(Context context) {
        this.context = context;
    }

    public Subscription getFavoritosEspecialistas(Integer idUsuario, final FavoritosEspecialistaServiceListener listener) {
        final FavoritosEspecialistaServiceInterface favoritosEspecialistaServiceInterface =
                ServiceGenerator.createService(FavoritosEspecialistaServiceInterface.class);

        Observable<LinkedTreeMap> o = favoritosEspecialistaServiceInterface.getFavoritosEspecialistas(idUsuario);
        return o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LinkedTreeMap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(new Throwable(context.getString(R.string.error_consultorio_load)));
                    }

                    @Override
                    public void onNext(LinkedTreeMap linkedTreeMap) {
                        try {
                            List<FavoritosEspecialista> favoritosEspecialistas =
                                    JsonUtil.getEntity(JsonUtil.convertLinkedTreeToArray(linkedTreeMap, "favoritosEspecialistas"),
                                            new TypeReference<List<FavoritosEspecialista>>() {
                                            });
                            listener.onSuccess(favoritosEspecialistas);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail(new Throwable(context.getString(R.string.error_consultorio_load)));
                        }
                    }
                });
    }
}