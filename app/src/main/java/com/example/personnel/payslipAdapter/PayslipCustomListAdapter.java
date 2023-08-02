package com.example.personnel.payslipAdapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.personnel.R;
import java.util.ArrayList;

public class PayslipCustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> dataList;
    //private final String fileName = "payslip.pdf";

    public PayslipCustomListAdapter(Activity context, ArrayList<String> dataList) {
        super(context, R.layout.payslip_list_view, dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 0) {
            convertView = LayoutInflater.from(context).inflate(R.layout.payslip_list_view_first_item, parent, false);
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.payslip_list_view, parent, false);
        }

        TextView month = convertView.findViewById(R.id.month);
        TextView date = convertView.findViewById(R.id.date);
        TextView amount = convertView.findViewById(R.id.amount);
        ImageView downloadIcon = convertView.findViewById(R.id.downloadIcon);

        String rowData = dataList.get(position);
        String[] values = rowData.split("\n");
        month.setText(values[0]);
        date.setText(values[1]);
        amount.setText(values[2]);

        downloadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //downloadPdfFile();
                String websiteUrl = "https://www.dropbox.com/scl/fi/0m2nxdh8vdsf6bfldow4z/payslip.pdf?rlkey=dwmkwtvwxllflbtvl70ovhmxo&dl=0";

                // Intent for the browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));

                // Check if there is a web browser app for the intent
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Please download a pdf viewer app", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }
}
