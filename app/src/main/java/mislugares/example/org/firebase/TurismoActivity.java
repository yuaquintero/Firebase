package mislugares.example.org.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
    public static  List<LugarTuristico> Lugares;
    private Firebase FBData;
    public  static Turismo LTur;

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

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num =rv.getChildAdapterPosition(v);
                String msg="se presionó  "+ Integer.toString(num);

                Toast.makeText(TurismoActivity.this,msg,Toast.LENGTH_SHORT).show();
                lanzarLugarTurismo(v, num);
            }
        });

    }

    public void lanzarLugarTurismo(View view, int pos){
        Intent i = new Intent(this, LugarActivity.class);
        //long id = (long)pos;
        i.putExtra("id", pos);
        startActivity(i);

    }

}
