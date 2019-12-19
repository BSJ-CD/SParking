package com.example.sparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sparking.GetData.PostUserData;
import com.example.sparking.GetData.configure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText ed_name, ed_email, ed_pass, ed_pass2;
    private Button register;
    private String password = null, password2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed_name = (EditText) findViewById(R.id.register_name_input);
        ed_email = (EditText) findViewById(R.id.register_email_input);
        register = (Button) findViewById(R.id.Next);
        ed_pass = (EditText) findViewById(R.id.register_password);
        ed_pass2 = (EditText) findViewById(R.id.register_password2);
        ed_pass.setTypeface(ed_email.getTypeface());
        ed_pass2.setTypeface(ed_email.getTypeface());


        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                final String name = ed_name.getText().toString();
                final String email = ed_email.getText().toString();
                password = ed_pass.getText().toString();
                password2 = ed_pass2.getText().toString();



                // 判断email是否合法
                if (email.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Email is null", Toast.LENGTH_LONG).show();
                } else if (isEmail(email) == false) {
                    Toast.makeText(RegisterActivity.this, "Illegal email", Toast.LENGTH_LONG).show();
                } else if (name.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Name is null", Toast.LENGTH_LONG).show();
                } else if (password.equals("") == true) {
                    Toast.makeText(RegisterActivity.this, "Password is null", Toast.LENGTH_LONG).show();
                } else if (password.equals(password2) == false) {
                    Toast.makeText(RegisterActivity.this, "Wrong with retime", Toast.LENGTH_LONG).show();
                } else if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "The length should be longer than 6", Toast.LENGTH_LONG).show();
                } else {
                    Log.i("", "start check user exist or not");
                    new PostUserData(name, email, password, new PostUserData.SuccessCallback() {
                        @Override
                        public void onSuccess(String id) {
                            configure.ID = id;
                            Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            RegisterActivity.this.finish();
                        }
                    }, new PostUserData.FailCallback() {
                        @Override
                        public void onFail() {
                            Toast.makeText(RegisterActivity.this, "Register Failed!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    // 判断email格式是否正确
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
