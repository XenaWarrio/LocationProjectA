package dx.queen.a_app.code.model;

import java.text.DateFormat;
import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GPS {

    private double lat, lon;
    private Date date;

    @PrimaryKey
    private String lastUpdate;

    public GPS(double latitude, double longitude) {
        this.lat = latitude;
        this.lon = longitude;
        this.date = new Date();
        this.lastUpdate = DateFormat.getTimeInstance().format(this.date);
    }



    public double getLatitude() {
        return lat;
    }

    public Date getDate() {
        return date;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public double getLongitude() {
        return lon;
    }


}

