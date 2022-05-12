package belov.daniil.petproject.learningnow;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        linearLayout = findViewById(R.id.main_content_layout);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        ImageButton goHomeBtn = findViewById(R.id.home_btn);
        ImageButton addWordBtn = findViewById(R.id.add_word_btn);
        goHomeBtn.setOnClickListener(view -> {
            Intent go_home = new Intent(DictionaryActivity.this, MainActivity.class);
            startActivity(go_home);
        });

        addWordBtn.setOnClickListener(view -> {
            Intent go_add = new Intent(DictionaryActivity.this, DictionaryAddDialog.class);
            startActivity(go_add);
        });

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

        dict.forEach((k, v) ->{
            View myView = inflater.inflate(R.layout.dict_line, null, false);
            TextView textView1 = myView.findViewById(R.id.dict_line_text1);
            textView1.setText(k);

            TextView textView2 = myView.findViewById(R.id.dict_line_text2);
            textView2.setText(v);

            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editView = new Intent(DictionaryActivity.this, DictionaryEditDialog.class);

                    editView.putExtra("key", k);
                    editView.putExtra("value", v);

                    startActivity(editView);
                }
            });

            linearLayout.addView(myView);
        });

    }
}