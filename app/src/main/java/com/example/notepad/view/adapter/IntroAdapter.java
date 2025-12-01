package com.example.notepad.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.databinding.ViewpagerIntroBinding;
import com.example.notepad.model.Intro;

import java.util.ArrayList;
import java.util.List;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.ViewHolder> {
    private List<Intro> intros = new ArrayList<>();

    private OnNextClickListener listener;

    public void setOnNextClickListener(OnNextClickListener listener) {
        this.listener = listener;
    }

    public void setIntros(List<Intro> intros) {
        this.intros.clear();
        this.intros.addAll(intros);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewpagerIntroBinding binding = ViewpagerIntroBinding.inflate(inflater, parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Intro intro = intros.get(position);
        holder.bindView(intro);
    }

    @Override
    public int getItemCount() {
        return intros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewpagerIntroBinding binding;
        public ViewHolder(@NonNull ViewpagerIntroBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bindView(Intro intro) {
            binding.imgPagerIntro.setImageResource(intro.getImgIntro());
            binding.txtPagerTitle.setText(intro.getTxtTitle());
            binding.txtPagerContent.setText(intro.getTxtContent());
            binding.btnNextIntro.setText(intro.getTxtBtnNextIntro());

            binding.btnNextIntro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int pos = getBindingAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            listener.onNext(pos);
                        }
                    }
                }
            });
        }
    }
}
