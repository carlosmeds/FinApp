package br.ufpr.tads.finapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufpr.tads.finapp.R;
import br.ufpr.tads.finapp.model.TransactionCategory;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{

    private List<TransactionCategory> categoriesStatements;
    private TransactionCategory categoryStatement;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView transactionValue, transactionType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionValue = itemView.findViewById(R.id.textViewTransaction);
            transactionType = itemView.findViewById(R.id.textViewTransactionType);
        }
    }

    public CategoriesAdapter(List<TransactionCategory> categoriesStatements) {
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
        categoryStatement = categoriesStatements.get(position);

        holder.transactionValue.setText(String.valueOf(categoryStatement.getValue()));
        holder.transactionType.setText(categoryStatement.getTypeName());
    }

    @Override
    public int getItemCount() {
        return categoriesStatements.size();
    }
}
