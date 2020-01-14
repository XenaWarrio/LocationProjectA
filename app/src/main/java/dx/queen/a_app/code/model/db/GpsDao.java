package dx.queen.a_app.code.model.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import dx.queen.a_app.code.model.GPS;

@Dao
public interface GpsDao {

    @Query("SELECT * FROM location")
    List<GPS>getAll();

    @Insert
    void insert(GPS gps);

    @Delete
    void delete(GPS gps);
}
