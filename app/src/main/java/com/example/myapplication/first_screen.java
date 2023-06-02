package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.view.FeedActivity;

public class first_screen extends AppCompatActivity {

    ImageButton foto_paylasim;
    ImageButton yemek_listesi;
    ImageButton etkinlik_programi;
    ImageButton ilac_takibi;
    ImageButton duyurular;
    ImageButton message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        foto_paylasim = (ImageButton)findViewById(R.id.imageButton);
        foto_paylasim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tiklandiginda yapilacak islemler burada yer alacak
                Intent intent = new Intent(first_screen.this, FeedActivity.class); //hangi activity gelmesini istiyorsak feed yerine onu yazariz
                startActivity(intent);
            }
        });


        yemek_listesi = (ImageButton)findViewById(R.id.imageButton2);
        yemek_listesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_yemek = new Intent(first_screen.this, YemekListesi.class);
                startActivity(intent_yemek);
            }
        });

        duyurular = (ImageButton)findViewById(R.id.imageButton3);
        duyurular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_duyurular = new Intent(first_screen.this, Duyurular.class);
                startActivity(intent_duyurular);
            }
        });


        ilac_takibi = (ImageButton)findViewById(R.id.imageButton4);
        ilac_takibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(first_screen.this,IlacTakibi.class);
                startActivity(intent2);
            }
        });

        message =  (ImageButton)findViewById(R.id.imageButton6);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(first_screen.this,Message.class);
                startActivity(intent3);
            }
        });
    }
}