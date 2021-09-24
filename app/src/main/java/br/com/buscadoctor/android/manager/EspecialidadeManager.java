package br.com.buscadoctor.android.manager;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.Especialidade;
import br.com.buscadoctor.android.service.EspecialidadeServiceInterface;
import br.com.buscadoctor.android.service.ServiceGenerator;
import br.com.buscadoctor.android.service.listener.EspecialidadeServiceListener;
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
public class EspecialidadeManager {

    private Context context;

    public EspecialidadeManager(Context context) {
        this.context = context;
    }

    public Subscription getEspecialidades(final EspecialidadeServiceListener listener) {
        final EspecialidadeServiceInterface especialidadeServiceInterface =
                ServiceGenerator.createService(EspecialidadeServiceInterface.class);

        Observable<LinkedTreeMap> o = especialidadeServiceInterface.getEspecialidades();
        return o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LinkedTreeMap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(new Throwable(context.getString(R.string.error_especialidade_load)));
                    }

                    @Override
                    public void onNext(LinkedTreeMap linkedTreeMap) {
                        try {
                            List<Especialidade> especialidades = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToArray(linkedTreeMap, "especialidades"), new TypeReference<List<Especialidade>>() {
                            });
                            listener.onSuccess(especialidades);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail(new Throwable(context.getString(R.string.error_consultorio_load)));
                        }
                    }
                });
    }
}