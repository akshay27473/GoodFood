package com.example.newproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.Calendar;

public class Forgot_password extends AppCompatActivity {
    EditText forg_phone, forg_dob;
    private int year, month, day;
    Button for_btn,btn_copy;
    TextView txtresult,txtlogin;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forg_phone = findViewById(R.id.forgot_phone);
        forg_dob = findViewById(R.id.forgot_dob);
        for_btn = findViewById(R.id.forgot_btn);
        txtresult = findViewById(R.id.textViewoutput);
        txtlogin = findViewById(R.id.lgnpage);
        btn_copy = findViewById(R.id.copy_btn);
        progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText("",txtresult.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

            }
        });

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login_activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  //removing back screen
                startActivity(intent);
            }
        });

        forg_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Forgot_password.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                forg_dob.setText(dayOfMonth + "-" + (month+1) + "-" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        for_btn.setOnClickListener(new View.OnClickListener() {
            public Boolean validationphome() {
                String val = forg_phone.getText().toString();
                if (val.isEmpty()) {
                    forg_phone.setError("Field cannot be empty");
                    return false;
                } else if (val.length() < 10 || val.length() > 10) {
                    forg_phone.setError("Entered Number is wrong");
                    return false;
                } else {
                    forg_phone.setError(null);
                    return true;
                }
            }

            public Boolean validdob() {
                String val = forg_dob.getText().toString();
                if (val.isEmpty()) {
                    forg_dob.setError("Field is empty");
                    return false;
                } else {
                    forg_dob.setError(null);
                    return true;
                }
            }

            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (!validationphome() | !validdob()) {
                    progressBar.setVisibility(View.GONE);
                    return;
                } else {
                    String enterphone = forg_phone.getText().toString();
                    String enterdob = forg_dob.getText().toString();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                    Query chkuser = reference.orderByChild("phone").equalTo(enterphone);
                    chkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                progressBar.setVisibility(View.VISIBLE);
                                forg_phone.setError(null);
                                String dobfromdatabase = snapshot.child(enterphone).child("dob").getValue(String.class);
                                if (dobfromdatabase.equals(enterdob)) {
                                    progressBar.setVisibility(View.GONE);
                                    String passfromdatabase = snapshot.child(enterphone).child("password").getValue(String.class);
                                    txtresult.setText(passfromdatabase);
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    forg_dob.setError("Wrong DOB");
                                    Snackbar.make(view,"Wrong Date of Birth", BaseTransientBottomBar.LENGTH_SHORT).show();
                                    //Toast.makeText(Forgot_password.this, "Wrong DOB", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                                forg_phone.setError("No such User Exists");
                                Snackbar.make(view,"No such Phone Number Exists!!", BaseTransientBottomBar.LENGTH_SHORT).show();
                                //Toast.makeText(Forgot_password.this, "No such Phone Number Exists!!", Toast.LENGTH_SHORT).show();
                                forg_dob.requestFocus();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
    }
}