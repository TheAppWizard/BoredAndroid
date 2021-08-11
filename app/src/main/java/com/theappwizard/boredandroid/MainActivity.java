package com.theappwizard.boredandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

//for json array
//import com.android.volley.toolbox.JsonArrayRequest;
//import org.json.JSONArray;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    //Variables
    public TextView activityTV;
    public TextView typeTV;
    public TextView participantsTV;
    //public TextView priceTV;
    public TextView linkTV;
   // public TextView keyTV;
   // public TextView accessibilityTV;



    Button buttonShuffle;



//    public TextView dateTV;
//    private Calendar calendar;
//    private SimpleDateFormat dateFormat;
//    private String date;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);


        //Initialize
        activityTV = (TextView)findViewById(R.id.activityTV);
        typeTV = (TextView)findViewById(R.id.typeTV);
        participantsTV = (TextView)findViewById(R.id.participantsTV);

        linkTV = (TextView)findViewById(R.id.linkTV);




        //    priceTV = (TextView)findViewById(R.id.priceTV);
        //   keyTV = (TextView)findViewById(R.id.keyTV);
        //  accessibilityTV = (TextView)findViewById(R.id.accessibilityTV);



        //Button shuffle and colors
        buttonShuffle = (Button)findViewById(R.id.shuffleButton) ;

//
//        //for Date / Time
//        calendar = Calendar.getInstance();
//        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        date = dateFormat.format(calendar.getTime());
//        dateTV.setText(date);



        buttonShuffle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                JsonResponse();

            }

            private void JsonResponse() {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        "https://www.boredapi.com/api/activity", null, new Response.Listener<JSONObject>() {
                    @Override


                    public void onResponse( JSONObject response) {



                        try {
                              //Logging
//                            Log.d("boredtask", "onResponse: Anime/Character/Quote" +
//                                    response.getString("activity") + " : " + response.getString("type") + " : " + response.getString("participants"));

                            activityTV.setText(response.getString("activity"));
                            activityTV.setMovementMethod(new ScrollingMovementMethod());



                            participantsTV.setText(response.getString("participants"));
                            linkTV.setText(response.getString("link"));
                            linkTV.setMovementMethod(new ScrollingMovementMethod());

                            //capitalize first letter in java
                            String typeTVS =  response.getString("type");
                            String firsttypeTVS = typeTVS.substring(0,1);
                            String remtypeTVS = typeTVS.substring(1);

                            firsttypeTVS = firsttypeTVS.toUpperCase();
                            String firstLetterCapitalizedName = firsttypeTVS + remtypeTVS;
                            typeTV.setText(firstLetterCapitalizedName);






                            //  priceTV.setText(response.getString("price"));
                            // keyTV.setText(response.getString("key"));
                            //  accessibilityTV.setText(response.getString("accessibility"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }




                }, new Response.ErrorListener()


                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("boredtask", "Something went wrong");
                        Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                    }
                });




                requestQueue.add(jsonObjectRequest);


            }
        });

    }

}

