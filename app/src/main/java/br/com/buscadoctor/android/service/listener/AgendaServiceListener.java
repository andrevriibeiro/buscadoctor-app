package br.com.buscadoctor.android.service.listener;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AgendaServiceListener {

    void onSuccess(String string);

    void onFail(Throwable t);
}