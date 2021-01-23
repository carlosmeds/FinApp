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
    private String pattern = "yyyy-MM-dd HH:mm:ss";

    public TransactionDAO (Context context){
        DBHelper db = new DBHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    public boolean insertTransaction(Transaction transaction){
        ContentValues values = new ContentValues();
        values.put("value",transaction.getTransactionValue());

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

    public Double getBalance(Context context) {
        Double balance = 0.0;
        Cursor cursor = read.query(DBHelper.TABLE_TRANSATION, new String[]{"value","transactionTypeId"},
                null,null,null,null,null);

        while(cursor.moveToNext()) {
            Double transactionValue = cursor.getDouble(cursor.getColumnIndex("value"));
            Long transactionType = cursor.getLong(cursor.getColumnIndex("transactionTypeId"));
            if (transactionType <= 1)
                balance += transactionValue;
            else
                balance -= transactionValue;
        }

        return balance;
    }

    public List<Transaction> getStatement(Context context){
        List<Transaction> transactionList = new ArrayList<>();
        Cursor cursor = read.query(DBHelper.TABLE_TRANSATION, new String[]{"id","value","date","transactionTypeId"},
                null,null,null,null,"date DESC", "15");

        while(cursor.moveToNext()){
            Transaction t = buildTransactionObject(cursor, context);
            transactionList.add(t);
        }
        return transactionList;
    }

    public Double[] getCategoriesStatement(Context context)     {
        Double[] categoriesStatements = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        Cursor cursor = read.query(DBHelper.TABLE_TRANSATION, new String[]{"SUM(value) as TRANSACTION_SUM","transactionTypeId"},
                null,null,"transactionTypeId",null,null);

        while(cursor.moveToNext()){
            Double transactionValue = cursor.getDouble(cursor.getColumnIndex("TRANSACTION_SUM"));
            Long transactionType = cursor.getLong(cursor.getColumnIndex("transactionTypeId"));
            categoriesStatements[transactionType.intValue()] = transactionValue;
        }

        return categoriesStatements;
    }

    public List<Transaction> getTransactionsByPeriod(Context context, String firstDate, String lastDate){

        List<Transaction> transactionList = new ArrayList<>();
        Cursor cursor = read.query(DBHelper.TABLE_TRANSATION, new String[]{"id","value","date","transactionTypeId"},
                "date <= " + firstDate + "AND date >= " + lastDate,null,null,null,"date DESC");


        while(cursor.moveToNext()){
            Transaction t = buildTransactionObject(cursor, context);
            transactionList.add(t);
        }
        return transactionList;
    }

    public Transaction buildTransactionObject(Cursor cursor,Context context) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO(context);
        Transaction t = new Transaction();

        try {
            Long transactionId = cursor.getLong(cursor.getColumnIndex("id"));
            Double transactionValue = cursor.getDouble(cursor.getColumnIndex("value"));
            String transactionDateString = cursor.getString(cursor.getColumnIndex("date"));
            Date transactionDate = format.parse(transactionDateString);
            Long transactionType = cursor.getLong(cursor.getColumnIndex("transactionTypeId"));
            TransactionType type =  transactionTypeDAO.getTypeById(transactionType);

            Log.i("data", transactionDateString);

            t.setId(transactionId);
            t.setTransactionValue(transactionValue);
            t.setTransactionDate(transactionDate);
            t.setTransactionType(type);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return t;
    }
}
