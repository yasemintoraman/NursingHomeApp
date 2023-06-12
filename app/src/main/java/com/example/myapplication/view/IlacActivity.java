package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityIlacBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class IlacActivity extends AppCompatActivity {

    private ActivityIlacBinding binding;

    private FirebaseStorage firebaseStorage;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ilac);
        binding = ActivityIlacBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void kaydetButtonClicked(View view){
        String adi = binding.editTextName.getText().toString();
        String aciklama = binding.editTextIlacAciklama.getText().toString();
        String ilacSaati = binding.editTextTime.getText().toString();
        String gunSayisi = binding.textViewGun.getText().toString();

        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();

        HashMap<String, Object> ilacData = new HashMap<>();
        ilacData.put("useremail",email);
        ilacData.put("ilacsaati",ilacSaati);
        ilacData.put("adi",adi);
        ilacData.put("aciklama", aciklama);
        ilacData.put("gunsayisi",gunSayisi);
        ilacData.put("date", FieldValue.serverTimestamp()); //eklendigi tarihi alir

        firebaseFirestore.collection("Ilaclar").add(ilacData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Intent intent2 = new Intent(IlacActivity.this, IlacBilgileri.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(IlacActivity.this, e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void iptalButtonClicked(View view){
        Intent intent3 = new Intent(IlacActivity.this, IlacBilgileri.class);
        startActivity(intent3);
    }
}
