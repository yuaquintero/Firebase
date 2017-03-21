package mislugares.example.org.firebase;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import static android.R.attr.id;

/**
 * Created by Quintero on 20/03/2017.
 */

public class LugarActivity extends AppCompatActivity {



    private TextView Lynombre;
    private TextView Lydireccion;
    private TextView Lytelefono;
    private TextView Lyemail;
    private TextView Lydistancia;
    private TextView Lyruta;
    private double Lylat;
    private double Lylon;
    private EditText Lyfoto;
    private double Lyvaloracion;
    private TextView Lycomentario;
    private long id;



    LugarTuristico miLugar;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */




    public LugarActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_turismo);
        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id", -1);
        miLugar=TurismoActivity.Lugares.get(id);

        Lynombre = (TextView) findViewById(R.id.nombre);
        Lynombre.setText( miLugar.getNombre());

        Lydireccion = (TextView) findViewById(R.id.dir);
        Lydireccion.setText(miLugar.getDireccion());

        Lytelefono = (TextView) findViewById(R.id.tel);
        Lytelefono.setText(Integer.toString(miLugar.getTelefono()));

        Lyemail = (TextView) findViewById(R.id.temail);
        Lyemail.setText(miLugar.getEmail());

        Lycomentario = (TextView) findViewById(R.id.comentario);
        Lycomentario.setText(miLugar.getComentario());

        Lydistancia= (TextView) findViewById(R.id.dis);
        Lydistancia.setText(miLugar.getDistancia());

        Lyruta = (TextView) findViewById(R.id.truta);
        Lyruta.setText(miLugar.getRuta());




    }
}
