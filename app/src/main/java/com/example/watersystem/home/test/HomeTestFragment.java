package com.example.watersystem.home.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watersystem.R;

public class HomeTestFragment extends Fragment {

    private TextView sensorTds;
    private TextView sensorTemp;
    private TextView sensorPh;
    private TextView sensorTu;

    private TextView basicElectric;
    private TextView basicSolar;
    private TextView basicMovement;
    private TextView basicFloat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_test, container, false);

        sensorTds = view.findViewById(R.id.home_test_sensor_tds);
        sensorTemp = view.findViewById(R.id.home_test_sensor_temp);
        sensorPh = view.findViewById(R.id.home_test_sensor_ph);
        sensorTu = view.findViewById(R.id.home_test_sensor_tu);

        basicElectric = view.findViewById(R.id.home_test_basic_electric);
        basicSolar = view.findViewById(R.id.home_test_basic_solar);
        basicMovement = view.findViewById(R.id.home_test_basic_movement);
        basicFloat = view.findViewById(R.id.home_test_basic_float);

        return view;
    }
}
