package com.example.vuthy.workplease;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateBooking extends AppCompatActivity {

    private EditText dateEditText;
    private EditText timeEditText;
    private Spinner startingStationSpinner;
    private Spinner endingStationSpinner;
    private String startingStation;
    private String endingStation;
    private Button submitButton;
    private Calendar calendar = Calendar.getInstance();
    private Calendar bookingDate = Calendar.getInstance();

    private AlertDialog.Builder builder;

    private String[] khmerMonth = {"មករា", "កុម្ភៈ", "មិនា", "មេសា","ឧសភា", "មិថុនា","កក្ដដា", "សីហា","កញ្ញា", "តុលា","វិច្ឆិកា", "ធ្នូ"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);

        dateEditText = (EditText) findViewById(R.id.booking_date);
        timeEditText = (EditText) findViewById(R.id.booking_time);

        startingStationSpinner = (Spinner) findViewById(R.id.stating_station);
        endingStationSpinner = (Spinner) findViewById(R.id.ending_station);

        submitButton = (Button) findViewById(R.id.submit);

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
            amOrpm = "ល្ងាច";
        } else {
            amOrpm = "ព្រឹក";
        }

        String monthString = khmerMonth[month];

        dateEditText.setHint(dayOfMonth + " " + monthString + " " + year );
        timeEditText.setHint(hour + ":" + minuteString + " " + amOrpm);

        builder = new AlertDialog.Builder(CreateBooking.this);
        builder.setMessage("តើ​អ្នក​យល់ព្រម​កក់​កៅអីឫ?")
                .setTitle("សូមបញ្ជាក់");
        builder.setPositiveButton("យល់ព្រម", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(CreateBooking.this, Homepage.class);
                startActivity(intent);
                return;
            }
        });

        builder.setNegativeButton("បកក្រោយ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        final AlertDialog confirmDialog = builder.create();


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

        startingStationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                startingStation = startingStationSpinner.getItemAtPosition(i).toString();
                Log.d("Starting Station", startingStation);
                return;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        endingStationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                endingStation = endingStationSpinner.getItemAtPosition(i).toString();
                Log.d("Ending Station", endingStation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message;


                if (dateEditText.getText().toString().length() == 0) {
                    message = "សូម​ប្រាប់​ថ្ងៃ​កក់ចេញដំណើរផង!";
                } else if (timeEditText.getText().toString().length() == 0) {
                    message = "ត្រូវ​ការ​ម៉ោង​កក់";
                } else if (startingStation == endingStation) {
                    message = "មិន​អាច​មានគោលដៅ​ដូច​គ្នា​ឡើយ";
                } else {
                    message = "";
                    confirmDialog.show();
                }

                if (message.length() != 0) {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
                    String monthString = khmerMonth[month];
                    dateEditText.setText(day + " " + monthString  + " " + year);
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
                amOrpm = "ល្ងាច";
            } else {
                amOrpm = "ព្រឹក";
            }
            timeEditText.setText(hour  + ":"+ minuteString + amOrpm);
        }
    };
}
