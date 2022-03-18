package com.example.newproj;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class Firstimg extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstimg);
        button=findViewById(R.id.firstorder);
    }
    public  void onClick(View view){
            //Toast.makeText(this, String.valueOf(ratingBar.getRating()), Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Order");
            builder.setMessage("Are you sure you want to Place an Order");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(Firstimg.this, "Thank for yor Ordering!!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MainPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  //removing back screen
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Snackbar.make(view,"Tell us, how we can improve ", BaseTransientBottomBar.LENGTH_SHORT).show();
                    //Toast.makeText(Ratings.this, "calcel was clicked", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setCancelable(false);
            builder.show();

        }
    }