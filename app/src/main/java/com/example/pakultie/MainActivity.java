package com.example.pakultie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pakultie.API.ApiCaller;
import com.example.pakultie.API.Client;
import com.example.pakultie.API.Fields;
import com.example.pakultie.API.response;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText fullName, email, mobile;
    TextView old, birthDate;
    Spinner gender;
    Integer ageInt;
    Button submit;

    String[] items = new String[]{"Male", "Female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullName = findViewById(R.id.ed_fname);
        email = findViewById(R.id.ed_email);
        mobile = findViewById(R.id.ed_mobile);
        birthDate = findViewById(R.id.txt_date);
        old = findViewById(R.id.txt_age);
        gender = findViewById(R.id.sp_gender);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        submit = findViewById(R.id.btn_submit);


        mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Utils.isMobileNumberInvalid(mobile.getText().toString())) {
                    mobile.setError("Invalid Mobile Number");
                } else {
                    mobile.setError(null);
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!Utils.isEmailValid(email.getText().toString())) {
                    email.setError("Invalid Email");
                } else {
                    email.setError(null);
                }
            }
        });

        birthDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        old.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ageInt < 18) {
                    old.setError("Age must be grater than 18");
                } else {
                    old.setError(null);
                }
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobile == null || fullName == null || email == null || birthDate == null || ageInt == null) {
                    Toast.makeText(MainActivity.this, "Please Fill up the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Fields getFields = new Fields(
                            fullName.getText().toString(),
                            email.getText().toString(),
                            mobile.getText().toString(),
                            birthDate.getText().toString(),
                            ageInt,
                            gender.getSelectedItem().toString());
                    ApiCaller service = Client.getRetrofit().create(ApiCaller.class);
                    Call<List<response>> call = service.getData(getFields);
                    call.enqueue(new Callback<List<response>>() {
                        @Override
                        public void onResponse(Call<List<response>> call, Response<List<response>> response) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
                            builder.setTitle("Mocky Success");
                            builder.setMessage(response.message());
                            builder.setCancelable(true);

                            builder.setPositiveButton(
                                    "Return",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert1 = builder.create();
                            alert1.show();
                        }


                        @Override
                        public void onFailure(Call<List<response>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    });
                }
            }
        });
    }


    private void showDatePickerDialog() {
        DatePickerDialog datePick = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePick.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        birthDate.setText(month + "/" + dayOfMonth + "/" + year);
        getAge(year, month, dayOfMonth);
        old.setText(String.valueOf(ageInt));

    }

    private int getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        ageInt = new Integer(age);

        return ageInt;
    }
}
