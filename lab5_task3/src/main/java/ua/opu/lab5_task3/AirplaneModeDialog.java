package ua.opu.lab5_task3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AirplaneModeDialog extends DialogFragment {

    private static final String ARG_STATE = "state";
    private boolean state;

    public static AirplaneModeDialog newInstance(boolean state) {
        Bundle args = new Bundle();
        args.putBoolean(ARG_STATE, state);
        AirplaneModeDialog fragment = new AirplaneModeDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = getArguments().getBoolean(ARG_STATE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Оповещение");
        builder.setMessage("Режим самолёта: " + (state ? "Вкл." : "Выкл."));
        builder.setPositiveButton("OK", null);
        return builder.create();
    }
}
