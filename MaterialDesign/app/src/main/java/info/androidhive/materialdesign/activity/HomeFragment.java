package info.androidhive.materialdesign.activity;

/**
 * Created by Ravi on 29/07/15.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.app.AppConfig;


public class HomeFragment extends Fragment {
  static private ProgressDialog loading;
    private TextView editTextId;
    Context context;
    Spinner spinner1;
    Spinner spinner2;
    TextView selVersion;
    String item;
    static String name,phone1,specialist,vik,latitude,longitude;
    List<String> list;
    private String[] state = { "Car", "Bike", "Truck", "Bus","others"
           };
    private String[] state2 = { "Petrol", "Engine", "Puncture", "Breakdown","Lockdown",

    };

    public HomeFragment() {
    }
    // public void onClick(View view){
    //Intent intent = new Intent(getActivity(), payment.class);
    // startActivity(intent);
    // }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        context = rootView.getContext();
        Button btn1 = (Button) rootView.findViewById(R.id.button10);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                getData();
                Intent intent = new Intent(context, payment.class);
                startActivity(intent);

            }
        });
        editTextId =(TextView) rootView.findViewById(R.id.editTextId);
        Spinner spinner =(Spinner) rootView.findViewById(R.id.spinner);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,state);
        Spinner spinner2 =(Spinner) rootView.findViewById(R.id.spinner2);
        ArrayAdapter<String>LTRadapter1 = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,state2);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);
        spinner2.setAdapter(LTRadapter1);

        /////////////////////////////////////////////
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                switch (arg2) {

                    case 0:
                        editTextId.setText("1");
                        break;
                    case 1:
                        editTextId.setText("2");
                        break;
                    case 2:
                        editTextId.setText("3");
                        break;
                    case 3:
                        editTextId.setText("4");
                        break;
                    default:
                        editTextId.setText("Nothing");
                        break;
                }
                item = arg0.getItemAtPosition(arg2).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        //View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

     public void getData() {
        String id = editTextId.getText().toString().trim();
        if (id.equals("")) {
           // Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(context, "Please wait...", "Loading...", false, false);

        String url = AppConfig.DATA_URL+editTextId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue;
         requestQueue = Volley.newRequestQueue(context);
         requestQueue.add(stringRequest);
    }

    public void showJSON(String response){
        name="";
        phone1="";
        specialist= "";
        latitude="";
        longitude="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(AppConfig.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            name = collegeData.getString(AppConfig.KEY_NAME);
            phone1 = collegeData.getString(AppConfig.KEY_PHONE);
            specialist = collegeData.getString(AppConfig.KEY_SPECIALIST);
            latitude = collegeData.getString(AppConfig.KEY_LAT);
            longitude = collegeData.getString(AppConfig.KEY_LONG);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        switch(specialist)
        {
            case "1": vik="car";
                break;
            case "2": vik="bike";
                break;
            case "3": vik="truck";
                break;
            case "4": vik="bus";
                break;
            default:vik="NULL";

        }


    }
    // @Override
    // public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //     Bundle savedInstanceState) {

    //View rootView = inflater.inflate(R.layout.fragment_home, container, false);

    //return rootView;
    //}
   // public void onClick (View view){
   // Intent i = new Intent(HomeFragment.this, payment.class);
   //  startActivity(i);}
}
