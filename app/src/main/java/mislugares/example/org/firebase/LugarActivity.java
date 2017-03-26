package mislugares.example.org.firebase;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Quintero on 20/03/2017.
 */

public class LugarActivity extends AppCompatActivity  implements ActivityCompat.OnRequestPermissionsResultCallback{



    private TextView Tvnombre;
    private TextView Tvdireccion;
    private TextView Tvtelefono;
    private TextView Tvemail;
    private TextView Tvdistancia;
    private TextView Tvruta;
    private double Tvlat;
    private double Tvlon;
    private EditText Tvfoto;
    private double Tvvaloracion;
    private TextView Tvcomentario;
    private ImageView ViewFoto;
    private long id;
    private Permisos permiso;
    private View miLayout;
    LugarTuristico miLugar;
    final Context contexto = this;
    private Button b_califica;
    private TextView tv_nota;
    private ImageButton b_telefono;
    private ImageButton b_correo;

    private static final String urlStorage="gs://turismo-a3b2c.appspot.com";
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef;
    private static  String FotoStore;
    public LugarActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_turismo);
        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id", -1);
        miLugar=TurismoActivity.Lugares.get(id);
        miLayout = findViewById(R.id.Lugar);

        FotoStore=miLugar.getFoto();

        storageRef= storage.getReferenceFromUrl(urlStorage).child(FotoStore);

        Tvnombre = (TextView) findViewById(R.id.nombre);
        Tvnombre.setText( miLugar.getNombre());

        Tvdireccion = (TextView) findViewById(R.id.dir);
        Tvdireccion.setText(miLugar.getDireccion());

        Tvcomentario = (TextView) findViewById(R.id.comentario);
        Tvcomentario.setText(miLugar.getComentario());

        ViewFoto= (ImageView) findViewById(R.id.foto);

        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                ViewFoto.setImageBitmap(bitmap);
            }
        });

        Tvtelefono = (TextView) findViewById(R.id.tel);
        String tel=String.valueOf(miLugar.getTelefono());
        Tvtelefono.setText(String.valueOf(miLugar.getTelefono()));

        Tvemail = (TextView) findViewById(R.id.temail);
        Tvemail.setText(miLugar.getEmail());

        Tvdistancia = (TextView) findViewById(R.id.dis);
        Tvdistancia.setText(miLugar.getDistancia());

        Tvruta = (TextView) findViewById(R.id.ruta);
        Tvruta.setText(miLugar.getRuta());
        //tv_nota = (TextView)findViewById(R.id.tv_nota);

        permiso = new Permisos(miLayout,LugarActivity.this);

            b_telefono = (ImageButton) findViewById(R.id.b_tel);
            b_telefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tele=String.valueOf(miLugar.getTelefono());

                    if(tele != null)
                    permiso.LanzarLlamada(String.valueOf(miLugar.getTelefono()));
                    else {
                        String msg=getString(R.string.no_tele);
                        Toast.makeText(LugarActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }
                }

            });

        b_califica = (Button)findViewById(R.id.b_califica);
        b_califica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomDialog(miLayout);
            }
        });

            //invocar código para enviar mail
            b_correo = (ImageButton) findViewById(R.id.mail);
            b_correo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mail=miLugar.getEmail();
                    if(mail != null)
                        permiso.Lanzar_Email(miLugar.getEmail());
                    else {
                            String msg=getString(R.string.no_mail);
                            Toast.makeText(LugarActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
//        permissionCheck = ContextCompat.checkSelfPermission(miActividad, Manifest.permission.CALL_PHONE);
        if (requestCode == permiso.SOLICITAR_PERMISO_LLAMADAS) {
            // Si la solicitud es cancelada, grantResults estará vacío.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(miLayout, getString(R.string.permiso_si),
                        Snackbar.LENGTH_SHORT)
                        .show();
//                permissionCheck = ContextCompat.checkSelfPermission(miActividad, Manifest.permission.CALL_PHONE);
                // permiso concedido, ejecutar la acción.
                permiso.HacerLlamada();

            } else {

                Snackbar.make(miLayout, getString(R.string.permiso_no),
                        Snackbar.LENGTH_SHORT)
                        .show();
                // permiso negado, no se puede continuar
            }
        }
    }

    public void openCustomDialog(View view){
        // dialogo personalizado
        final Dialog dialog = new Dialog(contexto);
        //asignar un layout XML para el diálogo
        dialog.setContentView(R.layout.dialogo_calificar);

        // personalizar componentes del XML
        Button botCalificar = (Button) dialog.findViewById(R.id.b_aceptar);
        final RatingBar rbValoracion = (RatingBar) dialog.findViewById(R.id.rb_calificar);

        //Acción del botón, cerrar ventana al terminar
        botCalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float stars = rbValoracion.getRating();
                //tv_nota.setText(String.valueOf(stars));
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
