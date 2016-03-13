package nsit.vivek.com.billing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class Calculate extends AppCompatActivity {
    EditText etNoOfUnits, etCalculate, etEstimatedBill;
    Button bCalculate;
    int[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Your current Meter Reading is: ";
                String emailAdress[] = {"bhatvivek93@gmail.com"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, emailAdress);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Meter Reading");
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int totalBill = 1;


        etNoOfUnits = (EditText) findViewById(R.id.etNoOfUnits);
        etCalculate = (EditText) findViewById(R.id.etCalculate);
        bCalculate = (Button) findViewById(R.id.bCalculate);
        int sumOfUnits = 0;

        list = new int[30];

        Random randomObj = new Random();

        for (int i = 0; i < 30; i++) {
            list[i] = randomObj.nextInt(40);

            i++;
        }

        for (int i = 0; i < 30; i++) {
            sumOfUnits += list[i];
        }
        etNoOfUnits.setText(sumOfUnits + " kWh ");
        int tempUnit = 0;



/*
First 100 units: Rs 2.96/unit
Next 200 units (from 101 to 300): Rs 5.56/unit
Next 200 units (from 301 to 500): Rs 9.16/unit
Any units after that (above 500): Rs 10.61/unit
 */

        final int finalSumOfUnits = sumOfUnits;

        bCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //       EditText et = (EditText) findViewById(R.id.etCalculate);
                doCalculations(etCalculate, finalSumOfUnits);
            }
        });
    }


    public String getList() {
        String listString = null;
        listString = this.list.toString();
        return listString;
    }

    private void doCalculations(EditText etCalculate, int sumOfUnits) {
        int totalBill;
        int tempUnit;
        if (sumOfUnits < 101) {
            totalBill = (int) (sumOfUnits * 2.96);
            etCalculate.setText("Rs " + totalBill);
        } else if (sumOfUnits > 100 && sumOfUnits < 301) {
            tempUnit = sumOfUnits - 100;
            totalBill = (int) (296 + (tempUnit * 5.56));
            etCalculate.setText("Rs " + totalBill);
        } else if (sumOfUnits > 300 && sumOfUnits < 501) {
            tempUnit = sumOfUnits - 300;
            totalBill = (int) (1408 + (tempUnit * 9.16));
            etCalculate.setText("Rs " + totalBill);
        } else if (sumOfUnits > 500) {
            tempUnit = sumOfUnits - 100;
            totalBill = (int) (3240 + (tempUnit * 10.61));
            etCalculate.setText("Rs " + totalBill);

        }
    }
}
