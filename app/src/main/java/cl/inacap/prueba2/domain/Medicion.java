package cl.inacap.prueba2.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Medicion {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String calle;

    @NonNull
    private String numero;

    @NonNull
    private int medicion;

    @NonNull
    private int tipoServicio;

    public Medicion(@NonNull String calle, @NonNull String numero, int medicion, int tipoServicio) {
        this.calle = calle;
        this.numero = numero;
        this.medicion = medicion;
        this.tipoServicio = tipoServicio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getMedicion() {
        return medicion;
    }

    public void setMedicion(int medicion) {
        this.medicion = medicion;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Override
    public String toString() {
        return "Medicion{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", medicion=" + medicion +
                ", tipoServicio=" + tipoServicio +
                '}';
    }
}
