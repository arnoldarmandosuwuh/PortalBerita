package com.charisma.portalberita;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.charisma.portalberita.face.Listener;
import com.charisma.portalberita.model.Response;
import com.charisma.portalberita.utils.WebService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Bayu Charisma Putra on 3/19/2018.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Listener {

    private EditText etUsername, etPassword;
    private Button bLogin, bRegister;

    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        bRegister = findViewById(R.id.bRegister);

        bLogin.setOnClickListener(this);
        bRegister.setOnClickListener(this);

        readDataLocal();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogin:
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if (isValidInput()) {
                    WebService service = new WebService(LoginActivity.this, LoginActivity.this,
                            "login.php", WebService.METHOD_POST);
                    service.execute("username=" + username + "&" +
                            "password=" + password);
                }
                break;
            case R.id.bRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private boolean isValidInput() {

        if (username.length() < 8) {
            Toast.makeText(this, "Username minimal 8 karakter", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 8) {
            Toast.makeText(this, "Password minimal 8 karakter", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onSucessWs(Object[] objects) {
        saveDataLocal();

        Response response = (Response) objects[0];
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("AUTHOR_ID", Integer.valueOf((String) response.getData()));
        startActivity(intent);
    }

    @Override
    public void onFailedWs(Object[] objects) {
        Response response = (Response) objects[0];
        Toast.makeText(this, response.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void saveDataLocal() {
        try {
            FileOutputStream fileout = openFileOutput("berita_papsi.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(username + "::" + password);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readDataLocal() {
        try {
            FileInputStream fileIn = openFileInput("berita_papsi.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[100];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring= String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            if (!s.equals("") && s.contains("::")) {
                String[] sSplit = s.split("::");
                username = sSplit[0];
                password = sSplit[1];
                WebService service = new WebService(LoginActivity.this, LoginActivity.this,
                        "login.php", WebService.METHOD_POST);
                service.execute("username=" + username + "&" +
                        "password=" + password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
