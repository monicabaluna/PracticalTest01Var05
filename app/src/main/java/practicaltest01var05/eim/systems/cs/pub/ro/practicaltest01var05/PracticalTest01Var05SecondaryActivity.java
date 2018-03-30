package practicaltest01var05.eim.systems.cs.pub.ro.practicaltest01var05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    private EditText editTextSum = null;
    private Button buttonReturn = null;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_return:
                    String numbers = editTextSum.getText().toString();
                    String[] mynumbers = numbers.split("\\+");

                    int sum = 0;
                    for (int i = 0; i < mynumbers.length; i++)
                        sum += Integer.parseInt(mynumbers[i]);

                    setResult(sum, null);
                    finish();
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        editTextSum = findViewById(R.id.edit_text_received_numbers);
        buttonReturn = findViewById(R.id.button_return);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("numbers")) {
            String numbers = intent.getStringExtra("numbers");
            editTextSum.setText(numbers);
        }

        buttonReturn.setOnClickListener(buttonClickListener);
    }
}
