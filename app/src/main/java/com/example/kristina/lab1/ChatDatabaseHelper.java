package com.example.kristina.lab1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//
///**
// * Created by kristina on 2016-10-11.
// */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Chats.db";
    //Another hint is to make the TABLE_NAME variable of ChatDatabaseHelper as a public variable so you can access it from ChatWindow.
    public static final String TABLE_NAME = "chat_table";
    public static final int VERSION_NUM = 52;

    public static final String KEY_ID = "_id";
    public static final String KEY_MESSAGE = "_message";



    //Create a new class called ChatDatabaseHelper, and it should extend SQLiteOpenHelper.
    // Write a constructor that opens a database file “Chats.db”:
    //You will have to define the variables DATABASE_NAME, and VERSION_NUM as static.
    public ChatDatabaseHelper(Context ctx) {

        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }




    //Write the onCreate(SQLiteDatabase db)  function to create a table with a column for id of integers that autoincrement,
    // and a column for MESSAGE as strings.
    // It is best to define the Column names as final static
    // String variables: KEY_ID, KEY_MESSAGE.
    @Override
    public void onCreate(SQLiteDatabase db)
    {//look at db.execsql, its broke *FIXED*
        db.execSQL("CREATE TABLE "+ TABLE_NAME + " ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_MESSAGE+" VARCHAR(50))");
        Log.i("ChatDataBaseHelper","Calling onCreate");
    }


    /*Write the onUpgrade(SQLiteDatabase db) function so that it executes the SQL statement:
     “DROP TABLE IF EXISTS “ + TABLE_NAME
     followed by recreating the database by calling: onCreate(db)
    */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(ChatDatabaseHelper.class.getName(),"Upgrading database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(ChatDatabaseHelper.class.getName(),"Downgrading database from version " + newVersion
                + " to " + oldVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
