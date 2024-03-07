package com.example.watersystem.home.analysis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watersystem.R;
import com.example.watersystem.home.analysis.type.HomeAnalysisType;

import java.util.List;

public class HomeAnalysisAdapter extends RecyclerView.Adapter<HomeAnalysisAdapter.HomeAnalysisHolder> {
    private List<HomeAnalysisType> data;

    public HomeAnalysisAdapter(List<HomeAnalysisType> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public HomeAnalysisAdapter.HomeAnalysisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_analysis, parent, false);
        return new HomeAnalysisHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAnalysisAdapter.HomeAnalysisHolder holder, int position) {
        HomeAnalysisType type = data.get(position);
        holder.homeAnalysisImg.setImageResource(type.getHomeAnalysisImg());
        holder.homeAnalysisTitle.setText(type.getHomeAnalysisTitle());
        holder.homeAnalysisTds.setText(type.getHomeAnalysisTds());
        holder.homeAnalysisPh.setText(type.getHomeAnalysisPh());
        holder.homeAnalysisTemp.setText(type.getHomeAnalysisTemp());
        holder.homeAnalysisTu.setText(type.getHomeAnalysisTu());
    }

    public void setData(List<HomeAnalysisType> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HomeAnalysisHolder extends RecyclerView.ViewHolder {

        private ImageView homeAnalysisImg;
        private TextView homeAnalysisTitle;
        private TextView homeAnalysisTds;
        private TextView homeAnalysisPh;
        private TextView homeAnalysisTemp;
        private TextView homeAnalysisTu;
        private CheckBox homeAnalysisCheck;

        public HomeAnalysisHolder(@NonNull View itemView) {
            super(itemView);

            homeAnalysisImg = itemView.findViewById(R.id.home_analysis_img);
            homeAnalysisTitle = itemView.findViewById(R.id.home_analysis_title);
            homeAnalysisTds = itemView.findViewById(R.id.home_analysis_tds);
            homeAnalysisPh = itemView.findViewById(R.id.home_analysis_ph);
            homeAnalysisTemp = itemView.findViewById(R.id.home_analysis_temp);
            homeAnalysisTu = itemView.findViewById(R.id.home_analysis_tu);
            homeAnalysisCheck = itemView.findViewById(R.id.home_analysis_checkbox);

        }
    }
}
