package dx.queen.a_app.code.model.db;

import dx.queen.a_app.code.model.GPS;

import static dx.queen.a_app.code.model.db.GpsDatabase.getDatabase;

public class LoadToDb {

    public static void loadToDB(GPS gps) {
        GpsDatabase database = getDatabase();
        GpsDao dao = database.gpsDao();

        dao.insert(gps);
    }
}

