package practicaltest01var05.eim.systems.cs.pub.ro.practicaltest01var05;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private Button addButton = null;
    private Button computeButton = null;
    private EditText editTextNumbers = null;
    private EditText editTextNextTerm = null;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private boolean firstTime;
    private int serviceStatus = 0;

    private String savedNumbers = null;
    private Integer sum = null;

    private static final int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_add:
                    int val = Integer.parseInt(editTextNextTerm.getText().toString());
                    String numbers = editTextNumbers.getText().toString();
                    if (numbers.length() > 0)
                        editTextNumbers.setText(numbers + "+" + String.valueOf(val));
                    else
                        editTextNumbers.setText(String.valueOf(val));
                    break;
                case R.id.button_compute:
                    String currentNumbers = editTextNumbers.getText().toString();
                    if (savedNumbers != null && sum != null && currentNumbers.equals(savedNumbers)) {
                        Toast.makeText(getApplicationContext(),
                                "Previous sum " + Integer.toString(sum), Toast.LENGTH_LONG).show();
                    } else {
                        Intent secondaryIntent = new Intent(getApplicationContext(),
                                PracticalTest01Var05SecondaryActivity.class);
                        secondaryIntent.putExtra("numbers", editTextNumbers.getText().toString());
                        startActivityForResult(secondaryIntent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        addButton = findViewById(R.id.button_add);
        computeButton = findViewById(R.id.button_compute);
        editTextNumbers = findViewById(R.id.edit_text_numbers);
        editTextNextTerm = findViewById(R.id.edit_text_next_term);

        addButton.setOnClickListener(buttonClickListener);
        computeButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("sum")) {
                sum = new Integer(savedInstanceState.getInt("sum"));
            }
            if (savedInstanceState.containsKey("numbers")) {
                savedNumbers = savedInstanceState.getString("numbers");
                editTextNumbers.setText(savedNumbers);
            }
            if (savedInstanceState.containsKey("first_time")) {
                firstTime = savedInstanceState.getBoolean("first_time");
            }
        }
        else firstTime = false;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (sum != null) {
            outState.putInt("sum", sum);
            String numbers = editTextNumbers.getText().toString();
            outState.putString("numbers", numbers);
        }
        outState.putBoolean("first_time", firstTime);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("sum")) {
            sum = new Integer(savedInstanceState.getInt("sum"));
        }
        if (savedInstanceState.containsKey("numbers")) {
            savedNumbers = savedInstanceState.getString("numbers");
        }
        if (savedInstanceState.containsKey("first_time")) {
            firstTime = savedInstanceState.getBoolean("first_time");
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            sum = new Integer(resultCode);

            Intent broadcastIntent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
            broadcastIntent.putExtra("sum", sum);
            getApplicationContext().startService(broadcastIntent);
            serviceStatus = 1;
        }
    }
}
