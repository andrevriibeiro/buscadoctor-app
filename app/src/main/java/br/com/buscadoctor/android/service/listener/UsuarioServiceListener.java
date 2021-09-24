package br.com.buscadoctor.android.service.listener;

import br.com.buscadoctor.android.model.Usuario;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public interface UsuarioServiceListener {

    void onSuccess(Usuario usuario);

    void onFail(Throwable t);
}