package info.androidhive.materialdesign.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import info.androidhive.materialdesign.R;

public class mechanicdetailsactivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanicdetailsactivity);
        String[] mechanic = {"Sr.No. NAME  Dist. Time ",
                "1.     DAMON        40    20",
                "2.     STEPHON    20    10",
                "3.     ELENA        50    30",
                "4.     KAROLINA    40    35",
                "5.     STAR           35    20",
                "6.     BUCKY         50    15 ",
                "7      AABCD         21     5 ",
                "8      WXYZ           30     10 ",
                "9      SUPER         45     15 ",
                "10     JOHN           60     20 "};
        ListAdapter aadiAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mechanic);
        ListView aadiListView = (ListView) findViewById(R.id.aadiListView);
        aadiListView.setAdapter(aadiAdapter);
        aadiListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String mechanic = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(mechanicdetailsactivity.this, mechanic, Toast.LENGTH_LONG).show();

                        if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8 || position == 9 || position == 10)
                        //while(position== 10)
                        {
                            //code specific to first list item
                            Intent myIntent = new Intent(view.getContext(), phonecall.class);
                            // myIntent.putExtra("mechanic",mechanic);
                            startActivityForResult(myIntent, 1);


                        }
                    }


                });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mechanicdetailsactivity, menu);
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
