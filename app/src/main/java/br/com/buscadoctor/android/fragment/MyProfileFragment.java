package br.com.buscadoctor.android.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.manager.UsuarioManager;
import br.com.buscadoctor.android.model.Usuario;
import br.com.buscadoctor.android.service.DatabaseSQLiteService;
import br.com.buscadoctor.android.service.listener.UsuarioServiceListener;
import br.com.buscadoctor.android.util.Functions;
import br.com.buscadoctor.android.util.Mask;

/**
 * @author Andre
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyProfileFragment extends Fragment implements View.OnClickListener {

    private EditText mEditTextFullName;
    private EditText mEditTextCellphone;
    private EditText mEditTextBirthday;
    private EditText mEditTextCpf;
    private EditText mEditTextEmail;
    private EditText mEditTextResidencialPhone;
    private RadioButton mRadioButtonF;
    private RadioButton mRadioButtonM;
    private Button mButtonSave;
    private Usuario mUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mEditTextFullName = (EditText) view.findViewById(R.id.edit_text_full_name);
        mEditTextCellphone = (EditText) view.findViewById(R.id.edit_text_cellphone);
        mEditTextCellphone.addTextChangedListener(Mask.insert("(##)#####-####", mEditTextCellphone));
        mEditTextBirthday = (EditText) view.findViewById(R.id.edit_text_birthday);
        mEditTextBirthday.addTextChangedListener(Mask.insert("##/##/####", mEditTextBirthday));
        mEditTextCpf = (EditText) view.findViewById(R.id.edit_text_cpf);
        mEditTextCpf.addTextChangedListener(Mask.insert("###.###.###.##", mEditTextCpf));
        mEditTextEmail = (EditText) view.findViewById(R.id.edit_text_email);
        mEditTextResidencialPhone = (EditText) view.findViewById(R.id.edit_text_telefone);
        mEditTextResidencialPhone.addTextChangedListener(Mask.insert("(##)####-####", mEditTextResidencialPhone));

        mRadioButtonF = (RadioButton) view.findViewById(R.id.radio_button_female);
        mRadioButtonM = (RadioButton) view.findViewById(R.id.radio_button_male);

        mButtonSave = (Button) view.findViewById(R.id.save_button);
        mButtonSave.setOnClickListener(this);

        if (!Functions.isconnected(view.getContext())) {
            DatabaseSQLiteService sqLiteService = new DatabaseSQLiteService(view.getContext());
            mUser = sqLiteService.getUsuario();

            mEditTextFullName.setText(mUser.getNome());
            mEditTextCellphone.setText(mUser.getCelular());
            mEditTextCpf.setText(mUser.getCpf());
            mEditTextEmail.setText(mUser.getEmail());
            mEditTextResidencialPhone.setText(mUser.getTelefoneresidencia());

            if (mUser.getSexo().equals("Feminino")) {
                mRadioButtonF.setChecked(true);
            } else {
                if (mUser.getSexo().equals("Masculino")) {
                    mRadioButtonM.setChecked(true);
                }
            }

            mEditTextBirthday.setText(Functions.getDateFormat(mUser.getBirthday()));
        } else {
            UsuarioManager usuarioManager = new UsuarioManager(view.getContext());
            usuarioManager.getUsuario(Functions.getUserId(view.getContext()), new UsuarioServiceListener() {
                @Override
                public void onSuccess(Usuario usuario) {
                    DatabaseSQLiteService databaseSQLiteService = new DatabaseSQLiteService(view.getContext());
                    databaseSQLiteService.updateUsuario(usuario);
                    databaseSQLiteService.close();
                    mUser = usuario;

                    mEditTextFullName.setText(mUser.getNome());
                    mEditTextCellphone.setText(mUser.getCelular());
                    mEditTextCpf.setText(mUser.getCpf());
                    mEditTextEmail.setText(mUser.getEmail());
                    mEditTextResidencialPhone.setText(mUser.getTelefoneresidencia());

                    if (mUser.getSexo().equals("Feminino")) {
                        mRadioButtonF.setChecked(true);
                    } else {
                        if (mUser.getSexo().equals("Masculino")) {
                            mRadioButtonM.setChecked(true);
                        }
                    }

                    mEditTextBirthday.setText(Functions.getDateFormat(mUser.getBirthday()));
                }

                @Override
                public void onFail(Throwable t) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                    alert.setTitle(getString(R.string.message_alert));
                    alert.setMessage(t.getMessage());
                    alert.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert.show();
                }
            });
        }

        return view;
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.save_button:
                if (Functions.isconnected(v.getContext())) {
                    String nome = mEditTextFullName.getText().toString();
                    String celular = mEditTextCellphone.getText().toString();
                    String cpf = mEditTextCpf.getText().toString();
                    String email = mEditTextEmail.getText().toString();
                    String telefoneresidencial = mEditTextResidencialPhone.getText().toString();
                    String nascimento = mEditTextBirthday.getText().toString();
                    String sexo = "";

                    if (mRadioButtonF.isChecked()) {
                        sexo = "Feminino";
                    } else if (mRadioButtonM.isChecked()) {
                        sexo = "Masculino";
                    }

                    if (!Functions.isconnected(v.getContext())) {
                        Toast.makeText(v.getContext(), v.getContext().getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                    } else {
                        if (!nome.isEmpty() && !celular.isEmpty() && !nascimento.isEmpty() && !sexo.isEmpty()) {
                            final ProgressDialog progressDialog = new ProgressDialog(v.getContext());
                            progressDialog.setMessage(getString(R.string.update_profile));
                            progressDialog.show();

                            mUser.setNome(nome);
                            mUser.setCelular(celular);
                            mUser.setEmail(email);
                            mUser.setTelefoneresidencia(telefoneresidencial);
                            mUser.setBirthday(Functions.getDate(nascimento));
                            mUser.setSexo(sexo);

                            if (cpf.isEmpty())
                                mUser.setCpf(null);
                            else
                                mUser.setCpf(cpf);

                            UsuarioManager usuarioManager = new UsuarioManager(v.getContext());
                            usuarioManager.updateUsuario(mUser, new UsuarioServiceListener() {

                                @Override
                                public void onSuccess(Usuario usuario) {
                                    DatabaseSQLiteService databaseSQLiteService = new DatabaseSQLiteService(v.getContext());
                                    databaseSQLiteService.updateUsuario(usuario);
                                    databaseSQLiteService.close();
                                    progressDialog.dismiss();

                                    Toast.makeText(v.getContext(), v.getContext().getString(R.string.update_successfully), Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFail(Throwable t) {
                                    progressDialog.dismiss();
                                    AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                                    alert.setTitle(getString(R.string.error_usuario_update));
                                    alert.setMessage(t.getMessage());
                                    alert.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    alert.show();
                                }
                            });
                        } else {
                            if (nome.isEmpty()) {
                                mEditTextFullName.setError(getString(R.string.error_field));
                                mEditTextFullName.requestFocus();
                            }
                            if (celular.isEmpty()) {
                                mEditTextCellphone.setError(getString(R.string.error_field));
                                mEditTextCellphone.requestFocus();
                            }
                            if (nascimento.isEmpty()) {
                                mEditTextBirthday.setError(getString(R.string.error_field));
                                mEditTextBirthday.requestFocus();
                            }
                            if (sexo.isEmpty()) {
                                mRadioButtonM.setError(getString(R.string.error_field));
                                mRadioButtonM.requestFocus();
                                Toast.makeText(v.getContext(), v.getContext().getString(R.string.sexo_required), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    break;
                } else {
                    Toast.makeText(v.getContext(), v.getContext().getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                }
        }
    }
}