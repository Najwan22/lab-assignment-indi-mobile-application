package com.example.labassignmentindimobileapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.text.DecimalFormat;

public class HomeFragment extends Fragment {

    private EditText investedAmount, dividendRate, monthsInvested;
    private TextView resultText;
    private Button calculateButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        investedAmount = view.findViewById(R.id.edit_invested_amount);
        dividendRate = view.findViewById(R.id.edit_dividend_rate);
        monthsInvested = view.findViewById(R.id.edit_months_invested);
        resultText = view.findViewById(R.id.result_text);
        calculateButton = view.findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(v -> calculateDividend());

        return view;
    }

    private void calculateDividend() {
        try {
            double invested = Double.parseDouble(investedAmount.getText().toString());
            double rate = Double.parseDouble(dividendRate.getText().toString());
            int months = Integer.parseInt(monthsInvested.getText().toString());

            if (months > 12) {
                resultText.setText("Error: Maximum 12 months allowed");
                return;
            }

            double monthlyDividend = (rate / 100 / 12) * invested;
            double totalDividend = monthlyDividend * months;

            DecimalFormat df = new DecimalFormat("#.00");
            String result = "Monthly Dividend: RM " + df.format(monthlyDividend) +
                    "\nTotal Dividend: RM " + df.format(totalDividend);
            resultText.setText(result);
        } catch (NumberFormatException e) {
            resultText.setText("Error: Please enter valid numbers");
        }
    }
}