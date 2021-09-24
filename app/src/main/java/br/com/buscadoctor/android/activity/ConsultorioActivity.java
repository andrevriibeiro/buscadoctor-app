package br.com.buscadoctor.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.Consultorio;
import br.com.buscadoctor.android.util.Functions;

public class ConsultorioActivity extends AppCompatActivity {

    private boolean changeImage = false;

    private Consultorio mConsultorio;

    private TextView mTextViewCidade;
    private TextView mTextViewLogradouro;
    private TextView mTextViewComplemento;

    private BottomNavigationView mBottomMenuNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationBottonMenuSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            Intent intent = new Intent(ConsultorioActivity.this, MainActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_my_consultas:
                            break;

                        case R.id.navigation_perfil:
                            Intent i = new Intent(ConsultorioActivity.this, MyProfileActivity.class);
                            startActivity(i);
                            break;
                    }
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultorio);

        mConsultorio = getIntent().getParcelableExtra("consultorio");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mConsultorio.getNome());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBottomMenuNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomMenuNavigationView.setOnNavigationItemSelectedListener(mOnNavigationBottonMenuSelectedListener);

        mTextViewCidade = (TextView) findViewById(R.id.txt_cidade_clinica);
        mTextViewLogradouro = (TextView) findViewById(R.id.txt_end_clinica);
        mTextViewComplemento = (TextView) findViewById(R.id.txt_complemento);

        RelativeLayout relativeLayoutRating1 = (RelativeLayout) findViewById(R.id.relative_layout_coments1);
        RelativeLayout relativeLayoutRating2 = (RelativeLayout) findViewById(R.id.relative_layout_coments2);
        RelativeLayout relativeLayoutRating3 = (RelativeLayout) findViewById(R.id.relative_layout_coments3);
        RelativeLayout relativeLayoutRating4 = (RelativeLayout) findViewById(R.id.relative_layout_coments4);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.favorite_fab);

        if (changeImage) {
            fab.setImageResource(R.drawable.custom_ratingbar_filled);
        } else {
            fab.setImageResource(R.drawable.favoritos_icon);
        }

        mTextViewCidade.setText(mConsultorio.getLogradouro().getCidade().getNome() + " - " +
                mConsultorio.getLogradouro().getCidade().getEstado().getAcronimo());

        mTextViewLogradouro.setText(mConsultorio.getLogradouro().getTipo() + ". " +
                mConsultorio.getLogradouro().getNome() + ", " +
                mConsultorio.getNumero() + " - " +
                mConsultorio.getLogradouro().getBairro());

        mTextViewComplemento.setText(mConsultorio.getComplemento());

        /**
         * Onclick para favoritar o especialista
         *
         * */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!changeImage) {
                    changeImage = true;
                    fab.setImageResource(R.drawable.custom_ratingbar_filled);
                    Toast.makeText(ConsultorioActivity.this, getString(R.string.add_fav), Toast.LENGTH_SHORT).show();
                } else {
                    if (changeImage) {
                        changeImage = false;
                        fab.setImageResource(R.drawable.favoritos_icon);
                        Toast.makeText(ConsultorioActivity.this, getString(R.string.remove_fav), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /**
         * Onclick para acessar o listview dos especialistasConsultorios
         *
         * */
        RelativeLayout relativeLayoutAgendar = (RelativeLayout) findViewById(R.id.relative_layout_agendar);
        relativeLayoutAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ConsultorioActivity.this, "Em construção...", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * Onclick para compartilhar o consultorio
         *
         * */
        RelativeLayout relativeLayoutShare = (RelativeLayout) findViewById(R.id.relative_layout_compartilhar);
        relativeLayoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions.share(ConsultorioActivity.this, builShareMessage());
            }
        });

        /**
         * Onclick para acessar acessar o whatsAPP (depois será trocado para o nosso chat)
         *
         * */
        RelativeLayout relativeLayoutChat = (RelativeLayout) findViewById(R.id.relative_layout_chat);
        relativeLayoutChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions.callWhatsapp(ConsultorioActivity.this);
            }
        });

        /**
         * Onclick para ligar para o consultorio do especialista
         *
         * */
        RelativeLayout relativeLayoutCall = (RelativeLayout) findViewById(R.id.relative_layout_ligar);
        relativeLayoutCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions.makeCall(ConsultorioActivity.this, mConsultorio.getTelefone());
            }
        });

        relativeLayoutRating1.setVisibility(View.INVISIBLE);
        relativeLayoutRating2.setVisibility(View.INVISIBLE);
        relativeLayoutRating3.setVisibility(View.INVISIBLE);
        relativeLayoutRating4.setVisibility(View.INVISIBLE);
    }

    /**
     * Build da mensagem que será enviada pelo compartilhamento
     *
     * */
    private StringBuilder builShareMessage(){

        final StringBuilder str = new StringBuilder("");

        str.append(mConsultorio.getNome());
        str.append("\n");
        str.append(mConsultorio.getTipo().getNome());
        str.append("\n");
        str.append(mConsultorio.getLogradouro().getTipo() + ". ");
        str.append(mConsultorio.getLogradouro().getNome() + ", " + mConsultorio.getNumero());
        str.append("\n");
        str.append(mConsultorio.getLogradouro().getCidade().getNome());
        str.append(" - ");
        str.append(mConsultorio.getLogradouro().getCidade().getEstado().getAcronimo());
        str.append("\n");
        str.append(mConsultorio.getLogradouro().getBairro());
        str.append("\n");
        str.append("\n");
        str.append(mConsultorio.getTelefone());
        str.append("\n");
        str.append("\n");
        str.append("Saiba mais baixando o aplicativo BuscaDoctor - www.buscadoctor.net");

        return str;
    }

    /**
     * Este metodo tem objetivo de validar se o consultorio possui mais avaliaçoes (> 4 avaliações)
     * caso positivo esse metodo irá chamar uma listview populando todas as avaliaçoes na listview
     *
     * */
    public void allRating(View view){
        Toast.makeText(ConsultorioActivity.this, R.string.without_users_rating, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
