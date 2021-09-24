package br.com.buscadoctor.android.manager;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.FavoritosConsultorio;
import br.com.buscadoctor.android.service.ServiceGenerator;
import br.com.buscadoctor.android.service.listener.FavoritosConsultorioServiceInterface;
import br.com.buscadoctor.android.service.listener.FavoritosConsultorioServiceListener;
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
public class FavoritosConsultorioManager {

    private Context context;

    public FavoritosConsultorioManager(Context context) {
        this.context = context;
    }

    public Subscription getFavoritosConsultorios(Integer idUsuario, final FavoritosConsultorioServiceListener listener) {
        final FavoritosConsultorioServiceInterface favoritosConsultorioServiceInterface =
                ServiceGenerator.createService(FavoritosConsultorioServiceInterface.class);

        Observable<LinkedTreeMap> o = favoritosConsultorioServiceInterface.getFavoritosConsultorios(idUsuario);
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
                            List<FavoritosConsultorio> favoritosConsultorios =
                                    JsonUtil.getEntity(JsonUtil.convertLinkedTreeToArray(linkedTreeMap, "favoritosConsultorios"),
                                            new TypeReference<List<FavoritosConsultorio>>() {
                                            });
                            listener.onSuccess(favoritosConsultorios);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail(new Throwable(context.getString(R.string.error_consultorio_load)));
                        }
                    }
                });
    }
}
