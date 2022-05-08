package belov.daniil.petproject.learningnow;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DictionaryActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private LayoutInflater inflater;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        linearLayout = findViewById(R.id.main_content_layout);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        Button addWordBtn = findViewById(R.id.add_word_btn);
        addWordBtn.setOnClickListener(view -> {
            Toast toast = new Toast(this);
            toast.setText("Hello, You have clicked at the button!");
            toast.show();
        });

        for (int i = 0; i < 15; i++) {
            View myView = inflater.inflate(R.layout.dict_line, null, false);
            TextView textView = myView.findViewById(R.id.text1);
            textView.setText(Integer.toString(i));
            if((i & 1) == 1){
                myView.setBackgroundColor(Color.LTGRAY);
            }
            linearLayout.addView(myView);
        }
    }
}