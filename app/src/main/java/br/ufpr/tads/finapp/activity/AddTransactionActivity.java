package br.ufpr.tads.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

    List<TransactionType> TransactionTypeList;
    private SpinAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO(getApplicationContext());

        TransactionTypeList = transactionTypeDAO.getAllTransactionTypes();
        for (TransactionType type : TransactionTypeList) {
            Log.i("INFO", "TransactionTypes List:" + type.getType() + " - " + type.getId());
        }

        spinner = findViewById(R.id.spinnerTransactionType);
        adapter = new SpinAdapter(this, android.R.layout.simple_spinner_item, TransactionTypeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                TransactionType t = adapter.getItem(position);
                // Here you can do the action you want to...
                Log.i("TYPE("+t.getId()+"):",t.getType());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {}
        });

        TextView dateText = findViewById(R.id.dateTransaction);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateFragment = new DatePickerFragment();
                dateFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        TextView hourText = findViewById(R.id.hourTransaction);
        hourText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timerFragment = new TimePickerFragment();
                timerFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });
    }

    public void showDatePickerDialog(View view) {

    }

    public void onConfirmTransaction(View view) {
        EditText inputValue = findViewById(R.id.inputValue);
        Spinner spinnerType = findViewById(R.id.spinnerTransactionType);
        Integer typePosition = spinnerType.getSelectedItemPosition();
        String type = spinnerType.getItemAtPosition(typePosition).toString();
        Double value = Double.parseDouble(inputValue.getText().toString());
        Log.i("INFO", "Transaction Value:" + value + " Spinner Position: " + typePosition);

        TextView dateTransactionText = findViewById(R.id.dateTransaction);
        TextView hourTransactionText = findViewById(R.id.hourTransaction);
        Date dateNow = Calendar.getInstance().getTime();
        Date dateTransaction = Calendar.getInstance().getTime();
        String dateString = dateTransactionText.getText().toString() + " " + hourTransactionText.getText().toString();

        try {
            //Parse da data informada:
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dateTransaction = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (value <= 0 || value.isNaN()) {
            Toast.makeText(this, "Forneça um valor Válido!", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("INFO", "Transaction Time: " + dateTransaction);

            TransactionType t = (TransactionType) spinner.getSelectedItem();
            Log.i("INFO", "Transaction Type: " + t.getType());
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
        ;
    }


}
