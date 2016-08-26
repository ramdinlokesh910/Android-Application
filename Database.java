package lokesh.com.friendsprofile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

public class Database extends SQLiteOpenHelper
{
   public Database(Context context){
       super(context, Environment.getExternalStorageDirectory()+ File.separator+"Assignement3_db3"+File.separator+"Assgn3_db",null,1);

   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCreate = "CREATE TABLE PROFILE(_id INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,LastName TEXT,Email_ID TEXT,Image BLOB)";
        db.execSQL(tableCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
