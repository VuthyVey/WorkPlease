package com.example.vuthy.workplease;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private Spinner stating_station;
    private Spinner ending_station;
    private Button submit;
    private Calendar calendar = Calendar.getInstance();
    private Calendar bookingDate = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);

        dateEditText = (EditText) findViewById(R.id.booking_date);
        timeEditText = (EditText) findViewById(R.id.booking_time);


        int year, month, dayOfMonth, hour, minute;
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        int am_pm = calendar.get(Calendar.AM_PM);
        Log.d("Check: ", String.valueOf(am_pm));
        String amOrpm = new String("am");
        if (hour > 12) {
            hour = hour - 12;
            amOrpm = "pm";
        } else {
            amOrpm = "am";
        }

        String monthString = new DateFormatSymbols().getMonths()[month];

        dateEditText.setHint(dayOfMonth + " " + monthString + ", " + year );
        timeEditText.setHint(hour + ":" + minute + " " + amOrpm);

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });

        dateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showDialog(0);
                }
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });

        timeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showDialog(1);
                }
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
            String amOrpm = new String("am");
            if (hour > 12) {
                hour = hour - 12;
                amOrpm = "pm";
            } else {
                amOrpm = "am";
            }
            timeEditText.setText(hour  + ":"+ minute + amOrpm);
        }
    };
}
