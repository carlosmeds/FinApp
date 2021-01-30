package br.ufpr.tads.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.adapter.TransactionAdapter;
import br.ufpr.tads.finapp.helper.TransactionDAO;
import br.ufpr.tads.finapp.model.Transaction;

public class StatementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter TransactionAdapter;
    private List<Transaction> TransactionList = new ArrayList<>();
    private Double balance;
    Double valueDeb, valueCred;
    TextView textViewCredit, textViewDebit, textViewBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        recyclerView = findViewById(R.id.recyclerViewTransitionList);
        textViewBalance = findViewById(R.id.textViewBalance);

    }

    public void updateRecyclerTransaction(){
        TransactionDAO transactionDAO = new TransactionDAO(getApplicationContext());
        TransactionList = transactionDAO.getStatement(this);
        balance = transactionDAO.getBalance(this);

        String stringBalance = String.format("Seu saldo: R$ %.2f", balance);
        stringBalance = stringBalance.replace(".", ",");
        textViewBalance.setText(stringBalance);
        TransactionAdapter = new TransactionAdapter(TransactionList);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new
                DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(TransactionAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateRecyclerTransaction();
    }

}