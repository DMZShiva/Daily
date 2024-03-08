package com.example.watersystem.home.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.watersystem.R;

public class HomePhoneDialogFragment extends DialogFragment {

    private EditText editText;
    private EditTextDialogListener listener;
    private TextView cancel;
    private TextView sure;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity(), R.style.HomeFragmentPhoneDialog);

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_home_phone, null);

        editText = view.findViewById(R.id.home_phone_dialog_edit);
        cancel = view.findViewById(R.id.home_phone_dialog_button_cancel);
        sure = view.findViewById(R.id.home_phone_dialog_button_sure);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (listener != null) {
                    listener.onDialogPositiveClick(text);
                }
                dismiss();
            }
        });

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditTextDialogListener) {
            listener = (EditTextDialogListener) context;
        } else {
            throw new ClassCastException(context.toString() +
                    " must implement EditTextDialogListener");
        }
    }

    public interface EditTextDialogListener {
        void onDialogPositiveClick(String text);
    }
}
