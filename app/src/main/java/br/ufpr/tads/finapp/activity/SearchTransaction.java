package br.ufpr.tads.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.adapter.SpinAdapter;
import br.ufpr.tads.finapp.adapter.TransactionAdapter;
import br.ufpr.tads.finapp.helper.DatePickerFragment;
import br.ufpr.tads.finapp.helper.TransactionDAO;
import br.ufpr.tads.finapp.model.Transaction;
import br.ufpr.tads.finapp.model.TransactionType;


public class SearchTransaction extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter TransactionAdapter;
    private List<Transaction> TransactionList = new ArrayList<>();
    private Spinner spinner;
    private SpinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_transaction);

        List<TransactionType> TransactionListValues = new ArrayList<>();
        TransactionType t1 = new TransactionType((long) 20, "credito");
        TransactionType t2 = new TransactionType((long) 21, "debito");
        TransactionType t3 = new TransactionType((long) 22, "todos");

        TransactionListValues.add(t1);
        TransactionListValues.add(t2);
        TransactionListValues.add(t3);


        recyclerView = findViewById(R.id.recyclerViewTransitionList);

        TextView dateTextIni = findViewById(R.id.textViewDateInicio);
        dateTextIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateFragment = new DatePickerFragment(dateTextIni);
                dateFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        TextView dateTextFim = findViewById(R.id.textViewDateFim);
        dateTextFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateFragment = new DatePickerFragment(dateTextFim);
                dateFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        spinner = findViewById(R.id.spinnerTransactionFilter);
        adapter = new SpinAdapter(SearchTransaction.this, android.R.layout.simple_spinner_item, TransactionListValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                TransactionType t = adapter.getItem(position);
                Log.i("TYPE(" + t.getId() + "):", t.getType());
                if(!(dateTextIni.getText().equals("Data Inicio")) && !(dateTextIni.getText().equals("Data fim")))
                    updateRecyclerTransaction(t);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

    }

    public void updateRecyclerTransaction(TransactionType t){
        TextView dateTextIni = findViewById(R.id.textViewDateInicio);
        TextView dateTextFim = findViewById(R.id.textViewDateFim);
        String dtini = dateTextIni.getText().toString();
        String dtfim = dateTextFim.getText().toString();



        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date dateTransactionIni = simpleDate.parse(dtini);
            Date dateTransactionFim = simpleDate.parse(dtfim);

            dtini = simpleDateString.format(dateTransactionIni);
            dtfim = simpleDateString.format(dateTransactionFim);

            Log.i("info: ", dtfim);



            TransactionDAO transactionDAO = new TransactionDAO(getApplicationContext());
            TransactionList = transactionDAO.getTransactionsByPeriod(this, dtini, dtfim, t);

            TransactionAdapter = new TransactionAdapter(TransactionList);

        } catch (ParseException e) {
            e.printStackTrace();
        }



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
    }



}