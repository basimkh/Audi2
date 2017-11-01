package com.example.hp1.audi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt1, bt2;
    ImageView Img4;
    Bitmap bitmap;
    SharedPreferences preferences;

    static final int SELECT_IMAGE = 1;
    static final int TAKE_IMAGE = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        bt1 = (Button) findViewById(R.id.button4);
        bt2 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        Img4 = (ImageView) findViewById(R.id.img4);
        preferences  = this.getSharedPreferences("profile",MODE_PRIVATE);
        String path = preferences.getString("image",null);
        if(path != null) {
            bitmap = BitmapFactory.decodeFile(path);
            Img4.setImageBitmap(bitmap);
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor= preferences.edit();
        //start another activirty and receive a result back in case the activity exisits.
        if (v == bt1) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, TAKE_IMAGE);
        } else {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_IMAGE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_IMAGE && resultCode == RESULT_OK) {
            Bundle extra = data.getExtras();
            bitmap = (Bitmap) extra.get("data");
            Img4.setImageBitmap(bitmap);
            saveImage(bitmap);


        } else if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            Toast.makeText(getApplicationContext(), targetUri.toString(), Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor= preferences.edit();
            editor.putString("image",targetUri.toString());
            editor.commit();
            //textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                Img4.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    public File saveImage(Bitmap bitmap) {
        File root = Environment.getExternalStorageDirectory();// internal storage launching .
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        SharedPreferences.Editor editor= preferences.edit();
        String filePath = root.getAbsolutePath() + "/DCIM/Camera/IMG_" + timeStamp + ".jpg";
        File file = new File(filePath);// determinig the type of the file and its place.
        editor.putString("image",filePath);
        editor.commit();
        try {
            // if gallary nit full create a file and save images
            file.createNewFile();// create new file to save image.
            FileOutputStream ostream = new FileOutputStream(file);//saves root in this file
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);// compass bitmap in file
            ostream.close();// close
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Faild to save image", Toast.LENGTH_SHORT).show();
        }
        return file;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Camera Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
