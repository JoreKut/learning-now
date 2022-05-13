package belov.daniil.petproject.learningnow;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class DictionaryActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private LayoutInflater inflater;

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onResume() {
        super.onResume();
        loadWordsFromDataBase();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        linearLayout = findViewById(R.id.main_content_layout);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        ImageButton goHomeBtn = findViewById(R.id.home_btn);
        ImageButton addWordBtn = findViewById(R.id.add_word_btn);

        goHomeBtn.setOnClickListener(view -> {
            finish();
        });
        addWordBtn.setOnClickListener(view -> {
            Intent go_add = new Intent(DictionaryActivity.this, DictionaryAddDialog.class);
            startActivity(go_add);
        });

        Cursor cursor = db.query(DBHelper.TABLE_DICTIONARY, null, null, null, null, null, null);

        if(cursor.getCount() == 0){
            loadDataToDataBase();
        }
        cursor.close();

        loadWordsFromDataBase();

    }

    private void loadWordsFromDataBase(){

        db = dbHelper.getWritableDatabase();

        linearLayout.removeAllViews();

        Cursor cursor = db.query(DBHelper.TABLE_DICTIONARY, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            int foreignWordId = cursor.getColumnIndex(DBHelper.KEY_FOREIGN_WORD);
            int translateWordId = cursor.getColumnIndex(DBHelper.KEY_TRANSLATE_WORD);
            int id = cursor.getColumnIndex(DBHelper.KEY_ID);

            do{
                String foreignWord = cursor.getString(foreignWordId);
                String translateWord = cursor.getString(translateWordId);
                Integer pairId = cursor.getInt(id);

                View myView = inflater.inflate(R.layout.dict_line, null, false);
                TextView textView1 = myView.findViewById(R.id.dict_line_text1);
                textView1.setText(foreignWord);

                TextView textView2 = myView.findViewById(R.id.dict_line_text2);
                textView2.setText(translateWord);

                myView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent editView = new Intent(DictionaryActivity.this, DictionaryEditDialog.class);

                        editView.putExtra("key", foreignWord);
                        editView.putExtra("value", translateWord);
                        editView.putExtra("pair_id", pairId);

                        startActivity(editView);
                    }
                });

                linearLayout.addView(myView);

            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadDataToDataBase(){
        Map<String, String> dict = new HashMap<>();
        dict.put("sophisticated", "сложный");
        dict.put("inherits", "наследует");
        dict.put("frequently", "часто");
        dict.put("approach", "продход");
        dict.put("bundle", "связка");
        dict.put("emit", "испускать");
        dict.put("arbitrary", "произвольный");
        dict.put("suspend", "приостановить");
        dict.put("occasionally", "Переодически");
        dict.put("regard", "Учитывать");
        dict.put("decouple", "отделить");
        dict.put("easy", "легко");
        dict.put("society", "Общество");
        dict.put("previous", "предыдущий");
        dict.put("visible", "видимый");

        dict.forEach((k, v) ->{
            ContentValues contentValues = new ContentValues();

            contentValues.put(DBHelper.KEY_FOREIGN_WORD, k);
            contentValues.put(DBHelper.KEY_TRANSLATE_WORD, v);

            db.insert(DBHelper.TABLE_DICTIONARY, null, contentValues);
        });
    }
}