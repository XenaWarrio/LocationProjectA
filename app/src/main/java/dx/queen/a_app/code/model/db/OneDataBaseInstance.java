package dx.queen.a_app.code.model.db;

import android.app.Application;

import androidx.room.Room;

public class OneDataBaseInstance extends Application {
    public static OneDataBaseInstance instance;

    private GpsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, GpsDatabase.class, "database")
                .build();
    }

    public static OneDataBaseInstance getInstance() {
        return instance;
    }

    public GpsDatabase getDatabase() {
        return database;
    }
}
