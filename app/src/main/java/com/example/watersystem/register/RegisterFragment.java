package com.example.watersystem.register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.watersystem.R;

public class RegisterFragment extends Fragment {

    private TextView register;
    private ImageView back;
    private TextView sendCode;
    private EditText registerNumber;
    private EditText registerPassword;
    private EditText registerPasswordAgain;
    private EditText registerPhoneNumber;
    private EditText registerCode;
    private ImageView cleanNumber;
    private ImageView cleanPassword;
    private ImageView cleanPasswordAgain;
    private ImageView cleanPhone;
    private ImageView cleanCode;
    private ToggleButton showPassword;

    private ToggleButton showAgainPassword;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        register = view.findViewById(R.id.register);
        back = view.findViewById(R.id.back);
        sendCode = view.findViewById(R.id.register_by_code_send);
        registerNumber= view.findViewById(R.id.register_number);
        registerPassword = view.findViewById(R.id.register_password);
        registerPasswordAgain = view.findViewById(R.id.register_password_again);
        registerPhoneNumber = view.findViewById(R.id.register_phone);
        registerCode = view.findViewById(R.id.register_code);
        cleanNumber = view.findViewById(R.id.register_clean_number);
        cleanPassword = view.findViewById(R.id.register_clean_password);
        cleanPasswordAgain = view.findViewById(R.id.register_clean_password_again);
        cleanPhone = view.findViewById(R.id.register_clean_phone);
        cleanCode = view.findViewById(R.id.register_clean_code);
        showPassword = view.findViewById(R.id.register_by_password_off);
        showAgainPassword = view.findViewById(R.id.register_by_password_off_again);

        initRegister();
        initBack();
        initCodeSend();
        initEditText();
        initPassword();

        return view;

    }

    private void initRegister() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegisterToast();
            }
        });
    }

    private void initRegisterToast() {

        //  未输入账号
        if (TextUtils.isEmpty(registerNumber.getText())) {
            Toast.makeText(getContext(), R.string.register_toast_number, Toast.LENGTH_SHORT).show();
        }

        // 未输入密码
        else if (TextUtils.isEmpty(registerPassword.getText()) || TextUtils.isEmpty(registerPasswordAgain.getText())) {
            Toast.makeText(getContext(), R.string.register_toast_password_null, Toast.LENGTH_SHORT).show();
        }

        // 未输入验证码
        else if (TextUtils.isEmpty(registerCode.getText())) {
            Toast.makeText(getContext(), R.string.register_toast_code_null, Toast.LENGTH_SHORT).show();
        }

        // 两次密码不一致
        else if (!registerPassword.getText().equals(registerPasswordAgain.getText())) {
            Toast.makeText(getContext(), R.string.register_toast_password, Toast.LENGTH_SHORT).show();
        }

        // 验证码输入有误
//        else if () {
//            Toast.makeText(getContext(), R.string.register_toast_code, Toast.LENGTH_SHORT).show();
//        }

        else {
            NavHostFragment.findNavController(RegisterFragment.this).navigate(R.id.loginFragment);
        }

    }

    private void initBack() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(registerNumber.getText())
                        || !TextUtils.isEmpty(registerPassword.getText())
                        || !TextUtils.isEmpty(registerPasswordAgain.getText())
                        || !TextUtils.isEmpty(registerPhoneNumber.getText())
                        || !TextUtils.isEmpty(registerCode.getText())) {
                    new AlertDialog.Builder(getContext()).setTitle(R.string.quick_dialog_title)
                            .setPositiveButton(R.string.quick_dialog_sure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            assert getFragmentManager() != null;
                            getFragmentManager().popBackStack();
                        }
                    })
                            .setNegativeButton(R.string.quick_dialog_cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                } else {
                    assert getFragmentManager() != null;
                    getFragmentManager().popBackStack();
                }
            }
        });
    }

    private void initCodeSend() {
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(registerPhoneNumber.getText())){
                    Toast.makeText(getContext(), R.string.register_toast_phone_null, Toast.LENGTH_SHORT).show();
                } else if (registerPhoneNumber.getText().length() != 11 ) {
                    Toast.makeText(getContext(), R.string.register_toast_phone, Toast.LENGTH_SHORT).show();
                } else {
                    // 发送验证码
                }
            }
        });
    }

    private void initEditText() {
        initEditTextNumber();
        initEditTextPassword();
        initEditTextPasswordAgain();
        initEditTextPhone();
        initEditTextCode();
    }

    private void initEditTextNumber() {
        registerNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (registerNumber.hasFocus()) {
                    if (registerNumber.getText().toString().equals("")) {
                        cleanNumber.setVisibility(View.GONE);
                    } else {
                        cleanNumber.setVisibility(View.VISIBLE);
                    }
                } else {
                    cleanNumber.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        registerNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    cleanNumber.setVisibility(View.INVISIBLE);
                } else {
                    if (registerNumber.getText().toString().equals("")) {
                        cleanNumber.setVisibility(View.GONE);
                    } else {
                        cleanNumber.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        cleanNumber.setOnClickListener(v -> registerNumber.setText(""));
    }

    private void initEditTextPassword() {
        registerPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (registerPassword.hasFocus()) {
                    if (registerPassword.getText().toString().equals("")) {
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

        registerPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    cleanPassword.setVisibility(View.INVISIBLE);
                } else {
                    if (registerPassword.getText().toString().equals("")) {
                        cleanPassword.setVisibility(View.GONE);
                    } else {
                        cleanPassword.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        cleanPassword.setOnClickListener(v -> registerPassword.setText(""));
    }

    private void initEditTextPasswordAgain() {
        registerPasswordAgain.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (registerPasswordAgain.hasFocus()) {
                    if (registerPasswordAgain.getText().toString().equals("")) {
                        cleanPasswordAgain.setVisibility(View.GONE);
                    } else {
                        cleanPasswordAgain.setVisibility(View.VISIBLE);
                    }
                } else {
                    cleanPasswordAgain.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        registerPasswordAgain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    cleanPasswordAgain.setVisibility(View.INVISIBLE);
                } else {
                    if (registerPasswordAgain.getText().toString().equals("")) {
                        cleanPasswordAgain.setVisibility(View.GONE);
                    } else {
                        cleanPasswordAgain.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        cleanPasswordAgain.setOnClickListener(v -> registerPasswordAgain.setText(""));
    }

    private void initEditTextPhone() {
        registerPhoneNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (registerPhoneNumber.hasFocus()) {
                    if (registerPhoneNumber.getText().toString().equals("")) {
                        cleanPhone.setVisibility(View.GONE);
                    } else {
                        cleanPhone.setVisibility(View.VISIBLE);
                    }
                } else {
                    cleanPhone.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        registerPhoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    cleanPhone.setVisibility(View.INVISIBLE);
                } else {
                    if (registerPhoneNumber.getText().toString().equals("")) {
                        cleanPhone.setVisibility(View.GONE);
                    } else {
                        cleanPhone.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        cleanPhone.setOnClickListener(v -> registerPhoneNumber.setText(""));
    }

    private void initEditTextCode() {
        registerCode.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (registerCode.hasFocus()) {
                    if (registerCode.getText().toString().equals("")) {
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

        registerCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    cleanCode.setVisibility(View.INVISIBLE);
                } else {
                    if (registerCode.getText().toString().equals("")) {
                        cleanCode.setVisibility(View.GONE);
                    } else {
                        cleanCode.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        cleanCode.setOnClickListener(v -> registerCode.setText(""));
    }

    private void initPassword() {

        showPassword.setOnCheckedChangeListener((compoundButton, isChecked) -> registerPassword.setInputType(
                isChecked ? (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                        : (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)));

        showAgainPassword.setOnCheckedChangeListener((compoundButton, isChecked) -> registerPasswordAgain.setInputType(
                isChecked ? (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                        : (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)));
    }
}
