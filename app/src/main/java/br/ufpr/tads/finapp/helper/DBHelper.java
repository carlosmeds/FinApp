package br.ufpr.tads.finapp.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    //Database version:
    public static int DB_VERSION = 1;
    //Database name:
    public static String DB_NAME = "FinApp.DB";
    //Table names:
    public static String TABLE_TRANSATION_TYPE = "transactions_types";
    public static String TABLE_TRANSATION = "transactions";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //TRANSACTION_TYPE Table:
        String CREATE_TABLE_TRANSACTION_TYPE = "CREATE TABLE IF NOT EXISTS " + TABLE_TRANSATION_TYPE +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  description TEXT NOT NULL);";

        //TRANSACTION Table:
         String CREATE_TABLE_TRANSACTION = "CREATE TABLE IF NOT EXISTS " + TABLE_TRANSATION +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "  value REAL NOT NULL," +
                "  date TEXT NOT NULL," +
                "  transactionTypeId INTEGER," +
                "  FOREIGN KEY(transactionTypeId) REFERENCES "+TABLE_TRANSATION_TYPE+"(id));";


         String INSERT_TABLE_TRANSACTION_TYPE = "INSERT INTO " + TABLE_TRANSATION_TYPE + "(id, description) VALUES " +
                "(0,'Crédito - Salário')," +
                "(1,'Crédito - Transferências')," +
                "(2,'Débito - Educação')," +
                "(3,'Débito - Lazer')," +
                "(4,'Débito - Moradia')," +
                "(5,'Débito - Saúde')," +
                "(6,'Débito - Outros');";
        try{
            sqLiteDatabase.execSQL(CREATE_TABLE_TRANSACTION_TYPE);
            sqLiteDatabase.execSQL(INSERT_TABLE_TRANSACTION_TYPE);
            sqLiteDatabase.execSQL(CREATE_TABLE_TRANSACTION);
            Log.i("INFO DB", "Tabelas criadas com sucesso!");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar tabela: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSATION);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSATION_TYPE);
            onCreate(sqLiteDatabase);
            Log.i("INFO DB","Tabela atualizada com sucesso!");
        }catch (Exception e){
            Log.i("INFO DB","Erro ao atualizar tabela: "+ e.getMessage());
        }
    }
}
