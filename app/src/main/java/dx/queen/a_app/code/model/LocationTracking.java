package dx.queen.a_app.code.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.core.content.ContextCompat;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import dx.queen.a_app.code.model.db.LoadToDb;
import dx.queen.a_app.code.view.gps_fragment.FragmentLocationContract;

import static android.content.Context.LOCATION_SERVICE;

public class LocationTracking implements LocationListener, FragmentLocationContract.Model {

    //private static final int PERMISSIONS_REQUEST = 100;
     FragmentLocationContract.Presenter presenter;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("location");
    //String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private String id = ref.push().getKey();

    public void startTracking(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);// keep

        int permission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600000, 60, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 600000, 60, this);

            Location location = locationManager.getLastKnownLocation("Gps");

            GPS gps = new GPS(location.getLatitude(), location.getLongitude(), id);
            loadToFirebase(gps);

            final boolean networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!networkEnabled) {
                loadToDb(gps);
            }
        } else {
//
//            ActivityCompat.requestPermissions(MainActivity,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST);
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        GPS gps = new GPS(location.getLatitude(), location.getLongitude(), id);
        loadToFirebase(gps);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        if (s.equals(LocationManager.GPS_PROVIDER)) {
            presenter.makeToast("Status: " + i);
        } else if (s.equals(LocationManager.NETWORK_PROVIDER)) {
            presenter.makeToast("Status: " + i);
        }

    }

    @Override
    public void onProviderEnabled(String s) {
        if (s.equals(LocationManager.NETWORK_PROVIDER)) {
            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();

            OneTimeWorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(WorkM.class)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance().enqueue(myWorkRequest);
        }
    }

    @Override
    public void onProviderDisabled(String s) {
        if (s.equals(LocationManager.GPS_PROVIDER)) {
            presenter.makeToast(s + "DISABLED");
        } else if (s.equals(LocationManager.NETWORK_PROVIDER)) {
            presenter.makeToast(s + "DISABLED");
        }
    }

    private void loadToFirebase(GPS gps) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("location");
        ref.child(gps.getUserId()).setValue(gps);

    }

    private void loadToDb(GPS gps) {
        LoadToDb.loadToDB(gps);
    }


}