package com.example.ishanisrivastava.fifa;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;




public class CustomListView extends ArrayAdapter<String>{
    Activity context;
    private final String[] team1list;
     private final String[] team2list;
   private final  String[] venuelist;
     private final String[] datelist;
    private final String[] timelist;
    //ArrayList<Bitmap> bitmapArray1=new ArrayList<Bitmap>();
    //ArrayList<Bitmap> bitmapArray2=new ArrayList<Bitmap>();
    private final String[] bitmapArray1;
    private final String[] bitmapArray2;

    public LayoutInflater inflater;



    CustomListView(Activity context, String[] team1list, String[] team2list, String[] venuelist, String[] datelist, String[] timelist, String[] bitmapArray1,String[] bitmapArray2){
        super(context,R.layout.customview,team1list);
        this.context=context;
        this.team2list=team2list;
        this.team1list=team1list;
        this.venuelist=venuelist;
        this.datelist=datelist;
        this.timelist=timelist;
        this.bitmapArray1=bitmapArray1;
        this.bitmapArray2=bitmapArray2;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        //LayoutInflater.from(getContext());
        View customView=inflater.inflate(R.layout.customview,null);
         int i=0;
        String team1=getItem(position);
        for(i=0;i<team1list.length;i++)
        {
            if(team1.equals(team1list[i]))
                break;

        }
        String team2=team2list[i];
        String venue=venuelist[i];
        String datet=datelist[i];
        String timet=timelist[i];
        //Bitmap img1=bitmapArray1.get(i);
        //Bitmap img2=bitmapArray2.get(i);
        String img1=bitmapArray1[i];
        String img2=bitmapArray2[i];



        TextView team1Text=customView.findViewById(R.id.team1);
        TextView team2Text=customView.findViewById(R.id.team2);
        TextView venueText=customView.findViewById(R.id.venue);
        TextView dateText=customView.findViewById(R.id.datet);
        TextView timeText=customView.findViewById(R.id.timet);
        ImageView team1img=customView.findViewById(R.id.team1img);
       ImageView team2img=customView.findViewById(R.id.team2img);
        team1Text.setText(team1);
        team2Text.setText(team2);
        venueText.setText(venue);
        dateText.setText(datet);
        timeText.setText(timet);
        Uri selectedImageUri1 = Uri.parse(bitmapArray1[i]);
        Uri selectedImageUri2 = Uri.parse(bitmapArray2[i]);
        try{
            Bitmap bitmap1= MediaStore.Images.Media.getBitmap(context.getContentResolver(), selectedImageUri1);
            team1img.setImageBitmap(bitmap1);
            Bitmap bitmap2= MediaStore.Images.Media.getBitmap(context.getContentResolver(), selectedImageUri1);
            team1img.setImageBitmap(bitmap2);
        }
            catch(Exception e)
            {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG)
                        .show();
            }

                // BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri1));


        //.setImageBitmap(bitmap2);
        return customView;
        //team1img.setImageResource(R.drawable.stage1);


    }
}
