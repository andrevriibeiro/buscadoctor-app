package br.com.buscadoctor.android.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.adapter.ConveniosUsuarioAdapter;
import br.com.buscadoctor.android.manager.ConvenioManager;
import br.com.buscadoctor.android.manager.UsuarioConvenioManager;
import br.com.buscadoctor.android.model.Convenio;
import br.com.buscadoctor.android.model.Usuario;
import br.com.buscadoctor.android.model.UsuarioConvenio;
import br.com.buscadoctor.android.service.DatabaseSQLiteService;
import br.com.buscadoctor.android.service.listener.ConvenioServiceListener;
import br.com.buscadoctor.android.service.listener.UsuarioConvenioServiceListener;
import br.com.buscadoctor.android.util.Functions;

/**
 * @author Andre
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyConveniosFragment extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;
    private ConveniosUsuarioAdapter mConveniosUsuarioAdapter;
    private int mUsuerID;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my_convenios, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_convenios_recyclerView);
        UsuarioConvenioManager usuarioConvenioManager = new UsuarioConvenioManager(view.getContext());

        final FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.VISIBLE);

        mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        if (!Functions.isconnected(view.getContext())) {
            DatabaseSQLiteService sqLiteService = new DatabaseSQLiteService(view.getContext());
            mConveniosUsuarioAdapter = new ConveniosUsuarioAdapter(sqLiteService.getAllUsuarioconvenios());
            recyclerView.setAdapter(mConveniosUsuarioAdapter);
            sqLiteService.close();
        } else {
            mUsuerID = Functions.getUserId(view.getContext());

            Map<String, Integer> request = new HashMap<>();
            request.put("usuario", mUsuerID);

            usuarioConvenioManager.getUsuarioConvenios(request, new UsuarioConvenioServiceListener() {
                @Override
                public void onSuccess(List<UsuarioConvenio> usuarioConvenioList) {
                    if (!usuarioConvenioList.isEmpty()) {
                        DatabaseSQLiteService sqLiteService = new DatabaseSQLiteService(view.getContext());
                        sqLiteService.insertUsuarioConvenios(usuarioConvenioList);
                        sqLiteService.close();
                        mConveniosUsuarioAdapter = new ConveniosUsuarioAdapter(usuarioConvenioList);
                        recyclerView.setAdapter(mConveniosUsuarioAdapter);
                    }
                }

                @Override
                public void onSuccess(UsuarioConvenio usuarioConvenio) {
                }

                @Override
                public void onSuccess(int status) {
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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Functions.isconnected(v.getContext())) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle(getString(R.string.add_new_convenio));
                    final View view = inflater.inflate(R.layout.new_convenio, null);

                    final AutoCompleteTextView mAutoCompleteTextViewConvenio = (AutoCompleteTextView) view.findViewById(R.id.auto_complete_text_view_convenio);
                    final EditText mEditTextRegister = (EditText) view.findViewById(R.id.edit_text_convenio_register);
                    final RadioGroup mTipoConvenio = (RadioGroup) view.findViewById(R.id.radio_button_convenio_type);

                    final ArrayList<String> list = new ArrayList<>();
                    final List<Convenio> convenioList = new ArrayList<>();

                    ConvenioManager convenioManager = new ConvenioManager(view.getContext());
                    convenioManager.getConvenios(new ConvenioServiceListener() {
                        @Override
                        public void onSuccess(List<Convenio> convenios) {
                            for (int i = 0; i < convenios.size(); i++) {
                                list.add(convenios.get(i).getNome());
                                convenioList.add(convenios.get(i));
                            }
                        }

                        @Override
                        public void onFail(Throwable t) {
                        }
                    });

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.select_dialog_item, list);
                    mAutoCompleteTextViewConvenio.setAdapter(adapter);

                    builder.setView(view);

                    builder.setPositiveButton(getString(R.string.salve_button), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseSQLiteService databaseSQLiteService = new DatabaseSQLiteService(view.getContext());
                            Usuario usuario;
                            usuario = databaseSQLiteService.getUsuario();
                            databaseSQLiteService.close();
                            UsuarioConvenio usuarioConvenio = new UsuarioConvenio();
                            usuarioConvenio.setUsuario(usuario);
                            usuarioConvenio.setNumero(mEditTextRegister.getText().toString());

                            String convenioName = mAutoCompleteTextViewConvenio.getText().toString();

                            for (int i = 0; i < convenioList.size(); i++) {
                                if (convenioList.get(i).getNome().equals(convenioName)) {
                                    Convenio con = new Convenio();
                                    con.setId(convenioList.get(i).getId());
                                    con.setNome(convenioList.get(i).getNome());
                                    usuarioConvenio.setConvenio(con);
                                }
                            }

                            switch (mTipoConvenio.getCheckedRadioButtonId()) {
                                case R.id.radio_button_principal:
                                    usuarioConvenio.setTipo(getString(R.string.titular));
                                    break;

                                case R.id.radio_button_dependente:
                                    usuarioConvenio.setTipo(getString(R.string.dependente));
                                    break;
                            }

                            UsuarioConvenioManager usuarioConvenioManager = new UsuarioConvenioManager(view.getContext());
                            usuarioConvenioManager.createUsuarioConvenio(usuarioConvenio, new UsuarioConvenioServiceListener() {
                                @Override
                                public void onSuccess(List<UsuarioConvenio> usuarioConvenioList) {
                                }

                                @Override
                                public void onSuccess(UsuarioConvenio usuarioConvenio) {
                                    DatabaseSQLiteService databaseSQLiteService = new DatabaseSQLiteService(view.getContext());
                                    databaseSQLiteService.insertUsuarioConvenio(usuarioConvenio);
                                    List<UsuarioConvenio> data = databaseSQLiteService.getAllUsuarioconvenios();
                                    databaseSQLiteService.close();
                                    mConveniosUsuarioAdapter.update(data);
                                    Toast.makeText(view.getContext(), view.getContext().getString(R.string.convenio_registred_successfully), Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onSuccess(int status) {
                                }

                                @Override
                                public void onFail(Throwable t) {
                                    Toast.makeText(view.getContext(), view.getContext().getString(R.string.conection_problem), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });

                    builder.setNegativeButton(getString(R.string.cancel_button), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else {
                    Toast.makeText(v.getContext(), v.getContext().getString(R.string.conection_problem), Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}