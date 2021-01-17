package br.ufpr.tads.finapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufpr.tads.finapp.model.Transaction;

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

        try{
            write.insert(DBHelper.TABLE_TRANSATION, null, values);
            Log.i("INFO","Transação salva com sucesso. ");
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

    public List<Transaction> getAllTransaction(Transaction transaction){
        List<Transaction> transactionList = new ArrayList<>();
        Cursor cursor = read.query(DBHelper.TABLE_TRANSATION, new String[]{"id","value"},
                null,null,null,null,null);

        while(cursor.moveToNext()){
            Transaction t = new Transaction();
            Long transactionId = cursor.getLong(cursor.getColumnIndex("id"));
            Double transactionValue = cursor.getDouble(cursor.getColumnIndex("value"));
            Date transactionDate;
            t.setId(transactionId);
            t.setTransactionValue(transactionValue);

            transactionList.add(t);
        }

        return null;
    }
}
