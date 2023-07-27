package com.example.personnel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class Rota_adapter extends RecyclerView.Adapter<Rota_adapter.ViewHolder> {


    Context context;
    List<Rota_Model> list;

    public Rota_adapter( List<Rota_Model> list) {
        this.list = list;
    }


    @Override
    public Rota_adapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rota,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( Rota_adapter.ViewHolder holder, int position) {
        Rota_Model model=list.get(position);
        holder.day.setText(model.getDay());
        holder.startTime.setText(model.getStartTime());
        holder.endTime.setText(model.getEndTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView day,startTime,endTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day=itemView.findViewById(R.id.day);
            startTime=itemView.findViewById(R.id.startTime);
            endTime=itemView.findViewById(R.id.endTime);

        }
    }
}

