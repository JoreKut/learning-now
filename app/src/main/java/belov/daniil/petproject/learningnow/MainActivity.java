package belov.daniil.petproject.learningnow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected ImageButton dictionaryBtn, changeModeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        dictionaryBtn = findViewById(R.id.dict_btn);
        changeModeBtn = findViewById(R.id.change_mode);

        dictionaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DictionaryActivity.class);
                startActivity(intent);
            }
        });
        changeModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast infoMsg = Toast.makeText(getApplicationContext(), "Now in progress...", Toast.LENGTH_LONG);
                infoMsg.show();

                setTheme(android.R.style.Theme);
            }
        });
    }

}