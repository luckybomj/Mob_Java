package ua.opu.lab5_task1;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class IconsAdapter extends RecyclerView.Adapter<IconsAdapter.IconViewHolder> {

    private final FragmentManager fragmentManager;

    public IconsAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    class IconViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IconViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        Random random = new Random();
        int number = random.nextInt(99) + 1;
        holder.textView.setText(String.valueOf(number));
        int colorThreshold = 100;
        holder.imageView.setBackgroundColor(Color.rgb(
                random.nextInt(255 - colorThreshold) + colorThreshold,
                random.nextInt(255 - colorThreshold) + colorThreshold,
                random.nextInt(255 - colorThreshold) + colorThreshold));
        holder.imageView.setOnClickListener(v -> NumberDialog.newInstance(number).show(fragmentManager, "dlg"));
    }

    @Override
    public int getItemCount() {
        return 24;
    }
}
