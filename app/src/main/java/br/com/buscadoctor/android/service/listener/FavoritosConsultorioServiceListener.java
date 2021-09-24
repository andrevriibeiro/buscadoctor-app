package br.com.buscadoctor.android.service.listener;

import java.util.List;

import br.com.buscadoctor.android.model.FavoritosConsultorio;
import br.com.buscadoctor.android.model.Usuario;

/**
 * Created by andreribeiro on 13/07/17.
 */

public interface FavoritosConsultorioServiceListener {

    void onSuccess(List<FavoritosConsultorio> favoritosConsultorios);

    void onFail(Throwable t);
}
