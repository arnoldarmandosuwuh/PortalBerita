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

/**
 * Created by Bayu Charisma Putra on 3/19/2018.
 */

public class RegisterActivity extends AppCompatActivity implements Listener {

    private EditText etName, etUsername, etPassword, etConfirmPassword;
    private Button bRegister;

    String name, username, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirm);
        bRegister = findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = etName.getText().toString();
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                confirmPassword = etConfirmPassword.getText().toString();

                if (isValidInput()) {
                    WebService service = new WebService(RegisterActivity.this, RegisterActivity.this,
                            "registrasi.php", WebService.METHOD_POST);
                    service.execute("name=" + name + "&" +
                            "username=" + username + "&" +
                            "password=" + password);
                }
            }
        });
    }

    private boolean isValidInput() {

        if (name.length() < 6) {
            Toast.makeText(this, "Nama minimal 6 karakter", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (username.length() < 8) {
            Toast.makeText(this, "Username minimal 8 karakter", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 8) {
            Toast.makeText(this, "Password minimal 8 karakter", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onSucessWs(Object[] objects) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onFailedWs(Object[] objects) {
        Response response = (Response) objects[0];
        Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
