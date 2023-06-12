package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.IlacAdapter;
import com.example.myapplication.databinding.ActivityIlacBilgileriBinding;
import com.example.myapplication.first_screen;
import com.example.myapplication.model.Ilac;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class IlacBilgileri extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    ArrayList<Ilac> ilacArrayList;
    private ActivityIlacBilgileriBinding binding2;
    IlacAdapter ilacAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ilac_bilgileri);
        binding2 = ActivityIlacBilgileriBinding.inflate(getLayoutInflater());
        View view = binding2.getRoot();
        setContentView(view);

        ilacArrayList = new ArrayList<>();

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getData();

        binding2.recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        ilacAdapter = new IlacAdapter(ilacArrayList);
        binding2.recyclerView2.setAdapter(ilacAdapter);

    }

    private void getData(){

        firebaseFirestore.collection("Ilaclar").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(IlacBilgileri.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

                if(value != null){
                    ilacArrayList.clear();
                    for(DocumentSnapshot snapshot : value.getDocuments()){
                        Map<String, Object> data = snapshot.getData();

                        //almak istedigim bilgiler
                        String userEmail =(String)data.get("useremail");
                        String ilacAdi = (String) data.get("adi");
                        String aciklama = (String)data.get("aciklama");
                        String gun = (String)data.get("gunsayisi") ;
                        String ilacSaati = (String)data.get("ilacsaati") ;


                        Ilac ilac = new Ilac(aciklama,ilacAdi,userEmail,gun,ilacSaati);
                        ilacArrayList.add(ilac);

                    }

                    ilacAdapter.notifyDataSetChanged();

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu2) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu2,menu2);

        return super.onCreateOptionsMenu(menu2);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item2) {

        if(item2.getItemId() == R.id.add_ilac){

            Intent intentToIlac = new Intent(IlacBilgileri.this, IlacActivity.class);
            startActivity(intentToIlac);

        }else if(item2.getItemId() == R.id.signout){

            auth.signOut();

            Intent intenToMain2 = new Intent(IlacBilgileri.this, MainActivity.class);
            startActivity(intenToMain2);
            finish();

        }else if(item2.getItemId() == R.id.geri){
            Intent intentToBack = new Intent(IlacBilgileri.this, first_screen.class);
            startActivity(intentToBack);
        }

        return super.onOptionsItemSelected(item2);
    }


}