package ua.opu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText mEmailEditText;
    private EditText mSubjectEditText;
    private EditText mMessageEditText;

    private Button mConfirmButton;
    private Button mCancelButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mEmailEditText = findViewById(R.id.email_et);
        mSubjectEditText = findViewById(R.id.subject_et);
        mMessageEditText = findViewById(R.id.message_et);

        mConfirmButton = findViewById(R.id.button_confirm);
        mConfirmButton.setOnClickListener(v -> {
            String email = mEmailEditText.getText().toString();
            String subject = mSubjectEditText.getText().toString();
            String message = mMessageEditText.getText().toString();

            Intent data = new Intent();
            data.putExtra(Intent.EXTRA_EMAIL, email);
            data.putExtra(Intent.EXTRA_SUBJECT, subject);
            data.putExtra(Intent.EXTRA_TEXT, message);

            setResult(RESULT_OK, data);
            finish();
        });

        mCancelButton = findViewById(R.id.button_cancel);
        mCancelButton.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }
}
