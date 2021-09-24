package br.com.buscadoctor.android.adapter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.manager.UsuarioConvenioManager;
import br.com.buscadoctor.android.model.UsuarioConvenio;
import br.com.buscadoctor.android.service.DatabaseSQLiteService;
import br.com.buscadoctor.android.service.listener.UsuarioConvenioServiceListener;
import br.com.buscadoctor.android.util.Functions;

/**
 * @author Andre
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConveniosUsuarioAdapter extends RecyclerView.Adapter<ConveniosUsuarioAdapter.ViewHolder> {

    private List<UsuarioConvenio> mUsuarioConvenios;

    public ConveniosUsuarioAdapter(List<UsuarioConvenio> usuarioConvenioList) {
        mUsuarioConvenios = usuarioConvenioList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_usuario_convenios_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UsuarioConvenio usuarioConvenio = mUsuarioConvenios.get(position);

        holder.textViewNomeConvenio.setText(usuarioConvenio.getConvenio().getNome());
        holder.textViewConvenioNumber.setText(usuarioConvenio.getNumero());

        holder.imageViewEditConvenio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Functions.isconnected(v.getContext())) {
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.setContentView(R.layout.update_convenio);
                    dialog.setTitle(v.getContext().getString(R.string.update_convenio));
                    dialog.setCancelable(true);

                    final EditText mEditTextRegister = (EditText) dialog.findViewById(R.id.edit_text_convenio_register);

                    final RadioButton mRadioButtonPrincipal = (RadioButton) dialog.findViewById(R.id.radio_button_principal);
                    final RadioButton mRadioButtonDepdendente = (RadioButton) dialog.findViewById(R.id.radio_button_dependente);

                    Button mButtonSave = (Button) dialog.findViewById(R.id.btn_save);
                    Button mButtonCancel = (Button) dialog.findViewById(R.id.btn_cancel);

                    mEditTextRegister.setText(usuarioConvenio.getNumero());

                    if (usuarioConvenio.getTipo() != null) {

                        if (usuarioConvenio.getTipo().equals("Titular")) {
                            mRadioButtonPrincipal.setChecked(true);
                        } else if (usuarioConvenio.getTipo().equals("Dependente")) {
                            mRadioButtonDepdendente.setChecked(true);
                        }
                    }

                    mButtonSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View view) {
                            usuarioConvenio.setNumero(mEditTextRegister.getText().toString());
                            if (mRadioButtonPrincipal.isChecked()) {
                                usuarioConvenio.setTipo("Titular");

                            } else if (mRadioButtonDepdendente.isChecked()) {
                                usuarioConvenio.setTipo("Dependente");
                            }

                            UsuarioConvenioManager usuarioConvenioManager = new UsuarioConvenioManager(view.getContext());
                            usuarioConvenioManager.editUsuarioConvenio(usuarioConvenio, new UsuarioConvenioServiceListener() {
                                @Override
                                public void onSuccess(List<UsuarioConvenio> usuarioConvenioList) {
                                }

                                @Override
                                public void onSuccess(UsuarioConvenio usuarioConvenio) {
                                    DatabaseSQLiteService sqLiteService = new DatabaseSQLiteService(view.getContext());
                                    sqLiteService.updateUsuarioConvenio(usuarioConvenio);
                                    update(sqLiteService.getAllUsuarioconvenios());
                                    sqLiteService.close();

                                    dialog.dismiss();
                                    Toast.makeText(view.getContext(), view.getContext().getString(R.string.convenio_changed_successfully), Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onSuccess(int status) {
                                }

                                @Override
                                public void onFail(Throwable t) {
                                }
                            });
                        }
                    });

                    mButtonCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });

                    dialog.show();

                } else {
                    Toast.makeText(v.getContext(), v.getContext().getString(R.string.conection_problem), Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.imageViewRemoveConvenio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(v.getContext().getString(R.string.confirm_remove_convenio));
                builder.setCancelable(true);

                builder.setPositiveButton(R.string.confirm_action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UsuarioConvenioManager usuarioConvenioManager = new UsuarioConvenioManager(v.getContext());
                        usuarioConvenioManager.deleteUsuarioConvenio(usuarioConvenio, new UsuarioConvenioServiceListener() {
                            @Override
                            public void onSuccess(List<UsuarioConvenio> usuarioConvenioList) {
                            }

                            @Override
                            public void onSuccess(UsuarioConvenio usuarioConvenio) {
                            }

                            @Override
                            public void onSuccess(int status) {
                                DatabaseSQLiteService sqLiteService = new DatabaseSQLiteService(v.getContext());
                                sqLiteService.deleteUsuarioConvenio(usuarioConvenio.getId());
                                update(sqLiteService.getAllUsuarioconvenios());
                                sqLiteService.close();
                                Toast.makeText(v.getContext(), v.getContext().getString(R.string.convenio_removed_successfully), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFail(Throwable t) {
                                Toast.makeText(v.getContext(), v.getContext().getString(R.string.conection_problem), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDioalig = builder.create();
                alertDioalig.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsuarioConvenios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNomeConvenio;
        private TextView textViewConvenioNumber;
        private ImageView imageViewRemoveConvenio;
        private ImageView imageViewEditConvenio;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNomeConvenio = (TextView) itemView.findViewById(R.id.text_view_nome_convenio);
            textViewConvenioNumber = (TextView) itemView.findViewById(R.id.text_view_convenio_number);
            imageViewEditConvenio = (ImageView) itemView.findViewById(R.id.image_edit_card);
            imageViewRemoveConvenio = (ImageView) itemView.findViewById(R.id.image_remover_card);

        }
    }

    public void update(List<UsuarioConvenio> data) {
        mUsuarioConvenios.clear();
        mUsuarioConvenios.addAll(data);
        notifyDataSetChanged();
    }
}