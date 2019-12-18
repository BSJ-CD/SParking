package com.example.sparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sparking.GetData.GetUserData;


public class LoginActivity extends AppCompatActivity {

    public Button btn_loginButton;
    public Button btn_register;
    private EditText mUser;
    private EditText mPassword;
    private SharedPreferences sp;
    private LinearLayout relativeLayoutLoggingLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_loginButton = (Button) findViewById(R.id.login_login_btn);
        btn_register = (Button) findViewById(R.id.forget_passwd);
        mUser = (EditText) findViewById(R.id.login_user_edit);
        mPassword = (EditText) findViewById(R.id.login_passwd_edit);
        relativeLayoutLoggingLoad=(LinearLayout) findViewById(R.id.relativeLayout_logging_load);
        mPassword.setTypeface(mUser.getTypeface());

        btn_loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                relativeLayoutLoggingLoad.setVisibility(View.VISIBLE);
                String  username = mUser.getText().toString();
                String  password = mPassword.getText().toString();
                String url = getResources().getString(R.string.URL_User) + "?User.username=" + username+"&User.password="+password;
                System.out.println("get url "+url);

                boolean flag;
                GetUserData getuserdata = new GetUserData();
                flag = getuserdata.getUserData(url);
                if(flag == true){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
            }
        });

    }
}
