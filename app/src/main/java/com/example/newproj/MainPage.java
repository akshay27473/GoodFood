package com.example.newproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainPage extends AppCompatActivity {
    ImageView one,two,three,four,five,six,seventh ,eigth,nine,ten,eleven,twelve,thirteen;
    Button button1,button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        one=findViewById(R.id.firstimg);
        two=findViewById(R.id.secondimg);
        three=findViewById(R.id.thirdimg);
        four=findViewById(R.id.fourthimg);
        five=findViewById(R.id.fifthimg);
        six=findViewById(R.id.sixthimg);
        seventh=findViewById(R.id.seventhimg);
        eigth=findViewById(R.id.eigthimg);
        nine=findViewById(R.id.nineimg);
        ten=findViewById(R.id.tenimg);
        eleven=findViewById(R.id.eleventhimg);
        twelve=findViewById(R.id.twelthimg);
        thirteen=findViewById(R.id.thirteenimg);

        button1=findViewById(R.id.firsttext);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MyAccount.class);
                startActivity(intent);
            }
        });
        button2=findViewById(R.id.secondtext);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ContactUs.class);
                startActivity(intent);
            }
        });


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Firstimg.class);
                startActivity(intent);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Secondimg.class);
                startActivity(intent);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Thirdimg.class);
                startActivity(intent);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Fourthimg.class);
                startActivity(intent);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Fifthimg.class);
                startActivity(intent);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Siximg.class);
                startActivity(intent);
            }
        });
        seventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Seventhimg.class);
                startActivity(intent);
            }
        });
        eigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Eigthimg.class);
                startActivity(intent);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Nineimg.class);
                startActivity(intent);
            }
        });
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Tenimg.class);
                startActivity(intent);
            }
        });
        eleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Elevenimg.class);
                startActivity(intent);
            }
        });
        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Twelveimg.class);
                startActivity(intent);
            }
        });
        thirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Thirteenimg.class);
                startActivity(intent);
            }
        });

    }
}