package com.example.kristina.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
static    ListView listView;
    EditText editText;
    Button sendButton;
    ArrayList<String> storedMessages = new ArrayList<String>();

    Context ctx;
    public static final String ACTIVITY_NAME = "Query";
    public static final String SQL_MESSAGE="SQL MESSAGE:";
    public static final String COLUMN_COUNT = "Cursor\'s  column count= ";

    ChatDatabaseHelper chatDatabaseHelper;
    SQLiteDatabase dataBase;

    Cursor cursor;

    int mId;
    String mMessage;
    boolean mHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
  //      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        listView = (ListView) findViewById(R.id.listView25);//list view
        editText = (EditText) findViewById(R.id.edittext25);//text field
        sendButton = (Button) findViewById(R.id.send);//send button


        chatDatabaseHelper = new ChatDatabaseHelper(this);
        //open database
        dataBase = chatDatabaseHelper.getWritableDatabase();

        //This creates a string array for the
        String[] allColumns = { ChatDatabaseHelper.KEY_ID, ChatDatabaseHelper.KEY_MESSAGE };
        cursor = dataBase.query(chatDatabaseHelper.TABLE_NAME,allColumns, null, null, null, null, null);

        cursor.moveToFirst();


        while(!cursor.isAfterLast() ) {

            String newMessage = cursor.getString(cursor.getColumnIndex(chatDatabaseHelper.KEY_MESSAGE));
            storedMessages.add(newMessage);
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(chatDatabaseHelper.KEY_MESSAGE)));
            // String thisName = results1.geString(nameColumnIndex);
            cursor.moveToNext();
        }


        for(int x = 0; x < cursor.getColumnCount(); x++){
            cursor.getColumnName(x);
            Log.i(ACTIVITY_NAME, "Cursors  column count =" + cursor.getColumnCount() );

        }

        class ChatAdapter extends ArrayAdapter<String> {

            public ChatAdapter(Context ctx) {

                super(ctx, 0);
            }

            //Returning the position of the chat list
            public String getItem(int position) {

                return storedMessages.get(position);

            }

            //Getting the count of the chat list size
            public int getCount() {

                return storedMessages.size();
            }

            public View getView(int position, View convertView, ViewGroup parent) {

                LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

                //If the remainder of the position, module 2, is equal to zero, a message is incoming
                View result = null;
                if (position % 2 == 0)
                    result = inflater.inflate(R.layout.chat_row_incoming, null);
                else
                    result = inflater.inflate(R.layout.chat_row_outgoing, null);

                // From the resulting view, get the TextView which holds the string message:
                TextView message = (TextView) result.findViewById(R.id.message_text);
                message.setText(getItem(position)); // get the string at position, should I be using the get position?
                return result;
            }//end of view
        }//end of chatadapter

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Once send is clicked, get and add the text from the chat box
                storedMessages.add(editText.getText().toString());

                //Modify the sendButton’s onClickListener callback function so that whenever a message is added to the ArrayList,
                // it also inserts the new message into the database. Use a ContentValues object to put the new message.
                ContentValues contentValues = new ContentValues();

                contentValues.put(chatDatabaseHelper.KEY_MESSAGE, editText.getText().toString());
                dataBase.insert(chatDatabaseHelper.TABLE_NAME, "null", contentValues);

                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
                editText.setText("");

            }
        });

    } //End of onCreate


    @Override
    protected void onDestroy() {
        Log.i(ACTIVITY_NAME, "In onDestroy()");
        super.onDestroy();
        cursor.close();
        dataBase.close();
    }

}//End of class