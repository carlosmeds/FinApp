package br.ufpr.tads.finapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.finapp.model.TransactionType;

public class TransactionTypeDAO {
    private SQLiteDatabase write;
    private SQLiteDatabase read;
    public TransactionTypeDAO(Context context) {
        DBHelper db = new DBHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    public TransactionType getTypeById(Long id){
        TransactionType transactionType = new TransactionType();
        String table = DBHelper.TABLE_TRANSATION_TYPE;
        String where = "id=?";
        String[] args = {id.toString()};

        Cursor cursor = read.query(table, new String[]{"id", "description"}, where, args, null, null, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            String description = cursor.getString(cursor.getColumnIndex("description"));
            transactionType.setType(description);
            transactionType.setId(id);
        }

        return transactionType;
    }

    public List<TransactionType> getAllTransactionTypes() {
        List<TransactionType> TransactionTypeList = new ArrayList<>();
        Cursor cursor = read.query(DBHelper.TABLE_TRANSATION_TYPE, new String[]{"id", "description"},
                null, null, null, null, null );

        while(cursor.moveToNext()) {
            TransactionType transactionType = new TransactionType();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String type = cursor.getString(cursor.getColumnIndex("description"));
            transactionType.setId(id);
            transactionType.setType(type);
            TransactionTypeList.add(transactionType);

        }
        return TransactionTypeList;
    }
}
