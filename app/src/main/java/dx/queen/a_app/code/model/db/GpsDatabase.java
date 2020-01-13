package dx.queen.a_app.code.model.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import dx.queen.a_app.code.AppInstance;
import dx.queen.a_app.code.model.GPS;

@Database(entities = {GPS.class}, version = 1, exportSchema = false)
public abstract class GpsDatabase extends RoomDatabase {

    private static  volatile GpsDatabase INSTANCE;

    public static GpsDatabase getDatabase() {
        if(INSTANCE == null){
            synchronized (GpsDatabase.class){
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(AppInstance.getContext(), GpsDatabase.class, "database")
                            .build();
            }
        }
        return INSTANCE;
    }

    public abstract GpsDao gpsDao();
}
