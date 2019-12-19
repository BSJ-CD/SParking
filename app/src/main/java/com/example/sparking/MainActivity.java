package com.example.sparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button parking, rend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parking = (Button) findViewById(R.id.Parking);
        rend = (Button) findViewById(R.id.Rend);

        parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ParkingActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        rend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RendActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }
}
