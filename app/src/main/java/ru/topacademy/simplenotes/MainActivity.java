package ru.topacademy.simplenotes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button buttonSave;
    private TextView textViewOutput;
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String KEY_NOTES = "notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

        editTextInput = findViewById(R.id.edit_text_input);
        buttonSave = findViewById(R.id.button_save);
        textViewOutput = findViewById(R.id.text_view_output);

        loadNotes();

        buttonSave.setOnClickListener(v -> saveNotes(editTextInput.getText().toString()));
    }


    private void loadNotes() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String notes = settings.getString(KEY_NOTES, "");
        textViewOutput.setText(notes);
    }


    private void saveNotes(String input) {
        String currentText = textViewOutput.getText().toString();
        String updatedText = currentText + "\n" + input;

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_NOTES, updatedText);
        editor.apply();

        editTextInput.setText("");

        textViewOutput.setText(updatedText);
    }
}