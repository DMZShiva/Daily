package com.example.watersystem.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watersystem.R;

public class LoginByCodeFragment extends Fragment {

    private EditText code;
    private EditText phoneNumber;
    private ImageView cleanCode;
    private TextView sendCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_by_code, container, false);

        code = view.findViewById(R.id.login_by_code_code_number);
        phoneNumber = view.findViewById(R.id.login_by_password_number_edit);
        cleanCode = view.findViewById(R.id.login_clean);
        sendCode = view.findViewById(R.id.register_by_code_send);

        initEditText();
        initCodeSend();

        return view;
    }

    private void initEditText() {
        code.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (code.hasFocus()) {
                    if (code.getText().toString().equals("")) {
                        cleanCode.setVisibility(View.GONE);
                    } else {
                        cleanCode.setVisibility(View.VISIBLE);
                    }
                } else {
                    cleanCode.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        code.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    cleanCode.setVisibility(View.INVISIBLE);
                } else {
                    if (code.getText().toString().equals("")) {
                        cleanCode.setVisibility(View.GONE);
                    } else {
                        cleanCode.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        cleanCode.setOnClickListener(v -> code.setText(""));

    }

    private void initCodeSend() {
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(phoneNumber.getText())){
                    Toast.makeText(getContext(), R.string.register_toast_phone_null, Toast.LENGTH_SHORT).show();
                } else if (phoneNumber.getText().length() != 11 ) {
                    Toast.makeText(getContext(), R.string.register_toast_phone, Toast.LENGTH_SHORT).show();
                } else {
                    // 发送验证码
                }
            }
        });
    }

}
