package com.example.blipped.siargao2;

import java.io.File;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import org.osmdroid.views.util.constants.MapViewConstants;

import android.util.Log;
import java.util.ArrayList;

import org.osmdroid.ResourceProxy;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class MainActivity extends Activity implements IRegisterReceiver,
        LocationListener, MapViewConstants {

    private MapView mMapView;
    private MapController mapController;
    private LocationManager mLocMgr;
    //private ArrayList<SimpleLocationOverlay> mMyLocationOverlay;
    private ResourceProxy mResourceProxy;
    //ArrayList<OverlayItem> overlayItemArray;
    SimpleLocationOverlay myLocationOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DefaultResourceProxyImpl resProxy;
        resProxy = new DefaultResourceProxyImpl(this.getApplicationContext());
        String packageDir = "mnt/sdcard/osmdroid";
        //String path = Environment.getExternalStorageDirectory() + packageDir;
        myLocationOverlay = new SimpleLocationOverlay(this);
        File file = new File(packageDir, "gz.mbtiles");
        Log.d("Penis", file.toString());
        MBTileProvider provider = new MBTileProvider(this, file);
        MapView mapView = new MapView(this,
                                      provider.getTileSource().getTileSizePixels(),
                                      resProxy,
                                      provider);

//        mLocMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
//        mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100,
//                this);
        GeoPoint pt = new GeoPoint(23.120000, -113.320000); //Sample Point


        Drawable squareM = this.getResources().getDrawable(R.mipmap.ic_launcher);


        double north = 23.164667;
        double east  = 113.362083;
        double south = 23.102786;
        double west  =  113.285866;
        BoundingBoxE6 bBox = new BoundingBoxE6(north, east, south, west);
        Log.d("Penis", "Bounding Box created");

        mapView.setScrollableAreaLimit(bBox);
        mapView.setBuiltInZoomControls(true);


        // Zoom in and go to Amsterdam
        IMapController controller = mapView.getController();
        controller.setZoom(15);
        controller.setCenter(bBox.getCenter());

        // Create an ArrayList with overlays to display objects on map
        //overlayItemArray = new ArrayList<OverlayItem>();

        // Create som init objects
        OverlayItem linkopingItem = new OverlayItem("Linkoping", "Sweden",
                new GeoPoint(23.134444, 113.30000));
        OverlayItem stockholmItem = new OverlayItem("Stockholm", "Sweden",
                new GeoPoint(23.15321, 113.32333));
        myLocationOverlay.setLocation(new GeoPoint(23.134444, 113.30000));
        // Add the init objects to the ArrayList overlayItemArray
        //overlayItemArray.add(linkopingItem);
        //overlayItemArray.add(stockholmItem);

        // Add the Array to the IconOverlay
        //ItemizedIconOverlay<OverlayItem> itemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(this, overlayItemArray, null);

        // Add the overlay to the MapView
        mapView.getOverlays().add(myLocationOverlay);
        // Set the MapView as the root View for this Activity; done!
        setContentView(mapView);

    }
    public void onLocationChanged(Location location) {
        int lat = (int) (location.getLatitude() * 1E6);
        int lng = (int) (location.getLongitude() * 1E6);
        GeoPoint gpt = new GeoPoint(lat, lng);
        mapController.setCenter(gpt);
        mMapView.invalidate();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onProviderDisabled(String arg0) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

}
