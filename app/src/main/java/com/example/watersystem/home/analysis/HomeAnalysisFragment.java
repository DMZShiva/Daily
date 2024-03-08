package com.example.watersystem.home.analysis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watersystem.R;
import com.example.watersystem.data.adapter.DataCardAdapter;
import com.example.watersystem.home.analysis.adapter.HomeAnalysisAdapter;
import com.example.watersystem.home.analysis.type.HomeAnalysisType;

import java.util.ArrayList;
import java.util.List;

public class HomeAnalysisFragment extends Fragment {

    private HomeAnalysisAdapter adapter;

    private RecyclerView recyclerView;

    private List<HomeAnalysisType> data = new ArrayList<>();

    private HomeAnalysisType waterType = new HomeAnalysisType();
    private HomeAnalysisType freshType = new HomeAnalysisType();
    private HomeAnalysisType seaType = new HomeAnalysisType();
    private HomeAnalysisType swimmingType = new HomeAnalysisType();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_analysis, container, false);

        recyclerView = view.findViewById(R.id.home_analysis_card);

        adapter = new HomeAnalysisAdapter(null);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setData();
        adapter.setData(data);


        return view;
    }

    private void setData() {

        waterType.setHomeAnalysisImg(R.drawable.home_analysis_water);
        waterType.setHomeAnalysisTitle("默认场景");
        waterType.setHomeAnalysisTds("TDS: 25 ~ 100");
        waterType.setHomeAnalysisPh("PH: 6.5 ~ 8.5");
        waterType.setHomeAnalysisTemp("TEM: 35 ~ 38");
        waterType.setHomeAnalysisTu("TU: 2 ~ 5");
        data.add(waterType);


        freshType.setHomeAnalysisImg(R.drawable.home_analysis_fresh);
        freshType.setHomeAnalysisTitle("淡水养殖");
        freshType.setHomeAnalysisTds("TDS: 200 ~ 300");
        freshType.setHomeAnalysisPh("PH: 6.5 ~ 7.5");
        freshType.setHomeAnalysisTemp("TEM: 20 ~ 28");
        freshType.setHomeAnalysisTu("TU: 15 ~ 20");
        data.add(freshType);

        seaType.setHomeAnalysisImg(R.drawable.home_analysis_sea);
        seaType.setHomeAnalysisTitle("海水养殖");
        seaType.setHomeAnalysisTds("TDS: 200 ~ 300");
        seaType.setHomeAnalysisPh("PH: 7.5 ~ 8.5");
        seaType.setHomeAnalysisTemp("TEM: 25 ~ 35");
        seaType.setHomeAnalysisTu("TU: 18 ~ 25");
        data.add(seaType);

        swimmingType.setHomeAnalysisImg(R.drawable.home_analysis_swimming_pool);
        swimmingType.setHomeAnalysisTitle("泳池场景");
        swimmingType.setHomeAnalysisTds("TDS: 150 ~ 300");
        swimmingType.setHomeAnalysisPh("PH: 6.5 ~ 8.5");
        swimmingType.setHomeAnalysisTemp("TEM: 26 ~ 28");
        swimmingType.setHomeAnalysisTu("TU: 7.4 ~ 7.6");
        data.add(swimmingType);

    }

}
