package com.example.SafetyReturn;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button showMarkersButton;
    private Button zoomInButton;
    private Button zoomOutButton;
    private Button gpsButton;
    private FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        showMarkersButton = findViewById(R.id.showMarkersButton);
        zoomInButton = findViewById(R.id.zoomInButton);
        zoomOutButton = findViewById(R.id.zoomOutButton);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        } else {
            // 권한이 부여되면 마지막으로 알려진 위치를 가져옵니다.
            getLastKnownLocation();
        }

        showMarkersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMarkers();
            }
        });

        zoomInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMap != null) {
                    mMap.animateCamera(CameraUpdateFactory.zoomIn());
                }
            }
        });

        zoomOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMap != null) {
                    mMap.animateCamera(CameraUpdateFactory.zoomOut());
                }
            }
        });
        gpsButton = findViewById(R.id.gpsButton);
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // GPS 버튼이 클릭되었을 때 현재 위치로 이동
                getLastKnownLocation();
            }
        });
    }

    private void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            BitmapDescriptor gpsMarker = BitmapDescriptorFactory.fromResource(R.drawable.gps_marker);
                            mMap.addMarker(new MarkerOptions().position(currentLocation).title("현재 위치").icon(gpsMarker));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15.0f));
                        }
                    }
                });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastKnownLocation();
            } else {
                Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 초기 지도 위치 설정 계명대학교 동문
        LatLng eastgate = new LatLng(35.855787, 128.491659);
        float zoomLevel = 15.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eastgate, zoomLevel));
    }

    private void showMarkers() {
        LatLng cctv1 = new LatLng(35.85273889, 128.4939833);
        LatLng cctv2 = new LatLng(35.85680278, 128.4973389);
        LatLng cctv3 = new LatLng(35.85729167, 128.4984611);
        LatLng cctv4 = new LatLng(35.85768333, 128.4976278);
        LatLng cctv5 = new LatLng(35.858875, 128.4925944);
        LatLng cctv6 = new LatLng(35.85575833, 128.4940722);
        LatLng cctv7 = new LatLng(35.85703611, 128.4980611);
        LatLng cctv8 = new LatLng(35.85697222, 128.4986833);
        LatLng cctv9 = new LatLng(35.8555, 128.4921611);
        LatLng cctv10 = new LatLng(35.8574492, 128.4924288);
        LatLng cctv11 = new LatLng(35.8563067, 128.4924051);
        LatLng cctv12 = new LatLng(35.85756944, 128.49225);
        LatLng cctv13 = new LatLng(35.85559167, 128.5016944);
        LatLng cctv14 = new LatLng(35.85754167, 128.4941056);
        LatLng cctv15 = new LatLng(35.85754167, 128.4941056);
        LatLng cctv16 = new LatLng(35.85754167, 128.4941056);

        mMap.addMarker(new MarkerOptions().position(cctv1).title("CCTV 1"));
        mMap.addMarker(new MarkerOptions().position(cctv2).title("CCTV 2"));
        mMap.addMarker(new MarkerOptions().position(cctv3).title("CCTV 3"));
        mMap.addMarker(new MarkerOptions().position(cctv4).title("CCTV 4"));
        mMap.addMarker(new MarkerOptions().position(cctv5).title("CCTV 5"));
        mMap.addMarker(new MarkerOptions().position(cctv6).title("CCTV 6"));
        mMap.addMarker(new MarkerOptions().position(cctv7).title("CCTV 7"));
        mMap.addMarker(new MarkerOptions().position(cctv8).title("CCTV 8"));
        mMap.addMarker(new MarkerOptions().position(cctv9).title("CCTV 9"));
        mMap.addMarker(new MarkerOptions().position(cctv10).title("CCTV 10"));
        mMap.addMarker(new MarkerOptions().position(cctv11).title("CCTV 11"));
        mMap.addMarker(new MarkerOptions().position(cctv12).title("CCTV 12"));
        mMap.addMarker(new MarkerOptions().position(cctv13).title("CCTV 13"));
        mMap.addMarker(new MarkerOptions().position(cctv14).title("CCTV 14"));
        mMap.addMarker(new MarkerOptions().position(cctv15).title("CCTV 15"));
        mMap.addMarker(new MarkerOptions().position(cctv16).title("CCTV 16"));
    }
}