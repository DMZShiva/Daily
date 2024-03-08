package com.example.watersystem.home.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.watersystem.R;

public class HomeAboutDialogFragment extends DialogFragment {

    private OnConfirmClickListener listener;

    public interface OnConfirmClickListener {
        void onConfirmClick();
    }

    public void setOnConfirmClickListener(OnConfirmClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        builder.setMessage(R.string.home_about_dialog)
                .setPositiveButton(R.string.home_phone_dialog_sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onConfirmClick();
                        }
                    }
                });

        return builder.create();
    }
}
