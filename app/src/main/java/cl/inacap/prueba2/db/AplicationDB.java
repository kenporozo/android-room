package cl.inacap.prueba2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cl.inacap.prueba2.dao.IMedicionDAO;
import cl.inacap.prueba2.domain.Medicion;

@Database(entities = Medicion.class, version = 1, exportSchema = false)
public abstract class AplicationDB extends RoomDatabase {

    public abstract IMedicionDAO medicionDAO();

    private static AplicationDB instance;

    public static AplicationDB getInstance(Context context){
        if(instance == null){
            instance = initInstance(context);
        }
        return instance;
    }

    private static AplicationDB initInstance(Context context) {
        return Room
                .databaseBuilder(context, AplicationDB.class, "db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
