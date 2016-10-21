package com.example.kristina.lab1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import static android.content.ClipData.newIntent;

public class ListItemsActivity extends AppCompatActivity {

    //public CheckBox checked;
    public Toast toast;
    public Switch theSwitch;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public ImageButton button1;
    CharSequence textOn = "Switch is On";
    CharSequence textOff = "Switch is Off";
    int duration = Toast.LENGTH_SHORT;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        button1 = (ImageButton) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    //dispatchTakePictureIntent(); });
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
        });



        theSwitch = (Switch) findViewById(R.id.switchbutton);
        theSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                CharSequence text;
                                               int duration;
                if(isChecked){
                    text = "Switch is On";
                    duration = Toast.LENGTH_SHORT;
                }else {
                    text = "Switch is Off";
                    duration = Toast.LENGTH_LONG;
                }
                Toast toast = Toast.makeText(ListItemsActivity.this, text, duration); //this is the ListActivity
                toast.show(); //Displaying the message Box


            }
        });

        CheckBox checked = (CheckBox) findViewById(R.id.checkbox);
        checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked == true) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);

                                                   // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(R.string.dialog_message); //Add a dialog message to strings.xml


                            builder.setTitle(R.string.dialog_title);
                                                           //If the user selects yes (the positive button), then call finish()
                            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("Response", "My information to share");
                                    setResult(Activity.RESULT_OK, resultIntent);
                                    finish();
                                }

                            });
                                                           //If the user selects no (the negative button), then call cancel()
                            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                                    builder.show();

                                               }
                                           }
                                       });
                                   }
            //@Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    button1.setImageBitmap(imageBitmap);
                }
            }


//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = newIntent (MediaStore,ACTION_IMAGE_CAPTURE);
//        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//    }


    protected static final String ACTIVITY_NAME = "ListItems Activity";


    public void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");

    }
    public void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");

    }

    public void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    public void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

}
