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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.Consultorio;
import br.com.buscadoctor.android.model.Especialidade;
import br.com.buscadoctor.android.model.Especialista;
import br.com.buscadoctor.android.util.Functions;
import de.hdodenhof.circleimageview.CircleImageView;

public class EspecialistaActivity extends AppCompatActivity {

    private boolean changeImage = false;

    private Especialista mEspecialista;
    private Consultorio mConsultorio;
    private Especialidade mEspecialidadeProcurada;

    private ArrayList<Especialidade> mEspecialidades;

    private TextView mTextViewEspecialistaName;
    private TextView mTextViewEspecialidadeName;
    private TextView mTextViewCidade;
    private TextView mTextViewLogradouro;
    private TextView mTextViewComplemento;

    private CircleImageView mCircleImageView;

    private BottomNavigationView mBottomMenuNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationBottonMenuSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            Intent intent = new Intent(EspecialistaActivity.this, MainActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_my_consultas:
                            break;

                        case R.id.navigation_perfil:
                            Intent i = new Intent(EspecialistaActivity.this, MyProfileActivity.class);
                            startActivity(i);
                            break;
                    }
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialista);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mEspecialista = getIntent().getParcelableExtra("especialista");
        mConsultorio = getIntent().getParcelableExtra("consultorio");
        mEspecialidadeProcurada = getIntent().getParcelableExtra("especialidadeProcurada");
        mEspecialidades = getIntent().getParcelableArrayListExtra("especialidades");

        mBottomMenuNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        mTextViewEspecialistaName = (TextView) findViewById(R.id.cat_title);
        mTextViewEspecialidadeName = (TextView) findViewById(R.id.especialidade_name);
        mTextViewCidade = (TextView) findViewById(R.id.txt_cidade_clinica);
        mTextViewLogradouro = (TextView) findViewById(R.id.txt_end_clinica);
        mTextViewComplemento = (TextView) findViewById(R.id.txt_complemento);

        mCircleImageView = (CircleImageView) findViewById(R.id.cat_avatar);

        mBottomMenuNavigationView.setOnNavigationItemSelectedListener(mOnNavigationBottonMenuSelectedListener);

        RelativeLayout relativeLayoutRating1 = (RelativeLayout) findViewById(R.id.relative_layout_coments1);
        RelativeLayout relativeLayoutRating2 = (RelativeLayout) findViewById(R.id.relative_layout_coments2);
        RelativeLayout relativeLayoutRating3 = (RelativeLayout) findViewById(R.id.relative_layout_coments3);
        RelativeLayout relativeLayoutRating4 = (RelativeLayout) findViewById(R.id.relative_layout_coments4);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.favorite_fab);

        String lastName = mEspecialista.getNome().substring(mEspecialista.getNome().lastIndexOf(" ") + 1);
        int firstSpace = mEspecialista.getNome().indexOf(" ");
        String firstName = mEspecialista.getNome().substring(0, firstSpace);

        if (mEspecialista.getSexo().equals("masculino")) {
            mCircleImageView.setImageResource(R.mipmap.ic_doutor);
            mTextViewEspecialistaName.setText("Dr. " + firstName + " " + lastName);
        } else if (mEspecialista.getSexo().equals("feminino")) {
            mCircleImageView.setImageResource(R.mipmap.ic_doutora);
            mTextViewEspecialistaName.setText("Dra. " + firstName + " " + lastName);
        }

        mTextViewEspecialidadeName.setText(mEspecialidadeProcurada.getEspecialidade());

        mTextViewCidade.setText(mConsultorio.getLogradouro().getCidade().getNome() + " - " +
                                mConsultorio.getLogradouro().getCidade().getEstado().getAcronimo());

        mTextViewLogradouro.setText(mConsultorio.getLogradouro().getTipo() + ". " +
                                    mConsultorio.getLogradouro().getNome() + ", " +
                                    mConsultorio.getNumero() + " - " +
                                    mConsultorio.getLogradouro().getBairro());

        mTextViewComplemento.setText(mConsultorio.getComplemento());

        if (changeImage) {
            fab.setImageResource(R.drawable.custom_ratingbar_filled);
        } else {
            fab.setImageResource(R.drawable.favoritos_icon);
        }

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
                    Toast.makeText(EspecialistaActivity.this, getString(R.string.add_fav), Toast.LENGTH_SHORT).show();
                } else {
                    if (changeImage) {
                        changeImage = false;
                        fab.setImageResource(R.drawable.favoritos_icon);
                        Toast.makeText(EspecialistaActivity.this, getString(R.string.remove_fav), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /**
         * Onclick para acessar a agenda do especialista
         *
         * */
        RelativeLayout relativeLayoutAgendar = (RelativeLayout) findViewById(R.id.relative_layout_agendar);
        relativeLayoutAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AgendaActivity.class);
                intent.putExtra("especialista", mEspecialista);
                intent.putExtra("consultorio", mConsultorio);
                intent.putExtra("especialidadeProcurada", mEspecialidadeProcurada);
                startActivity(intent);
            }
        });

        /**
         * Onclick para compartilhar o especialista
         *
         * */
        RelativeLayout relativeLayoutShare = (RelativeLayout) findViewById(R.id.relative_layout_compartilhar);
        relativeLayoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions.share(EspecialistaActivity.this, builShareMessage(mEspecialista.getSexo()));
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
                Functions.callWhatsapp(EspecialistaActivity.this);
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
               Functions.makeCall(EspecialistaActivity.this, mConsultorio.getTelefone());
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
    private StringBuilder builShareMessage(String sexo){

        final StringBuilder str = new StringBuilder("");

        if(sexo.equals("masculino")){
            str.append("Dr. " + mEspecialista.getNome());

        }else if(sexo.equals("feminino")){
            str.append("Dra. " + mEspecialista.getNome());
        }

        str.append("\n");
        str.append(mEspecialidadeProcurada.getEspecialidade());
        str.append("\n");
        str.append("\n");
        str.append(mConsultorio.getNome());
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
     * Este metodo tem objetivo de validar se o especialista possui mais avaliaçoes (> 4 avaliações)
     * caso positivo esse metodo irá chamar uma listview populando todas as avaliaçoes na listview
     *
     * */
    public void allRating(View view){
        Toast.makeText(EspecialistaActivity.this, R.string.without_users_rating, Toast.LENGTH_SHORT).show();
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