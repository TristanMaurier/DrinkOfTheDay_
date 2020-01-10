package com.spectre.drinkoftheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class AllDrinks extends AppCompatActivity {

    Button Alchool;
    Button button;
    Button favorite;
    Spinner spinner;
    TextView text;

    //String drinks;
    //FavoritesDB dbHandler = new FavoritesDB(myActivity, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_drinks);

        Alchool = (Button) findViewById(R.id.Alchool);
        button = (Button) findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinnerName);
        favorite = (Button) findViewById(R.id.Favorite);
        text = (TextView) findViewById(R.id.textView);

        AsyncHTTPall task = new AsyncHTTPall(AllDrinks.this);
        task.execute("https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic");

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Cocktail = spinner.getSelectedItem().toString();
                AsynchHTTPall2 task = new AsynchHTTPall2(AllDrinks.this);
                task.execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + Cocktail);
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String Cocktail = spinner.getSelectedItem().toString();
                //addDrink(Cocktail);
                //Drink a = new Drink();
                //a.setDrinkName(Cocktail);
                //dbHandler.addHandler(a);

                Context context = getApplicationContext();
                CharSequence text = "Favorite saved!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        Alchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Alchool.getText()=="Yes"){
                    Alchool.setText("No");
                    AsyncHTTPall task = new AsyncHTTPall(AllDrinks.this);
                    task.execute("https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic");
                }else{
                    Alchool.setText("Yes");
                    AsyncHTTPall task = new AsyncHTTPall(AllDrinks.this);
                    task.execute("https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic");
                }

            }

        });


    }

    public void goToMain(View view) {
    Intent intent = new Intent(this,MainActivity.class);
    startActivity(intent);
        finish();
    }


}
