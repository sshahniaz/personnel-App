package com.example.personnel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.personnel.DashboardAndMessagesModelClasses.ClockInModel;
import com.example.personnel.DashboardAndMessagesModelClasses.ClockOutModel;
import com.example.personnel.DashboardAndMessagesModelClasses.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //These are the values required for creating the DB.
    public static final String DB_NAME = "personnel.db";
    public static final int DB_VERSION = 1;

    //START-OF-SQLITE-STMT
    //All the variables can be accessed byy the DBHelper.(variable name) format throughout the package.

    //Employee Table -- Contains the main info of the employees
    public final String employeeTable = "employee";
    public final String employeeId = "employee_id";
    public final String firstName = "first_name";
    public final String lastName = "last_name";
    public final String email = "email";
    public final String phoneNumber = "phone_number";
    public final String dateOfBirth = "date_of_birth";
    public final String address = "address";
    public final String position = "position";
    public final String salary = "salary";

    public final String createEmployeeTable = "CREATE TABLE " + employeeTable + " ("
            + employeeId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + firstName + " TEXT,"
            + lastName + " TEXT,"
            + email + " TEXT,"
            + phoneNumber + " INTEGER,"
            + dateOfBirth + " TEXT,"
            + address + " TEXT,"
            + position + " TEXT,"
            + salary + " REAL"
            + ")";

    //User Table -- For login

    public final String usersTable = "users";
    public final String userId = "user_id";
    public final String username = "username";
    public final String password = "password";
    public final String employeeIdFK = "employee_id";

    public final String createUsersTable = "CREATE TABLE " + usersTable + " ("
            + userId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + username + " TEXT,"
            + password + " TEXT,"
            + employeeIdFK + " INTEGER,"
            + "CONSTRAINT fk_employee "
            + "FOREIGN KEY (" + employeeIdFK + ") REFERENCES " + employeeTable + "(" + employeeId + ") ON DELETE CASCADE ON UPDATE CASCADE"
            + ")";

    //Payroll -- for the payslips.

    public final String payrollTable = "payroll";
    public final String payrollId = "payroll_id";
    public final String payrollLink = "link";
    public final String month = "month";
    public final String year = "year";

    public final String createPayrollTable = "CREATE TABLE " + payrollTable + " ("
            + payrollId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + employeeIdFK + " INTEGER,"
            + month + " TEXT,"
            + year + " TEXT,"
            + salary + " REAL,"
            + payrollLink + " TEXT,"
            + "CONSTRAINT fk_employee "
            + "FOREIGN KEY (" + employeeIdFK + ") REFERENCES " + employeeTable + "(" + employeeId + ") ON DELETE CASCADE ON UPDATE CASCADE"
            + ")";

    //Leave/Holiday table -- for managing holidays.

    public final String leaveTable = "leave";
    public final String leaveId = "leave_id";
    public final String startDate = "start_date";
    public final String endDate = "end_date";
    public final String leaveType = "leave_type";
    public final String reason = "reason";
    public final String status = "status";

    public final String createLeaveTable = "CREATE TABLE " + leaveTable + " ("
            + leaveId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + employeeIdFK + " INTEGER,"
            + startDate + " TEXT,"
            + endDate + " TEXT,"
            + leaveType + " TEXT,"
            + reason + " TEXT,"
            + status + " INTEGER,"
            + "CONSTRAINT fk_employee "
            + "FOREIGN KEY (" + employeeIdFK + ") REFERENCES " + employeeTable + "(" + employeeId + ") ON DELETE CASCADE ON UPDATE CASCADE"
            + ")";

    //Attendance -- for the clock-in/clock-out

    public final String attendanceTable = "attendance";
    public final String attendanceId = "attendance_id";
    public final String date = "date";
    public final String clockInTime = "clock_in_time";
    public final String clockOutTime = "clock_out_time";

    public final String createAttendanceTable = "CREATE TABLE " + attendanceTable + " ("
            + attendanceId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + employeeIdFK + " INTEGER,"
            + date + " TEXT,"
            + clockInTime + " TEXT,"
            + clockOutTime + " TEXT,"
            + "CONSTRAINT fk_employee "
            + "FOREIGN KEY (" + employeeIdFK + ") REFERENCES " + employeeTable + "(" + employeeId + ") ON DELETE CASCADE ON UPDATE CASCADE"
            + ")";

    //Rota-Table -- for the rota

    public final String rotaTable = "rota";
    public final String rotaId = "rota_id";

    public final String rotaDate = "date";
    public final String startTime = "start_time";
    public final String endTime = "end_time";
    public final String breakTime = "break_time";

    public final String createRotaTable = "CREATE TABLE " + rotaTable + " ("
            + rotaId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + employeeIdFK + " INTEGER,"
            + rotaDate + " TEXT,"
            + startTime + " TEXT,"
            + endTime + " TEXT,"
            + breakTime + " TEXT,"
            + "CONSTRAINT fk_employee "
            + "FOREIGN KEY (" + employeeIdFK + ") REFERENCES " + employeeTable + "(" + employeeId + ") ON DELETE CASCADE ON UPDATE CASCADE"
            + ")";

    //Messages table -- for the messages
    public final String messagesTable = "messages";
    public final String messageId = "message_id";
    public final String messageTitle = "message_title";

    public final String messageDate = "message_date";

    public final String messageSubject = "message_subject";
    public final String messageTxt = "message_txt";

    public final String createMessagesTable = "CREATE TABLE " + messagesTable + " ("
            + messageId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + messageTitle + " TEXT,"
            + messageDate + " TEXT,"
            + messageSubject + " TEXT,"
            + messageTxt + " TEXT"
            + ")";


    //END-OF-SQLITE-STMT

    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createEmployeeTable);
        db.execSQL(createUsersTable);
        db.execSQL(createPayrollTable);
        db.execSQL(createLeaveTable);
        db.execSQL(createRotaTable);
        db.execSQL(createAttendanceTable);
        db.execSQL(createMessagesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+employeeTable+";");
        db.execSQL("DROP TABLE IF EXISTS "+usersTable+";");
        db.execSQL("DROP TABLE IF EXISTS "+payrollTable+";");
        db.execSQL("DROP TABLE IF EXISTS "+leaveTable+";");
        db.execSQL("DROP TABLE IF EXISTS "+rotaTable+";");
        db.execSQL("DROP TABLE IF EXISTS "+attendanceTable+";");
        db.execSQL("DROP TABLE IF EXISTS "+messagesTable+";");
        onCreate(db);
    }

//    Method-- to insert clock-in values to attendance table

    public  boolean addClockInData(ClockInModel clockIn) {

//        create db object

        SQLiteDatabase db = this.getWritableDatabase();

//        create content values object

        ContentValues inValues = new ContentValues();

//        pass values to content values object from clock-in model class
        inValues.put(employeeId, clockIn.getId());
        inValues.put(date, clockIn.getDate());
        inValues.put(clockInTime, clockIn.getClockInTime());

//        insert values to attendance table
        long insert = db.insert(attendanceTable, null, inValues);

//        test for success
        if (insert==-1) {
            return false;
        } else {

            return true;
        }

    }

    //        method-- update clock-out time in attendance table

    public boolean updateClockOutTime(ClockOutModel clockOut) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues outValues = new ContentValues();

        outValues.put(clockOutTime, clockOut.getClockOutTime());
        outValues.put(employeeId, clockOut.getId());

        int result = db.update(attendanceTable, outValues, "date = ? ", new String[]{clockOut.getDate()});
        return result > 0;
    }

// method-- insert values to messages table
    public boolean setMessages(List<MessageModel> msgList) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean success = true;
        try {
            for (MessageModel msg : msgList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(messageTitle, msg.getMessageTitle());
                contentValues.put(messageDate, msg.getMessageDate());
                contentValues.put(messageSubject, msg.getMessageSubject());
                contentValues.put(messageTxt, msg.getMessageText());
                db.insert(messagesTable, null, contentValues);
            }
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        } finally {
            db.close();
        }
        return success;
    }

//    method-- retrieve values from database

    public List<MessageModel> getAllMessages() {
        List<MessageModel> messageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {messageId, messageTitle, messageDate, messageSubject, messageTxt};
        Cursor c = db.query(messagesTable, columns, null, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            do {
                int  messageIdValue = c.getInt(0);
                String messageTitleValue = c.getString(1);
                String messageDateValue = c.getString(2);
                String messageSubjectValue = c.getString(3);
                String messageTxtValue = c.getString(4);
                MessageModel msg = new MessageModel(messageIdValue, messageTitleValue, messageDateValue, messageSubjectValue, messageTxtValue);
                messageList.add(msg);
            } while (c.moveToNext());

        }

        return messageList;

    }

    public Cursor getAlPayslip(int i) {
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + payrollTable + " WHERE employee_id = 1 ORDER BY payroll_id DESC";
        Cursor res = db.rawQuery(select, null);
        return res;
    }

    // setting placeholders in the SQL query and pass the actual value when the user is logged in
//    public Cursor getAlPayslip(int userId) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String select = "SELECT * FROM " + payrollTable + " WHERE employee_id = ? ORDER BY payroll_id DESC";
//        String[] selectionArgs = {String.valueOf(userId)};
//        Cursor res = db.rawQuery(select, selectionArgs);
//        return res;
//    }




    public List<Rota_Model> getWeek1(int empID) {
        List<Rota_Model> rotaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {rotaId,rotaDate, startTime, endTime};
        String select= " SELECT rota_id, date, start_time, end_time FROM " + rotaTable + " WHERE employee_id=?";
        Cursor c = db.rawQuery(select,new String[]{String.valueOf(empID)});


//                for(int i=0;i<4;i++)
//                {

        int count=0;
        if (c != null && c.moveToFirst()) {

            do{
                int  rotaIdValue = c.getInt(0);

                String rotaDayValue = c.getString(1);
                String rotaStartValue = c.getString(2);
                String rotaEndValue = c.getString(3);

                Rota_Model msg = new Rota_Model(rotaIdValue, rotaDayValue, rotaStartValue, rotaEndValue );
                rotaList.add(msg);

                count++;
            }while(c.moveToNext() && count<7);
        }

        //}


        return rotaList;

    }

    public List<Rota_Model> getWeek2(int empID) {
        List<Rota_Model> rotaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select= " SELECT rota_id, date, start_time, end_time FROM " + rotaTable + " WHERE employee_id=?";
        Cursor c = db.rawQuery(select,new String[]{String.valueOf(empID)});


        int count=0;
        if (c != null  && c.moveToFirst()) {

            c.moveToPosition(7);
            do{
                int  rotaIdValue = c.getInt(0);

                String rotaDayValue = c.getString(1);
                String rotaStartValue = c.getString(2);
                String rotaEndValue = c.getString(3);

                Rota_Model msg = new Rota_Model(rotaIdValue, rotaDayValue, rotaStartValue, rotaEndValue );
                rotaList.add(msg);

                count++;
            }while(c.moveToNext() && count<7);
        }
        return rotaList;

    }

    public List<Rota_Model> getWeek3(int empID) {
        List<Rota_Model> rotaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select= " SELECT rota_id, date, start_time, end_time FROM " + rotaTable + " WHERE employee_id =? ";
        Cursor c = db.rawQuery(select, new String[]{String.valueOf(empID)});

        int count=0;
        if (c != null  && c.moveToFirst()) {
            c.moveToPosition(14);
            do{
                int  rotaIdValue = c.getInt(0);

                String rotaDayValue = c.getString(1);
                String rotaStartValue = c.getString(2);
                String rotaEndValue = c.getString(3);

                Rota_Model msg = new Rota_Model(rotaIdValue, rotaDayValue, rotaStartValue, rotaEndValue );
                rotaList.add(msg);

                count++;
            }while(c.moveToNext() && count<7);
        }
        return rotaList;

    }

    public List<Rota_Model> getWeek4(int empID) {
        List<Rota_Model> rotaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select= " SELECT rota_id, date, start_time, end_time FROM " + rotaTable + " WHERE employee_id =? ";
        Cursor c = db.rawQuery(select, new String[]{String.valueOf(empID)});

        int count=0;
        if (c != null && c.moveToFirst()) {
            c.moveToPosition(21);
            do{
                int  rotaIdValue = c.getInt(0);

                String rotaDayValue = c.getString(1);
                String rotaStartValue = c.getString(2);
                String rotaEndValue = c.getString(3);

                Rota_Model msg = new Rota_Model(rotaIdValue, rotaDayValue, rotaStartValue, rotaEndValue );
                rotaList.add(msg);

                count++;
            }while(c.moveToNext() && count<7);
        }
        return rotaList;

    }
}
