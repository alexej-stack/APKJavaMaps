package com.example.task5_1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.example.task5_1.Retrofit.Coord;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    Button Update;
    Button btnActTwo;
    Button Arhiv;
    private GoogleMap mMap;
    public  double[] arr1= new double[16];
    public double[] arr2= new double[16];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Update = (Button) findViewById(R.id.Update);
        btnActTwo = (Button) findViewById(R.id.Weather);
      // btnActTwo.setOnClickListener(new View.OnClickListener());
        Arhiv = (Button) findViewById(R.id.Arhiv);
        // создаем обработчик нажатия
        btnActTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Weather.class);
                startActivityForResult(myIntent, 0);
            }

        });

        Arhiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
        Map<String,Integer> markers=new HashMap<String, Integer>();

        OnClickListener oclBtnUp = new OnClickListener() {

            public void onClick(View v) {
                // Меняем текст в TextView (tvOut)
                double rangeMin=-90.;
                double rangeMax=90.;
                mMap.clear();
                double[] arr1= new double[16];
                double[] arr2= new double[16];
                for (int i = 0; i < 16; i++) {
                    double random = new Random().nextDouble();
                    double x1 = rangeMin + (rangeMax - rangeMin) * random;
                    double random2 = new Random().nextDouble();
                    double x2 = rangeMin + (rangeMax - rangeMin) * random2;
                    arr1[i] = x1;
                    arr2[i] = x2;
                    LatLng sydney = new LatLng(arr1[i], arr2[i]);
                    MarkerOptions mo = new MarkerOptions()

                            .position(new LatLng(arr1[i], arr2[i]))
                            .flat(true)
                            .snippet("Click here for details")
                            .rotation(0)
                            ;
                    Coord myObject = new Coord(arr1[i],arr2[i]); // The class that you are rendering on the map with Markers, for example "Monument"
                    Projection projection = mMap.getProjection();

                    int l=arr1.length;
                    mMap.addMarker(new MarkerOptions().position(sydney).title(Integer.toString(l)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                }
            }
        };

        // присвоим обработчик кнопке OK (btnOk)
        Update.setOnClickListener(oclBtnUp);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Weather:
                Intent intent = new Intent(this, Weather.class);
                startActivity(intent);
                break;
            case R.id.Arhiv:
            Intent intent2 = new Intent(this, MainActivity.class);
            startActivity(intent2);
            break;

            default:
                break;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double rangeMin=-180;
        double rangeMax=180;


        double[] arr1= new double[16];
        double[] arr2= new double[16];
        for (int i = 0; i < arr1.length; i++) {
            double random = new Random().nextDouble();
          double x1 = rangeMin + (rangeMax - rangeMin) * random;
            double random2 = new Random().nextDouble();
          double x2 = rangeMin + (rangeMax - rangeMin) * random2;
            arr1[i] = x1;
            arr2[i] = x2;
            LatLng sydney = new LatLng(arr1[i], arr2[i]);
            mMap.addMarker(new MarkerOptions().position(sydney).title("$x2"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
        // Add a marker in Sydney and move the camera
      //  LatLng sydney = new LatLng(x1, x2);
     //   mMap.addMarker(new MarkerOptions().position(sydney).title("New MArker"));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

}
