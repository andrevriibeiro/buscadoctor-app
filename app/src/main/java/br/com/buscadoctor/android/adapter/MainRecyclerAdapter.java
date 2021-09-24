package br.com.buscadoctor.android.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.activity.ConsultorioActivity;
import br.com.buscadoctor.android.model.Consultorio;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<Consultorio> consultorios;

    public MainRecyclerAdapter(List<Consultorio> consultorios) {
        this.consultorios = consultorios;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Consultorio consultorio = consultorios.get(position);
        holder.mTextViewText.setText(consultorio.getNome());
        holder.mImageViewCard.setImageResource(R.mipmap.ouromed);

        holder.mImageViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ConsultorioActivity.class);
                intent.putExtra("consultorio", consultorio);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return consultorios.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewText;
        ImageView mImageViewCard;

        ViewHolder(View itemView) {
            super(itemView);
            mTextViewText = (TextView) itemView.findViewById(R.id.text_view_text);
            mImageViewCard = (ImageView) itemView.findViewById(R.id.image_view_card);
        }
    }
}