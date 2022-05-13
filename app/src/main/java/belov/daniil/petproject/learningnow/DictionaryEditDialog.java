package belov.daniil.petproject.learningnow;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DictionaryEditDialog extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_edit_dialog);

        Intent intent = getIntent();

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String foreignWord = intent.getStringExtra("key");
        String translate = intent.getStringExtra("value");
        Integer pariId = intent.getIntExtra("pair_id", 1);

        EditText foreignEditText = findViewById(R.id.edit_foreign);
        EditText translateEditText = findViewById(R.id.edit_translate);

        foreignEditText.setText(foreignWord);
        translateEditText.setText(translate);

        Button saveBtn = findViewById(R.id.edit_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foreignForm = foreignEditText.getText().toString();
                String translateForm = translateEditText.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.KEY_FOREIGN_WORD, foreignForm);
                contentValues.put(DBHelper.KEY_TRANSLATE_WORD, translateForm);

                Toast toast = Toast.makeText(DictionaryEditDialog.this, foreignForm + " " + translateForm, Toast.LENGTH_LONG);
                toast.show();

                db.update(
                        DBHelper.TABLE_DICTIONARY,
                        contentValues,
                        DBHelper.KEY_ID + " = ?",
                        new String[]{ pariId.toString()}
                );

                finish();
            }
        });

        Button deleteBtn = findViewById(R.id.edit_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete( DBHelper.TABLE_DICTIONARY,
                        DBHelper.KEY_ID + " = ?",
                        new String[]{ pariId.toString()}
                );

                finish();
            }
        });


    }
}