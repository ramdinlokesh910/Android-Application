package lokesh.com.friendsprofile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by lokeshramdin on 8/16/2016.
 */
public class ThirdActivityClass extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);



        TextView m_thirdView1 = (TextView)findViewById(R.id.textView4);
        TextView m_thirdView2=(TextView)findViewById(R.id.textView5);
        TextView m_thirdView3= (TextView)findViewById(R.id.textView6);
        ImageView m_thirdImg = (ImageView)findViewById(R.id.img_third);

        int Id = getIntent().getIntExtra("selectedID",0);
        //Logs
        Log.v("Lokesh","ThirdActivity.class: OnCreate Selected Id: "+Id);
        Log.d("Lokesh","ThirdActivity.class: OnCreate Selected Id: "+Id);
        Log.e("Lokesh","ThirdActivity.class: OnCreate Selected Id: "+Id);
        Log.i("Lokesh","ThirdActivity.class: OnCreate Selected Id: "+Id);

        if (Id != 0 ) {
            SQLiteDatabase db = new Database(ThirdActivityClass.this).getReadableDatabase();
            String selectedCol[] = {"FirstName","LastName"};

            Cursor cursor = db.query("PROFILE",null,"_id = "+Id,null,null,null,null);
            cursor.moveToFirst();

            String str1 = cursor.getString(cursor.getColumnIndex("FirstName"));
            String str2 = cursor.getString(cursor.getColumnIndex("LastName"));
            String str3 = cursor.getString(cursor.getColumnIndex("Email_ID"));
            byte image[] = cursor.getBlob(cursor.getColumnIndex("Image"));
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);


            m_thirdView1.setText(str1);
            m_thirdView2.setText(str2);
            m_thirdView3.setText(str3);
            m_thirdImg.setImageBitmap(bitmap);
        }


    }//End OnCreate

}
