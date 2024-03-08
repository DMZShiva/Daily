package com.example.watersystem.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watersystem.R;
import com.example.watersystem.data.type.DataCardType;

import java.util.List;

public class DataCardAdapter extends RecyclerView.Adapter<DataCardAdapter.DataCardHolder> {

    private List<DataCardType> data;

    public DataCardAdapter(List<DataCardType> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DataCardAdapter.DataCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_data_card_item, parent, false);
        DataCardHolder holder = new DataCardHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataCardAdapter.DataCardHolder holder, int position) {
        DataCardType dataCardType = data.get(position);

        holder.equipment.setText(dataCardType.getEquipment());
        holder.last_connection.setText(dataCardType.getLastConnection());
        holder.data_tds.setText(dataCardType.getDataTds());
        holder.data_ph.setText(dataCardType.getDataPh());
        holder.data_tem.setText(dataCardType.getDataTem());
        holder.data_tu.setText(dataCardType.getDataTu());

        holder.equipment_title.setText("设备:");
        holder.last_connection_title.setText("上次连接时间:");
        holder.data_tds_title.setText("TDS:");
        holder.data_ph_title.setText("PH:");
        holder.data_tem_title.setText("TEM:");
        holder.data_tu_title.setText("TU:");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<DataCardType> data) {
        this.data = data;
    }

    public class DataCardHolder extends RecyclerView.ViewHolder {


        private TextView equipment;
        private TextView last_connection;
        private TextView data_tds;
        private TextView data_ph;
        private TextView data_tem;
        private TextView data_tu;

        private TextView equipment_title;
        private TextView last_connection_title;
        private TextView data_tds_title;
        private TextView data_ph_title;
        private TextView data_tem_title;
        private TextView data_tu_title;

        private ProgressBar tds_progressBar;
        private  ProgressBar ph_progressBar;
        private  ProgressBar tu_progressBar;
        private ProgressBar tem_progressBar;


        public DataCardHolder(@NonNull View itemView) {
            super(itemView);

            equipment = itemView.findViewById(R.id.data_equipment);
            last_connection = itemView.findViewById(R.id.data_last_connection);
            data_tds = itemView.findViewById(R.id.data_tds);
            data_ph = itemView.findViewById(R.id.data_ph);
            data_tem = itemView.findViewById(R.id.data_tem);
            data_tu = itemView.findViewById(R.id.data_tu);

            equipment_title= itemView.findViewById(R.id.data_equipment_title);
            last_connection_title = itemView.findViewById(R.id.data_last_connection_title);
            data_tds_title = itemView.findViewById(R.id.data_tds_title);
            data_tem_title = itemView.findViewById(R.id.data_tem_title);
            data_ph_title = itemView.findViewById(R.id.data_ph_title);
            data_tu_title = itemView.findViewById(R.id.data_tu_title);
            data_tem_title = itemView.findViewById(R.id.data_tem_title);

            tds_progressBar = itemView.findViewById(R.id.data_tds_progressbar);
            ph_progressBar = itemView.findViewById(R.id.data_ph_progressbar);
            tu_progressBar = itemView.findViewById(R.id.data_tu_progressbar);
            tem_progressBar = itemView.findViewById(R.id.data_tem_progressbar);

        }
    }
}
