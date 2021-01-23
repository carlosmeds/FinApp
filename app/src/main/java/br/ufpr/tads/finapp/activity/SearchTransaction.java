package br.ufpr.tads.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.adapter.TransactionAdapter;
import br.ufpr.tads.finapp.helper.TransactionDAO;
import br.ufpr.tads.finapp.model.Transaction;



public class SearchTransaction extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter TransactionAdapter;
    private List<Transaction> TransactionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_transaction);

        recyclerView = findViewById(R.id.recyclerViewTransitionList);

    }

    public void updateRecyclerTransaction(){
        String firstDate = "'2021-01-23 12:20:11'";
        String lastDate = "'2021-01-23 11:30:10'";

        TransactionDAO transactionDAO = new TransactionDAO(getApplicationContext());
        TransactionList = transactionDAO.getTransactionsByPeriod(this, firstDate, lastDate);

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