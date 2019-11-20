package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText etAdult,etChildren,etRooms;
    private TextView tvCheckIn,tvCheckOut,tvTotal,tvLocation,tvRoomType,tvTotalRoom,tvServiceC,tvVat,tvOutputCheckIn,tvOutputCheckOut;
    private Button btnCalculate;
    private Boolean d1,d2;

    private Spinner spinLocation,spinRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCheckIn = findViewById(R.id.tvCheckIn);
        tvCheckOut = findViewById(R.id.tvCheckOut);
        etAdult = findViewById(R.id.etAdult);
        etChildren = findViewById(R.id.etChildren);
        etRooms = findViewById(R.id.etRoom);
        spinLocation = findViewById(R.id.sLocation);
        spinRoom = findViewById(R.id.sRoom);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvTotal = findViewById(R.id.tvTotal);
        tvLocation = findViewById(R.id.tvLocation);
        tvRoomType = findViewById(R.id.tvRoomT);
        tvTotalRoom = findViewById(R.id.tvNoOfRoom);
        tvServiceC = findViewById(R.id.tvService);
        tvVat = findViewById(R.id.tvVat);
        tvOutputCheckIn = findViewById(R.id.tvDCheckIn);
        tvOutputCheckOut = findViewById(R.id.tvDCheckOut);

        String Location[] = {"Select Location","Bhaktapur","Chitwan","Kathmandu"};
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                Location
        );
        spinLocation.setAdapter(adapter);

        final String Room[] = {"Select Room Type", "Delux" , "AC", "Platinum"};
        ArrayAdapter adapter1 = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                Room
        );
        spinRoom.setAdapter(adapter1);

        tvCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadDatePicker();
                d1 = true;
            }
        });

        tvCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadDatePicker();
                d2 = true;
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RoomType = spinRoom.getSelectedItem().toString();
                String Place =spinLocation.getSelectedItem().toString();
                String CheckIn = tvCheckIn.getText().toString();
                String CheckOut = tvCheckOut.getText().toString();
                int TotalRooms = Integer.parseInt(etRooms.getText().toString());
                int RoomValue = 0 ;
                int Price;
                int TotalPrice;
                int Vat;
                int ServiceCharge;

                if (RoomType == "Delux"){
                    RoomValue = 2000;
                }
                else if (RoomType == "AC"){
                    RoomValue = 3000;
                }
                else if (RoomType == "Platinum"){
                    RoomValue = 4000;
                }

                Price = RoomValue * TotalRooms;
                Vat = (Price * 13)/100;
                ServiceCharge = (Price * 10)/100;
                TotalPrice = Price + Vat + ServiceCharge;

                tvLocation.setText("Location : ".concat(Place));
                tvRoomType.setText("Room Type : ".concat(RoomType));
                tvOutputCheckIn.setText("CheckIn : ".concat(CheckIn));
                tvOutputCheckOut.setText("CheckOut : ".concat(CheckOut));
                tvTotalRoom.setText("Total Rooms: ".concat(Integer.toString(TotalRooms)));
                tvServiceC.setText("Service Charge: ".concat(Integer.toString(ServiceCharge)));
                tvVat.setText("Vat: ".concat(Integer.toString(Vat)));
                tvTotal.setText("Total : " .concat(Integer.toString(TotalPrice)));

            }
        });

    }

    private void LoadDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(c.YEAR);
        int month = c.get(c.MONTH);
        int day = c.get(c.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = month + "/" + dayOfMonth + "/" + year;
                if (d1 == true){
                    tvCheckIn.setText(date);
                    d1 = false;
                }
                else if (d2 == true)
                {
                    tvCheckOut.setText(date);
                    d2 = true;
                }
            }
        },year,month,day);
        datePickerDialog.show();
    }
}
