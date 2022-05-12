package belov.daniil.petproject.learningnow;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class DictionaryEditDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_edit_dialog);

        Intent intent = getIntent();

        String foreignWord = intent.getStringExtra("key");
        String translate = intent.getStringExtra("value");

        EditText foreignEditText = findViewById(R.id.edit_foreign);
        EditText translateEditText = findViewById(R.id.edit_translate);

        foreignEditText.setText(foreignWord);
        translateEditText.setText(translate);


    }
}