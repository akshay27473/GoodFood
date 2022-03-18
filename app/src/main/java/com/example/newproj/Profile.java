package com.example.newproj;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;



public class Profile extends AppCompatActivity {
    EditText regname, regphone, regemail, regpass;
    Button update;
    private int year, month, day;
    EditText textDOB;
    String username, userpass, userdob, useremail, userphone;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");  //firebase database
        drawerLayout=findViewById(R.id.drawer_layout);


        regname = findViewById(R.id.register_name);
        regemail = findViewById(R.id.register_email);
        regphone = findViewById(R.id.login_phone);
        regpass = findViewById(R.id.login_pass);
        update = findViewById(R.id.btnupdate);
        textDOB = findViewById(R.id.txtdob);

        textDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Profile.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                textDOB.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

      Intent intent=getIntent();
      Bundle bundle=intent.getExtras();

      username=bundle.getString("name");
      userphone=bundle.getString("phone");
      useremail=bundle.getString("email");
      userpass=bundle.getString("password");
      userdob=bundle.getString("dob");

        regname.setText(username);
        regphone.setText(userphone);
        regemail.setText(useremail);
        regpass.setText(userpass);
        textDOB.setText(userdob);


        update.setOnClickListener(new View.OnClickListener() {
            private boolean isnamechanged() {
                if (!username.equals(regname.getText().toString())) {
                    reference.child(userphone).child("name").setValue(regname.getText().toString());
                    username=regname.getText().toString();
                    return true;
                } else {
                    return false;
                }
            }
            private boolean ispasschanged(){
                if (!userpass.equals(regpass.getText().toString())) {
                    reference.child(userphone).child("password").setValue(regpass.getText().toString());
                    userpass=regpass.getText().toString();
                    return true;
                } else {
                    return false;
                }
            }
            private boolean isphonenumchanged(){
                if (!userphone.equals(regphone.getText().toString())) {
                    reference.child(userphone).child("phone").setValue(regphone.getText().toString());
                    userphone=regphone.getText().toString();
                    return true;
                } else {
                    return false;
                }
            }
            private boolean isemailchanged(){
                if (!useremail.equals(regemail.getText().toString())) {
                    reference.child(userphone).child("email").setValue(regpass.getText().toString());
                    useremail=regemail.getText().toString();
                    return true;
                } else {
                    return false;
                }
            }
            private boolean isdobchanged(){
                if (!userdob.equals(textDOB.getText().toString())) {
                    reference.child(userphone).child("dob").setValue(regpass.getText().toString());
                    userdob=textDOB.getText().toString();
                    return true;
                } else {
                    return false;
                }
            }


            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Profile.this);
                builder.setTitle("Updating Data ");
                builder.setMessage("Are you sure you want to Update?");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isnamechanged() || ispasschanged() || isphonenumchanged() || isemailchanged() || isdobchanged()) {
                            //Toast.makeText(Profile.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            Snackbar.make(view,"Data Updated Successfully", BaseTransientBottomBar.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Profile.this, "Data Same as Previous", Toast.LENGTH_SHORT).show();
                            Snackbar.make(view,"Data Same as Previous", BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        regname.setText(username);
                        regphone.setText(userphone);
                        regemail.setText(useremail);
                        regpass.setText(userpass);
                        textDOB.setText(userdob);
                        //Snackbar.make(view,"Tell us, how we can improve ", BaseTransientBottomBar.LENGTH_SHORT).show();
                        //Toast.makeText(Ratings.this, "calcel was clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setCancelable(false);
                builder.show();


            }
        });

    }

}