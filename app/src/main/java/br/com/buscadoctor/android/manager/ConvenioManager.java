package br.com.buscadoctor.android.manager;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.Convenio;
import br.com.buscadoctor.android.service.ConvenioServiceInterface;
import br.com.buscadoctor.android.service.ServiceGenerator;
import br.com.buscadoctor.android.service.listener.ConvenioServiceListener;
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
public class ConvenioManager {

    private Context context;

    public ConvenioManager(Context context) {
        this.context = context;
    }

    public Subscription getConvenios(final ConvenioServiceListener listener) {
        final ConvenioServiceInterface convenioServiceInterface =
                ServiceGenerator.createService(ConvenioServiceInterface.class);

        Observable<LinkedTreeMap> o = convenioServiceInterface.getConvenios();
        return o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LinkedTreeMap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(new Throwable(context.getString(R.string.error_convenios_load)));
                    }

                    @Override
                    public void onNext(LinkedTreeMap linkedTreeMap) {
                        try {
                            List<Convenio> convenios = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToArray(linkedTreeMap, "convenios"), new TypeReference<List<Convenio>>() {
                            });
                            listener.onSuccess(convenios);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail(new Throwable(context.getString(R.string.error_consultorio_load)));
                        }
                    }
                });
    }
}
