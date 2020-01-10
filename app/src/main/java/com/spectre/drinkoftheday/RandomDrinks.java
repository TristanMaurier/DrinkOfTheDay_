package com.spectre.drinkoftheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RandomDrinks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_drinks);

        AsyncHTTPrandom task = new AsyncHTTPrandom(RandomDrinks.this);
        task.execute("https://www.thecocktaildb.com/api/json/v1/1/random.php");
        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AsyncHTTPrandom task = new AsyncHTTPrandom(RandomDrinks.this);
                task.execute("https://www.thecocktaildb.com/api/json/v1/1/random.php");
            }
        });
    }
    public void goToMain(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
