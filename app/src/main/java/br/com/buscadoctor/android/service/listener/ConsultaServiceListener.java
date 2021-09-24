package br.com.buscadoctor.android.service.listener;

import java.util.List;

import br.com.buscadoctor.android.model.Consulta;
import br.com.buscadoctor.android.model.dto.HourDTO;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ConsultaServiceListener {

    void onSuccess(Consulta consulta);

    void onSuccess(List<HourDTO> hourDTOList);

    void onFail(Throwable t);
}