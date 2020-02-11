package org.tensorflow.lite.examples.banijjosthiti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class PricelistActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String priceApple= " ", priceCarrot = " ", priceOrange= " ", priceBanana = " ";
    TextView tvApple, tvOrange, tvCarrot, tvBanana;

    String location = "demolocation1";

    private Spinner spinner;
    private static final String[] paths = {"ডেমো অবস্থান ১", "ডেমো অবস্থান ২", "অবস্থান নির্বাচন করুন"};
    FirebaseDatabase database;

    TextView tvLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricelist);

        tvApple = (TextView) findViewById(R.id.price_apple);
        tvOrange = (TextView) findViewById(R.id.price_orange);
        tvCarrot = (TextView) findViewById(R.id.price_carrot);
        tvBanana = (TextView) findViewById(R.id.price_banana);

        tvLocation = (TextView) findViewById(R.id.current_location);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PricelistActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        database = FirebaseDatabase.getInstance();

        updatePrice();

        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        TextView tvDate = (TextView) findViewById(R.id.date);
        tvDate.setText(currentDateTimeString);
    }

    void updatePrice(){
        try {
            DatabaseReference myRefApple = database.getReference("products/"+location+"/apple/price");

            myRefApple.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    priceApple = dataSnapshot.getValue(String.class);
                    tvApple.setText(priceApple + "টাকা/কেজি");
                    Log.d( "appleprice","Apple is: " + priceApple);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("appleprice", "Failed to read value.", error.toException());
                }
            });

        }catch (Exception e){
            Log.d("Whyerror", e.getMessage());
        }

        try {
            DatabaseReference myRefOrange = database.getReference("products/"+location+"/orange/price");

            myRefOrange.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    priceOrange = dataSnapshot.getValue(String.class);
                    tvOrange.setText(priceOrange + "টাকা/কেজি");
                    Log.d( "orangeprice","Orange is: " + priceOrange);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("orangeprice", "Failed to read value.", error.toException());
                }
            });

        }catch (Exception e){
            Log.d("Whyerror", e.getMessage());
        }

        try {
            DatabaseReference myRefCarrot = database.getReference("products/"+location+"/carrot/price");

            myRefCarrot.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    priceCarrot = dataSnapshot.getValue(String.class);
                    tvCarrot.setText(priceCarrot + "টাকা/কেজি");
                    Log.d( "carrotprice","Carrot is: " + priceCarrot);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("carrotprice", "Failed to read value.", error.toException());
                }
            });

        }catch (Exception e){
            Log.d("Whyerror", e.getMessage());
        }

        try {
            DatabaseReference myRefBanana = database.getReference("products/"+location+"/banana/price");

            myRefBanana.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    priceBanana = dataSnapshot.getValue(String.class);
                    tvBanana.setText(priceBanana + "টাকা/ডজন");
                    Log.d( "bananaprice","Banana is: " + priceBanana);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("bananaprice", "Failed to read value.", error.toException());
                }
            });

        }catch (Exception e){
            Log.d("Whyerror", e.getMessage());
        }
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected

//        editor.putString("demolocation", "demolocation1"); // Storing string
                tvLocation.setText("ডেমো লোকেশন ১");
                location = "demolocation1";
                updatePrice();
//        editor.commit(); // commit changes
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected

//        editor.putString("demolocation", "demolocation2"); // Storing string
                tvLocation.setText("ডেমো লোকেশন ২");
                location = "demolocation2";
                updatePrice();
//        editor.commit(); // commit changes
                break;
            case 2:
                Intent intent = new Intent(PricelistActivity.this, ChooseLocation.class);
                startActivity(intent);

                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}
