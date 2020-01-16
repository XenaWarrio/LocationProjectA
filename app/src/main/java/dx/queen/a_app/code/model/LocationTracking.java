package dx.queen.a_app.code.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import dx.queen.a_app.code.model.db.LoadToDb;
import dx.queen.a_app.code.view.gps_fragment.FragmentLocationContract;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.location.LocationManagerCompat.isLocationEnabled;

public class LocationTracking implements LocationListener, FragmentLocationContract.Model {

    private Criteria criteria;
    private String bestProvider;
    private double latitude;
    private double longitude;
    FragmentLocationContract.Presenter presenter;
    LocationManager locationManager;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("location");
    private String id = ref.push().getKey();

    public void startTracking(Context context) {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

        getLocation(locationManager, context);

    }


    @Override
    public void onLocationChanged(Location location) {

        //locationManager.removeUpdates(this);
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        GPS gps = new GPS(latitude, longitude, id);
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
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                } else {
                    ref.child("location").setValue(gps);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       // locationManager.removeUpdates(this);

    }

    private void loadToDb(GPS gps) {
        LoadToDb.loadToDB(gps);
    }

    protected void getLocation(LocationManager locationManager, Context context) {
        if (isLocationEnabled(locationManager)) {
            criteria = new Criteria();
            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));
            final boolean networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


            int permission = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            if (permission == PackageManager.PERMISSION_GRANTED) {


//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600000, 60, this);
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 600000, 60, this);
//

                Location location = locationManager.getLastKnownLocation(bestProvider);

                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                } else {
                    locationManager.requestLocationUpdates(bestProvider, 600000, 60, this);

                }
                    GPS gps = new GPS(location.getLatitude(), location.getLongitude(), id);


                    loadToFirebase(gps);


                    if (!networkEnabled) {
                        loadToDb(gps);
                    }
                }
            }


        }

    }
