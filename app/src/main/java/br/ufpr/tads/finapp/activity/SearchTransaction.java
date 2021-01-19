package br.ufpr.tads.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.helper.TransactionDAO;
import br.ufpr.tads.finapp.model.Transaction;



public class SearchTransaction extends AppCompatActivity {

    List<Transaction> TransactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_transaction);
        TransactionDAO transactionDAO = new TransactionDAO(getApplicationContext());

        TransactionList = transactionDAO.getAllTransactions(this);
        for (Transaction transaction:TransactionList) {
            Log.i("INFO","Transaction List:" + transaction.getTransactionValue()
                    + " - " + transaction.getTransactionDate()
                    + " - " + transaction.getTransactionType().getType());
        }
    }
}