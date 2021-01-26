package br.ufpr.tads.finapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private List<Transaction> TransactionList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView transactionValue, transactionType, transactionDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionValue = itemView.findViewById(R.id.textViewTransaction);
            transactionType = itemView.findViewById(R.id.textViewTransactionType);
            transactionDate = itemView.findViewById(R.id.textViewTransactionDate);
        }
    }

    public TransactionAdapter(List<Transaction> TransactionList) {
        this.TransactionList = TransactionList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View toDoItem =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_cell,
                        parent, false);
        return new MyViewHolder(toDoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Transaction transaction = TransactionList.get(position);

        String pattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String stringDate = simpleDateFormat.format(transaction.getTransactionDate());

        holder.transactionValue.setText(String.valueOf(transaction.getTransactionValue()));
        holder.transactionDate.setText(stringDate);
        holder.transactionType.setText(String.valueOf(transaction.getTransactionType().getType()));
    }

    @Override
    public int getItemCount() {
        return this.TransactionList.size();
    }
}