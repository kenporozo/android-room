package cl.inacap.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import cl.inacap.prueba2.dao.IMedicionDAO;
import cl.inacap.prueba2.db.AplicationDB;
import cl.inacap.prueba2.domain.Medicion;

public class MainActivity extends AbstractActivityClass {

    private EditText txtCalle;
    private EditText txtNumero;
    private EditText txtMedicion;
    private Button btnGuardar;
    private Button btnVer;
    private RadioGroup radioButtonServicios;
    private IMedicionDAO medicionDAO;

    //accion del btnGuardar
    private final View.OnClickListener btnGuardarListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (datosOK()) {
                String calle = txtCalle.getText().toString();
                String numero = txtNumero.getText().toString();
                int medicion = Integer.parseInt(txtMedicion.getText().toString());

                Medicion m = new Medicion(calle, numero, medicion, tipoElemento(radioButtonServicios));

                medicionDAO.agregar(m);
                Toast.makeText(MainActivity.this, "Datos guardados correctamente", Toast.LENGTH_LONG)
                        .show();
            }else{
                Toast.makeText(MainActivity.this, "Error, ingrese todos los datos", Toast.LENGTH_LONG)
                        .show();
            }
        }
    };

    //accion del btnVer
    private final View.OnClickListener btnVerListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Datos.class);
            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancia de la db
        AplicationDB db = AplicationDB.getInstance(getApplicationContext());
        this.medicionDAO = db.medicionDAO();

        //instanciando datos
        this.txtCalle = findViewById(R.id.txtCalle);
        this.txtNumero = findViewById(R.id.txtNumero);
        this.txtMedicion = findViewById(R.id.txtMedicion);
        this.btnGuardar = findViewById(R.id.btnGuardar);
        this.btnVer = findViewById(R.id.btnVer);
        this.radioButtonServicios = findViewById(R.id.radioButtonServicios);

        //acciones
        this.btnVer.setOnClickListener(this.btnVerListener);
        this.btnGuardar.setOnClickListener(this.btnGuardarListener);
    }

    //Metodo que devuelve el tipo del elemento
    private int tipoElemento(RadioGroup radioGroup){
        int tipoElemento = 0;
        if(radioGroup.getCheckedRadioButtonId() != -1){
            switch (radioGroup.getCheckedRadioButtonId()){
                case R.id.radioEnergia:
                    tipoElemento = 1;
                    break;
                case R.id.radioAguaPotable:
                    tipoElemento = 2;
                    break;
            }
        }
        return tipoElemento;
    }

    @Override
    public void delete(int i) {
    }

    //funcion validar
    private boolean datosOK(){
     if(this.txtCalle.getText().toString().equalsIgnoreCase(" ") & this.txtCalle.getText().toString().isEmpty()){
         return false;
     }
        if(this.txtMedicion.getText().toString().equalsIgnoreCase(" ") & this.txtMedicion.getText().toString().isEmpty()){
            return false;
        }
        if(this.txtNumero.getText().toString().equalsIgnoreCase(" ") & this.txtNumero.getText().toString().isEmpty()){
            return false;
        }
        if(this.radioButtonServicios.getCheckedRadioButtonId() == -1){
            return false;
        }
        return true;
    }
}