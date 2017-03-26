package mislugares.example.org.firebase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Quintero on 18/03/2017.
 */

public class Adapter extends RecyclerView.Adapter <Adapter.InfoviewHolder>implements View.OnClickListener{


    List< LugarTuristico> Lugares;
    private View.OnClickListener listener;

    public Adapter(List<LugarTuristico> lugares) {
       this.Lugares = lugares;
    }

    public static  class InfoviewHolder extends RecyclerView.ViewHolder{
        TextView NombreLugar;
        TextView DescripcionLugar;
        ImageView FotoLugar;
        public RatingBar valoracion;

        public InfoviewHolder(View itemView){
            super(itemView);
            NombreLugar = (TextView)itemView.findViewById(R.id.tVsitio);
            DescripcionLugar = (TextView)itemView.findViewById(R.id.tVtextositio);
            FotoLugar = (ImageView)itemView.findViewById(R.id.iv_tur);
          //  valoracion = (RatingBar) itemView.findViewById(R.id.valoracion);
        }
    }

    @Override
    public InfoviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_recycler,parent,false);
        v.setOnClickListener(this);
        InfoviewHolder holder =new InfoviewHolder(v);
        return holder;
    }
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


    @Override
    public void onBindViewHolder(InfoviewHolder holder, int position) {
        LugarTuristico sitio =Lugares.get(position);
        holder.NombreLugar.setText(sitio.getNombre());
        holder.DescripcionLugar.setText(sitio.getComentario());

        int id = R.drawable.otros;
        switch(sitio.getTipo()) {
            case 1:            id = R.drawable.restaurante; break;
            case 2:            id = R.drawable.bar;         break;
            case 3:            id = R.drawable.copas;       break;
            case 4:            id = R.drawable.espectaculos; break;
            case 5:            id = R.drawable.hotel;       break;
            case 6:            id = R.drawable.compras;     break;
            case 7:            id = R.drawable.educacion;   break;
            case 8:            id = R.drawable.deporte;     break;
            case 9:            id = R.drawable.naturaleza;  break;
            case 10:           id = R.drawable.gasolinera;  break;
        }
        holder.FotoLugar.setImageResource(id);
        holder.FotoLugar.setScaleType(ImageView.ScaleType.FIT_END);
        //holder.valoracion.setRating((float)sitio.getValoracion());

    }

    @Override
    public int getItemCount() {
        return Lugares.size();
    }
}
