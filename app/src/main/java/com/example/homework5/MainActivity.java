package com.example.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picture = (Button) findViewById(R.id.buttonPicture);

    }

    public void onClick(View view){
        Intent i1 = new Intent(this, DrawActivity.class);
        startActivity(i1);
    }


}