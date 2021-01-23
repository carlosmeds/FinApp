package br.ufpr.tads.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.adapter.TransactionAdapter;
import br.ufpr.tads.finapp.helper.TransactionDAO;
import br.ufpr.tads.finapp.model.Transaction;

public class ClassificationActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private br.ufpr.tads.finapp.adapter.TransactionAdapter TransactionAdapter;
    private List<Transaction> TransactionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);

        recyclerView = findViewById(R.id.recyclerViewTransitionList);

    }

    public void updateRecyclerTransaction(){
        TransactionDAO transactionDAO = new TransactionDAO(getApplicationContext());
        TransactionList = transactionDAO.getStatement(this);
        transactionDAO.getCategoriesStatement(this);

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