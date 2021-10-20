package com.example.buivansinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edDescription;
    Spinner spinner;
    CheckBox cbCheckbox;
    Button btSend;
    String category = "Gripe";
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edDescription = (EditText) findViewById(R.id.edDescription);
        edUsername = (EditText) findViewById(R.id.edUsername);
        edEmail = (EditText) findViewById(R.id.edEmail);
        cbCheckbox = (CheckBox) findViewById(R.id.cbCheckbox);
        btSend = (Button) findViewById(R.id.btSend);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btSend:
                        onSendFeedback();
                        break;
                    default:
                        break;
                }
            }
        });

        String[] spinners = {"Gripe", "List", "Test"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinners);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean validate() {
        String req = null;
        if (edUsername.getText().toString().trim().isEmpty()) {
            req = "Username not entered";
        }else if (edEmail.getText().toString().trim().isEmpty()) {
            req = "Email not entered";
        }else if (edDescription.getText().toString().trim().isEmpty()) {
            req = "Description not entered";
        }else if (!cbCheckbox.isChecked()) {
            req = "Agree to terms of use";
        }
        if (req != null) {
            Toast.makeText(this, req, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void onSendFeedback() {
        if (!validate()) {
            return;
        }
        FeedbackEntity item = new FeedbackEntity();
        item.name = edUsername.getText().toString();
        item.email = edEmail.getText().toString();
        item.description = edDescription.getText().toString();
        item.category = category;
        long id = db.feedbackDao().insertFeedback(item);
        if (id > 0) {
            Toast.makeText(this, "Send Feedback Success", Toast.LENGTH_SHORT).show();
        }
    }


//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btSend:
//                onSendFeedback();
//                break;
//            default:
//                break;
//        }
//    }
}