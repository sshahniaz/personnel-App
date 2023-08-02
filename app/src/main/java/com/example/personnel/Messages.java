package com.example.personnel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.personnel.DashboardAndMessagesModelClasses.MessageModel;
import com.example.personnel.MessageAdapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class Messages extends AppCompatActivity {
    RecyclerView recyclerViewMsg;
    MessageAdapter mainAdapter;
    List<MessageModel> messageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);



//        Insert values
  DBHelper dbHelper = new DBHelper(Messages.this);

    List<MessageModel> msgList = new ArrayList<>();

        msgList.add(new MessageModel(-1,
                "New Expenses System",
                "18 Jul 2023" ,
                "We are launching an improved Expenses Management System, to streamline the reimbursement process and simplify expense reporting for all employees. " ,
                "Key Features of the New Expenses System:\n" +
                        "\n" +
                        "User-Friendly Interface: The new system boasts a user-friendly interface, making it easy for all employees to navigate and submit their expense claims efficiently.\n" +
                        "\n" +
                        "Mobile Accessibility: The system is accessible via mobile devices, allowing you to capture receipts and submit expenses on-the-go, eliminating the need for manual paperwork.\n" +
                        "\n" +
                        "Faster Reimbursements: With automated workflows, the processing time for expense reimbursements will be significantly reduced, ensuring quicker access to your funds.\n" +
                        "\n" +
                        "Real-Time Tracking: You will have real-time visibility into the status of your submitted expenses, allowing you to monitor the progress of your reimbursement requests.\n" +
                        "\n" +
                        "Policy Compliance: The system is designed to ensure strict adherence to company expense policies, reducing the likelihood of errors or policy violations.\n" +
                        "\n" +
                        "Getting Started:\n" +
                        "To begin using the new Expenses Management System, please follow the steps below:\n" +
                        "\n" +
                        "Access the System: On 29-07-2023, an email with login credentials and instructions on how to access the system will be sent to your official work email.\n" +
                        "\n" +
                        "Training Sessions: To familiarize you with the new system, we will be conducting training sessions. Details regarding the training schedule will be shared shortly.\n" +
                        "\n" +
                        "Receipts and Documentation: As usual, ensure that you retain all necessary receipts and documentation for your expense claims.\n" +
                        "\n" +
                        "Support and Assistance:\n" +
                        "We understand that adapting to a new system may raise questions or concerns. Our dedicated support team will be available to assist you with any queries or issues you may encounter during this transition.\n" +
                        "\n" +
                        "Should you need any guidance or encounter technical difficulties, please contact Eric.\n" +
                        "\n" +
                        "We believe that the introduction of this new Expenses Management System will not only simplify the expense reporting process but also improve overall efficiency and accuracy. Your cooperation and active participation in utilizing the new system are vital to its success.\n" +
                        "\n" +
                        "We look forward to a smooth implementation and thank you for your continued dedication to maintaining an effective and responsible expense management process. "));

   msgList.add(new MessageModel(-1,
           "Cycle to Work Scheme",
           "17 Jul 2023" ,
           "Please stay tuned for further communication from the HR department regarding the application process and the timeline for implementation." ,
           "We are thrilled to announce the launch of our Quick Cycle to Work Scheme, " +
                   "designed to promote a healthier and greener lifestyle while benefiting our valued employees like you. " +
                   "This program provides an excellent opportunity for you to get a brand-new bicycle and necessary safety equipment, " +
                   "all at an incredible discounted rate! It's a win-win for both you and the environment. " +
                   "\n" +
                   "Program Highlights: " +
                   "\n" +
                   "Cycling Benefits: The scheme aims to encourage employees to cycle to work instead of using conventional modes of transportation. " +
                   "\n" +
                   "Regular cycling has numerous benefits, including improved physical fitness, mental well-being, and reduced stress. " +
                   "\n" +
                   "Financial Incentives: As part of the program, employees can avail a tax-efficient cycle purchase. Under this scheme," +
                   " you can save 40% on the cost of a new bicycle and associated accessories, making it an affordable and appealing option. "));
  msgList.add(new MessageModel(-1,
          "Upcoming Meeting",
          "05 Jul 2023",
         "Please accept your calendar invites on Outlook as soon as they come through, thanks." ,

          "We would like to remind you of an important meeting scheduled for 10:00, on 10-06-2023 on Zoom." +
                  " Agenda:  " +
                  "\n" +
                  " Mid Quarter results. " +
                  "\n" +
                  "South region strategizing." +
                  "\n" +
                  "Pow-wow."));
       msgList.add(new MessageModel(-1,
        "Internal Vacancies",
               "07 Jun 2023",
        "New opportunities within the business, please read on for further details.",
               "Hi everyone, " +
                       "\n" +
               " We are currently seeking to fill two brand new positions that have been made available in the Cheshire office. " +
                       "\n" +
                       "Position 1: Senior Administrator, remote/hybrid working, 38 hours per week." +
                       "\n" +
                       "Position 2: Administrative Assistant, remote/hybrid working, 35 hours per week." +
                       "\n" +
                       "If you are interested in knowing more please email Rachel. " +
                       "\n" +
                       "Thanks"));

       msgList.add(new MessageModel(-1,
               "H&S visits",
               "07 Apr 2023",
               "Health and Safety inspections have been scheduled, please note your visit details in the information provided below.",
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

        msgList.add(new MessageModel(-1,
                "Internal Vacancies",
                "16 Mar 2023",
                "New opportunities within the business, please read on for further details.",
                "Hi everyone, " +
                        "\n" +
                        " Please see a round up of current vacancies in the business " +
                        "\n" +
                        "Position 1: Customer Service Manager, Hammersmith Office, 38 hours per week." +
                        "\n" +
                        "Position 2: Administrative Assistant, Chelmsford Office, 35 hours per week." +
                        "\n" +
                        "Position 3: Operations Manager, Chelmsford Office, 38 hours per week." +
                        "\n" +
                        "Position 4: Computer Engineer, Head Office, 35 hours per week." +
                        "\n" +
                        "If you are interested in knowing more please email Rachel. " +
                        "\n" +
                        "Thanks"));

        msgList.add(new MessageModel(-1,
                "Charity Event",
                "07 Mar 2023",
                "We are thrilled to announce an exciting opportunity for all of us to come together and make a positive impact in our community.",

                        "Our company will be hosting a charity bake sale on April 4th" +
                        " to support a cause that is close to our hearts.\n" +
                        "\n" +
                        "Event Details:\n" +
                        "Date: 04-04-2023\n" +
                        "Time: 10:00-14:00\n" +
                        "Location: In office\n" +
                        "\n" +
                        "Purpose of the Event:\n" +
                        "The primary objective of this charity event is to raise funds and awareness for Farleigh Hospice, a non-profit organization dedicated to " +
                        "End of life care and support for family members.\n" +
                        "\n" +
                        "Event Highlights:\n" +
                        "\n" +
                        "Bake Sale\n" +
                        "\n" +
                        "Competition for best muffins\n" +
                        "\n" +
                        "\n" +
                        "Get Involved:\n" +
                        "We encourage each and every one of you to participate in this event and contribute to the cause. There are several ways you can get involved:\n" +
                        "\n" +
                        "Donate: Even if you can't attend the event, you can still make a difference by making a donation. Details on how to donate will be shared in the coming days.\n" +
                        "\n" +
                        "Spread the Word: Share the event details with your friends, family, and social media networks to help raise awareness and encourage more people to participate.\n" +
                        "\n" +
                        "Let's come together as a team to make a positive impact and show our support for this worthy cause." +
                        " We believe that our collective efforts can make a significant difference in the lives of those in need.\n" +
                        "\n" +
                        "Stay tuned for more updates and further details about the event. For any questions or suggestions, please reach out to Rachel Stevens.\n" +
                        "\n" +
                        "Thank you for your generosity and compassion. Let's make this charity event a grand success!"));



       boolean success = dbHelper.setMessages(msgList);
//
     if (success) {
           Toast.makeText(Messages.this, "All rows inserted successfully", Toast.LENGTH_SHORT).show();
       } else {
           Toast.makeText(Messages.this, "Failed to insert messages", Toast.LENGTH_SHORT).show();
       }


//return all messages from database
        DBHelper dbHelper2 = new DBHelper(Messages.this);
        messageList = dbHelper2.getAllMessages();

//        set adapter to recycler view
        mainAdapter = new MessageAdapter(messageList);
        recyclerViewMsg = findViewById(R.id.myRecyclerView);

        // LinearLayoutManager ALWAYS CHECK orientation!!!!!!!!
        recyclerViewMsg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

//      increases performance- only set if sizes wont change
        recyclerViewMsg.setHasFixedSize(true);
        recyclerViewMsg.setAdapter(mainAdapter);

// method-- to remove item from recycler view from dialog box
        mainAdapter.setDialogActionListener(new MessageAdapter.OnDialogActionListener() {
            @Override
            public void onMessageClose(int position) {
                showCustomDialog(position);
            }
        });
        recyclerViewMsg.setAdapter(mainAdapter);
    }

    public void showCustomDialog(int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Messages.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.delete_dialog_popup, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);

        Button yesBtn = dialogView.findViewById(R.id.yesBtn);
        Button noBtn = dialogView.findViewById(R.id.noBtn);
        AlertDialog alertDialog = dialogBuilder.create();

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAdapter.deleteMessage(position);
                alertDialog.dismiss();
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });



        alertDialog.show();
    }

    }
