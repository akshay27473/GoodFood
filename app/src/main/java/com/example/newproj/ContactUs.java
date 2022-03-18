package com.example.newproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {
    TextView textView,textView1;
    Intent intent,chooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        textView=findViewById(R.id.textViewmail);
        textView1=findViewById(R.id.textViewloc);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:19.233,89.233"));
                chooser = Intent.createChooser(intent, "Launch Map for Nearest Services");
                startActivity(chooser);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to = {"abhijeetsrivastava8604@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Good Food");
                intent.putExtra(Intent.EXTRA_TEXT, "My Recent Order");
                intent.setType("message/rfc822");
                chooser = Intent.createChooser(intent, "Launch Email ");
                startActivity(chooser);
            }
        });
    }
}