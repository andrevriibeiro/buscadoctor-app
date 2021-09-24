package br.com.buscadoctor.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.dto.FavoritosDTO;

/**
 * @author Andre
 * @version 1.0.0
 * @since 1.0.0
 */
public class MeusFavoritosAdapter extends RecyclerView.Adapter<MeusFavoritosAdapter.ViewHolder> {

    private List<FavoritosDTO> mFavoritosDTO;

    public MeusFavoritosAdapter(List<FavoritosDTO> mFavoritosDTO) {
        this.mFavoritosDTO = mFavoritosDTO;
    }

    @Override
    public MeusFavoritosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_favoritos_item, parent, false);
        return new MeusFavoritosAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FavoritosDTO favoritosDTO = mFavoritosDTO.get(position);

        if (favoritosDTO.getTipo().equals("Especialista")) {
            holder.textViewNomeClinicaOrEspecialista.setText(favoritosDTO.getEspecialista().getNome());
            //holder.textViewEspecialidadeOrTipoClinica.setText(favoritosDTO.getEspecialidadeprincipal().getEspecialidade());
//            holder.textViewClinicaName.setText(favoritosDTO.getConsultorio().getNome());
//            holder.textViewCidadeEstado.setText(favoritosDTO.getConsultorio().getLogradouro().getCidade().getNome() +
//            " - " + favoritosDTO.getConsultorio().getLogradouro().getCidade().getEstado().getAcronimo());

        } else {
            holder.textViewNomeClinicaOrEspecialista.setText(favoritosDTO.getConsultorio().getNome());
            holder.textViewEspecialidadeOrTipoClinica.setText(favoritosDTO.getConsultorio().getTipo().getNome());
            holder.textViewClinicaName.setText("");
            holder.textViewCidadeEstado.setText(favoritosDTO.getConsultorio().getLogradouro().getCidade().getNome() +
                    " - " + favoritosDTO.getConsultorio().getLogradouro().getCidade().getEstado().getAcronimo());
        }
    }

    @Override
    public int getItemCount() {
        return mFavoritosDTO.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewCard;
        public TextView textViewNomeClinicaOrEspecialista;
        public TextView textViewEspecialidadeOrTipoClinica;
        public TextView textViewClinicaName;
        public TextView textViewCidadeEstado;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewCard = (ImageView) itemView.findViewById(R.id.image_view_card);
            textViewNomeClinicaOrEspecialista = (TextView) itemView.findViewById(R.id.text_view_nome_clinica_especialista);
            textViewEspecialidadeOrTipoClinica = (TextView) itemView.findViewById(R.id.text_view_tipo_clinic_especialidade);
            textViewClinicaName = (TextView) itemView.findViewById(R.id.text_view_clinic_name);
            textViewCidadeEstado = (TextView) itemView.findViewById(R.id.text_view_cidade_estado);
        }
    }
}