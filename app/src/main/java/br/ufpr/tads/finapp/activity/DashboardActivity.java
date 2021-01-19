package br.ufpr.tads.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.ufpr.tads.finapp.R;

public class DashboardActivity extends AppCompatActivity {
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //Coloar aqui o open e close dos banco!
    }

    public void onClickAddTransaction(View view){
        it = new Intent(this, AddTransactionActivity.class);
        startActivity(it);

    }

    public void onClickSearchTransaction(View view){
        it = new Intent(this, SearchTransaction.class);
        startActivity(it);

    }

    public void onClickShowStatement(View view) {
        it = new Intent(this, StatementActivity.class);
        startActivity(it);
    }

    public void onClickQuitApp(View view) {
        finishAndRemoveTask();
        System.exit(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}