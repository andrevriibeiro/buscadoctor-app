package br.com.buscadoctor.android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.adapter.EspecialidadeAdapter;
import br.com.buscadoctor.android.adapter.EspecialistaAdapter;
import br.com.buscadoctor.android.manager.EspecialidadeManager;
import br.com.buscadoctor.android.manager.EspecialistaManager;
import br.com.buscadoctor.android.model.Especialidade;
import br.com.buscadoctor.android.model.Especialista;
import br.com.buscadoctor.android.model.dto.EspecialistaDTO;
import br.com.buscadoctor.android.service.listener.EspecialidadeServiceListener;
import br.com.buscadoctor.android.service.listener.EspecialistaServiceListener;
import br.com.buscadoctor.android.util.listener.RecyclerItemClickListener;

public class SearchActivity extends AppCompatActivity {

    private Especialidade especialidade;

    private List<Especialidade> especialidades;

    private EspecialidadeAdapter especialidadeAdapter;

    private RecyclerView recyclerViewEspecialidade;
    private RecyclerView recyclerViewEspecialista;

    private RelativeLayout relativeLayoutSearch;
    private RelativeLayout relativeLayoutEmpty;

    private EspecialidadeManager especialidadeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView textViewEspecialidade = (TextView) findViewById(R.id.edit_text_especialidade);

        relativeLayoutSearch = (RelativeLayout) findViewById(R.id.relative_layout_search);
        relativeLayoutEmpty = (RelativeLayout) findViewById(R.id.relative_layout_empty_especialista);

        recyclerViewEspecialidade = (RecyclerView) findViewById(R.id.recycler_view_especialidade);
        recyclerViewEspecialista = (RecyclerView) findViewById(R.id.recycler_view_especialista);

        RecyclerView.LayoutManager layoutManagerEspecialidade = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManagerEspecialista = new LinearLayoutManager(this);

        recyclerViewEspecialidade.setLayoutManager(layoutManagerEspecialidade);
        recyclerViewEspecialista.setLayoutManager(layoutManagerEspecialista);

        especialidadeManager = new EspecialidadeManager(getApplicationContext());
        especialidadeManager.getEspecialidades(new EspecialidadeServiceListener() {
            @Override
            public void onSuccess(final List<Especialidade> e) {
                especialidades = e;
                especialidadeAdapter = new EspecialidadeAdapter(especialidades);
                recyclerViewEspecialidade.setAdapter(especialidadeAdapter);

                recyclerViewEspecialidade.addOnItemTouchListener(new RecyclerItemClickListener(SearchActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        especialidade = especialidades.get(position);

                        textViewEspecialidade.setText(especialidade.getEspecialidade());
                    }
                }));
            }

            @Override
            public void onFail(Throwable t) {
            }
        });

        textViewEspecialidade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                loadData(s.toString());
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setVisibility(View.GONE);
                recyclerViewEspecialista.setVisibility(View.GONE);
                relativeLayoutSearch.setVisibility(View.VISIBLE);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.button_aply);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (especialidade != null) {
                    fab.setVisibility(View.VISIBLE);
                    recyclerViewEspecialista.setVisibility(View.VISIBLE);
                    relativeLayoutSearch.setVisibility(View.GONE);

                    EspecialistaManager especialistaManager = new EspecialistaManager(getApplicationContext());
                    especialistaManager.getEspecialistaByEspecialidade(especialidade.getId(), new EspecialistaServiceListener() {
                        @Override
                        public void onSuccess(final List<EspecialistaDTO> especialistas) {
                            if (!especialistas.isEmpty()) {
                                relativeLayoutEmpty.setVisibility(View.GONE);

                                EspecialistaAdapter especialistaAdapter = new EspecialistaAdapter(especialistas);
                                recyclerViewEspecialista.setAdapter(especialistaAdapter);

                                recyclerViewEspecialista.addOnItemTouchListener(new RecyclerItemClickListener(SearchActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        EspecialistaDTO especialistaDTO = especialistas.get(position);
                                        Intent intent = new Intent(SearchActivity.this, EspecialistaActivity.class);
                                        intent.putExtra("especialista", especialistaDTO.getEspecialista());
                                        intent.putExtra("consultorio", especialistaDTO.getConsultorio());
                                        intent.putExtra("especialidadeProcurada", especialidade);
                                        intent.putParcelableArrayListExtra("especialidades", especialistaDTO.getEspecialidades());
                                        startActivity(intent);
                                    }
                                }));
                            } else {
                                relativeLayoutEmpty.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onSuccess(Especialista especialista) {
                        }

                        @Override
                        public void onFail(Throwable t) {
                        }
                    });
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                    builder.setTitle(getString(R.string.message_alert));
                    builder.setMessage(getString(R.string.error_especialidade_select));
                    builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    public void loadData(String query) {
        final List<Especialidade> mQueryList = new ArrayList<>();

        for (Especialidade especialidade : especialidades) {
            if (especialidade.getEspecialidade().toLowerCase().contains(query)) {
                mQueryList.add(especialidade);
            }
        }

        if (!mQueryList.isEmpty()) {
            especialidades = mQueryList;
            especialidadeAdapter = new EspecialidadeAdapter(especialidades);
            recyclerViewEspecialidade.setAdapter(especialidadeAdapter);
        }
    }
}