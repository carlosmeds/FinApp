package br.ufpr.tads.finapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.ufpr.tads.finapp.model.Transaction;
import br.ufpr.tads.finapp.model.TransactionType;

public class TransactionDAO {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TransactionDAO (Context context){
        DBHelper db = new DBHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    public boolean insertTransaction(Transaction transaction){
        ContentValues values = new ContentValues();
        values.put("value",transaction.getTransactionValue());

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String stringDate = simpleDateFormat.format(transaction.getTransactionDate());
        values.put("date",stringDate);
        values.put("transactionTypeId", transaction.getTransactionType().getId());

        try{
            write.insert(DBHelper.TABLE_TRANSATION, null, values);
            Log.i("INFO","Transação salva com sucesso. " + stringDate);
        }catch (Exception e){
            Log.i("INFO","Erro ao salvar transação: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateTransaction(Transaction transaction){
        ContentValues values = new ContentValues();
        values.put("value",transaction.getTransactionValue());

        try{
            String[] args = {transaction.getId().toString()};
            write.update(DBHelper.TABLE_TRANSATION, values, "id=?", args);
            Log.i("INFO","Transação atualizada com sucesso. ");
        }catch (Exception e) {
            Log.i("INFO", "Erro ao atualizar transação: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteTransaction(Transaction transaction){
        try{
            String[] args = {transaction.getId().toString()};
            write.delete(DBHelper.TABLE_TRANSATION, "id=?",args);
            Log.i("INFO","Transação removida com sucesso. ");
        }catch (Exception e){
            Log.i("INFO","Erro ao remover transação: " + e.getMessage());
        }
        return false;
    }

    public List<Transaction> getAllTransactions(Context context){
        final Locale myLocale = new Locale("pt", "BR");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        List<Transaction> transactionList = new ArrayList<>();
        Cursor cursor = read.query(DBHelper.TABLE_TRANSATION, new String[]{"id","value","date","transactionTypeId"},
                null,null,null,null,"date DESC");

        while(cursor.moveToNext()){
            Transaction t = new Transaction();
            try {
                TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO(context);

                Long transactionId = cursor.getLong(cursor.getColumnIndex("id"));
                Double transactionValue = cursor.getDouble(cursor.getColumnIndex("value"));
                String transactionDateString = cursor.getString(cursor.getColumnIndex("date"));
                Log.i("INFO","Data: " + transactionDateString);
                Date transactionDate = format.parse(transactionDateString);
                Long transactionType = cursor.getLong(cursor.getColumnIndex("transactionTypeId"));
                TransactionType type =  transactionTypeDAO.getTypeById(transactionType);

                t.setId(transactionId);
                t.setTransactionValue(transactionValue);
                t.setTransactionDate(transactionDate);
                t.setTransactionType(type);
                transactionList.add(t);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return transactionList;
    }
}
