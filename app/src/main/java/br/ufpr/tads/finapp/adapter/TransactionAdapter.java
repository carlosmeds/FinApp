package br.ufpr.tads.finapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        ImageView transactionImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionValue = itemView.findViewById(R.id.textViewCategory);
            transactionType = itemView.findViewById(R.id.textViewCategoryType);
            transactionDate = itemView.findViewById(R.id.textViewTransactionDate);
            transactionImage = itemView.findViewById(R.id.imageViewTransactionType);
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

        String pattern = "dd/MM/yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String stringDate = simpleDateFormat.format(transaction.getTransactionDate());
        holder.transactionDate.setText(stringDate);

        String stringValue = String.format("R$ %.2f", transaction.getTransactionValue());
        stringValue = stringValue.replace(".", ",");
        holder.transactionValue.setText(stringValue);

        holder.transactionImage.setImageResource(transaction.getImg());
        holder.transactionType.setText(String.valueOf(transaction.getTransactionType().getType()));
    }

    @Override
    public int getItemCount() {
        return this.TransactionList.size();
    }
}