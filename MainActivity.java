package lokesh.com.friendsprofile;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText m_fname;
    EditText m_lname;
    EditText m_email;
    Button m_btnImage;
    byte image[];
    private static final int CAPTURE_IMAGE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialization
        m_fname = (EditText)findViewById(R.id.fname);
        m_lname = (EditText)findViewById(R.id.lname);
        m_email = (EditText)findViewById(R.id.email);
        m_btnImage = (Button)findViewById(R.id.btn_image);

        m_btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,CAPTURE_IMAGE);
            }
        });



    }//End Oncreate

    //On Activity result for the Image Setting
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == CAPTURE_IMAGE){
                Bitmap capturedImage = (Bitmap)data.getExtras().get("data");    //This line of code is constant

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                capturedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                image = stream.toByteArray();

            }
        }
    }
    //Function SaveData
    public void saveData(){
        //Getting Parameters
        String str1 = m_fname.getText().toString();
        String str2 = m_lname.getText().toString();
        String str3 = m_email.getText().toString();
        //Setting Database
        Database mydb = new Database(MainActivity.this);
        SQLiteDatabase db = mydb.getWritableDatabase();

        //Setting Content Values and Inserting values in the database
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName",str1);
        contentValues.put("LastName",str2);
        contentValues.put("Email_ID",str3);
        contentValues.put("Image",image);


        db.insert("PROFILE",null,contentValues);
        //db.delete("FRIENDS",null,null);
        m_fname.setText("");
        m_lname.setText("");
        m_email.setText("");

        Intent intent = new Intent(MainActivity.this,Database_listView.class);
        startActivity(intent);
    }
    //Function save Image
   /* public void saveImage(){

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,CAPTURE_IMAGE);
    }*/




    //Menu Inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inft = getMenuInflater();

        inft.inflate(R.menu.menu,menu);

        return true;
    }
    //Here we assign the settings for the items selected and the operations for the item selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.set:
                saveData();
                Toast.makeText(this,"stored Details.",Toast.LENGTH_LONG).show();
                return true;

            case R.id.abt:
                //saveImage();
                Toast.makeText(this,"Capture Image",Toast.LENGTH_LONG).show();
                return true;

            case R.id.cnt:
                Toast.makeText(this,"Submit",Toast.LENGTH_LONG).show();
                return true;


        }

        return super.onOptionsItemSelected(item);
    }
    //End Of Menu Inflater
}
