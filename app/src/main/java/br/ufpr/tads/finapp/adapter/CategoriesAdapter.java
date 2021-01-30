package br.ufpr.tads.finapp.adapter;

import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        ImageView transactionImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionValue = itemView.findViewById(R.id.textViewCategory);
            transactionType = itemView.findViewById(R.id.textViewCategoryType);
            transactionImage = itemView.findViewById(R.id.imageViewCategoryImage);
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

        String stringValue = String.format("R$ %.2f", categoryStatement.getValue());
        stringValue = stringValue.replace(".", ",");
        holder.transactionValue.setText(stringValue);

        holder.transactionType.setText(categoryStatement.getTypeName());
        holder.transactionImage.setImageResource(categoryStatement.getTypeImage());
    }
    @Override
    public int getItemCount() {
        return categoriesStatements.size();
    }
}
