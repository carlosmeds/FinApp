package br.ufpr.tads.finapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.model.Transaction;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{

    private Double[] categoriesStatements;
    private Double categoryStatement;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView transactionValue, transactionType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionValue = itemView.findViewById(R.id.textViewTransaction);
            transactionType = itemView.findViewById(R.id.textViewTransactionType);
        }
    }

    public CategoriesAdapter(Double[] categoriesStatements) {
        this.categoriesStatements = categoriesStatements;
    }

    @NonNull
    @Override
    public CategoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View toDoItem =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.category_cell,
                        parent, false);
        return new CategoriesAdapter.MyViewHolder(toDoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.MyViewHolder holder, int position) {
        categoryStatement = categoriesStatements[position];

        holder.transactionValue.setText(String.valueOf(categoryStatement));
        holder.transactionType.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return categoriesStatements.length;
    }
}
