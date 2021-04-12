package ua.opu.androidlabs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private double firstOperand;
    private double secondOperand;
    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sumButton = findViewById(R.id.sum_button);
        Button difButton = findViewById(R.id.dif_button);
        Button multButton = findViewById(R.id.mult_button);
        Button divisionButton = findViewById(R.id.division_button);

        EditText firstOperandView = findViewById(R.id.first_operand);
        EditText secondOperandView = findViewById(R.id.second_operand);

        TextView resultView = findViewById(R.id.result);


        sumButton.setOnClickListener(v -> {
            firstOperand = Double.parseDouble(firstOperandView.getText().toString());
            secondOperand = Double.parseDouble(secondOperandView.getText().toString());
            result = firstOperand + secondOperand;
            resultView.setText(Objects.toString(result));
        });

        difButton.setOnClickListener(v -> {
            firstOperand = Double.parseDouble(firstOperandView.getText().toString());
            secondOperand = Double.parseDouble(secondOperandView.getText().toString());
            result = firstOperand - secondOperand;
            resultView.setText(Objects.toString(result));
        });

        multButton.setOnClickListener(v -> {
            firstOperand = Double.parseDouble(firstOperandView.getText().toString());
            secondOperand = Double.parseDouble(secondOperandView.getText().toString());
            result = firstOperand * secondOperand;
            resultView.setText(Objects.toString(result));
        });

        divisionButton.setOnClickListener(v -> {
            firstOperand = Double.parseDouble(firstOperandView.getText().toString());
            secondOperand = Double.parseDouble(secondOperandView.getText().toString());
            if (secondOperand == 0) {
                resultView.setText("Infinity");
            } else {
                result = firstOperand / secondOperand;
                result = (double) (Math.round(result * 100)) / 100;
                resultView.setText(Objects.toString(result));
            }
        });
    }
}