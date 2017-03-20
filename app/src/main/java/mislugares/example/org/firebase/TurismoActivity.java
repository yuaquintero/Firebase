package mislugares.example.org.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quintero on 20/03/2017.
 */

public class TurismoActivity extends AppCompatActivity {

    RecyclerView rv;
    List<LugarTuristico> Lugares;
    private Firebase FBData;

    Adapter adapter;
    private  static final String FirebaseUrl="https://turismo-a3b2c.firebaseio.com/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turismo);

        rv = (RecyclerView) findViewById(R.id.recy);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Lugares = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter= new Adapter(Lugares);
        rv.setAdapter(adapter);

        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Lugares.removeAll(Lugares);

                for (DataSnapshot snapchat:dataSnapshot.getChildren()) {
                    LugarTuristico lugar=snapchat.getValue(LugarTuristico.class);
                    Lugares.add(lugar);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



}
