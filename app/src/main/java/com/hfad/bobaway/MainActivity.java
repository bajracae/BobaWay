package com.hfad.bobaway;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText searchBarEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBarEditText = (EditText)findViewById(R.id.ent_location);

        Button addLocationButton = (Button)findViewById(R.id.btn_add_location);
        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationText = searchBarEditText.getText().toString();
                if (!TextUtils.isEmpty(locationText)) {
                    searchBarEditText.setText("");
                }
            }
        });
    }
}
