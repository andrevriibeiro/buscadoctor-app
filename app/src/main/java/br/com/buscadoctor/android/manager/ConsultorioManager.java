package br.com.buscadoctor.android.manager;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.Cidade;
import br.com.buscadoctor.android.model.Consultorio;
import br.com.buscadoctor.android.model.Estado;
import br.com.buscadoctor.android.model.Logradouro;
import br.com.buscadoctor.android.service.ConsultorioServiceInterface;
import br.com.buscadoctor.android.service.ServiceGenerator;
import br.com.buscadoctor.android.service.listener.ConsultorioServiceListener;
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
public class ConsultorioManager {

    private Context context;
    private List<Consultorio> mConsultorioList = new ArrayList<>();

    public ConsultorioManager(Context context) {
        this.context = context;
    }

    public Subscription getConsultorios(final ConsultorioServiceListener listener) {
        final ConsultorioServiceInterface consultorioServiceInterface =
                ServiceGenerator.createService(ConsultorioServiceInterface.class);

        Observable<LinkedTreeMap> o = consultorioServiceInterface.getConsultorios();
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
                        if (linkedTreeMap != null) {
                            ArrayList arrayList = (ArrayList) linkedTreeMap.get("consultorios");

                            for (int i = 0; i < arrayList.size(); i++) {
                                LinkedTreeMap c = (LinkedTreeMap) arrayList.get(i);
                                Consultorio consultorio = new Consultorio();
                                Double id = Double.parseDouble(c.get("id").toString());
                                consultorio.setId(id.intValue());
                                consultorio.setNome(c.get("nome").toString());

                                LinkedTreeMap l = (LinkedTreeMap) c.get("logradouro");
                                Logradouro logradouro = new Logradouro();

                                LinkedTreeMap cid = (LinkedTreeMap) l.get("cidade");
                                Cidade cidade = new Cidade();
                                cidade.setNome(cid.get("nome").toString());

                                LinkedTreeMap est = (LinkedTreeMap) cid.get("estado");
                                Estado estado = new Estado();
                                estado.setNome(est.get("nome").toString());
                                estado.setAcronimo(est.get("acronimo").toString());

                                cidade.setEstado(estado);
                                logradouro.setCidade(cidade);

                                mConsultorioList.add(consultorio);
                            }
                            listener.onSuccess(mConsultorioList);
                        } else {
                            //listener.onFail(new Throwable(context.getString(R.string.error_connection)));
                        }
                    }
                });
    }

    public Subscription getConsultorio(Integer id, final ConsultorioServiceListener listener) {
        final ConsultorioServiceInterface consultorioServiceInterface = ServiceGenerator.createService(ConsultorioServiceInterface.class);

        Observable<LinkedTreeMap> o = consultorioServiceInterface.getConsultorio(id);
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
                        if (linkedTreeMap != null) {
                            LinkedTreeMap c = (LinkedTreeMap) linkedTreeMap.get("consultorio");
                            Consultorio consultorio = new Consultorio();
                            Double id = Double.parseDouble(c.get("id").toString());
                            consultorio.setId(id.intValue());
                            consultorio.setNome(c.get("nome").toString());
                            consultorio.setTelefone(c.get("telefone").toString());
                            Double d = Double.parseDouble(c.get("numero").toString());
                            consultorio.setNumero(d.intValue());
                            consultorio.setComplemento(c.get("complemento").toString());
                            consultorio.setEmail(c.get("email").toString());

                            LinkedTreeMap l = (LinkedTreeMap) c.get("logradouro");
                            Logradouro logradouro = new Logradouro();
                            logradouro.setTipo(l.get("tipo").toString());
                            logradouro.setNome(l.get("nome").toString());
                            logradouro.setBairro(l.get("bairro").toString());
                            logradouro.setCep(l.get("cep").toString());

                            LinkedTreeMap cid = (LinkedTreeMap) l.get("cidade");
                            Cidade cidade = new Cidade();
                            cidade.setNome(cid.get("nome").toString());

                            LinkedTreeMap est = (LinkedTreeMap) cid.get("estado");
                            Estado estado = new Estado();
                            estado.setNome(est.get("nome").toString());
                            estado.setAcronimo(est.get("acronimo").toString());

                            cidade.setEstado(estado);
                            logradouro.setCidade(cidade);

                            listener.onSuccess(consultorio);
                        } else {
                            //listener.onFail(new Throwable(context.getString(R.string.error_connection)));
                        }
                    }
                });
    }

    /**
     * Este metodo retorna consultorio por tipo
     *
     * @param tipo     do consultorio
     * @param listener para tratamento do retorno
     * @return lista de consultorios
     * @since 1.0.0
     */
    public Subscription getConsultoriosByTipo(Integer tipo, final ConsultorioServiceListener listener) {
        final ConsultorioServiceInterface consultorioServiceInterface =
                ServiceGenerator.createService(ConsultorioServiceInterface.class);

        Observable<LinkedTreeMap> o = consultorioServiceInterface.getConsultoriosByTipo(tipo);
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
                            List<Consultorio> consultorios = JsonUtil.getEntity(JsonUtil.convertLinkedTreeToArray(linkedTreeMap, "consultorios"), new TypeReference<List<Consultorio>>() {
                            });
                            listener.onSuccess(consultorios);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail(new Throwable(context.getString(R.string.error_consultorio_load)));
                        }
                    }
                });
    }
}