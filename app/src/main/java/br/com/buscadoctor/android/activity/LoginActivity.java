package br.com.buscadoctor.android.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.manager.UsuarioConvenioManager;
import br.com.buscadoctor.android.manager.UsuarioManager;
import br.com.buscadoctor.android.model.Usuario;
import br.com.buscadoctor.android.model.UsuarioConvenio;
import br.com.buscadoctor.android.service.DatabaseSQLiteService;
import br.com.buscadoctor.android.service.listener.UsuarioConvenioServiceListener;
import br.com.buscadoctor.android.service.listener.UsuarioServiceListener;
import br.com.buscadoctor.android.util.Functions;

public class LoginActivity extends AppCompatActivity {

    private static final String PREF_NAME = "BuscaDoctor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText editTextUser = (EditText) findViewById(R.id.edit_text_user);
        final EditText editTextPassword = (EditText) findViewById(R.id.edit_text_password);

        Button buttonEnter = (Button) findViewById(R.id.button_enter);

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Functions.isconnected(getApplicationContext())) {
                    Toast.makeText(v.getContext(), v.getContext().getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                } else {
                    String username = editTextUser.getText().toString();
                    String password = editTextPassword.getText().toString();
                    if (username.isEmpty() && password.isEmpty()) {
                        editTextUser.setError(getString(R.string.error_field));
                        editTextPassword.setError(getString(R.string.error_field));
                    } else if (username.isEmpty()) {
                        editTextUser.setError(getString(R.string.error_field));
                    } else if (password.isEmpty()) {
                        editTextPassword.setError(getString(R.string.error_field));
                    } else {
                        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                        progressDialog.setMessage(getString(R.string.message_login));
                        progressDialog.show();

                        Map<String, String> request = new HashMap<>();
                        request.put("user", username);
                        request.put("senha", password);

                        UsuarioManager usuarioManager = new UsuarioManager(getApplication());
                        usuarioManager.login(request, new UsuarioServiceListener() {
                            @Override
                            public void onSuccess(Usuario usuario) {
                                Functions.saveUser(getApplicationContext(), true);
                                Functions.saveUserId(LoginActivity.this, usuario.getId());
                                DatabaseSQLiteService databaseSQLiteService = new DatabaseSQLiteService(LoginActivity.this);
                                databaseSQLiteService.insertUsuario(usuario);
                                databaseSQLiteService.close();

                                loadUsuarioConvenioIntoSQLite();
                                loadEspecialistasFavoritosIntoSQLite();
                                loadConsultoriosFavoritosIntoSQLite();

                                progressDialog.dismiss();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFail(Throwable t) {
                                progressDialog.dismiss();
                                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
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
                }
            }
        });

        TextView textViewCreateAccount = (TextView) findViewById(R.id.text_view_register);

        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Esse metodo é responsavel por carregar
     * todos os convênios do usuário no momento do login
     */
    private void loadUsuarioConvenioIntoSQLite() {
        UsuarioConvenioManager usuarioConvenioManager = new UsuarioConvenioManager(LoginActivity.this);
        Map<String, Integer> request = new HashMap<>();
        request.put("usuario", Functions.getUserId(LoginActivity.this));

        usuarioConvenioManager.getUsuarioConvenios(request, new UsuarioConvenioServiceListener() {
            @Override
            public void onSuccess(List<UsuarioConvenio> usuarioConvenioList) {
                if (!usuarioConvenioList.isEmpty()) {
                    DatabaseSQLiteService sqLiteService = new DatabaseSQLiteService(LoginActivity.this);
                    sqLiteService.insertUsuarioConvenios(usuarioConvenioList);
                    sqLiteService.close();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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

    /**
     * Esse metodo é responsavel por carregar
     * todos os especialistas favoritos do usuário no momento do login
     */
    private void loadEspecialistasFavoritosIntoSQLite() {

    }

    /**
     * Esse metodo é responsavel por carregar
     * todos os consultórios favoritos do usuário no momento do login
     */
    private void loadConsultoriosFavoritosIntoSQLite() {

    }
}