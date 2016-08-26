package lokesh.com.friendsprofile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Database_listView extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ListView listView = (ListView)findViewById(R.id.listView);
        Database database = new Database(Database_listView.this);
        SQLiteDatabase sql_db = database.getReadableDatabase();

          final Cursor c = sql_db.query("PROFILE",null,null,null,null,null,null);
       final CustomSimplecursorAdapter customAdapter = new CustomSimplecursorAdapter(Database_listView.this,c,0);



        listView.setAdapter(customAdapter);


        //Action performed On item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                 Cursor cursor = (Cursor) customAdapter.getCursor();
                cursor.moveToPosition(position);
                //long categoryId = cursor.getLong(cursor.getColumnIndex("_id"));
               // Toast.makeText(Database_listView.this,"Clicked on ITEM: ",Toast.LENGTH_LONG).show();

                String str1 = cursor.getString(cursor.getColumnIndex("FirstName"));
                String str2 = cursor.getString(cursor.getColumnIndex("LastName"));
                String str3 = cursor.getString(cursor.getColumnIndex("Email_ID"));
                byte image[] = cursor.getBlob(cursor.getColumnIndex("Image"));
                int Id = cursor.getInt(cursor.getColumnIndex("_id"));

                //Logs
                Log.v("Lokesh","Database_listView: OnItemClickistner Selected Id: "+Id);
                Intent intent = new Intent(Database_listView.this,ThirdActivityClass.class);
//                intent.putExtra("BitMapImage",image);
//                intent.putExtra("FirstName",str1);
//                intent.putExtra("LastName",str2);
//                intent.putExtra("Email_ID",str3);

                intent.putExtra("selectedID",Id);
                startActivity(intent);

            }
        });

    }//End on create

    //This adapter is for the storing of images.Adapter Used for executing code line by line
    class CustomSimplecursorAdapter extends CursorAdapter{

        private LayoutInflater inflater;
        public CustomSimplecursorAdapter(Context context,Cursor c,int flags)
        {
            super(context,c,flags);
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            TextView view1 = (TextView) view.findViewById(R.id.txt_vi1);
            TextView view2 = (TextView) view.findViewById(R.id.txt_vi2);
            ImageView view3 = (ImageView) view.findViewById(R.id.img_view);

            String str1 = cursor.getString(cursor.getColumnIndex("FirstName"));
            String str2 = cursor.getString(cursor.getColumnIndex("LastName"));
            byte image[] = cursor.getBlob(cursor.getColumnIndex("Image"));

            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

            view1.setText(str1);
            view2.setText(str2);
            view3.setImageBitmap(bitmap);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return inflater.inflate(R.layout.list_item,parent,false);
        }
    }

}
