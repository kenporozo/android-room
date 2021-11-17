package cl.inacap.prueba2.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import cl.inacap.prueba2.AbstractActivityClass;
import cl.inacap.prueba2.R;
import cl.inacap.prueba2.domain.Medicion;

public class CustomAdapter extends BaseAdapter {

    private final AbstractActivityClass activity;
    private final List<Medicion> list;

    public CustomAdapter(AbstractActivityClass activity, List<Medicion> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
     ViewHolder viewHolder = null;

     if(view == null){
         LayoutInflater inflater = activity.getLayoutInflater();
         view = inflater.inflate(R.layout.custom_activity_datos, viewGroup, false);
         viewHolder = new ViewHolder();
         viewHolder.setLblMedicion( view.findViewById(R.id.lblMedicion));
         viewHolder.setDelete(view.findViewById(R.id.imageViewDelete));
         viewHolder.setImgAgua(view.findViewById(R.id.imageViewAgua));
         viewHolder.setImgElectrcidad(view.findViewById(R.id.imageViewElectricidad));

         viewHolder.getDelete().setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 activity.delete(i);
             }
         });

         view.setTag(viewHolder);
     }else{
         viewHolder = (ViewHolder) view.getTag();
     }
     Medicion medicion = (Medicion) this.getItem(i);
     switch (medicion.getTipoServicio()){
         case 1:
             viewHolder.getImgElectrcidad().setVisibility(View.INVISIBLE);
             viewHolder.getImgAgua().setVisibility(View.VISIBLE);
             break;
         case 2:
             viewHolder.getImgElectrcidad().setVisibility(View.VISIBLE);
             viewHolder.getImgAgua().setVisibility(View.INVISIBLE);
             break;

     }
     viewHolder.getLblMedicion().setText((medicion.getCalle() + " " + medicion.getNumero() + ", " + medicion.getMedicion()));
     return view;
    }

    static class ViewHolder{
       private TextView lblMedicion;
       private ImageView delete;
       private ImageView imgAgua;
       private ImageView imgElectrcidad;

        public TextView getLblMedicion() {
            return lblMedicion;
        }

        public void setLblMedicion(TextView lblMedicion) {
            this.lblMedicion = lblMedicion;
        }

        public ImageView getDelete() {
            return delete;
        }

        public void setDelete(ImageView delete) {
            this.delete = delete;
        }

        public ImageView getImgAgua() {
            return imgAgua;
        }

        public void setImgAgua(ImageView imgAgua) {
            this.imgAgua = imgAgua;
        }

        public ImageView getImgElectrcidad() {
            return imgElectrcidad;
        }

        public void setImgElectrcidad(ImageView imgElectrcidad) {
            this.imgElectrcidad = imgElectrcidad;
        }
    }

}
