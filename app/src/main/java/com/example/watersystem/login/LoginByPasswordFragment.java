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


public class LoginByPasswordFragment extends Fragment {

    private ToggleButton showPassword;
    private EditText password;
    private ImageView cleanPassword;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_by_password, container, false);

        password = view.findViewById(R.id.login_by_password_password_number);
        showPassword = view.findViewById(R.id.login_by_password_off);
        cleanPassword = view.findViewById(R.id.login_clean);

        initPassword();
        initEditText();

        return view;
    }

    private void initPassword() {

        showPassword.setOnCheckedChangeListener((compoundButton, isChecked) -> password.setInputType(
                isChecked ? (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                        : (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)));
    }

    private void initEditText() {
        password.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (password.hasFocus()) {
                    if (password.getText().toString().equals("")) {
                        cleanPassword.setVisibility(View.GONE);
                    } else {
                        cleanPassword.setVisibility(View.VISIBLE);
                    }
                } else {
                    cleanPassword.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    cleanPassword.setVisibility(View.INVISIBLE);
                } else {
                    if (password.getText().toString().equals("")) {
                        cleanPassword.setVisibility(View.GONE);
                    } else {
                        cleanPassword.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        cleanPassword.setOnClickListener(v -> password.setText(""));

    }
}
