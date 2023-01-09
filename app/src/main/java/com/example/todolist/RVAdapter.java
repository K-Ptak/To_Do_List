package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private ArrayList<String> lista;
    private FragmentManager fm;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv;
        private final ImageButton ib;

        public ViewHolder(View view) {
            super(view);

            tv = (TextView) view.findViewById(R.id.tv);
            ib = (ImageButton) view.findViewById(R.id.imageView);


        }

        public TextView getTextView() {
            return tv;
        }

        public ImageButton getImageButton() {
            return ib;
        }
    }

    public RVAdapter(ArrayList<String> dataSet, FragmentManager fm) {
        lista = dataSet;
        this.fm = fm;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(lista.get(position));
        holder.getImageButton().setOnClickListener(v -> {
            DialogFragment df = new AlertDialogFragment(position);
            df.show(fm, AlertDialogFragment.TAG + position);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
