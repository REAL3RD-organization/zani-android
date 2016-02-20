package com.jani.real.jani;

import static us.monoid.web.Resty.*;

import us.monoid.json.JSONObject;
import us.monoid.web.Content;
import us.monoid.web.Resty.*;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;

import us.monoid.web.Resty;

public class EntryActivity extends ActionBarActivity {

    private boolean     is_log_in = false;

    private EditText    text_id;
    private EditText    text_pw;
    private Button      btn_login;
    private Button      btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        text_id = (EditText)findViewById(R.id.edit_text_id);
        text_pw = (EditText)findViewById(R.id.edit_text_pw);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_login.setOnClickListener(listener);
        btn_register.setOnClickListener(listener);
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    checkLogin(text_id.getText().toString(),
                            text_pw.getText().toString(),
                            "hi");
                    goToMainActivity();
                    break;
                case R.id.btn_register:
                    goToRegisterActivity();
                    break;
                default:
                    Log.d("button listener", "none");
                    break;
            }
        }
    };

    private void checkLogin(String loginId, String password, String gcmRegId) {
        String url = "http://192.168.20.59:8888/login";
        try {
            JSONObject obj = new JSONObject();
            obj.put("loginId", loginId);
            obj.put("password", password);
            obj.put("gcmRegID", gcmRegId);
            new Resty().json(url, put(content(obj)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(EntryActivity.this, FriendListActivity.class);
        startActivity(intent);
    }

    private void goToRegisterActivity() {
        Intent intent = new Intent(EntryActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
