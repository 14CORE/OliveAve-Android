package cs246.oliveave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

public class MapsOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_options);
    }

    public void openRexburgMap(View view){
        LatLng rexburg = new LatLng(43.826153, -111.781905);
        Bundle args = new Bundle();
        args.putParcelable("cityCoordinates", rexburg);
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("latlng", args);
        startActivity(i);
    }

    public void openIFMap(View view){
        LatLng idahoFalls = new LatLng(43.469469, -111.984977);
        Bundle args = new Bundle();
        args.putParcelable("cityCoordinates", idahoFalls);
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("latlng", args);
        startActivity(i);
    }
}
