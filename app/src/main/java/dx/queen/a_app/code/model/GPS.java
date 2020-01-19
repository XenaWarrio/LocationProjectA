package dx.queen.a_app.code.model;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "location")
public class GPS {

    @NonNull
   @PrimaryKey
    private String userId;

    private double lat, lon;

    @Ignore
    public GPS(double lat, double lon, String userId) {
        this.lat = lat;
        this.lon = lon;
        this.userId = userId;
    }

    public GPS(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        this.userId = String.valueOf(UUID.randomUUID());
    }






    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

}

