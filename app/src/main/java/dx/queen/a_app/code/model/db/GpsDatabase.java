package dx.queen.a_app.code.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import dx.queen.a_app.code.model.GPS;

@Database(entities = {GPS.class}, version = 1)
public abstract class GpsDatabase extends RoomDatabase {
    public abstract GpsDao gpsDao();
}
