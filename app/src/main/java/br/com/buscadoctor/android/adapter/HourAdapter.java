package br.com.buscadoctor.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.model.dto.HourDTO;
import br.com.buscadoctor.android.util.Functions;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.ViewHolder> {

    private List<HourDTO> hourDTOList;

    public HourAdapter(List<HourDTO> hourDTOList) {
        this.hourDTOList = hourDTOList;
    }

    @Override
    public HourAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_adapter_item, parent, false);
        return new HourAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HourAdapter.ViewHolder holder, int position) {
        HourDTO hourDTO = hourDTOList.get(position);

        holder.mTextViewHour.setText(Functions.formatHour(hourDTO.getDiainicio()));
    }

    @Override
    public int getItemCount() {
        return hourDTOList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewHour;

        ViewHolder(View itemView) {
            super(itemView);
            mTextViewHour = itemView.findViewById(R.id.text_view_hour);
        }
    }
}