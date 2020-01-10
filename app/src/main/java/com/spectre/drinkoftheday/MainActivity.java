package com.spectre.drinkoftheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToDrinksByName(View view) {
        Intent intent = new Intent(this,DrinksByName.class);
        startActivity(intent);
        finish();
    }
    public void goToDrinksByIngredient(View view) {
        Intent intent = new Intent(this,DrinksByIngredient.class);
        startActivity(intent);
        finish();
    }
    public void goToRandomDrinks(View view) {
        Intent intent = new Intent(this,RandomDrinks.class);
        startActivity(intent);
        finish();
    }
    public void goToAllDrinks(View view) {
        Intent intent = new Intent(this,AllDrinks.class);
        startActivity(intent);
        finish();
    }
    public void finish(View view) {
        finish();
    }
}
