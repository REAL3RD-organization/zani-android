package com.jani.real.jani;

import static us.monoid.web.Resty.*;

import us.monoid.json.JSONObject;
import us.monoid.web.Content.*;
import us.monoid.web.Resty.*;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import us.monoid.web.Resty;

/**
 * Created by JeongMinCha on 2016. 2. 3..
 */
public class RegisterActivity extends Activity {

    private DBHelper helper;

    private EditText text_id;
    private EditText text_name;
    private EditText text_pw;
    private EditText text_pw2;

    private Button btn_register;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        helper = new DBHelper(this, null, null, 0);

        text_id = (EditText) findViewById(R.id.edit_text_id);
        text_name = (EditText) findViewById(R.id.edit_text_name);
        text_pw = (EditText) findViewById(R.id.edit_text_pw);
        text_pw2 = (EditText) findViewById(R.id.edit_text_pw_check);

        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(listener);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_register:
                    registerUser(text_id.getText().toString(),
                            text_pw.getText().toString(),
                            text_name.getText().toString());
                    break;
                default:
                    break;
            }
        }
    };

    private void registerUser(String loginId, String password, String nickname) {
        // 서버로 회원 정보 JSON 만들어서 보내주기
        String url = "http://192.168.20.59:8888/register";
        try {
            JSONObject someJson = new JSONObject();
            someJson.put("loginId", loginId);
            someJson.put("password", password);
            someJson.put("nickname", nickname);

            new Resty().json(url, put(Resty.content(someJson)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Register Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.jani.real.jani/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Register Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.jani.real.jani/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
