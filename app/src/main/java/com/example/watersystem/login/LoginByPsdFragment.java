package com.example.watersystem.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watersystem.R;


public class LoginByPsdFragment extends Fragment {

    private ToggleButton psd_off;
    private EditText psd;
    private ImageView clean;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_by_psd, container, false);

        psd = view.findViewById(R.id.login_by_psd_psd_number);
        psd_off = view.findViewById(R.id.login_by_psd_off);
        clean = view.findViewById(R.id.login_clean);

        initPsd();
        initEditText();

        return view;
    }

    private void initPsd() {

        psd_off.setOnCheckedChangeListener((compoundButton, isChecked) -> psd.setInputType(
                isChecked ? (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                        : (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)));
    }

    private void initEditText() {
        psd.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (psd.hasFocus()) {
                    if (psd.getText().toString().equals("")) {
                        clean.setVisibility(View.GONE);
                    } else {
                        clean.setVisibility(View.VISIBLE);
                    }
                } else {
                    clean.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        psd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    clean.setVisibility(View.INVISIBLE);
                } else {
                    if (psd.getText().toString().equals("")) {
                        clean.setVisibility(View.GONE);
                    } else {
                        clean.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        clean.setOnClickListener(v -> psd.setText(""));

    }
}
