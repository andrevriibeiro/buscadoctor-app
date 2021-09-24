package br.com.buscadoctor.android.service.listener;

import java.util.List;

import br.com.buscadoctor.android.model.Especialista;
import br.com.buscadoctor.android.model.dto.EspecialistaDTO;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public interface EspecialistaServiceListener {

    void onSuccess(List<EspecialistaDTO> especialistas);

    void onSuccess(Especialista especialista);

    void onFail(Throwable t);
}