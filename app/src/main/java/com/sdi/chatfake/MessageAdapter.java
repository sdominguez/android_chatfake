package com.sdi.chatfake;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sdi.chatfake.databinding.MsgItemBinding;

public class MessageAdapter
        extends ListAdapter<Message, MessageAdapter.MsgViewHolder> {

    public static final DiffUtil.ItemCallback<Message> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<Message>() {
        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected MessageAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MessageAdapter.MsgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MsgItemBinding binding = MsgItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MsgViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MsgViewHolder holder, int position) {
        Message msg = getItem(position);
        holder.bind(msg);
    }

    class MsgViewHolder extends RecyclerView.ViewHolder {
        MsgItemBinding binding;

        public MsgViewHolder(MsgItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Message msg){
            binding.textMessage.setText(msg.getMsg());
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) binding.textMessage.getLayoutParams();
            if(msg.getId()==1){
                binding.textMessage.setBackgroundResource(R.drawable.chat_bubble_blue);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END, 1);
                binding.textMessage.setLayoutParams(layoutParams);
            }

            binding.executePendingBindings();
        }
    }
}
