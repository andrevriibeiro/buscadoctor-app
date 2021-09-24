package br.com.buscadoctor.android.service.listener;

import java.util.List;

import br.com.buscadoctor.android.model.FavoritosEspecialista;

/**
 * Created by andreribeiro on 15/07/17.
 */

public interface FavoritosEspecialistaServiceListener {

    void onSuccess(List<FavoritosEspecialista> favoritosEspecialistas);

    void onFail(Throwable t);
}
