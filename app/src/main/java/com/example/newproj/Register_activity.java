package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Register_activity extends AppCompatActivity {
    EditText regname,regphone,regemail,regpass;
    private int year, month, day;
    EditText textDOB;
    Button signup;
    ProgressBar progressBar;
TextView textView;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        regname=findViewById(R.id.register_name);
        regemail=findViewById(R.id.register_email);
        regphone=findViewById(R.id.login_phone);
        regpass=findViewById(R.id.login_pass);
        signup=findViewById(R.id.btnsignup);
        textDOB=findViewById(R.id.txtdob);
        textView=findViewById(R.id.textView);
        progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login_activity.class);
                startActivity(intent);
            }
        });
        textDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Register_activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                textDOB.setText(dayOfMonth + "-" + (month+1) + "-" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            public Boolean validationname(){
                String val=regname.getText().toString();
                if (val.isEmpty()){
                    regname.setError("Field cannot be empty");
                    return  false;
                }else
                {
                    regname.setError(null);
                    return  true;
                }
            }
            public Boolean validationemail(){
                String val=regemail.getText().toString();
                String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (val.isEmpty()){
                    regemail.setError("Field cannot be empty");
                    return  false;
                }else if (!val.matches(emailpattern)){
                    regemail.setError("Invalid email address");
                    return false;
                }else
                {
                    regemail.setError(null);
                    return  true;
                }
            }
            public Boolean validationphome(){
                String val=regphone.getText().toString();
                if (val.isEmpty() ){
                    regphone.setError("Field cannot be empty");
                    return  false;
                }else if (val.length()<10 || val.length()>10){
                    regphone.setError("Entered Number is wrong");
                    return  false;
                }else
                {
                    regphone.setError(null);
                    return  true;
                }
            }
            public Boolean validationpass(){
                String val=regpass.getText().toString();
                if (val.isEmpty()){
                    regpass.setError("Field cannot be empty");
                    return  false;
                }else
                {
                    regpass.setError(null);
                    return  true;
                }
            }
            public Boolean validdob(){
                String val=textDOB.getText().toString();
                if (val.isEmpty()){
                    textDOB.setError("select DOB");
                    return  false;
                }else
                {
                    textDOB.setError(null);
                    return true;
                }
            }
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (!validationname() | !validationemail() | !validationphome() | !validationpass() | !validdob()){
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference("users");
                String name=regname.getText().toString();
                String email=regemail.getText().toString();
                String phone=regphone.getText().toString();
                String pass=regpass.getText().toString();
                String dob=textDOB.getText().toString();
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
                Query checkuser=reference.orderByChild("phone").equalTo(phone);
                checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            progressBar.setVisibility(View.GONE);
                            Snackbar.make(view,"Already user exists!! Please Sign-in", BaseTransientBottomBar.LENGTH_SHORT).show();
                            //Toast.makeText(Register_activity.this, "Already user exists!! Please Sign-in", Toast.LENGTH_SHORT).show();
                            regphone.setError("Phone Number already registered");
                        }else
                        {Query chkemail=reference.orderByChild("email").equalTo(email);
                        chkemail.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    progressBar.setVisibility(View.GONE);
                                    Snackbar.make(view,"Email is Already taken", BaseTransientBottomBar.LENGTH_SHORT).show();
                                    //Toast.makeText(Register_activity.this, "Email is Already taken", Toast.LENGTH_SHORT).show();
                                    regemail.setError("Email is already taken");
                                }else {
                                    progressBar.setVisibility(View.VISIBLE);
                                    regemail.setError(null);
                                    regphone.setError(null);
                                    Intent intent=new Intent(getApplicationContext(),MainPage.class);
                                    intent.putExtra("phonenumber",phone);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  //removing back screen
                                    startActivity(intent);
                                    database helperClass=new database(name,email,phone,pass,dob);  //storing data in database
                                    reference.child(phone).setValue(helperClass);    //storing data in database
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

}