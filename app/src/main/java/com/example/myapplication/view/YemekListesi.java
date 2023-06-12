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

import com.example.myapplication.R;
import com.example.myapplication.adapter.YemekListesiAdapter;
import com.example.myapplication.databinding.ActivityYemekListesiBinding;
import com.example.myapplication.first_screen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class YemekListesi extends AppCompatActivity {
    private FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    ArrayList<YemekListesi> yemekArrayList;

    private ActivityYemekListesiBinding binding;
    YemekListesiAdapter yemekListesiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYemekListesiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        yemekArrayList = new ArrayList<>();

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getData();


        //binding.recyclerView.setLayoutManager(layoutManager);

        //yemekListesiAdapter = new YemekListesiAdapter(yemekArrayList);
        //binding.recyclerView.setAdapter(yemekListesiAdapter);

    }

    private void getData(){

        firebaseFirestore.collection("YemekListesi").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(YemekListesi.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

                if(value != null){
                    yemekArrayList.clear();
                    for(DocumentSnapshot snapshot : value.getDocuments()){
                        Map<String, Object> data = snapshot.getData();

                        //almak istedigim bilgiler
                        String email =(String)data.get("useremail");
                        String kahvalti = (String) data.get("kahvalti");
                        String ogle = (String)data.get("ogle");
                        String aksam = (String)data.get("aksam") ;
                        String aciklama = (String)data.get("aciklama") ;

                        YemekListesi yemekListesi = new YemekListesi();
                        yemekArrayList.add(yemekListesi);

                    }

                    yemekListesiAdapter.notifyDataSetChanged();

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu4) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu4,menu4);

        return super.onCreateOptionsMenu(menu4);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item4) {

        if(item4.getItemId() == R.id.add_yemeklistesi){

            Intent intentToYemekListesi = new Intent(YemekListesi.this, YemekListesiUpload.class);
            startActivity(intentToYemekListesi);

        }else if(item4.getItemId() == R.id.signout){

            auth.signOut();

            Intent intenToMain2 = new Intent(YemekListesi.this, MainActivity.class);
            startActivity(intenToMain2);
            finish();

        }else if(item4.getItemId() == R.id.geri){
            Intent intentToBack = new Intent(YemekListesi.this, first_screen.class);
            startActivity(intentToBack);
        }

        return super.onOptionsItemSelected(item4);
    }

}
