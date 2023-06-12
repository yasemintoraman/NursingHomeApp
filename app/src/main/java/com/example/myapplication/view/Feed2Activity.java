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
import com.example.myapplication.adapter.DuyuruAdapter;
import com.example.myapplication.databinding.ActivityFeedBinding;
import com.example.myapplication.first_screen;
import com.example.myapplication.model.Duyuru;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Feed2Activity extends AppCompatActivity{

        private FirebaseAuth auth;
        private FirebaseFirestore firebaseFirestore;
        ArrayList<Duyuru> duyuruArrayList;
        private ActivityFeedBinding binding3;

        DuyuruAdapter duyuruAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding3 = ActivityFeedBinding.inflate(getLayoutInflater());
            View view = binding3.getRoot();
            setContentView(view);


            duyuruArrayList = new ArrayList<>();

            auth = FirebaseAuth.getInstance();
            firebaseFirestore = FirebaseFirestore.getInstance();

            getData();

            binding3.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            duyuruAdapter = new DuyuruAdapter(duyuruArrayList);
            binding3.recyclerView.setAdapter(duyuruAdapter);
        }


        private void getData() {

            //where filtreleme yapmak icin cok iyi
            firebaseFirestore.collection("Duyurular").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if(error != null){
                        Toast.makeText(Feed2Activity.this, error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }

                    if(value != null){
                        for(DocumentSnapshot snapshot : value.getDocuments()){
                            Map<String, Object> data = snapshot.getData();


                            String userEmail = (String) data.get("useremail");
                            String duyuru1 = (String) data.get("duyuru");
                            String downloadUrl = (String) data.get("downloadurl");

                            Duyuru duyuru = new Duyuru(userEmail,duyuru1,downloadUrl);
                            duyuruArrayList.add(duyuru);
                        }

                        duyuruAdapter.notifyDataSetChanged();
                    }
                }
            });

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) { //menuyu baglama isini yapiyoruz
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.option_menu3,menu); //menuyu bagliyoruz
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            if(item.getItemId() == R.id.add_duyuru){
                //upload activity
                Intent intentToUpload = new Intent(com.example.myapplication.view.Feed2Activity.this,DuyurularActivity.class);
                startActivity(intentToUpload);
            }else if(item.getItemId() == R.id.signout){
                //signout
                auth.signOut(); //cikis yapiyor

                Intent intentToMain = new Intent(com.example.myapplication.view.Feed2Activity.this, MainActivity.class);
                startActivity(intentToMain);
                finish();

            }else if(item.getItemId() == R.id.geri){
            Intent intentToBack = new Intent(Feed2Activity.this, first_screen.class);
            startActivity(intentToBack);
    }

            return super.onOptionsItemSelected(item);
        }

}
