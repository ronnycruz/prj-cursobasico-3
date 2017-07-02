package com.rgsystems.loantable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class AmortizeActivity extends AppCompatActivity {

    private double btxtMontoPrestamo;
    private int btxtCantidadCuotas;
    private double btxtInteresPrestamo;

    TextView txtPagoMensual;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amortize);

        String btxtFullName;

        // get the Intent that started this Activity
        Intent intent = getIntent();

        // get the Bundle that stores the data of this Activity
        Bundle bundle = intent.getExtras();

        btxtFullName         = bundle.getString("txtName");
        btxtMontoPrestamo    = bundle.getDouble("txtMontoPrestamo");
        btxtCantidadCuotas   = bundle.getInt("txtCantidadCuotas");
        btxtInteresPrestamo  = bundle.getDouble("txtInteresPrestamo");

        TextView txtName = (TextView)findViewById(R.id.txt_amName);
            txtName.setText(btxtFullName);

        txtPagoMensual = (TextView)findViewById(R.id.txt_pagoMensual);
        listView = (ListView)findViewById(R.id.listViewComponent);

        initAmortize();

    }

    private void initAmortize(){

        double txtPayment;
        ArrayList<String[]> tableSchedule;

        txtPayment = AmortizeActivity.pAmount((btxtInteresPrestamo/100)/12 , btxtCantidadCuotas , btxtMontoPrestamo);

        tableSchedule = AmortizeActivity.computeSchedule(btxtMontoPrestamo, btxtInteresPrestamo, btxtCantidadCuotas, txtPayment);

        txtPagoMensual.setText(String.format(Locale.ENGLISH, "%.2f", txtPayment));

        // Create the adapter to convert the array to views
        AmortizeAdapter aAdapter = new AmortizeAdapter(AmortizeActivity.this, tableSchedule);

        // Attach the adapter to a ListView
        listView.setAdapter(aAdapter);
    }

    /**
     * Computes the payment amount for a loan.
     *
     * @param rate  is the interest rate for the loan.
     * @param nper  is the total number of payments for the loan.
     * @param pv    is the total amount of the loan.
     *
     * @return pmt
     */
    private static double pAmount(double rate, int nper, double pv){

        double pvif, pmt;
        pvif = Math.pow( 1 + rate, -(nper));
        pmt = (rate / (1 - pvif)) * pv ;

        return pmt;
    }

    /**
     * Function to compute amortization schedule table
     *
     * @param loanAmount        is the amount borrowed to purchase the property.
     * @param interestRate      is the percentage used to calculate interest due.
     * @param paymentPeriods    is the number of payments to be made.
     * @param payment           is the amount of the payment each period.
     *
     * @return schedule
     */
    private static ArrayList<String[]> computeSchedule(double loanAmount, double interestRate, int paymentPeriods, double payment){

        ArrayList<String[]> schedule = new ArrayList<>();
        double remaining = loanAmount;

        String hRow[] = {  "Mes", "Principle", "Interest", "Remaining" };
        schedule.add(  hRow );

        for (int i=1; i <= paymentPeriods; i++) {
            double interest = remaining * ((interestRate/100)/12);
            double principle = (payment - interest);

            String row[] = {  String.valueOf(i)
                            , String.format(Locale.ENGLISH, "%.2f", (principle > 0 ? (principle < payment ? principle : payment) : 0))
                            , String.format(Locale.ENGLISH, "%.2f", (interest > 0 ? interest : 0))
                            , String.format(Locale.ENGLISH, "%.2f", (remaining > 0 ? remaining : 0))
                        };

            schedule.add(  row );

            remaining -= principle;
        }

        return schedule;
    }

}
