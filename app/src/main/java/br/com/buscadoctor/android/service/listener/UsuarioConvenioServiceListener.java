package br.com.buscadoctor.android.service.listener;

import java.util.List;

import br.com.buscadoctor.android.model.Usuario;
import br.com.buscadoctor.android.model.UsuarioConvenio;

/**
 * Created by andreribeiro on 05/07/17.
 */

public interface UsuarioConvenioServiceListener {

    void onSuccess(List<UsuarioConvenio> usuarioConvenioList);

    void onSuccess(UsuarioConvenio usuarioConvenio);

    void onSuccess(int status);

    void onFail(Throwable t);
}
