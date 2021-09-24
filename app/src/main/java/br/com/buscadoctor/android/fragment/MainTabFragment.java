package br.com.buscadoctor.android.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.activity.EspecialistaActivity;
import br.com.buscadoctor.android.adapter.MainRecyclerAdapter;
import br.com.buscadoctor.android.manager.ConsultorioManager;
import br.com.buscadoctor.android.model.Consultorio;
import br.com.buscadoctor.android.service.listener.ConsultorioServiceListener;
import br.com.buscadoctor.android.util.Functions;
import br.com.buscadoctor.android.util.listener.RecyclerItemClickListener;

public class MainTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        final RecyclerView recyclerViewList = (RecyclerView) view.findViewById(R.id.recycler_view_list);
        recyclerViewList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewList.setLayoutManager(layoutManager);

        final TextView textViewEmpty = (TextView) view.findViewById(R.id.text_view_empty);

        int position = getArguments().getInt("tipo");

        if (Functions.isconnected(getContext())) {
            ConsultorioManager consultorioManager = new ConsultorioManager(getActivity());
            if (position == 0) {
                consultorioManager.getConsultoriosByTipo(3, new ConsultorioServiceListener() {
                    @Override
                    public void onSuccess(List<Consultorio> consultorios) {
                        if (!consultorios.isEmpty()) {
                            textViewEmpty.setVisibility(View.GONE);
                            MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(consultorios);
                            recyclerViewList.setAdapter(mainRecyclerAdapter);

                            recyclerViewList.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    //EspecialistaDTO especialistaDTO = especialistas.get(position);
                                    Intent intent = new Intent(getContext(), EspecialistaActivity.class);
                                    //intent.putExtra("especialista", especialistaDTO.getEspecialista().getId());
                                    //intent.putExtra("consultorio", especialistaDTO.getEspecialista().getId());
                                    //startActivity(intent);
                                }
                            }));
                        } else {
                            textViewEmpty.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onSuccess(Consultorio consultorio) {

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
            } else {
                consultorioManager.getConsultoriosByTipo(1, new ConsultorioServiceListener() {
                    @Override
                    public void onSuccess(List<Consultorio> consultorios) {
                        if (!consultorios.isEmpty()) {
                            textViewEmpty.setVisibility(View.GONE);
                            MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(consultorios);
                            recyclerViewList.setAdapter(mainRecyclerAdapter);

                            recyclerViewList.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    //EspecialistaDTO especialistaDTO = especialistas.get(position);
                                    Intent intent = new Intent(getContext(), EspecialistaActivity.class);
                                    //intent.putExtra("especialista", especialistaDTO.getEspecialista().getId());
                                    //intent.putExtra("consultorio", especialistaDTO.getEspecialista().getId());
                                    startActivity(intent);
                                }
                            }));
                        } else {
                            textViewEmpty.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onSuccess(Consultorio consultorio) {
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
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(getString(R.string.message_alert));
            builder.setMessage(getString(R.string.error_connection));
            builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }

        return view;
    }
}