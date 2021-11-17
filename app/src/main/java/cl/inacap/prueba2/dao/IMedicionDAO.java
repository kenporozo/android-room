package cl.inacap.prueba2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cl.inacap.prueba2.domain.Medicion;

@Dao
public interface IMedicionDAO {

    @Query("select * from Medicion")
    List<Medicion> obtenerMediciones();

    @Query("select * from Medicion where tipoServicio = :id")
    List<Medicion> medicionesFiltradas(int id);

    @Insert
    void agregar(Medicion medicion);

    @Delete
    void delete(Medicion medicion);
}
