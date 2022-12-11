package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.*;
import java.util.*;

import android.app.DatePickerDialog;
import android.content.*;
import android.widget.*;
import android.os.Bundle;

/**
 * The CreatePaymentActivity class is an Android activity that represents a booking session.
 *
 * <p>It displays a calendar and allows the user to select a start and end date for the payment, and then
 * navigates to the `PaymentDetailActivity` to display the payment details.</p>
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class CreatePaymentActivity extends AppCompatActivity {
    /**
     * The calendar view used to display the dates for the payment.
     */
    private CalendarView calendarView;

    /**
     * The end date for the payment, as a string in the format "yyyy-MM-dd".
     */
    public static String enddate;

    /**
     * The start date for the payment, as a string in the format "yyyy-MM-dd"
     */
    public static String startdate;

    /**
     * Button to continue to payment page
     */
    Button continueInvoiceButton;

    /**
     * The {@link EditText} where the user can enter the start date and the end date when book a room.
     */
    EditText checkInDate, checkOutDate;

    /**
     * The {@link DatePickerDialog} used to choose the range date for booking a room and for the payment.
     */
    DatePickerDialog datePickerDialogEnd,datePickerDialogStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {}
        setContentView(R.layout.activity_create_payment);

        calendarView = findViewById(R.id.paymentdetail_calendar);
        calendarView.setWeekDayTextAppearance(R.style.CalendarViewDateTextAppearance);

        checkInDate = findViewById(R.id.paymentdetail_edittext_start);
        checkOutDate = findViewById(R.id.paymentdetail_edittext_end);

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        datePickerDialogStart = new DatePickerDialog(CreatePaymentActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        checkInDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        startdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    }
                }, mYear, mMonth, mDay);

        datePickerDialogEnd = new DatePickerDialog(CreatePaymentActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        checkOutDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        enddate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    }
                }, mYear, mMonth, mDay);

        checkInDate.setOnClickListener(v -> {
            datePickerDialogStart.show();
        });

        checkOutDate.setOnClickListener(v -> {
            datePickerDialogEnd.show();
        });

        continueInvoiceButton = findViewById(R.id.paymentdetail_button);

        continueInvoiceButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                startdate = checkInDate.getText().toString();
                enddate = checkOutDate.getText().toString();
                Intent move = new Intent(CreatePaymentActivity.this, PaymentDetailActivity.class);
                startActivity(move);
            }
        });
    }
}