package cl.inacap.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.List;

import cl.inacap.prueba2.adapter.CustomAdapter;
import cl.inacap.prueba2.dao.IMedicionDAO;
import cl.inacap.prueba2.db.AplicationDB;
import cl.inacap.prueba2.domain.Medicion;

public class Datos extends AbstractActivityClass {

    private RadioGroup filtro;
    private Button btnMostrar;
    private IMedicionDAO medicionDAO;
    private List<Medicion> medicionesList;
    private ListView listViewDatos;
    private CustomAdapter adapter;

    //accion del btn mostrar
    private View.OnClickListener mostrar = new View.OnClickListener() {
        List<Medicion> m;
        @Override
        public void onClick(View view) {
            switch (tipoFiltro(filtro)){
                case 1:
                    m = medicionDAO.medicionesFiltradas(1);
                    break;
                case 2:
                    m = medicionDAO.medicionesFiltradas(2);
                    break;
                default:
                    m = medicionDAO.obtenerMediciones();
                    break;
            }
            CustomAdapter adapterFiltro = new CustomAdapter(Datos.this, m);
            listViewDatos.setAdapter(adapterFiltro);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        //instancio parametros
        this.listViewDatos = findViewById(R.id.listViewDatos);
        this.btnMostrar = findViewById(R.id.btnMostrar);
        this.filtro = findViewById(R.id.radioGroupVer);
        btnMostrar.setOnClickListener(mostrar);

        //instancio bd
        AplicationDB db = AplicationDB.getInstance(getApplicationContext());
        this.medicionDAO = db.medicionDAO();

        this.medicionesList = this.medicionDAO.obtenerMediciones();
        adapter = new CustomAdapter(this, this.medicionesList);
        this.listViewDatos.setAdapter(adapter);
    }

    //metodo eliminar
    @Override
    public void delete(int i) {
    Medicion medicion = this.medicionesList.get(i);
    this.medicionDAO.delete(medicion);
    this.medicionesList.remove(medicion);
    this.adapter.notifyDataSetChanged();
    }

    //metodo para el filtrar
    private int tipoFiltro(RadioGroup radioGroup){
        int filtro = 0;
        if(radioGroup.getCheckedRadioButtonId() != -1){
            switch (radioGroup.getCheckedRadioButtonId()){
                case R.id.radioVerAgua:
                    filtro = 1;
                    break;
                case R.id.radioVerEnergia:
                    filtro = 2;
                    break;
                case R.id.radioVerTodos:
                    filtro = 3;
                    break;
            }
        }
        return filtro;
    }
}