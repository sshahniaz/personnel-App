package com.example.personnel.holidayFiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.personnel.R;

import java.util.List;

public class HolidayListAdapter extends ArrayAdapter<HolidayCardDataModel> {

    public HolidayListAdapter(@NonNull Context context, @NonNull List<HolidayCardDataModel> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listview = convertView;

        if (listview == null) {
            listview = LayoutInflater.from(getContext()).inflate(R.layout.holiday_cards, parent, false);
        }

        HolidayCardDataModel holidayCardDataModel = getItem(position);
        TextView day = listview.findViewById(R.id.holiday_cards_day);
        TextView month = listview.findViewById(R.id.holiday_cards_month);
        TextView type = listview.findViewById(R.id.holiday_cards_holiday_type);
        TextView status = listview.findViewById(R.id.holiday_cards_status);

        //Date data formatting for being suitable for display.




        return listview;
    }
}
