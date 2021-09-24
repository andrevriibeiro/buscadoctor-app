package br.com.buscadoctor.android.manager;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;

import java.util.List;
import java.util.Map;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.Consulta;
import br.com.buscadoctor.android.model.dto.ConsultaDTO;
import br.com.buscadoctor.android.model.dto.HourDTO;
import br.com.buscadoctor.android.service.ConsultaServiceInterface;
import br.com.buscadoctor.android.service.ServiceGenerator;
import br.com.buscadoctor.android.service.listener.ConsultaServiceListener;
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
public class ConsultaManager {

    private Context context;

    public ConsultaManager(Context context) {
        this.context = context;
    }

    public Subscription getConsulta(Integer id, final ConsultaServiceListener listener) {
        final ConsultaServiceInterface consultaServiceInterface = ServiceGenerator.createService(ConsultaServiceInterface.class);

        Observable<Consulta> o = consultaServiceInterface.getConsulta(id);
        return o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Consulta>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                        //listener.onFail(new Throwable(context.getString(R.string.error_consultorio_load)));
                    }

                    @Override
                    public void onNext(Consulta consulta) {
                        listener.onSuccess(consulta);
                    }
                });
    }

    public Subscription searchHorarios(Map<String, String> map, final ConsultaServiceListener listener) {
        final ConsultaServiceInterface consultaServiceInterface = ServiceGenerator.createService(ConsultaServiceInterface.class);

        Observable<LinkedTreeMap> o = consultaServiceInterface.searchHorarios(map);
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
                        try {
                            List<HourDTO> hourDTO = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToArray(linkedTreeMap, "search"), new TypeReference<List<HourDTO>>() {
                            });
                            listener.onSuccess(hourDTO);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void createConsulta(final ConsultaDTO consulta, final ConsultaServiceListener listener) {
        final ConsultaServiceInterface consultaServiceInterface = ServiceGenerator.createService(ConsultaServiceInterface.class);

        Call<LinkedTreeMap> call = consultaServiceInterface.createConsulta(consulta);
        call.enqueue(new Callback<LinkedTreeMap>() {
            @Override
            public void onResponse(Call<LinkedTreeMap> call, Response<LinkedTreeMap> response) {
                if (response.code() == 201) {
                    try {
                        List<Consulta> consultas = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToArray(response.body(), "consultas"), new TypeReference<List<Consulta>>() {
                        });
                        listener.onSuccess(consultas.get(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onFail(new Throwable(context.getString(R.string.error_consulta)));
                    }
                } else {
                    listener.onFail(new Throwable(context.getString(R.string.error_consulta)));
                }
            }

            @Override
            public void onFailure(Call<LinkedTreeMap> call, Throwable t) {
                listener.onFail(new Throwable(context.getString(R.string.error_consulta)));
            }
        });
    }
}