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
    private SQLiteDatabase write; //Para escrever nas tabelas
    private SQLiteDatabase read; //Para ler nas tabelas

    public TransactionTypeDAO(Context context) {
        DBHelper db = new DBHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }


    public boolean insertTransactionType(TransactionType transactionType) {
        ContentValues values = new ContentValues();
        values.put("description", transactionType.getType());
        try {
            write.insert(DBHelper.TABLE_TRANSATION_TYPE, null, values);
            Log.i("INFO", "Tipo salva com sucesso. ");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar Tipo. " + e.getMessage());
            return false;
        }
        return true;
    }

    public TransactionType getTypeById(String id){
        return null;

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
