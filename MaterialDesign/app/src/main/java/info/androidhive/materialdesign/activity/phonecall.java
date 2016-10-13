package info.androidhive.materialdesign.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.helper.SQLiteHandler;
import info.androidhive.materialdesign.helper.SessionManager;

public class phonecall extends AppCompatActivity {
    private Button button;
    private TextView etPhoneno;
    private TextView mechname;
    private TextView mechspecifcication;
    private Button button2;
    private Button button3;
    //EditText txtphoneNo;
    EditText txtMessage;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonecall);
        button = (Button) findViewById(R.id.button11);
        button2 = (Button)findViewById(R.id.button7);
        etPhoneno = (TextView) findViewById(R.id.textView14);
        mechname = (TextView) findViewById(R.id.textView8);
        mechspecifcication = (TextView) findViewById(R.id.textView13);
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // add button listener textView3= (TextView) findViewById(R.id.textView3);
        etPhoneno.setText(HomeFragment.phone1);
        mechname.setText(HomeFragment.name);
        mechspecifcication.setText(HomeFragment.vik);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String phnum = etPhoneno.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phnum));
                startActivity(callIntent);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        etPhoneno = (TextView) findViewById(R.id.textView14);
        txtMessage = (EditText) findViewById(R.id.editText2);

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });
        //
       // getSupportActionBar().setTitle("phonecall");
    }
    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure?You wanted to logout");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                logoutUser();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(phonecall.this, LoginActivity.class);
        startActivity(intent);

    }

    protected void sendSMSMessage() {
        Log.i("Send SMS", "");




        String phoneNo = etPhoneno.getText().toString();
        String message = "I'm RAVeN user,Need Services";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
         /*   Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.putExtra("sms_body", "default content");
            sendIntent.setType("vnd.android-dir/mms-sms");
            startActivity(sendIntent);*/
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }



 public void onClick3(View v){
     Intent i =new Intent(this,MapActivity.class);
     startActivity(i);
 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phonecall, menu);
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
            open(onCreatePanelView(1));
        }

        return super.onOptionsItemSelected(item);
    }
}
