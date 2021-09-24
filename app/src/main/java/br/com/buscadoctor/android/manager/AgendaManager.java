package br.com.buscadoctor.android.manager;

import android.content.Context;
import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;

import java.util.Map;

import br.com.buscadoctor.android.service.AgendaServiceInterface;
import br.com.buscadoctor.android.service.ServiceGenerator;
import br.com.buscadoctor.android.service.listener.AgendaServiceListener;
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
public class AgendaManager {

    private Context context;

    public AgendaManager(Context context) {
        this.context = context;
    }

    public Subscription getNonWordays(Map<String, String> map, final AgendaServiceListener listener) {
        final AgendaServiceInterface agendaServiceInterface = ServiceGenerator.createService(AgendaServiceInterface.class);

        Observable<LinkedTreeMap> o = agendaServiceInterface.getNonWordays(map);
        return o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LinkedTreeMap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                        //listener.onFail(new Throwable(context.getString(R.string.error_consultorio_load)));
                    }

                    @Override
                    public void onNext(LinkedTreeMap linkedTreeMap) {
                        Log.d("", "");
                        /*try {
                            List<Consultorio> consultorios = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToArray(linkedTreeMap, "consultorios"), new TypeReference<List<Consultorio>>() {
                            });
                            listener.onSuccess(consultorios);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail(new Throwable(context.getString(R.string.error_consultorio_load)));
                        }*/
                    }
                });
    }
}