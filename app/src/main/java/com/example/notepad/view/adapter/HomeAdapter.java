package com.example.notepad.view.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.database.NoteDiaryEntity;
import com.example.notepad.databinding.NotediaryRcviewBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<NoteDiaryEntity> noteDiaryEntityList = new ArrayList<>();
    private OnClickListener listener;

    public void setNoteDiaryEntityList(List<NoteDiaryEntity> noteDiaryEntityList) {
        this.noteDiaryEntityList.clear();
        this.noteDiaryEntityList.addAll(noteDiaryEntityList);
        notifyDataSetChanged();
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NotediaryRcviewBinding binding = NotediaryRcviewBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteDiaryEntity noteDiaryEntity = noteDiaryEntityList.get(position);
        holder.bindView(noteDiaryEntity);
    }

    @Override
    public int getItemCount() {
        return noteDiaryEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private NotediaryRcviewBinding binding;

        public ViewHolder(@NonNull NotediaryRcviewBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bindView(NoteDiaryEntity noteDiaryEntity) {
            //binding.txtDateNoteDiary.setText(noteDiaryEntity.getDate());
            if (noteDiaryEntity.getTitle() != null) {
                binding.txtTitleNoteDiary.setVisibility(View.VISIBLE);
                binding.txtTitleNoteDiary.setText(noteDiaryEntity.getTitle());
            }else {
                binding.txtTitleNoteDiary.setVisibility(View.GONE);
            }

            if (noteDiaryEntity.getContent() != null) {
                binding.txtContentNoteDiary.setVisibility(View.VISIBLE);
                binding.txtContentNoteDiary.setText(noteDiaryEntity.getContent());
            }else {
                binding.txtContentNoteDiary.setVisibility(View.GONE);
            }

            if (noteDiaryEntity.getImgContent() != null) {
                binding.imgNoteDiary.setVisibility(View.VISIBLE);
                binding.imgNoteDiary.setImageURI(Uri.parse(noteDiaryEntity.getImgContent()));
            }else {
                binding.imgNoteDiary.setVisibility(View.GONE);
            }

            //binding.imgFeelNote.setImageResource(noteDiaryEntity.getImgFeel());

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onUpdate(noteDiaryEntity);
                    }
                }
            });

            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null){
                        listener.onDelete(noteDiaryEntity);
                    }
                    return true;
                }
            });
        }
    }
}
