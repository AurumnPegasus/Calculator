package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {
    private EditText result, input;

    private Double operand1 = null;
    private  Double operand2 = null;
    private String pendingOperation = "=";
    private static final String TAG = "My Application";
    private static final String STATE_PENDING_OPERATION  = "PendingOPeration";
    private static final String STATE_OPERAND1 = "Operand1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.Result);
        input = findViewById(R.id.Input);

        Button button0 = findViewById(R.id.num0);
        Button button1 = findViewById(R.id.num1);
        Button button2 = findViewById(R.id.num2);
        Button button3 = findViewById(R.id.num3);
        Button button4 = findViewById(R.id.num4);
        Button button5 = findViewById(R.id.num5);
        Button button6 = findViewById(R.id.num6);
        Button button7 = findViewById(R.id.num7);
        Button button8 = findViewById(R.id.num8);
        Button button9 = findViewById(R.id.num9);


        Button buttonDecimal = findViewById(R.id.Decimal);
        Button buttonAdd = findViewById(R.id.Addition);
        Button buttonSubtract = findViewById(R.id.Subtraction);
        Button buttonMultiply = findViewById(R.id.Multiplication);
        Button buttonDivide = findViewById(R.id.Division);
        Button buttonModulo = findViewById(R.id.Modulo);
        Button buttonFactorial = findViewById(R.id.xFactorial);
        Button buttonPower = findViewById(R.id.xPowerN);
        Button buttonSquare = findViewById(R.id.Parenthesis);
        Button buttonRoot = findViewById(R.id.xRoot);
        Button buttonNRoot = findViewById(R.id.xNthRoot);
        Button buttonReciprocal = findViewById(R.id.xReciprocal);
        Button buttonValueE = findViewById(R.id.valueE);
        Button buttonValuePi = findViewById(R.id.valuePi);
        Button buttonClear = findViewById(R.id.Clear);
        Button buttonEquals = findViewById(R.id.Equals);

        input.setText("");
        result.setText("");
        View.OnClickListener listner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String value = b.getText().toString();
                if(value.equals("e"))
                    value = "2.71828";
                else if(value.equals("pi"))
                    value = "3.1415";
                input.append(value);
            }

        };

        button0.setOnClickListener(listner);
        button1.setOnClickListener(listner);
        button2.setOnClickListener(listner);
        button3.setOnClickListener(listner);
        button4.setOnClickListener(listner);
        button5.setOnClickListener(listner);
        button6.setOnClickListener(listner);
        button7.setOnClickListener(listner);
        button8.setOnClickListener(listner);
        button9.setOnClickListener(listner);
        buttonDecimal.setOnClickListener(listner);
        buttonValueE.setOnClickListener(listner);
        buttonValuePi.setOnClickListener(listner);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = input.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                    if (value.length() != 0) {
                        performOperationTwo(value, op);
                    }
                }
                catch (NumberFormatException e)
                {
                    input.setText("");
                }
                pendingOperation = op;
            }
        };
        View.OnClickListener singleListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Button b = (Button)view;
                String op = b.getText().toString();
                String value;
                if(result.getText().toString().length() == 0)
                {
                    value = input.getText().toString();
                }
                else
                {
                    value = result.getText().toString();
                }

                performOperationOne(value, op);
            }
        };
        buttonAdd.setOnClickListener(opListener);
        buttonSubtract.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonFactorial.setOnClickListener(singleListener);
        buttonModulo.setOnClickListener(opListener);
        buttonNRoot.setOnClickListener(opListener);
        buttonRoot.setOnClickListener(singleListener);
        buttonPower.setOnClickListener(opListener);
        buttonSquare.setOnClickListener(singleListener);
        buttonReciprocal.setOnClickListener(opListener);
        buttonEquals.setOnClickListener(opListener);

        View.OnClickListener clearListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                input.setText("");
                result.setText("");
                operand1 = null;
                operand2 = null;
            }
        };

        buttonClear.setOnClickListener(clearListener);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putString(STATE_PENDING_OPERATION,  pendingOperation);
        if(operand1 != null)
            outState.putDouble(STATE_OPERAND1, operand1);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);

    }

    private void performOperationOne(String value, String operation)
    {
        Log.d(TAG, "performOperationOne: pending operation is" + pendingOperation);
        if(operand1 == null)
        {
            operand1 = Double.valueOf(value);
        }
        pendingOperation = operation;
        switch(pendingOperation)
        {
            case "x!":
                double f = 1;
                for(int i=1;i<=operand1;i++)
                    f = f*i;
                operand1 = f;
                break;
            case "rootX":
                operand1 = Math.sqrt(operand1);
                break;
            case "x^2":
                operand1 = Math.pow(operand1, 2);
                break;
        }
        result.setText(operand1.toString());
        input.setText("");
    }


    private void performOperationTwo(String value, String operation)
    {
        Log.d(TAG, "performOperationTwo: pending operation is " + pendingOperation);
        if(operand1 == null)
        {
            operand1 = Double.valueOf(value);
        }
        else
        {
            operand2 = Double.valueOf(value);
        }
        if(pendingOperation.equals("=") && operand2 == null)
        {
            pendingOperation = operation;
            result.setText(operand1.toString());
            input.setText("");
            return;
        }
        switch (pendingOperation) {
            case "=":
                operand1 = operand2;
                break;
            case "+":
                operand1 = operand1 + operand2;
                break;
            case "-":
                operand1 = operand1 - operand2;
                break;
            case "*":
                operand1 = operand1 * operand2;
                break;
            case "/":
                if (operand2 == 0)
                    operand1 = 0.0;  // Have to throw some error
                else
                    operand1 = operand1 / operand2;
                break;
            case "%":
                if (operand2 == 0)
                    operand1 = 0.0;     //throw some error
                else
                    operand1 = operand1 % operand2;
                break;
            case "x^n":
                operand1 = Math.pow(operand1, operand2);
                break;
            case "nroot":
                double guess = Math.random() % 10;
                double precision = 0.00000001;
                double maxDifference = 2147483647;
                double currentVal;
                while (maxDifference > precision) {
                    currentVal = ((operand2 - 1) * guess + operand1 / Math.pow(guess, operand2 - 1)) / operand2; //
                    maxDifference = Math.abs(currentVal - guess);
                    guess = currentVal;
                }
                operand1 = guess;
                break;
            default:
                operand1 = 0.0;

            }
        result.setText(operand1.toString());
        input.setText("");
    }
}
