package com.example.personnel.holidayFiles;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personnel.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HolidayListAdapter extends RecyclerView.Adapter<HolidayListAdapter.HolidayCardViewHolder> {
    private Date startDate;
    private Date endDate;

    Context context;
    List<HolidayCardDataModel> models;
    public HolidayListAdapter(@NonNull Context context, @NonNull List<HolidayCardDataModel> objects) {
        this.context = context;
        this.models = objects;

    }


    @NonNull
    @Override
    public HolidayCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflater and viewholder
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_cards,parent,false);
        HolidayCardViewHolder holder = new HolidayCardViewHolder(cardView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayCardViewHolder holder, int position) {

        HolidayCardDataModel holidayCardDataModel = models.get(position);

        SimpleDateFormat mainDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");

        try {
            startDate = mainDateFormat.parse(holidayCardDataModel.getStartDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            endDate = mainDateFormat.parse(holidayCardDataModel.getEndDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //Day formatted and displayed and checks if the dates are the same or not.
        if((startDate.getTime() == endDate.getTime())){
            holder.day.setText(dayFormat.format(startDate));
        }else {
           holder.day.setText(dayFormat.format(startDate)+" - "+dayFormat.format(endDate));
        }

        holder.month.setText(monthFormat.format(startDate));
        holder.type.setText(holidayCardDataModel.getLeaveType());

        //leave status changed 0 == Pending, -1 == rejected, 1 == approved.
        if(holidayCardDataModel.getStatus() == 0){
           holder.status.setText("Pending");
           holder.status.setTextColor(Color.parseColor("#707070"));
        } else if (holidayCardDataModel.getStatus() == 1) {
           holder.status.setText("Approved");
            holder.status.setTextColor(Color.parseColor("#82AAE3"));
        }else {
           holder.status.setText("Rejected");
            holder.status.setTextColor(Color.parseColor("#e38282"));
        }


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class HolidayCardViewHolder extends RecyclerView.ViewHolder {
        TextView day;
        TextView month;
        TextView type;
        TextView status;
        public HolidayCardViewHolder(@NonNull View itemView) {
            super(itemView);
            this.day = itemView.findViewById(R.id.holiday_cards_day);
            this.month = itemView.findViewById(R.id.holiday_cards_month);
            this.type = itemView.findViewById(R.id.holiday_cards_holiday_type);
            this.status =itemView.findViewById(R.id.holiday_cards_status);
        }
    }

}
