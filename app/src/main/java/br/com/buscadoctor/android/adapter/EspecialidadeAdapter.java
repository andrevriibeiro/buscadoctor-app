package br.com.buscadoctor.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.Especialidade;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class EspecialidadeAdapter extends RecyclerView.Adapter<EspecialidadeAdapter.ViewHolder> {

    private List<Especialidade> especialidades;

    public EspecialidadeAdapter(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public EspecialidadeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.especialidade_item, parent, false);
        return new EspecialidadeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EspecialidadeAdapter.ViewHolder holder, int position) {
        Especialidade especialidade = especialidades.get(position);
        holder.mTextViewText.setText(especialidade.getEspecialidade());
    }

    @Override
    public int getItemCount() {
        return especialidades.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewText;

        ViewHolder(View itemView) {
            super(itemView);
            mTextViewText = (TextView) itemView.findViewById(R.id.text_view_especialidade);
        }
    }
}