package com.example.vuthy.workplease;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class CreateBooking extends AppCompatActivity {

    private EditText dateEditText;
    private EditText timeEditText;
    private Spinner statingStation;
    private Spinner endingStation;
    private Button submit;
    private Calendar calendar = Calendar.getInstance();
    private Calendar bookingDate = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);

        dateEditText = (EditText) findViewById(R.id.booking_date);
        timeEditText = (EditText) findViewById(R.id.booking_time);

        statingStation = (Spinner) findViewById(R.id.stating_station);
        endingStation = (Spinner) findViewById(R.id.ending_station);

        // Disable Auto Focus
        dateEditText.setSelected(false);
        timeEditText.setSelected(false);

        int year, month, dayOfMonth, hour, minute;
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        int am_pm = calendar.get(Calendar.AM_PM);
        Log.d("Check: ", String.valueOf(am_pm));

        String minuteString;
        if (minute < 10 ) {
            minuteString = "0" + minute;
        } else {
            minuteString = String.valueOf(minute);
        }

        String amOrpm;
        if (hour > 12) {
            hour = hour - 12;
            amOrpm = "pm";
        } else {
            amOrpm = "am";
        }

        String monthString = new DateFormatSymbols().getMonths()[month];

        dateEditText.setHint(dayOfMonth + " " + monthString + ", " + year );
        timeEditText.setHint(hour + ":" + minuteString + " " + amOrpm);


        dateEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showDialog(0);
                return false;

            }
        });

        timeEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showDialog(1);
                return false;

            }
        });
        
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 0) {
            // Get today information
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            // BookingDate is auto select today at as default
            DatePickerDialog bookingDateDialog = new DatePickerDialog(this, myDateListener, year, month, dayOfMonth);

            // Set min date to the dialog picker to TODAY
            bookingDateDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

            // Booking date pops up
            return bookingDateDialog;

        } else if (id == 1){
            TimePickerDialog bookingTimeDialog = new TimePickerDialog(this, myTimeListener, 12, 00, false);
            return bookingTimeDialog;

        } else {
            return null;
        }
    }

    public DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int year, int month, int day) {
                    bookingDate.set(year, month, day);
                    String monthString = new DateFormatSymbols().getMonths()[month];
                    dateEditText.setText(day + " " + monthString  + ", " + year);
                }
            };

    private TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            String minuteString;
            if (minute < 10 ) {
                minuteString = "0" + minute;
            } else {
                minuteString = String.valueOf(minute);
            }

            String amOrpm;
            if (hour > 12) {
                hour = hour - 12;
                amOrpm = "pm";
            } else {
                amOrpm = "am";
            }
            timeEditText.setText(hour  + ":"+ minuteString + amOrpm);
        }
    };
}
