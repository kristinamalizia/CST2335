package com.example.kristina.lab1;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        listView = (ListView) findViewById(R.id.listView25);//list view
        editText = (EditText) findViewById(R.id.edittext25);//text field
        sendButton = (Button) findViewById(R.id.send);//send button

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String message = editText.getText().toString();
                storedMessages.add(message);
                messageAdapter.notifyDataSetChanged();
                editText.setText("");
                }
           });
        }

        private class ChatAdapter extends ArrayAdapter<String> {

                Context mContext;

            public ChatAdapter(Context ctx) {
                super(ctx, 0);
            }

            public int getCount() {
                return storedMessages.size();
            }



            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

                View result = null;
                if (position%2==0) {
                    result = inflater.inflate(R.layout.chat_row_incoming, null);
                }else {
                    result = inflater.inflate(R.layout.chat_row_outgoing, null);
                }


                TextView message2 = (TextView)result.findViewById(R.id.message_text);
                message2.setText(getItem(position)); // get the string at position
                return result;
            }

            public String getItem(int position) {
                return storedMessages.get(position);
            }
        public void onDestroy() {
           // super();
        }
        }

    }