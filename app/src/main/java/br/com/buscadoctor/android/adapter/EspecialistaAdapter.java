package br.com.buscadoctor.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.dto.EspecialistaDTO;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class EspecialistaAdapter extends RecyclerView.Adapter<EspecialistaAdapter.ViewHolder> {

    private List<EspecialistaDTO> especialistas;

    public EspecialistaAdapter(List<EspecialistaDTO> especialistas) {
        this.especialistas = especialistas;
    }

    @Override
    public EspecialistaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.especialista_adapter_item, parent, false);
        return new EspecialistaAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EspecialistaAdapter.ViewHolder holder, int position) {
        EspecialistaDTO especialista = especialistas.get(position);

        holder.mTextViewEspecialistaNome.setText(especialista.getEspecialista().getNome());
        holder.mTextViewEspecialistaEspecialidade.setText(especialista.getEspecialidades().get(0).getEspecialidade());
        holder.mTextViewEspecialistaConsultorio.setText(especialista.getConsultorio().getNome());

        if (especialista.getEspecialista().getSexo().equalsIgnoreCase("Feminino")) {
            holder.mImageViewFoto.setImageResource(R.mipmap.ic_doutora);
        } else {
            holder.mImageViewFoto.setImageResource(R.mipmap.ic_doutor);
        }

        String endereco = especialista.getConsultorio().getLogradouro().getCidade().getNome() +
                " - " + especialista.getConsultorio().getLogradouro().getCidade().getEstado().getAcronimo();
        holder.mTextViewEndereco.setText(endereco);
    }

    @Override
    public int getItemCount() {
        return especialistas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewEspecialistaNome;
        TextView mTextViewEspecialistaEspecialidade;
        TextView mTextViewEspecialistaConsultorio;
        TextView mTextViewEndereco;

        ImageView mImageViewFoto;

        ViewHolder(View itemView) {
            super(itemView);
            mTextViewEspecialistaNome = (TextView) itemView.findViewById(R.id.text_view_especialista_nome);
            mTextViewEspecialistaEspecialidade = (TextView) itemView.findViewById(R.id.text_view_especialista_especialidade);
            mTextViewEspecialistaConsultorio = (TextView) itemView.findViewById(R.id.text_view_especialista_consultorio);
            mTextViewEndereco = (TextView) itemView.findViewById(R.id.text_view_clinica_endereco);

            mImageViewFoto = (ImageView) itemView.findViewById(R.id.image_view_especialista_foto);
        }
    }
}