package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.personnel.DashboardAndMessagesModelClasses.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class Messages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

  DBHelper dbHelper = new DBHelper(Messages.this);

    List<MessageModel> msgList = new ArrayList<>();

   msgList.add(new MessageModel(-1,
           "Cycle to Work Scheme",
           "Please stay tuned for further communication from the HR department regarding the application process and the timeline for implementation."  ,
           "17 Jul 2023",
           "We are thrilled to announce the launch of our Quick Cycle to Work Scheme, " +
                   "designed to promote a healthier and greener lifestyle while benefiting our valued employees like you. " +
                   "This program provides an excellent opportunity for you to get a brand-new bicycle and necessary safety equipment, " +
                   "all at an incredible discounted rate! It's a win-win for both you and the environment. " +
                   "Program Highlights: " +
                   "Cycling Benefits: The scheme aims to encourage employees to cycle to work instead of using conventional modes of transportation. " +
                   "Regular cycling has numerous benefits, including improved physical fitness, mental well-being, and reduced stress. " +
                   "Financial Incentives: As part of the program, employees can avail a tax-efficient cycle purchase. Under this scheme," +
                   " you can save 40% on the cost of a new bicycle and associated accessories, making it an affordable and appealing option. "));
  msgList.add(new MessageModel(-1,
          "Upcoming Meeting",
         "Please accept your calendar invites on Outlook as soon as they come through, thanks." ,
          "05 Jul 2023",
          "We would like to remind you of an important meeting scheduled for 10:00, on 10-06-2023 on Zoom." +
                  " Agenda: Mid Quarter results, South region strategizing, Pow-wow"));
       msgList.add(new MessageModel(-1,
        "Internal Vacancies",
        "New opportunities within the business, please read on for further details.",
        "07 Jun 2023",
               "Hi everyone, " +
               " We are currently seeking to fill two brand new positions that have been made available in the Cheshire office. " +
                       "Position 1: Senior Administrator, remote/hybrid working, 38 hours per week." +
                       "Position 2: Administrative Assistant, remote/hybrid working, 35 hours per week." +
                       "If you are interested in knowing more please email Rachel. " +
                       "Thanks"));

       msgList.add(new MessageModel(-1,
               "H&S visits",
               "Health and Safety inspections have been scheduled, please note your visit dates in the information provided below.",
               "07 Apr 2023",
               "Dear Team,\n" +
                       "\n" +
                       "We hope this memo finds you well. We are writing to inform you that a routine health and safety inspection has been scheduled at our premises. The purpose of this inspection is to ensure that our workplace remains a safe and secure environment for all employees.\n" +
                       "\n" +
                       "Date: 24-04-2023\n" +
                       "Time: 10:00\n" +
                       "\n" +
                       "The inspection will be carried out by James Cooper from QuickFire H&S. During the inspection, the inspector will assess various aspects of our workplace, including but not limited to:\n" +
                       "\n" +
                       "Fire Safety: Verification of fire exits, emergency evacuation procedures, and fire extinguisher maintenance.\n" +
                       "\n" +
                       "Electrical Safety: Assessment of electrical equipment, cords, and outlets for potential hazards.\n" +
                       "\n" +
                       "Ergonomics: Evaluation of workstation setups to ensure ergonomic standards are met.\n" +
                       "\n" +
                       "Hazardous Substances: Inspection of proper storage and handling of hazardous materials.\n" +
                       "\n" +
                       "General Safety: Review of safety signage, first aid provisions, and general housekeeping.\n" +
                       "\n" +
                       "Employee Compliance: Ensuring that all employees are following health and safety guidelines and using appropriate personal protective equipment (PPE) when necessary." +
                       "\n" +
                       "Please can you all send confirmation the whole team have read and understood this information."));

       boolean success = dbHelper.setMessages(msgList);
//
     if (success) {
           Toast.makeText(Messages.this, "All rows inserted successfully", Toast.LENGTH_LONG).show();
       } else {
           Toast.makeText(Messages.this, "Failed to insert messages", Toast.LENGTH_LONG).show();
       }
//
////            textViewMsg.setText(msg.toString());
        //   DBHelper dbHelper = new DBHelper(messages.this);


    }
}