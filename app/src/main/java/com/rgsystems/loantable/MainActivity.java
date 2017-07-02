package com.rgsystems.loantable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtMontoPrestamo;
    private EditText txtCantidadCuotas;
    private EditText txtInteresPrestamo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName             = (EditText)findViewById(R.id.txt_name);
        txtMontoPrestamo    = (EditText)findViewById(R.id.txt_montoPrestamo);
        txtCantidadCuotas   = (EditText)findViewById(R.id.txt_cantidad_cuotas);
        txtInteresPrestamo  = (EditText)findViewById(R.id.txt_interes_prestamo);
    }

    public void onClickButton(View view){
        // Creating Bundle object
        Bundle bundle = new Bundle();
            bundle.putString("txtName",txtName.getText().toString());
            bundle.putDouble("txtMontoPrestamo",Double.valueOf(txtMontoPrestamo.getText().toString()));
            bundle.putInt("txtCantidadCuotas",Integer.valueOf(txtCantidadCuotas.getText().toString()));
            bundle.putDouble("txtInteresPrestamo",Double.valueOf(txtInteresPrestamo.getText().toString()));

        Intent intent = new Intent(MainActivity.this, AmortizeActivity.class);
            intent.putExtras(bundle);

        startActivity(intent);
    }
}
