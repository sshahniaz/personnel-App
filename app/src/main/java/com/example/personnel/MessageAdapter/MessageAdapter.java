package com.example.personnel.MessageAdapter;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.personnel.DashboardAndMessagesModelClasses.MessageModel;
import com.example.personnel.R;
import java.util.List;
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.messageViewHolder> {

    Context context;
    List<MessageModel> messageList;

   OnDialogActionListener dialogActionListener;

    public MessageAdapter(List<MessageModel> messageList) {
        this.messageList = messageList;
    }
    public interface OnDialogActionListener {
        void onMessageClose(int position);
    }

    public void setDialogActionListener(OnDialogActionListener listener) {
        dialogActionListener = listener;
    }
// method--- delete message
    public void deleteMessage(int position) {
        if (position>= 0 && position < messageList.size()) {
            messageList.remove(position);
            notifyItemRemoved(position);
        }
    }

    @NonNull
    @Override
    public MessageAdapter.messageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Set view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);
        TextView date = view.findViewById(R.id.messageDate);
        return new messageViewHolder(view);
    }

//    Pass values to message model
    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.messageViewHolder holder, int position) {
        MessageModel model = messageList.get(position);
        holder.messageTitle.setText(model.getMessageTitle());
        holder.messageDate.setText(model.getMessageDate());
        holder.messageSubject.setText(model.getMessageSubject());
        holder.messageBody.setText(model.getMessageText());


        //         Set OnClickListener on card view for expansion/colour focus
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                boolean expanded = model.isExpanded();
                model.setExpanded(!expanded);
                holder.messageBody.setVisibility(expanded ? View.GONE : View.VISIBLE);
                if (!model.isExpanded()) {
                    holder.messageLayout.setBackgroundResource(R.drawable.cards);
                    holder.messageTitle.setTextColor(Color.parseColor("#343A40"));
                    holder.messageDate.setTextColor(Color.parseColor("#707070"));
                    holder.messageSubject.setTextColor(Color.parseColor("#343A40"));
                } else {
                    holder.messageLayout.setBackgroundResource(R.color.widgets);
                    holder.messageTitle.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.messageDate.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.messageSubject.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.messageBody.setTextColor(Color.parseColor("#FFFFFF"));
                }
            }
        });
        // Set the visibility of the messageBody based on the model's expanded state
        holder.messageBody.setVisibility(model.isExpanded() ? View.VISIBLE : View.GONE);



// Set the background color of the first item to blue
        if (holder.getAdapterPosition() ==0) {
            holder.messageLayout.setBackgroundResource(R.color.widgets);
            holder.messageTitle.setTextColor(Color.parseColor("#FFFFFF"));
            holder.messageDate.setTextColor(Color.parseColor("#FFFFFF"));
            holder.messageSubject.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            boolean expanded = model.isExpanded();
            holder.messageLayout.setBackgroundResource(expanded ? R.color.widgets : R.drawable.cards);
            holder.messageTitle.setTextColor(Color.parseColor("#343A40"));
            holder.messageDate.setTextColor(Color.parseColor("#707070"));
            holder.messageSubject.setTextColor(Color.parseColor("#343A40"));
        }
        holder.messageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != NO_POSITION && dialogActionListener != null) {
                    dialogActionListener.onMessageClose(position);
                }
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

//    Set components
    public class messageViewHolder extends RecyclerView.ViewHolder {
        TextView messageTitle;
        TextView messageDate;
        TextView messageSubject;
        TextView messageBody;
        LinearLayout messageLayout;
        ImageView messageClose;
        public messageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTitle = itemView.findViewById(R.id.messageTitle);
            messageDate = itemView.findViewById(R.id.messageDate);
            messageSubject = itemView.findViewById(R.id.messageSubject);
            messageBody = itemView.findViewById(R.id.messageBody);
            messageLayout = itemView.findViewById(R.id.mLinearView);
            messageClose = itemView.findViewById(R.id.close);

        }
    }
}
