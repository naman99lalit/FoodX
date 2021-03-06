package com.foodx.nsh;

import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.tzanou.PercentVisibleLayout.PercentVisibleLayout;

import org.json.JSONObject;

import jp.wasabeef.blurry.Blurry;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class MainActivity extends AppCompatActivity {

    public static int q1 = 0, q2 = 0, q3 = 0, q4 = 0, q5 = 0, q6 = 0, q7 = 0, q8 = 0, q9 = 0, q10 = 0;
    public static int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 10, p6 = 10, p7 = 10, p8 = 10, p9 = 10, p10 = 10;
    public static int total = 0, total1a = 0, total2a = 0,total3a = 0,total4a = 0,total5a = 0,total6a = 0,total7a = 0,total8a = 0,total9a = 0,total10a = 0;
    public static String t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
    public int check = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        final TextView quantity1 = findViewById(R.id.quantity1);
        final TextView quantity2 = findViewById(R.id.quantity2);
        final TextView quantity3 = findViewById(R.id.quantity3);
        final TextView quantity4 = findViewById(R.id.quantity4);
        final TextView quantity5 = findViewById(R.id.quantity5);
        final TextView quantity6 = findViewById(R.id.quantity6);
        final TextView quantity7 = findViewById(R.id.quantity7);
        final TextView quantity8 = findViewById(R.id.quantity8);
        final TextView quantity9 = findViewById(R.id.quantity9);
        final TextView quantity10 = findViewById(R.id.quantity10);

        final TextView amount1 = findViewById(R.id.amount1);
        final TextView amount2 = findViewById(R.id.amount2);
        final TextView amount3 = findViewById(R.id.amount3);
        final TextView amount4 = findViewById(R.id.amount4);
        final TextView amount5 = findViewById(R.id.amount5);
        final TextView amount6 = findViewById(R.id.amount6);
        final TextView amount7 = findViewById(R.id.amount7);
        final TextView amount8 = findViewById(R.id.amount8);
        final TextView amount9 = findViewById(R.id.amount9);
        final TextView amount10 = findViewById(R.id.amount10);

        final ImageButton add1 = findViewById(R.id.add1);
        final ImageButton add2 = findViewById(R.id.add2);
        final ImageButton add3 = findViewById(R.id.add3);
        final ImageButton add4 = findViewById(R.id.add4);
        final ImageButton add5 = findViewById(R.id.add5);
        final ImageButton add6 = findViewById(R.id.add6);
        final ImageButton add7 = findViewById(R.id.add7);
        final ImageButton add8 = findViewById(R.id.add8);
        final ImageButton add9 = findViewById(R.id.add9);
        final ImageButton add10 = findViewById(R.id.add10);

        final ImageButton sub1 = findViewById(R.id.sub1);
        final ImageButton sub2 = findViewById(R.id.sub2);
        final ImageButton sub3 = findViewById(R.id.sub3);
        final ImageButton sub4 = findViewById(R.id.sub4);
        final ImageButton sub5 = findViewById(R.id.sub5);
        final ImageButton sub6 = findViewById(R.id.sub6);
        final ImageButton sub7 = findViewById(R.id.sub7);
        final ImageButton sub8 = findViewById(R.id.sub8);
        final ImageButton sub9 = findViewById(R.id.sub9);
        final ImageButton sub10 = findViewById(R.id.sub10);

        final TextView real1 = findViewById(R.id.real1);
        final TextView real2 = findViewById(R.id.real2);
        final TextView real3 = findViewById(R.id.real3);
        final TextView real4 = findViewById(R.id.real4);
        final TextView real5 = findViewById(R.id.real5);
        final TextView real6 = findViewById(R.id.real6);
        final TextView real7 = findViewById(R.id.real7);
        final TextView real8 = findViewById(R.id.real8);
        final TextView real9 = findViewById(R.id.real9);
        final TextView real10 = findViewById(R.id.real10);


        final LottieAnimationView anim = findViewById(R.id.menu_anim);

        final TextView total1 = findViewById(R.id.total1);
        final TextView total2 = findViewById(R.id.total2);
        final TextView total3 = findViewById(R.id.total3);

        final ImageButton cart = findViewById(R.id.cart);
        final ImageButton info = findViewById(R.id.info);

        final LinearLayout main = findViewById(R.id.main);
        final LinearLayout g1 = findViewById(R.id.group1);
        final LinearLayout g2 = findViewById(R.id.group2);
        final LinearLayout g3 = findViewById(R.id.group3);
        final LinearLayout g4 = findViewById(R.id.group4);
        final LinearLayout g5 = findViewById(R.id.group5);

        final ImageView image1 = findViewById(R.id.image1);
        final ImageView image2 = findViewById(R.id.image2);
        final ImageView image3 = findViewById(R.id.image3);
        final ImageView image4 = findViewById(R.id.image4);
        final ImageView image5 = findViewById(R.id.image5);
        final ImageView image6 = findViewById(R.id.image6);
        final ImageView image7 = findViewById(R.id.image7);
        final ImageView image8 = findViewById(R.id.image8);
        final ImageView image9 = findViewById(R.id.image9);
        final ImageView image10 = findViewById(R.id.image10);

        final TextView title1 = findViewById(R.id.title1);
        final TextView title2 = findViewById(R.id.title2);
        final TextView title3 = findViewById(R.id.title3);
        final TextView title4 = findViewById(R.id.title4);
        final TextView title5 = findViewById(R.id.title5);
        final TextView title6 = findViewById(R.id.title6);
        final TextView title7 = findViewById(R.id.title7);
        final TextView title8 = findViewById(R.id.title8);
        final TextView title9 = findViewById(R.id.title9);
        final TextView title10 = findViewById(R.id.title10);

        title1.setText("Loading");
        title2.setText("Loading");
        title3.setText("Loading");
        title4.setText("Loading");
        title5.setText("Loading");
        title6.setText("Loading");
        title7.setText("Loading");
        title8.setText("Loading");
        title9.setText("Loading");
        title10.setText("Loading");

        final TextView price1 = findViewById(R.id.price1);
        final TextView price2 = findViewById(R.id.price2);
        final TextView price3 = findViewById(R.id.price3);
        final TextView price4 = findViewById(R.id.price4);
        final TextView price5 = findViewById(R.id.price5);
        final TextView price6 = findViewById(R.id.price6);
        final TextView price7 = findViewById(R.id.price7);
        final TextView price8 = findViewById(R.id.price8);
        final TextView price9 = findViewById(R.id.price9);
        final TextView price10 = findViewById(R.id.price10);

        price1.setText("Loading");
        price2.setText("Loading");
        price3.setText("Loading");
        price4.setText("Loading");
        price5.setText("Loading");
        price6.setText("Loading");
        price7.setText("Loading");
        price8.setText("Loading");
        price9.setText("Loading");
        price10.setText("Loading");



        //Welcome Screen
        final RelativeLayout welcome = findViewById(R.id.welcome);
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("Welcome", MODE_PRIVATE);
        final SharedPreferences.Editor editor1 = pref.edit();
        int wel_v = pref.getInt("welcome", 0);
        if ( wel_v == 0 )
        {
            welcome.setVisibility(View.VISIBLE);
            editor1.putInt("welcome", 1);
            editor1.commit();
        }
        else
        {
            welcome.setVisibility(View.GONE);
        }

        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              welcome.setVisibility(View.GONE);
            }
        });





        //panel click
        findViewById(R.id.contributors).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,info.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.Feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] TO = {"jrec.singh@gmail.com"};
                String[] CC = {"hadanis.singh@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "FoodX Feedback");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, Please give your feedback here (Dont change the subject)");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        findViewById(R.id.contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9560705734"));
                startActivity(intent);
            }
        });

        findViewById(R.id.recent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(MainActivity.this, feedback.class);
                startActivity(intent);*/

                Toast.makeText(MainActivity.this, "This feature will be added shortly", Toast.LENGTH_LONG).show();


            }
        });

        final Button update = findViewById(R.id.update);

        //firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("title").child("title1");
        DatabaseReference myRef2 = database.getReference("title").child("title2");
        DatabaseReference myRef3 = database.getReference("title").child("title3");
        DatabaseReference myRef4 = database.getReference("title").child("title4");
        DatabaseReference myRef5 = database.getReference("title").child("title5");
        DatabaseReference myRef6 = database.getReference("title").child("title6");
        DatabaseReference myRef7 = database.getReference("title").child("title7");
        DatabaseReference myRef8 = database.getReference("title").child("title8");
        DatabaseReference myRef9 = database.getReference("title").child("title9");
        DatabaseReference myRef10 = database.getReference("title").child("title10");


        DatabaseReference myRef1a = database.getReference("price").child("price1");
        DatabaseReference myRef2a = database.getReference("price").child("price2");
        DatabaseReference myRef3a = database.getReference("price").child("price3");
        DatabaseReference myRef4a = database.getReference("price").child("price4");
        DatabaseReference myRef5a = database.getReference("price").child("price5");
        DatabaseReference myRef6a = database.getReference("price").child("price6");
        DatabaseReference myRef7a = database.getReference("price").child("price7");
        DatabaseReference myRef8a = database.getReference("price").child("price8");
        DatabaseReference myRef9a = database.getReference("price").child("price9");
        DatabaseReference myRef10a = database.getReference("price").child("price10");

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference updater = database1.getReference("updater");

        final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        final StorageReference imgRef1 = mStorageRef.child("images/img1.jpg");
        final StorageReference imgRef2 = mStorageRef.child("images/img2.jpg");
        final StorageReference imgRef3 = mStorageRef.child("images/img3.jpg");
        final StorageReference imgRef4 = mStorageRef.child("images/img4.jpg");
        final StorageReference imgRef5 = mStorageRef.child("images/img5.jpg");
        final StorageReference imgRef6 = mStorageRef.child("images/img6.jpg");
        final StorageReference imgRef7 = mStorageRef.child("images/img7.jpg");
        final StorageReference imgRef8 = mStorageRef.child("images/img8.jpg");
        final StorageReference imgRef9 = mStorageRef.child("images/img9.jpg");
        final StorageReference imgRef10 = mStorageRef.child("images/img10.jpg");

        final LinearLayout up_layout = findViewById(R.id.up_layout);

        updater.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyUpdate", MODE_PRIVATE);
                final SharedPreferences.Editor editor = pref.edit();
                int update_value = pref.getInt("updater", 0);

                if (update_value < value) {
                    editor.putInt("updater", value);
                    editor.commit();
                    up_layout.setVisibility(View.VISIBLE);
                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Glide.get(MainActivity.this).clearMemory();
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    Glide.get(MainActivity.this).clearDiskCache();


                                }
                            });


                            new Handler().postDelayed(new Runnable() {


                                @Override
                                public void run() {

                                    Intent mStartActivity = new Intent(MainActivity.this, splashscreen.class);
                                    int mPendingIntentId = 123456;
                                    PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this, mPendingIntentId, mStartActivity,
                                            PendingIntent.FLAG_CANCEL_CURRENT);
                                    AlarmManager mgr = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
                                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                                    System.exit(0);


                                }
                            }, 1000);


                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });


        GlideApp.with(MainActivity.this)
                .load(imgRef1)
                .centerCrop()
                .transition(withCrossFade())
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image1);
        GlideApp.with(MainActivity.this)
                .load(imgRef2)
                .transition(withCrossFade())
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image2);
        GlideApp.with(MainActivity.this)
                .load(imgRef3)
                .transition(withCrossFade())
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image3);
        GlideApp.with(MainActivity.this)
                .load(imgRef4)
                .transition(withCrossFade())
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image4);
        GlideApp.with(MainActivity.this)
                .load(imgRef5)
                .transition(withCrossFade())
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image5);
        GlideApp.with(MainActivity.this)
                .load(imgRef6)
                .transition(withCrossFade())
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image6);
        GlideApp.with(MainActivity.this)
                .load(imgRef7)
                .transition(withCrossFade())
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image7);
        GlideApp.with(MainActivity.this)
                .load(imgRef8)
                .transition(withCrossFade())
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image8);
        GlideApp.with(MainActivity.this)
                .load(imgRef9)
                .transition(withCrossFade())
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image9);
        GlideApp.with(MainActivity.this)
                .load(imgRef10)
                .transition(withCrossFade())
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image10);

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                t1 = value;
                title1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                t2 = value;
                title2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                t3 = value;
                title3.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                t4 = value;
                title4.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                t5 = value;
                title5.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                t6 = value;
                title6.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                t7 = value;
                title7.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                t8 = value;
                title8.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                t9 = value;
                title9.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                t10 = value;
                title10.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });

        myRef1a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                p1 = (value);
                price1.setText("Price : " + Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef2a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                p2 = (value);
                price2.setText("Price : " + Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef3a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                p3 = (value);
                price3.setText("Price : " + Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef4a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                p4 = (value);
                price4.setText("Price : " + Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef5a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                p5 = (value);
                price5.setText("Price : " + Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef6a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                p6 = (value);
                price6.setText("Price : " + Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef7a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                p7 = (value);
                price7.setText("Price : " + Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef8a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                p8 = (value);
                price8.setText("Price : " + Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef9a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                p9 = (value);
                price9.setText("Price : " + Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });
        myRef10a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                p10 = (value);
                price10.setText("Price : " + Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });


        //animation
        Animation main_anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slideup_main);
        main_anim.setStartOffset(200);
        g1.startAnimation(main_anim);

        Animation main_anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slideup_main);
        main_anim1.setStartOffset(400);
        g2.startAnimation(main_anim1);

        Animation main_anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slideup_main);
        main_anim2.setStartOffset(600);
        g3.startAnimation(main_anim2);

        Animation main_anim3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slideup_main);
        main_anim3.setStartOffset(800);
        g4.startAnimation(main_anim3);

        Animation main_anim4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slideup_main);
        main_anim4.setStartOffset(1000);
        g5.startAnimation(main_anim4);


        //Clicking Listeners begin here

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q1 < 15)
                    q1 += 1;
                if (q1 > 0) {
                    quantity1.setText(Integer.toString(q1 * p1));
                    if (total1a < 15 * p1)
                    {
                        total1a += (p1);
                    total += p1;}
                    quantity1.setVisibility(View.VISIBLE);
                    real1.setText(Integer.toString(q1));
                    real1.setVisibility(View.VISIBLE);
                    amount1.setVisibility(View.VISIBLE);
                } else {
                    quantity1.setVisibility(View.INVISIBLE);
                    real1.setVisibility(View.INVISIBLE);
                    amount1.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q2 < 15)
                    q2 += 1;
                if (q2 > 0) {
                    quantity2.setText(Integer.toString(q2 * p2));

                    if (total2a < 15 * p2)

                    {    total += p2;
                            total2a += (p2);}

                    quantity2.setVisibility(View.VISIBLE);
                    real2.setText(Integer.toString(q2));
                    real2.setVisibility(View.VISIBLE);
                    amount2.setVisibility(View.VISIBLE);
                } else {
                    quantity2.setVisibility(View.INVISIBLE);
                    real2.setVisibility(View.INVISIBLE);
                    amount2.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q3 < 15)
                    q3 += 1;
                if (q3 > 0) {
                    quantity3.setText(Integer.toString(q3 * p3));
                    if (total3a < 15 * p3)

                    {    total += p3;
                        total3a += (p3);}
                    quantity3.setVisibility(View.VISIBLE);
                    real3.setText(Integer.toString(q3));
                    real3.setVisibility(View.VISIBLE);
                    amount3.setVisibility(View.VISIBLE);
                } else {
                    quantity3.setVisibility(View.INVISIBLE);
                    real3.setVisibility(View.INVISIBLE);
                    amount3.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q4 < 15)
                    q4 += 1;
                if (q4 > 0) {
                    quantity4.setText(Integer.toString(q4 * p4));
                    if (total4a < 15 * p4)

                    {    total += p4;
                        total4a += (p4);}
                    quantity4.setVisibility(View.VISIBLE);
                    real4.setText(Integer.toString(q4));

                    real4.setVisibility(View.VISIBLE);
                    amount4.setVisibility(View.VISIBLE);
                } else {
                    quantity4.setVisibility(View.INVISIBLE);
                    real4.setVisibility(View.INVISIBLE);
                    amount4.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q5 < 15)
                    q5 += 1;
                if (q5 > 0) {
                    quantity5.setText(Integer.toString(q5 * p5));
                    if (total5a < 15 * p5)

                    {    total += p5;
                        total5a += (p5);}
                    quantity5.setVisibility(View.VISIBLE);
                    real5.setText(Integer.toString(q5));
                    real5.setVisibility(View.VISIBLE);
                    amount5.setVisibility(View.VISIBLE);
                } else {
                    quantity5.setVisibility(View.INVISIBLE);
                    real5.setVisibility(View.INVISIBLE);
                    amount5.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q6 < 15)
                    q6 += 1;
                if (q6 > 0) {
                    quantity6.setText(Integer.toString(q6 * p6));
                    if (total6a < 15 * p6)

                    {    total += p6;
                        total6a += (p6);}
                    quantity6.setVisibility(View.VISIBLE);
                    real6.setText(Integer.toString(q6));
                    real6.setVisibility(View.VISIBLE);
                    amount6.setVisibility(View.VISIBLE);
                } else {
                    quantity6.setVisibility(View.INVISIBLE);
                    real6.setVisibility(View.INVISIBLE);
                    amount6.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q7 < 15)
                    q7 += 1;
                if (q7 > 0) {
                    quantity7.setText(Integer.toString(q7 * p7));
                    if (total7a < 15 * p7)

                    {    total += p7;
                        total7a += (p7);}
                    quantity7.setVisibility(View.VISIBLE);
                    real7.setText(Integer.toString(q7));
                    real7.setVisibility(View.VISIBLE);
                    amount7.setVisibility(View.VISIBLE);
                } else {
                    quantity7.setVisibility(View.INVISIBLE);
                    real7.setVisibility(View.INVISIBLE);
                    amount7.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q8 < 15)
                    q8 += 1;
                if (q8 > 0) {
                    quantity8.setText(Integer.toString(q8 * p8));
                    if (total8a < 15 * p8)

                    {    total += p8;
                        total8a += (p8);}
                    quantity8.setVisibility(View.VISIBLE);
                    real8.setText(Integer.toString(q8));
                    real8.setVisibility(View.VISIBLE);
                    amount8.setVisibility(View.VISIBLE);
                } else {
                    quantity8.setVisibility(View.INVISIBLE);
                    real8.setVisibility(View.INVISIBLE);
                    amount8.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q9 < 15)
                    q9 += 1;
                if (q9 > 0) {
                    quantity9.setText(Integer.toString(q9 * p9));
                    if (total9a < 15 * p9)

                    {    total += p9;
                        total9a += (p9);}
                    quantity9.setVisibility(View.VISIBLE);
                    real9.setText(Integer.toString(q9));
                    real9.setVisibility(View.VISIBLE);
                    amount9.setVisibility(View.VISIBLE);
                } else {
                    quantity9.setVisibility(View.INVISIBLE);
                    real9.setVisibility(View.INVISIBLE);
                    amount9.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q10 < 15)
                    q10 += 1;

                if (q10 > 0) {
                    quantity10.setText(Integer.toString(q10 * p10));
                    if (total10a < 15 * p10)

                    {    total += p10;
                        total10a += (p10);}
                    quantity10.setVisibility(View.VISIBLE);
                    real10.setText(Integer.toString(q10));
                    real10.setVisibility(View.VISIBLE);
                    amount10.setVisibility(View.VISIBLE);
                } else {
                    quantity10.setVisibility(View.INVISIBLE);
                    real10.setVisibility(View.INVISIBLE);
                    amount10.setVisibility(View.INVISIBLE);
                }


                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q1 > 0) {
                    q1 -= 1;
                    if (total1a > 0)

                    {    total -= p1;
                        total1a -= (p1);}
                }
                if (q1 > 0) {
                    quantity1.setText(Integer.toString(q1 * p1));

                    quantity1.setVisibility(View.VISIBLE);
                    real1.setText(Integer.toString(q1));
                    real1.setVisibility(View.VISIBLE);
                    amount1.setVisibility(View.VISIBLE);
                } else {
                    quantity1.setVisibility(View.INVISIBLE);
                    real1.setVisibility(View.INVISIBLE);
                    amount1.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });

        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q2 > 0) {
                    if (total2a > 0)

                    {    total -= p2;
                        total2a -= (p2);}
                    q2 -= 1;
                }
                if (q2 > 0) {
                    quantity2.setText(Integer.toString(q2 * p2));

                    quantity2.setVisibility(View.VISIBLE);
                    real2.setText(Integer.toString(q2));
                    real2.setVisibility(View.VISIBLE);
                    amount2.setVisibility(View.VISIBLE);
                } else {
                    quantity2.setVisibility(View.INVISIBLE);
                    real2.setVisibility(View.INVISIBLE);
                    amount2.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q3 > 0) {
                    if (total3a > 0)

                    {    total -= p3;
                        total3a -= (p3);}
                    q3 -= 1;
                }
                if (q3 > 0) {
                    quantity3.setText(Integer.toString(q3 * p3));

                    quantity3.setVisibility(View.VISIBLE);
                    real3.setText(Integer.toString(q3));
                    real3.setVisibility(View.VISIBLE);
                    amount3.setVisibility(View.VISIBLE);
                } else {
                    quantity3.setVisibility(View.INVISIBLE);
                    real3.setVisibility(View.INVISIBLE);
                    amount3.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q4 > 0) {
                    if (total4a > 0)

                    {    total -= p4;
                        total4a -= (p4);}
                    q4 -= 1;
                }
                if (q4 > 0) {
                    quantity4.setText(Integer.toString(q4 * p4));

                    quantity4.setVisibility(View.VISIBLE);
                    real4.setText(Integer.toString(q4));
                    real4.setVisibility(View.VISIBLE);
                    amount4.setVisibility(View.VISIBLE);
                } else {
                    quantity4.setVisibility(View.INVISIBLE);
                    real4.setVisibility(View.INVISIBLE);
                    amount4.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q5 > 0) {
                    if (total5a > 0)

                    {    total -= p5;
                        total5a -= (p5);}
                    q5 -= 1;
                }
                if (q5 > 0) {
                    quantity5.setText(Integer.toString(q5 * p5));

                    quantity5.setVisibility(View.VISIBLE);
                    real5.setText(Integer.toString(q5));
                    real5.setVisibility(View.VISIBLE);
                    amount5.setVisibility(View.VISIBLE);
                } else {
                    quantity5.setVisibility(View.INVISIBLE);
                    real5.setVisibility(View.INVISIBLE);
                    amount5.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q6 > 0) {
                    if (total6a > 0)

                    {    total -= p6;
                        total6a -= (p6);}
                    q6 -= 1;
                }
                if (q6 > 0) {
                    quantity6.setText(Integer.toString(q6 * p6));

                    quantity6.setVisibility(View.VISIBLE);
                    real6.setText(Integer.toString(q6));
                    real6.setVisibility(View.VISIBLE);
                    amount6.setVisibility(View.VISIBLE);
                } else {
                    quantity6.setVisibility(View.INVISIBLE);
                    real6.setVisibility(View.INVISIBLE);
                    amount6.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q7 > 0) {
                    if (total7a > 0)

                    {    total -= p7;
                        total7a -= (p7);}
                    q7 -= 1;
                }
                if (q7 > 0) {
                    quantity7.setText(Integer.toString(q7 * p7));

                    quantity7.setVisibility(View.VISIBLE);
                    real7.setText(Integer.toString(q7));
                    real7.setVisibility(View.VISIBLE);
                    amount7.setVisibility(View.VISIBLE);
                } else {
                    quantity7.setVisibility(View.INVISIBLE);
                    real7.setVisibility(View.INVISIBLE);
                    amount7.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q8 > 0) {
                    if (total8a > 0)

                    {    total -= p8;
                        total8a -= (p8);}
                        q8 -= 1;
                }
                if (q8 > 0) {
                    quantity8.setText(Integer.toString(q8 * p8));

                    quantity8.setVisibility(View.VISIBLE);
                    real8.setText(Integer.toString(q8));
                    real8.setVisibility(View.VISIBLE);
                    amount8.setVisibility(View.VISIBLE);
                } else {
                    quantity8.setVisibility(View.INVISIBLE);
                    real8.setVisibility(View.INVISIBLE);
                    amount8.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q9 > 0) {
                    if (total9a > 0)

                    {    total -= p9;
                        total9a -= (p9);}
                    q9 -= 1;
                }
                if (q9 > 0) {
                    quantity9.setText(Integer.toString(q9 * p9));

                    quantity9.setVisibility(View.VISIBLE);
                    real9.setText(Integer.toString(q9));
                    real9.setVisibility(View.VISIBLE);
                    amount9.setVisibility(View.VISIBLE);
                } else {
                    quantity9.setVisibility(View.INVISIBLE);
                    real9.setVisibility(View.INVISIBLE);
                    amount9.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }

            }
        });
        sub10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q10 > 0) {

                    q10 -= 1;
                    if (total10a > 0)

                    {    total -= p10;
                        total10a -= (p10);}
                }
                if (q10 > 0) {
                    quantity10.setText(Integer.toString(q10 * p10));



                    quantity10.setVisibility(View.VISIBLE);
                    real10.setText(Integer.toString(q10));
                    amount10.setVisibility(View.VISIBLE);
                    real10.setVisibility(View.VISIBLE);
                } else {
                    quantity10.setVisibility(View.INVISIBLE);
                    real10.setVisibility(View.INVISIBLE);
                    amount10.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));

                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });


        findViewById(R.id.tick_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cart.class);
                startActivity(intent);

            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, info.class);
                startActivity(intent);

            }
        });

    }

    public void appBarAnim(int total) {

       /* final TextView menu1 = findViewById(R.id.menu1);
        final TextView menu2 = findViewById(R.id.menu2);*/
        final TextView total1 = findViewById(R.id.total1);
        final TextView total2 = findViewById(R.id.total2);
        final TextView total3 = findViewById(R.id.total3);
        final LottieAnimationView anim = findViewById(R.id.menu_anim);


        Animation zero_up = AnimationUtils.loadAnimation(this, R.anim.slideup);
        Animation down_zero = AnimationUtils.loadAnimation(this, R.anim.slideup1);

        total3.setText(Integer.toString(total));
        /*menu1.startAnimation(zero_up);
        menu2.startAnimation(zero_up);
        menu1.setVisibility(View.INVISIBLE);
        menu2.setVisibility(View.INVISIBLE);
*/
        anim.startAnimation(zero_up);
        anim.setVisibility(View.INVISIBLE);

        total1.startAnimation(down_zero);
        total2.startAnimation(down_zero);
        total3.startAnimation(down_zero);
        total1.setVisibility(View.VISIBLE);
        total2.setVisibility(View.VISIBLE);
        total3.setVisibility(View.VISIBLE);
        findViewById(R.id.tick_anim).setVisibility(View.VISIBLE);
        check = 1;

    }

    public void appBarAnim1() {
/*    final TextView menu1 = findViewById(R.id.menu1);
    final TextView menu2 = findViewById(R.id.menu2);*/
        findViewById(R.id.tick_anim).setVisibility(View.INVISIBLE);
        final TextView total1 = findViewById(R.id.total1);
        final TextView total2 = findViewById(R.id.total2);
        final TextView total3 = findViewById(R.id.total3);
        final LottieAnimationView anim = findViewById(R.id.menu_anim);


        Animation up_zero = AnimationUtils.loadAnimation(this, R.anim.slideup2);
        Animation zero_down = AnimationUtils.loadAnimation(this, R.anim.slideup3);

 /*   menu1.startAnimation(up_zero);
    menu2.startAnimation(up_zero);
    menu1.setVisibility(View.VISIBLE);
    menu2.setVisibility(View.VISIBLE);
*/

        anim.startAnimation(up_zero);
        anim.setVisibility(View.VISIBLE);

        total1.startAnimation(zero_down);
        total2.startAnimation(zero_down);
        total3.startAnimation(zero_down);
        total1.setVisibility(View.INVISIBLE);
        total2.setVisibility(View.INVISIBLE);
        total3.setVisibility(View.INVISIBLE);


        check = 0;

    }

}

