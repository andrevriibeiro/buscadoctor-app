package br.com.buscadoctor.android.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.manager.UsuarioManager;
import br.com.buscadoctor.android.model.Usuario;
import br.com.buscadoctor.android.service.listener.UsuarioServiceListener;
import br.com.buscadoctor.android.util.Functions;
import br.com.buscadoctor.android.util.Mask;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextPassword;
    private EditText mEditTextEmail;
    private EditText mEditTextCelular;
    private EditText mEditTextBirthDay;

    private RadioGroup mRadioGroupSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditTextName = (EditText) findViewById(R.id.edit_text_full_name);
        mEditTextPassword = (EditText) findViewById(R.id.edit_text_password);
        mEditTextEmail = (EditText) findViewById(R.id.edit_text_email);
        mEditTextCelular = (EditText) findViewById(R.id.edit_text_telefone);
        mEditTextBirthDay = (EditText) findViewById(R.id.edit_text_birthday);

        mEditTextCelular.addTextChangedListener(Mask.insert("(##)#####-####", mEditTextCelular));
        mEditTextBirthDay.addTextChangedListener(Mask.insert("##/##/####", mEditTextBirthDay));

        mEditTextBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);

                        mEditTextBirthDay.setText(Functions.getDateFormat(calendar.getTime()));
                    }
                };

                DatePickerDialog pickerDialog = new DatePickerDialog(RegisterActivity.this, datePickerDialog, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                pickerDialog.show();
            }
        });

        mRadioGroupSex = (RadioGroup) findViewById(R.id.radio_group_genre);

        Button buttonRegister = (Button) findViewById(R.id.button_register);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = mEditTextName.getText().toString();
                String password = mEditTextPassword.getText().toString();
                String email = mEditTextEmail.getText().toString();
                String celular = mEditTextCelular.getText().toString();
                String birthDay = mEditTextBirthDay.getText().toString();

                int selected = mRadioGroupSex.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selected);
                String sex = "";
                if (radioButton != null) {
                    sex = radioButton.getText().toString();
                }

                if (!Functions.isconnected(RegisterActivity.this)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle(getString(R.string.message_alert));
                    builder.setMessage(getString(R.string.error_connection));
                    builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else if (!nome.isEmpty() & !password.isEmpty() & !email.isEmpty() & !birthDay.isEmpty()) {
                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setBirthday(Functions.getDate(birthDay));
                    usuario.setEmail(email);
                    usuario.setCelular(celular);
                    usuario.setSenha(password);
                    usuario.setSexo(sex);
                    usuario.setAcesso(true);

                    UsuarioManager usuarioManager = new UsuarioManager(getApplication());
                    usuarioManager.createUser(usuario, new UsuarioServiceListener() {
                        @Override
                        public void onSuccess(Usuario usuario) {
                            Functions.saveUser(RegisterActivity.this, true);
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFail(Throwable t) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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
                    if (nome.isEmpty()) {
                        mEditTextName.setError(getString(R.string.error_field));
                    }
                    if (password.isEmpty()) {
                        mEditTextPassword.setError(getString(R.string.error_field));
                    }

                    if (email.isEmpty()) {
                        mEditTextEmail.setError(getString(R.string.error_field));
                    }

                    if (birthDay.isEmpty()) {
                        mEditTextBirthDay.setError(getString(R.string.error_field));
                    }
                }
            }
        });
    }
}