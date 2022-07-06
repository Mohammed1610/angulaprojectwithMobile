package com.example.libraryregistation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.libraryregistation.Model.Register;
import com.example.libraryregistation.Services.RegisterAPI;
import com.example.libraryregistation.Services.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Initialize();
    }

    public void Initialize() {
        EditText f_name = findViewById(R.id.fname);
        EditText l_name = findViewById(R.id.lname);
        EditText email = findViewById(R.id.email);
        EditText birth_date = findViewById(R.id.birth);
        RadioGroup gender = findViewById(R.id.gender);
        EditText phone_number = findViewById(R.id.phone);

        Spinner school_name  = (Spinner) findViewById(R.id.school);

        ArrayAdapter<CharSequence> SchoolAdapter = ArrayAdapter.createFromResource(this,
                R.array.school_list, android.R.layout.simple_spinner_item);

        SchoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        school_name.setAdapter(SchoolAdapter);

        Spinner education_level = (Spinner) findViewById(R.id.education);

        ArrayAdapter<CharSequence> educationAdapter = ArrayAdapter.createFromResource(this,
                R.array.education_level, android.R.layout.simple_spinner_item);

        educationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        education_level.setAdapter(educationAdapter);






        Button Button = (Button) findViewById(R.id.button);

        RetrofitService retrofitService1 = new RetrofitService();
        RegisterAPI registerAPI = retrofitService1.getRetrofit().create(RegisterAPI.class);

        Button.setOnClickListener(view->{

            String Fname = String.valueOf(f_name.getText());
            String Lname = String.valueOf(l_name.getText());
            String Email= String.valueOf(email.getText());
            String Birth_date= String.valueOf(birth_date.getText());

            String Gender = ((RadioButton)findViewById(gender.getCheckedRadioButtonId())).getText().toString();

            String Phone_no = String.valueOf(phone_number.getText());
            String School_name = school_name.getSelectedItem().toString();
            String Education_level = education_level.getSelectedItem().toString();

            Register register = new Register();


            register.setF_name(Fname);
            register.setL_name(Lname);
            register.setEmail(Email);
            register.setBirth_date(Birth_date);
            register.setGender(Gender);
            register.setPhone_no(Phone_no);
            register.setSchool_name(School_name);
            register.setEducation_level(Education_level);

            registerAPI.save(register)
                    .enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {
                            Toast.makeText(MainActivity2.this, "saved", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<Register> call, Throwable t) {

                            Toast.makeText(MainActivity2.this, "", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error",t);


                        }
                    });






        });



    }

}