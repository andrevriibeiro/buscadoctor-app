package br.com.buscadoctor.android.service.listener;

import java.util.List;

import br.com.buscadoctor.android.model.Convenio;

/**
 * Created by andreribeiro on 15/07/17.
 */
public interface ConvenioServiceListener {

    void onSuccess(List<Convenio> convenios);

    void onFail(Throwable t);
}
