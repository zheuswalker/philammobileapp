package redeye.ghostofwar.philamlife.Classes.Branches;


import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import java.util.List;

import redeye.ghostofwar.philamlife.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mapbox extends AppCompatActivity implements OnMapReadyCallback, LocationEngineListener,
        PermissionsListener, MapboxMap.OnMapClickListener {


    private MapView mapView;
    private MapboxMap map;
    private Button navigate;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;
    private LocationLayerPlugin locationLayerPlugin;
    private Location originLocation;
    private Point originPosition;
    private Point destinationPosition;
    private Marker destinationMarker;
    private NavigationMapRoute navigationMapRoute;
    private static final String TAG = "mapbox";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.mapbox);
        mapView =  findViewById(R.id.mapView);
        navigate = findViewById(R.id.navigate);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        //dental markers



        //1



        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                MarkerOptions options = new MarkerOptions();
        //1
                options.title("GF, JLC Building EDSA, Monumento Cir,\n Caloocan, 1400 Metro Manila\n");
                IconFactory iconFactory = IconFactory.getInstance(mapbox.this);
                Icon icon = iconFactory.fromResource(R.drawable.marker);
                options.icon(icon);
                options.position(new LatLng(14.657240, 120.986458));
                mapboxMap.addMarker(options);



                //2
                options.title("41 W Lawin, Quezon City, \n Metro Manila");
                options.icon(icon);
                options.position(new LatLng(14.648880, 121.028520));
                mapboxMap.addMarker(options);


                //3
                options.title("GF, Downtown Center, Manila, 520 Quintin \n Paredes Rd, Binondo, Manila");
                options.icon(icon);
                options.position(new LatLng(14.599240, 120.975280));
                mapboxMap.addMarker(options);




                //4
                options.title("Aurora Boulevard Corner Araneta \n Avenuecubao, Metro Manila");
                options.icon(icon);
                options.position(new LatLng(14.622130, 121.051680));
                mapboxMap.addMarker(options);



                //5
                options.title("WRCC Building, Dragon Street, corner \n Mayor Gil Fernando Avenue, \n Midtown Subdivision, San Roque, Marikina,\n 1807 Metro Manila");
                options.icon(icon);
                options.position(new LatLng(14.547100, 121.210900));
                mapboxMap.addMarker(options);



                //6
                options.title("Richmonde Centre 8001 \n Acropolis, Eulogio Rodriguez Jr. \n  Ave, Quezon City, 1110 Metro Manila");
                options.icon(icon);
                options.position(new LatLng(14.596800,121.079480 ));
                mapboxMap.addMarker(options);




                //7
                options.title("13 Coral Way, Pasay, Metro Manila");
                options.icon(icon);
                options.position(new LatLng(14.531400,120.984700));
                mapboxMap.addMarker(options);





                //8
                options.title("1986 Taft Avenue Corner P. \n Samonte Street, Pasay");
                options.icon(icon);
                options.position(new LatLng(14.555380, 120.996910));
                mapboxMap.addMarker(options);







                //9
                options.title("Building III, McArthur Highway, \n San Juan, Balagtas, 3016, Bulacan");
                options.icon(icon);
                options.position(new LatLng(14.813040, 120.912910));
                mapboxMap.addMarker(options);


                //10
                options.title("Sandico, Poblacion 2, \n Marilao, 3019 Bulacan\n");
                options.icon(icon);
                options.position(new LatLng(14.782360, 120.988190));
                mapboxMap.addMarker(options);




                //






                //11
                options.title("41 W Lawin, Quezon City, Metro Manila");
                options.icon(icon);
                options.position(new LatLng(14.648880, 121.028519));
                mapboxMap.addMarker(options);

                //12
                options.title("960 Aurora Blvd, Cubao, \n Quezon City, Metro Manila");
                options.icon(icon);
                options.position(new LatLng(14.613750,121.034740));
                mapboxMap.addMarker(options);



                //13
                options.title("Ortigas Ave., 1500 San Juan, \n MM, San Juan, 1500");
                options.icon(icon);
                options.position(new LatLng(14.603630, 121.044200));
                mapboxMap.addMarker(options);

                //14
                options.title("101-102 ALCCO Bldg., \n Ortigas Ave., 1500 San Juan, \n MM, San Juan, 1500 Metro Manila");
                options.icon(icon);
                options.position(new LatLng(14.603630,121.044197));
                mapboxMap.addMarker(options);




                //15
                options.title("Downtown Center, Manila, 520 Quintin Paredes Rd, \n  Binondo, Manila, 1006 Metro Manila");
                options.icon(icon);
                options.position(new LatLng(14.599240,120.975280));
                mapboxMap.addMarker(options);

                //16
                options.title("Kingsborough Building, Jose Abad Santos Avenue, \n San Fernando, 2000 Pampanga");
                options.icon(icon);
                options.position(new LatLng(14.971090, 120.606150));
                mapboxMap.addMarker(options);
//17
                options.title("V. Tiomico Street Santo Rosario, \n San Fernando, 2000 Pampanga");
                options.icon(icon);
                options.position(new LatLng(15.028920, 120.692690));
                mapboxMap.addMarker(options);
//18
                options.title("Parzon Square, 625 Sto. Rosario Street, \n Barangay Sto. Domingo, Angeles, 2009");
                options.icon(icon);
                options.position(new LatLng(14.704806, 121.010053));
                mapboxMap.addMarker(options);





                mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {


                        return false;
                    }
                });
            }

        });

        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                        .origin(originPosition)
                        .destination(destinationPosition)
                        .shouldSimulateRoute(true)
                        .build();
                NavigationLauncher.startNavigation(mapbox.this, options);
            }
        });
    }









    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        map = mapboxMap;
        map.addOnMapClickListener(this);
        enableLocation();

    }

    private void enableLocation() {

        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            initializeLocationEngine();
            initializeLocationLayer();

        }   else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager. requestLocationPermissions(this);
        }
    }


    @SuppressWarnings("MissingPermission")
    private void initializeLocationEngine() {
        locationEngine = new LocationEngineProvider(this).obtainBestLocationEngineAvailable();
        locationEngine.setPriority(LocationEnginePriority.HIGH_ACCURACY);
        locationEngine.activate();

        Location lastLocation = locationEngine.getLastLocation();
        if (lastLocation != null) {
            originLocation = lastLocation;
            setCameraPosition(lastLocation);
        }   else {
            locationEngine.addLocationEngineListener(this);
        }

    }


    @SuppressWarnings("MissingPermission")
    private void initializeLocationLayer(){

        locationLayerPlugin = new LocationLayerPlugin(mapView, map, locationEngine);
        locationLayerPlugin.setLocationLayerEnabled(true);
        locationLayerPlugin.setCameraMode(CameraMode.TRACKING);
        locationLayerPlugin.setRenderMode(RenderMode.NORMAL);

    }

    private void setCameraPosition(Location location) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),
                location.getLongitude()), 15.0));

    }

    @Override
    public void onMapClick(@NonNull LatLng point) {

        if (destinationMarker !=null) {
            map.removeMarker(destinationMarker);
        }

        destinationMarker = map.addMarker(new MarkerOptions().position(point));

        destinationPosition = Point.fromLngLat(point.getLongitude(), point.getLatitude());
        originPosition = Point.fromLngLat(originLocation.getLongitude(), originLocation.getLatitude());
        getRoute(originPosition, destinationPosition);

        navigate.setEnabled(true);
        navigate.setBackgroundResource(R.drawable.background);
        navigate.setTextColor(Color.parseColor("#FFFFFF"));
    }

    private void getRoute(Point origin, Point destination) {
        NavigationRoute.builder()
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        if (response.body() == null) {
                            Log.e(TAG, "No Route Found check right user");
                            return;
                        }   else if (response.body().routes().size() == 0) {
                            Log.e(TAG, "No Routes Found");
                            return;
                        }


                        DirectionsRoute currentRoute = response.body().routes().get(0);

                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        }   else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, map);
                        }

                        navigationMapRoute.addRoute(currentRoute);
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {

                        Log.e(TAG, "ERROR:" + t.getMessage());
                    }
                });
    }


    @Override
    @SuppressWarnings("MissingPermission")
    public void onConnected() {
        locationEngine.requestLocationUpdates();

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location !=null) {
            originLocation = location;
            setCameraPosition(location);
        }

    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {

        if (granted) {
            enableLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @SuppressWarnings ("MissingPermission")
    @Override
    protected void onStart() {
        super.onStart();
        if (locationEngine !=null) {
            locationEngine.removeLocationUpdates();
        }
        if (locationLayerPlugin != null) {
            locationLayerPlugin.onStart();
        }
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (locationEngine !=null) {
            locationEngine.removeLocationUpdates();
        }
        if (locationLayerPlugin !=null) {
            locationLayerPlugin.onStop();
        }
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationEngine !=null) {
            locationEngine.deactivate();
        }
        mapView.onDestroy();

    }




}
