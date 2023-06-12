package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityYemekListesiBinding;
import com.example.myapplication.first_screen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class YemekListesiUpload extends AppCompatActivity {

    private ActivityYemekListesiBinding binding;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ilac);
        binding = ActivityYemekListesiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void yemekKaydetButtonClicked(View view){
        String kahvalti = binding.breakfastText.getText().toString();
        String ogle = binding.breakfastText.getText().toString();
        String aksam = binding.lunchText4.getText().toString();
        String aciklama = binding.descriptionText.getText().toString();


        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();

        HashMap<String, Object> yemekData = new HashMap<>();
        yemekData.put("useremail",email);
        yemekData.put("kahvalti",kahvalti);
        yemekData.put("ogle",ogle);
        yemekData.put("aksam", aksam);
        yemekData.put("aciklama",aciklama);

        yemekData.put("date", FieldValue.serverTimestamp()); //eklendigi tarihi alir

        firebaseFirestore.collection("Yemek Listesi").add(yemekData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Intent intent2 = new Intent(YemekListesiUpload.this, YemekListesi.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(YemekListesiUpload.this, e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu5) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu5,menu5);

        return super.onCreateOptionsMenu(menu5);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item5) {

        if((item5.getItemId() == R.id.signout)){
            auth.signOut();

            Intent intenToMain5 = new Intent(YemekListesiUpload.this, MainActivity.class);
            startActivity(intenToMain5);
            finish();
        }else if(item5.getItemId() == R.id.geri){
            Intent intentToBack = new Intent(YemekListesiUpload.this, first_screen.class);
            startActivity(intentToBack);
        }

        return super.onOptionsItemSelected(item5);
    }
}