package br.ufpr.tads.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.helper.TransactionDAO;
import br.ufpr.tads.finapp.model.Transaction;
import br.ufpr.tads.finapp.model.TransactionType;

public class AddTransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        //Carregar as categorias
    }

    public void onConfirmTransaction(View view){
        EditText inputValue = findViewById(R.id.inputValue);
        Double value = Double.parseDouble(inputValue.getText().toString());
        Log.i("INFO","Transaction Value:" + value);

        if(value == 0){
            Toast.makeText(this,"Forneça um valor diferente de 0!", Toast.LENGTH_SHORT);
        } else {
            Date dateNow = Calendar.getInstance().getTime();
            Log.i("INFO","Transaction Time:" + dateNow);

            TransactionType type = new TransactionType((long) 0,"SomenteTeste");
            Log.i("INFO","Transaction Type:" + type.getType());

            Transaction transaction = new Transaction();
            transaction.setTransactionType(type);
            transaction.setTransactionValue(value);
            transaction.setTransactionDate(dateNow);

            TransactionDAO transactionDAO = new TransactionDAO(this);
            if (transactionDAO.insertTransaction(transaction)){
                Toast.makeText(this,"Transação foi salva com sucesso!", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(this,"Não foi possível realizar a transação!", Toast.LENGTH_SHORT);
            }
        };
    }
}