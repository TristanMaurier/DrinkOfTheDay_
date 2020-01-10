package com.spectre.drinkoftheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DrinksByName extends AppCompatActivity {
    String cocktailName;
    EditText cocktail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_by_name);

        cocktail = (EditText) findViewById(R.id.ingredient);
        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cocktailName = cocktail.getText().toString();
                AsyncHTTPname task = new AsyncHTTPname(DrinksByName.this);
                task.execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + cocktailName);
            }
        });
    }
    public void goToMain(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}

