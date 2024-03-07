package com.example.watersystem.data;

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
import com.example.watersystem.data.type.DataCardType;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<DataCardType> dataCardTypes = new ArrayList<>();
    private DataCardAdapter adapter;
    private DataCardType dataCardType = new DataCardType();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        recyclerView = view.findViewById(R.id.data_card_view);
        adapter = new DataCardAdapter(null);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setData();
        adapter.setData(dataCardTypes);
        return view;
    }

    private void setData() {

        dataCardType.setDataPh("1");
        dataCardType.setDataTds("2");
        dataCardType.setDataTem("3");
        dataCardType.setDataTu("4");
        dataCardType.setEquipment("1号检测仪");
        dataCardType.setLastConnection("2024-03-07");

        dataCardTypes.add(dataCardType);
    }

}
