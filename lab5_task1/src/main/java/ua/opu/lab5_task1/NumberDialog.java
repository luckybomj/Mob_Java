package ua.opu.lab5_task1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NumberDialog extends DialogFragment {

    private static final String ARG_NUMBER = "number";
    private int number;

    public static NumberDialog newInstance(int number) {
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        NumberDialog fragment = new NumberDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        number = getArguments().getInt(ARG_NUMBER);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Число");
        builder.setMessage("Вы выбрали число " + number);
        builder.setPositiveButton("OK", null);
        return builder.create();
    }
}
