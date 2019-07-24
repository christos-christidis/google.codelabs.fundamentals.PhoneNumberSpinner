package com.fundamentals.phonenumberspinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String mSpinnerLabel = "";

    private EditText mEditText;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editText_main);
        mTextView = findViewById(R.id.text_phonelabel);

        Spinner spinner = findViewById(R.id.label_spinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSpinnerLabel = adapterView.getItemAtPosition(i).toString();
        showText();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d(LOG_TAG, "onNothingSelected: ");
    }

    private void showText() {
        String showString = mEditText.getText().toString() + " - " + mSpinnerLabel;
        Toast.makeText(this, showString, Toast.LENGTH_SHORT).show();
        mTextView.setText(showString);
    }
}
