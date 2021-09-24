package br.com.buscadoctor.android.manager;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.dto.EspecialistaDTO;
import br.com.buscadoctor.android.service.EspecialistaServiceInterface;
import br.com.buscadoctor.android.service.ServiceGenerator;
import br.com.buscadoctor.android.service.listener.EspecialistaServiceListener;
import br.com.buscadoctor.android.util.JsonUtil;
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
public class EspecialistaManager {

    private Context context;

    public EspecialistaManager(Context context) {
        this.context = context;
    }

    public Subscription getEspecialistaByEspecialidade(Integer especialidade, final EspecialistaServiceListener listener) {
        final EspecialistaServiceInterface especialistaServiceInterface = ServiceGenerator.createService(EspecialistaServiceInterface.class);

        Observable<LinkedTreeMap> o = especialistaServiceInterface.getEspecialistasByEspecialidade(especialidade);
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
                            List<EspecialistaDTO> especialistas = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToArray(linkedTreeMap, "especialistas"), new TypeReference<List<EspecialistaDTO>>() {
                            });
                            listener.onSuccess(especialistas);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail(new Throwable(context.getString(R.string.error_consultorio_load)));
                        }
                    }
                });
    }
}