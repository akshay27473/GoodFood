package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login_activity extends AppCompatActivity {
    Button btn_signin;
    EditText loginphone,loginregpass;
    TextView textView,textView1;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        btn_signin=findViewById(R.id.button);
        loginphone=findViewById(R.id.login_phone);
        loginregpass=findViewById(R.id.login_pass);
        textView=findViewById(R.id.textView2);
        textView1=findViewById(R.id.txtloginforg);
        progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Forgot_password.class);
                startActivity(intent);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Register_activity.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validationphome(){
        String val=loginphone.getText().toString();
        if (val.isEmpty() ){
            loginphone.setError("Field cannot be empty");
            return  false;
        }else if (val.length()<10 || val.length()>10){
            loginphone.setError("Entered Number is wrong");
            return  false;
        }else
        {
            loginphone.setError(null);
            return  true;
        }
    }
    public Boolean validationpass(){
        String val=loginregpass.getText().toString();
        if (val.isEmpty()){
            loginregpass.setError("Field cannot be empty");
            return  false;
        }else
        {
            loginregpass.setError(null);
            return  true;
        }
    }
    public  void onclick1(View view){
        progressBar.setVisibility(View.VISIBLE);
        if (!validationphome() | !validationpass()){
            progressBar.setVisibility(View.GONE);
            return;
        }
        else{
            String enterednumber=loginphone.getText().toString();
            String enteredpass=loginregpass.getText().toString();
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
            Query chkuser=reference.orderByChild("phone").equalTo(enterednumber);
            chkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        progressBar.setVisibility(View.VISIBLE);
                        loginphone.setError(null);
                        String passwordfromdatabase=snapshot.child(enterednumber).child("password").getValue(String.class);
                        if (passwordfromdatabase.equals(enteredpass)) {
                            String namefromdatabase = snapshot.child(enterednumber).child("name").getValue(String.class);
                            String emailfromdatabase = snapshot.child(enterednumber).child("email").getValue(String.class);
                            String phonefromdatabase = snapshot.child(enterednumber).child("phone").getValue(String.class);
                            String dobfromdatabase = snapshot.child(enterednumber).child("dob").getValue(String.class);



                            Intent intent = new Intent(Login_activity.this, MainPage.class);
                            intent.putExtra("name", namefromdatabase);
                            intent.putExtra("password", passwordfromdatabase);
                            intent.putExtra("email", emailfromdatabase);
                            intent.putExtra("phone", phonefromdatabase);
                            intent.putExtra("dob",dobfromdatabase);
                                Bundle bundle=new Bundle();
                                bundle.putString("name",namefromdatabase);
                                bundle.putString("password",passwordfromdatabase);
                                bundle.putString("email",emailfromdatabase);
                                bundle.putString("phone",phonefromdatabase);
                                bundle.putString("dob",dobfromdatabase);
                                intent.putExtras(bundle);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  //removing back screen
                            startActivity(intent);
                        }else {
                            progressBar.setVisibility(View.GONE);
                            loginregpass.setError("Wrong Password");
                            Snackbar.make(view,"Wrong Password", BaseTransientBottomBar.LENGTH_SHORT).show();
                            //Toast.makeText(Login_activity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    loginphone.setError("No such User Exists");
                    Snackbar.make(view,"No such User Exists!! Please Sign-up", BaseTransientBottomBar.LENGTH_SHORT).show();
                    //Toast.makeText(Login_activity.this, "No such User Exists!! Please Sign-up", Toast.LENGTH_SHORT).show();
                    loginregpass.requestFocus();
                }}

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }
}