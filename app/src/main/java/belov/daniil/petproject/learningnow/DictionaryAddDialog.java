package belov.daniil.petproject.learningnow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class DictionaryAddDialog extends AppCompatActivity {

    private LinearLayout linearLayout;
    private LayoutInflater inflater;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_word_dialog);

        linearLayout = findViewById(R.id.main_content_layout);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        EditText addForeign = findViewById(R.id.add_foreign);
        EditText addTranslate = findViewById(R.id.add_translate);

        dbHelper = new DBHelper(this);

        Button saveBtn = findViewById(R.id.add_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_FOREIGN_WORD, addForeign.getText().toString());
                contentValues.put(DBHelper.KEY_TRANSLATE_WORD, addTranslate.getText().toString());

                db.insert(DBHelper.TABLE_DICTIONARY, null, contentValues);

                finish();
            }
        });
    }
}
