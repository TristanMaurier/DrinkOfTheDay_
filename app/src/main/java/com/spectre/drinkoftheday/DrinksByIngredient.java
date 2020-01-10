package com.spectre.drinkoftheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DrinksByIngredient extends AppCompatActivity {

    String Ingredient;
    EditText Ingr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_by_ingredient);

        Ingr = (android.widget.EditText) findViewById(R.id.ingredient);
        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Ingredient = Ingr.getText().toString();
                AsyncHTTPingredient task = new AsyncHTTPingredient(DrinksByIngredient.this);
                task.execute("https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=" + Ingredient);
            }
        });
    }
    public void goToMain(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
