package com.example.ishanisrivastava.fifa;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> team1list = new ArrayList();
    List<String> team2list = new ArrayList();
    List<String> venuelist = new ArrayList();
    List<String> datelist = new ArrayList();
    List<String> timelist = new ArrayList();
    //ByteArrayOutputStream stream = new ByteArrayOutputStream();
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int RESULT_LOAD_IMG = 2;
    ImageView imageTeam1,imageTeam2;
    String imgDecodableString;
    public Bitmap bitmap;
    int img1flag=0;
    int img2flag=0;
    Button camera,gallery;
    //ByteArrayOutputStream stream = new ByteArrayOutputStream();
    List<String> bitmapArray1 = new ArrayList<String>();
    ArrayList<String> bitmapArray2 = new ArrayList<String>();
             //byte[] byteArray = stream.toByteArray();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);
        final TextView team1 = findViewById(R.id.team1);
        final TextView team2 = findViewById(R.id.team2);
        final TextView venue = findViewById(R.id.venue);
        final TextView datet = findViewById(R.id.date);
        final TextView timet = findViewById(R.id.time);
        final Button fixtureList = findViewById(R.id.fixtureList);
          camera=findViewById(R.id.camera1);
         gallery=findViewById(R.id.gallery1);

        imageTeam1 = findViewById(R.id.imageTeam1);
        imageTeam2=findViewById(R.id.imageTeam2);
        camera.setEnabled(false);
        gallery.setEnabled(false);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team1list.add(team1.getText().toString());
                team2list.add(team2.getText().toString());
                venuelist.add(venue.getText().toString());
                datelist.add(datet.getText().toString());
                timelist.add(timet.getText().toString());
                team1.setText("");
                team2.setText("");
                venue.setText("");
                datet.setText("");
                timet.setText("");


            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag=0;
                String team1Name =  team1.getText().toString();
                int m = team1list.indexOf(team1Name);
                for (int i = 0; i < team1list.size(); i++) {


                        if (team2list.get(m) .equals(team2.getText().toString()) && datelist.get(m).equals(datet.getText().toString()) && venuelist.get(m).equals(venue.getText().toString() )&& timelist.get(m).equals(timet.getText().toString())) {
                            team1list.remove(m);
                            team2list.remove(m);
                            datelist.remove(m);
                            venuelist.remove(m);
                            timelist.remove(m);
                            team1.setText("");
                            team2.setText("");
                            venue.setText("");
                            datet.setText("");
                            timet.setText("");
                            flag=1;
                            break;

                        }}if(flag==0)

                        Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();



                }

            }
        );



        fixtureList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] team1listarray=team1list.toArray(new String[team1list.size()]);
                String[] team2listarray=team2list.toArray(new String[team2list.size()]);
                String[] venuelistarray=venuelist.toArray(new String[venuelist.size()]);
                String[] datelistarray=datelist.toArray(new String[datelist.size()]);
                String[] timelistarray=timelist.toArray(new String[timelist.size()]);
                String[] imgArray1=bitmapArray1.toArray(new String[bitmapArray1.size()]);
                String[] imgArray2=bitmapArray2.toArray(new String[bitmapArray2.size()]);
                Intent intent = new Intent(MainActivity.this, TeamListing.class);
                intent.putExtra("listteam1", team1listarray);
                intent.putExtra("listteam2", team2listarray);
                intent.putExtra("listvenue", venuelistarray);
                intent.putExtra("listdate", datelistarray);
                intent.putExtra("listtime", timelistarray);
                intent.putExtra("listimg1",imgArray1);
                intent.putExtra("listimg2",imgArray2);

               // Log.d("my",team2listarray[0]);
                startActivity(intent);
                /*ListAdapter adapter = new CustomListView(MainActivity.this,team1listarray);

                ListView list = (ListView) findViewById(R.id.list);
                list.setAdapter(adapter);*/
                }
                });
        imageTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1flag=1;
                camera.setEnabled(true);
                gallery.setEnabled(true);

            }
            });

        imageTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2flag=1;
                camera.setEnabled(true);
                gallery.setEnabled(true);

            }
        });

       /* fixtureList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your second activity
                Intent intent = new Intent(MainActivity.this, TeamListing.class);
                intent.putExtra("listteam1", team1list);
                startActivity(intent);
            }
        });*/
    }





        public void dispatchTakePictureIntent (View v)
        {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
        /*@Override
        protected void onActivityResult(int requestCode,int resultCode, Intent data) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                try {


                    imageTeam1.setImageBitmap(imageBitmap);
                }
                catch(Exception e)
                {;
                }
            }
        }*/

       /* private void imageFromGallery(){
         Intent intent=new Intent();
         intent.setType("image/*");
         intent.setAction(Intent.ACTION_GET_CONTENT);
         startActivityForResult(Intent.createChooser(intent,"Select Picture"),REQUEST_CODE);*/
        // Create intent to Open Image applications like Gallery, Google Photos




        public void imageFromGallery (View v){
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // Start the Intent
            startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
        }
    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("picUri", picUri);
    }

    // Recover the saved state when the activity is recreated.
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        picUri= savedInstanceState.getParcelable("picUri");

    }*/


    public String getRealPathFromURI(Uri contentUri)
    {
        try
        {
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        catch (Exception e)
        {
            return contentUri.getPath();
        }
    }

        //Handle the result

        //Once after User picks image, we need to get the picked image data and set it in ImageView.
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            try { if(resultCode==RESULT_OK){

                switch(requestCode){
                case REQUEST_IMAGE_CAPTURE :
                    Bundle extras = data.getExtras();
                    if(extras!=null) {
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        Uri selectedImageUri = data.getData();
                        String uri=getRealPathFromURI(data.getData());
                        Log.d("hello1","image loaded");
                        if(img1flag==1)
                        {imageTeam1.setImageBitmap(imageBitmap);
                        bitmapArray1.add(uri);}
                        if(img2flag==1) {
                            imageTeam2.setImageBitmap(imageBitmap);
                            bitmapArray2.add(uri);
                            Log.d("hello2","Image loaded");
                        }
                        camera.setEnabled(false);
                        gallery.setEnabled(false);
                        img1flag=0;
                        img2flag=0;

                    }
                    break;

                    // When an Image is picked
                case RESULT_LOAD_IMG :
                    if(null != data)
            {
                        // Get the Image from data
                      /*  Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        // Get the cursor
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        imageTeam2.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));*/
                Uri selectedImageUri = data.getData();
                Bitmap bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));

                if(img1flag==1) {
                    imageTeam1.setImageBitmap(bitmap);
                    //bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    bitmapArray1.add(selectedImageUri.toString());


                }
                if(img2flag==1)
                {imageTeam2.setImageBitmap(bitmap);
                bitmapArray2.add(selectedImageUri.toString());}
                camera.setEnabled(false);
                gallery.setEnabled(false);
                img1flag=0;
                img2flag=0;

                  /* try{
                            if (bitmap != null) {
                                bitmap.recycle();
                            }
                       InputStream stream = getContentResolver().openInputStream(data.getData());
                        bitmap = BitmapFactory.decodeStream(stream);
                        stream.close();
                        imageTeam2.setImageBitmap(bitmap);}
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/


                }
                break;
                    /* else {
                        Toast.makeText(this, "You haven't picked Image",
                                Toast.LENGTH_LONG).show();*/
                    }
            }} catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG)
                        .show();
             Log.d("error",e.toString());}

        }







    }


