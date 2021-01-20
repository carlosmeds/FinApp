package br.ufpr.tads.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.helper.TransactionDAO;
import br.ufpr.tads.finapp.helper.TransactionTypeDAO;
import br.ufpr.tads.finapp.model.Transaction;
import br.ufpr.tads.finapp.model.TransactionType;

public class AddTransactionActivity extends AppCompatActivity {

    List<TransactionType> TransactionTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO(getApplicationContext());

        TransactionTypeList = transactionTypeDAO.getAllTransactionTypes();
        for (TransactionType type:TransactionTypeList
             ) {
            Log.i("INFO","TransactionTypes List:" + type.getType() + " - " + type.getId());
        }

        Spinner spinner = findViewById(R.id.spinnerTransactionType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.debitTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.getOnItemSelectedListener();
    }

    public void onConfirmTransaction(View view){
        EditText inputValue = findViewById(R.id.inputValue);
        Spinner spinnerType = findViewById(R.id.spinnerTransactionType);
        Integer typePosition = spinnerType.getSelectedItemPosition();
        String type = spinnerType.getItemAtPosition(typePosition).toString();
        Double value = Double.parseDouble(inputValue.getText().toString());
        Log.i("INFO","Transaction Value:" + value + " Spinner: " + type);

        if(value == 0){
            Toast.makeText(this,"Forneça um valor diferente de 0!", Toast.LENGTH_SHORT).show();
        } else {
            Date dateNow = Calendar.getInstance().getTime();
            Log.i("INFO","Transaction Time:" + dateNow);

            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionTypeList.get(2));
            transaction.setTransactionValue(value);
            transaction.setTransactionDate(dateNow);

            TransactionDAO transactionDAO = new TransactionDAO(getApplicationContext());
            if (transactionDAO.insertTransaction(transaction)){
                Toast.makeText(this,"Transação foi salva com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this,"Não foi possível realizar a transação!", Toast.LENGTH_SHORT).show();
            }
        };
    }


}