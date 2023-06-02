package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IlacTakibi extends AppCompatActivity {

    Button kaydet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_takibi);

        kaydet = (Button)findViewById(R.id.button5);
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IlacTakibi.this, first_screen.class); //hangi activity gelmesini istiyorsak feed yerine onu yazariz
                startActivity(intent);
            }
        });


    }
}