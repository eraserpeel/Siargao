package com.example.blipped.siargao2;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

public class PlacesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        loadDatabase();

    }

    private void loadDatabase() {
        Log.d("Penis", "Here");
        try {
            DatabaseHandler dbOpenHelper = new DatabaseHandler(this.getBaseContext(), "siargao");
            SQLiteDatabase placeDB = dbOpenHelper.openDataBase();
            String query = "SELECT * FROM place where _id=1";
            Cursor cursor = placeDB.rawQuery(query, null);
            cursor.moveToFirst();
            Log.d("Penis", cursor.getString(0));
            Log.d("Penis", cursor.getString(1));
            Log.d("Penis", cursor.getString(2));
            Log.d("Penis", "asdfasdfasdfas324asd");
        }
        catch(Exception e) {
            Log.d("Penis", e.toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_places, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
