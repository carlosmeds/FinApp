package br.ufpr.tads.finapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import br.ufpr.tads.finapp.model.TransactionType;

public class SpinAdapter extends ArrayAdapter<TransactionType> {
    private Context context;
    private List<TransactionType> values;

    public SpinAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<TransactionType> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public TransactionType getItem(int position){
        return values.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(values.get(position).getType());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getType());

        return label;
    }
}
