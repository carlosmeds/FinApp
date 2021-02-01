package br.ufpr.tads.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.adapter.SpinAdapter;
import br.ufpr.tads.finapp.helper.DatePickerFragment;
import br.ufpr.tads.finapp.helper.TimePickerFragment;
import br.ufpr.tads.finapp.helper.TransactionDAO;
import br.ufpr.tads.finapp.helper.TransactionTypeDAO;
import br.ufpr.tads.finapp.model.Transaction;
import br.ufpr.tads.finapp.model.TransactionType;

public class AddTransactionActivity extends AppCompatActivity {

    List<TransactionType> TransactionTypeListDebit;
    List<TransactionType> TransactionTypeListCredit;
    private Spinner spinner;
    private SpinAdapter adapter;
    private Boolean isDebit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO(getApplicationContext());

        TransactionTypeListDebit = transactionTypeDAO.getTransactionTypes(true);
        TransactionTypeListCredit = transactionTypeDAO.getTransactionTypes(false);

        TextView switchTransactionBackground = findViewById(R.id.switchTransactionBackground);
        switchTransactionBackground.setBackgroundColor(Color.parseColor("#ffb4b4"));

        spinner = findViewById(R.id.spinnerTransactionType);
        adapter = new SpinAdapter(AddTransactionActivity.this, android.R.layout.simple_spinner_item, TransactionTypeListDebit);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        Switch switchTransaction = findViewById(R.id.switchTransaction);


        switchTransaction.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    adapter = new SpinAdapter(AddTransactionActivity.this, android.R.layout.simple_spinner_item, TransactionTypeListCredit);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    switchTransactionBackground.setBackgroundColor(Color.parseColor("#b4ffc8"));
                    switchTransaction.setText("Crédito");

                } else {
                    adapter = new SpinAdapter(AddTransactionActivity.this, android.R.layout.simple_spinner_item, TransactionTypeListDebit);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    switchTransactionBackground.setBackgroundColor(Color.parseColor("#ffb4b4"));
                    switchTransaction.setText("Débito");
                }
            }
        });

        TextView dateText = findViewById(R.id.dateTransaction);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateFragment = new DatePickerFragment(dateText, true, getSupportFragmentManager());
                dateFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    public void showDatePickerDialog(View view) {

    }

    public void onConfirmTransaction(View view) {
        EditText inputValue = findViewById(R.id.inputValue);
        Double value = Double.parseDouble(inputValue.getText().toString());
        TextView dateTransactionText = findViewById(R.id.dateTransaction);

        Date dateTransaction = Calendar.getInstance().getTime();
        String dateString = dateTransactionText.getText().toString();
        if(value <= 0){
            Toast.makeText(this, "Forneça um Valor Válido!", Toast.LENGTH_SHORT).show();
        } else if (dateTransactionText.getText().toString().equals("Selecione a Data e Hora")){
            Toast.makeText(this, "Forneça uma Data Válida!", Toast.LENGTH_SHORT).show();
        } else{
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                dateTransaction = simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            TransactionType t = (TransactionType) spinner.getSelectedItem();
            Transaction transaction = new Transaction();
            transaction.setTransactionType(t);
            transaction.setTransactionValue(value);
            transaction.setTransactionDate(dateTransaction);

            TransactionDAO transactionDAO = new TransactionDAO(getApplicationContext());
            if (transactionDAO.insertTransaction(transaction)) {
                Toast.makeText(this, "Transação foi salva com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Não foi possível realizar a transação!", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
