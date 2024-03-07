package com.example.watersystem.personal.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.watersystem.R;


public class PersonalLogoutDialog extends DialogFragment {

    private TextView cancel;
    private TextView sure;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity(), R.style.HomeFragmentPhoneDialog);

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_personal_logout, null);

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
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.loginFragment);
                dismiss();
            }
        });

        builder.setView(view);

        return builder.create();
    }
}


