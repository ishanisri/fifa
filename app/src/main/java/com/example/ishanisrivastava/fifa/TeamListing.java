package com.example.ishanisrivastava.fifa;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamListing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_listing);

        String[] team1list=(String[])getIntent().getSerializableExtra("listteam1");
        String[] team2list=(String[])getIntent().getSerializableExtra("listteam2");
        String[] venuelist=(String[])getIntent().getSerializableExtra("listvenue");
        String[] datelist=(String[])getIntent().getSerializableExtra("listdate");
        String[] timelist=(String[])getIntent().getSerializableExtra("listtime");
        String[] bitmapArray1=(String[])getIntent().getSerializableExtra("listimg1");
        String[] bitmapArray2=(String[])getIntent().getSerializableExtra("listimg2");
       // List<Parcelable> pList= Arrays.asList(getIntent().getParcelableArrayExtra ("listimg1"));
       /* ArrayList<Bitmap> bitmapArray1=new ArrayList<Bitmap>();
        for(Parcelable px: pList){
            bitmapArray1.add((Bitmap)px);

        }
        List<Parcelable> pList2= Arrays.asList(getIntent().getParcelableArrayExtra ("listimg2"));
        ArrayList<Bitmap> bitmapArray2=new ArrayList<Bitmap>();
        for(Parcelable px: pList2){
            bitmapArray2.add((Bitmap)px);

        }*/

        //Bitmap[] bitmapArray1=(Bitmap[])getIntent().getParcelableArrayExtra ("listimg1");
       // Bitmap[] bitmapArray2=(Bitmap[])getIntent().getParcelableArrayExtra("listimg2");
        ListAdapter adapter = new CustomListView(TeamListing.this,team1list,team2list,venuelist,datelist,timelist,bitmapArray1,bitmapArray2);



        ListView list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);



            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

