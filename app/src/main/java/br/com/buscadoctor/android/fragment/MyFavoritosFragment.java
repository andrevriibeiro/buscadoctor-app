package br.com.buscadoctor.android.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.adapter.MeusFavoritosAdapter;
import br.com.buscadoctor.android.manager.FavoritosConsultorioManager;
import br.com.buscadoctor.android.manager.FavoritosEspecialistaManager;
import br.com.buscadoctor.android.model.FavoritosConsultorio;
import br.com.buscadoctor.android.model.FavoritosEspecialista;
import br.com.buscadoctor.android.model.dto.FavoritosDTO;
import br.com.buscadoctor.android.service.listener.FavoritosConsultorioServiceListener;
import br.com.buscadoctor.android.service.listener.FavoritosEspecialistaServiceListener;
import br.com.buscadoctor.android.util.Functions;

/**
 * @author Andre
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyFavoritosFragment extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;
    private MeusFavoritosAdapter myFavoritosAdapter;
    private int mUsuerID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_favoritos, container, false);

        final List<FavoritosDTO> mFavoritosDTOList = new ArrayList<>();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_favoritos_recyclerView);

        mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        if (!Functions.isconnected(view.getContext())) {
            //DatabaseSQLiteService sqLiteService = new DatabaseSQLiteService(view.getContext());
            //mConveniosUsuarioAdapter = new ConveniosUsuarioAdapter(sqLiteService.getAllUsuarioconvenios());
            //recyclerView.setAdapter(mConveniosUsuarioAdapter);
            //sqLiteService.close();
        } else {
            mUsuerID = Functions.getUserId(view.getContext());

            /** GET CONSULTORIOS FAVORITOS **/
            FavoritosConsultorioManager favoritosConsultorioManager = new FavoritosConsultorioManager(view.getContext());
            favoritosConsultorioManager.getFavoritosConsultorios(mUsuerID, new FavoritosConsultorioServiceListener() {
                @Override
                public void onSuccess(List<FavoritosConsultorio> favoritosConsultorios) {
                    if (!favoritosConsultorios.isEmpty()) {
                        for (FavoritosConsultorio f : favoritosConsultorios) {
                            FavoritosDTO favoritosDTO = new FavoritosDTO();
                            favoritosDTO.setConsultorio(f.getConsultorio());
                            favoritosDTO.setTipo(f.getConsultorio().getTipo().getNome());
                            mFavoritosDTOList.add(favoritosDTO);
                        }
                    }
                }

                @Override
                public void onFail(Throwable t) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(getString(R.string.message_alert));
                    builder.setMessage(t.getMessage());
                    builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            });


            /** GET ESPECIALISTAS FAVORITOS **/
            FavoritosEspecialistaManager favoritosEspecialistaManager = new FavoritosEspecialistaManager(view.getContext());
            favoritosEspecialistaManager.getFavoritosEspecialistas(mUsuerID, new FavoritosEspecialistaServiceListener() {
                @Override
                public void onSuccess(List<FavoritosEspecialista> favoritosEspecialistas) {
                    if (!favoritosEspecialistas.isEmpty()) {
                        for (FavoritosEspecialista f : favoritosEspecialistas) {
                            FavoritosDTO favoritosDTO = new FavoritosDTO();
                            favoritosDTO.setEspecialista(f.getEspecialista());
                            favoritosDTO.setTipo("Especialista");
                            mFavoritosDTOList.add(favoritosDTO);
                        }
                    }
                }

                @Override
                public void onFail(Throwable t) {

                }
            });

            myFavoritosAdapter = new MeusFavoritosAdapter(mFavoritosDTOList);
            recyclerView.setAdapter(myFavoritosAdapter);
        }

        return view;
    }
}