package com.sqlitetut;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText a, n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SqliteHelper db = new SqliteHelper(this);

        a = (EditText) findViewById(R.id.address);
        n = (EditText) findViewById(R.id.name);
        final Button add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Insert: ", "Inserting ..");
                String name = n.getText().toString();
                String address = a.getText().toString();
                db.addShop(new Shop(name, address));
            }
        });

        final Button show = (Button) findViewById(R.id.show);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Log.d("Reading: ", "Reading all shops..");
                List<Shop> shops = db.getAllShops();

                for (Shop shop : shops) {
                    String log = "Id: " + shop.getId() + " ,Name: " + shop.getName() + " ,Address: " + shop.getAddress();
                    // Writing shops  to log
                    Log.d("Shop: : ", log);
                }*/
                Intent intent = new Intent(MainActivity.this, SimpleService.class);
                stopService(intent);

            }
        });


        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(MainActivity.this, SimpleService.class);
                startService(intent);*/

                Intent broad = new Intent();
                broad.setAction("MyBroadcast");
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(broad);
            }
        });


        // Inserting Shop/Rows
        /*db.addShop(new Shop("Dockers", " 475 Brannan St #330, San Francisco, CA 94107, United States"));
        db.addShop(new Shop("Dunkin Donuts", "White Plains, NY 10601"));
        db.addShop(new Shop("Pizza Porlar", "North West Avenue, Boston , USA"));
        db.addShop(new Shop("Town Bakers", "Beverly Hills, CA 90210, USA"));*/

        // Reading all shops
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(new MyBroadCast(),
                        new IntentFilter("MyBroadcast"));

    }
}
