package com.example.ishanisrivastava.fifa;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList team1list = new ArrayList();
    ArrayList team2list = new ArrayList();
    ArrayList venuelist = new ArrayList();
    ArrayList datelist = new ArrayList();
    ArrayList timelist = new ArrayList();
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int RESULT_LOAD_IMG=1;
    ImageView imageTeam1;
    String imgDecodableString;



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
         imageTeam1 = findViewById(R.id.imageTeam1);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team1list.add(team1.getText());
                team2list.add(team2.getText());
                venuelist.add(venue.getText());
                datelist.add(datet.getText());
                timelist.add(timet.getText());
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
                String team1Name = (String) team1.getText();
                for (int i = 0; i < team1list.size(); i++) {
                    int m = team1list.indexOf(team1Name);
                    try{
                    if (team2list.get(m) == team2.getText() && datelist.get(m) == datet.getText() && venuelist.get(m) == venue.getText() && timelist.get(m) == timet.getText()) {
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

                    }}catch(Exception e) {
                        Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                    }


                }

            }
        });

    }

        public void dispatchTakePictureIntent(View v)
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
    public void imageFromGallery(View v) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    //Handle the result

    //Once after User picks image, we need to get the picked image data and set it in ImageView.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         try{
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");



                imageTeam1.setImageBitmap(imageBitmap);
            }else




            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data


                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                // Set the Image in ImageView after decoding the String
                imageTeam1.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));



            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG)
                    .show();
        }

    }


        }





