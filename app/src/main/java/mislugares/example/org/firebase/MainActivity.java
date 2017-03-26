package mislugares.example.org.firebase;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static ConnectivityManager manager;
    private final Context mContext=this;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bSalir =(Button) findViewById(R.id.bConexion);
        bSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

               if(isConnectedWifi(mContext)|| isConnectedMobile(mContext) )
                {
                    if(isOnlineNet())
                        lanzarTurismo(view);
                    else {
                        String msg=getString(R.string.no_conexion);
                        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    String msg=getString(R.string.no_conexion);
                    Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    /**
     * Comprueba si exite conexion a internet a traves de cualquier interfaz de red
     * mediante la realizacion de un ping a la pagina www.google.es
     * @return Estado de la conexion a internet
     */
    public Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Compueba si existe conexion a traves de la red WiFi
     * @param context Contexto
     * @return Estado de la conexion
     */
        public static boolean isConnectedWifi(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }

    /**
     * Compueba si existe conexion a traves de la red de datos movil.
     * @param context
     * @return Estado de la conexion.
     */
        public static boolean isConnectedMobile(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }

    public void lanzarTurismo(View view){
        Intent i = new Intent(this, TurismoActivity.class);
        startActivity(i);
    }
    }
