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
import com.example.sparking.GetData.configure;

import org.json.JSONException;
import org.json.JSONObject;


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
                final String  username = mUser.getText().toString();
                final String  password = mPassword.getText().toString();
//                //默认登录
//                username = "caodian";
//                password = "caodian";
//                //
                String url = configure.URL_User + "?User.username=" + username+"&User.password="+password;
                System.out.println("get url "+url);

                GetUserData getuserdata = new GetUserData();
                getuserdata.getUserData(url, new GetUserData.SuccessCallback() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_LONG).show();
                                relativeLayoutLoggingLoad.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                LoginActivity.this.finish();
                            }
                        }, new GetUserData.FailCallback() {
                            @Override
                            public void onFail() {
                                System.out.println("fail");

                                if (username.equals("network") && password.equals("network")){
                                    //login with default userpass
                                    JSONObject defaultuser=new JSONObject();
                                    try {
                                        defaultuser.put("id","000");
                                        defaultuser.put("username","network");
                                        defaultuser.put("username","network");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    configure.MEJSON=defaultuser;
                                    configure.changeData();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    LoginActivity.this.finish();
                                }
                                Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_LONG).show();
                                relativeLayoutLoggingLoad.setVisibility(View.INVISIBLE);
                            }
                        });
            }
        });

        btn_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                //LoginActivity.this.finish();
            }
        });

    }
}
