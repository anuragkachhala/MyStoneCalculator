package com.tekit.software.mystonecalculator.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tekit.software.mystonecalculator.Model.StoneDimention;
import com.tekit.software.mystonecalculator.Singleton.StoneSingleton;

import java.util.HashMap;
import java.util.List;

public class DataBaseAdapter {

    private static final String TAG  = DataBaseAdapter.class.getName();


    Context context;
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;

    public DataBaseAdapter(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    // open DataBase
    public void openDataBase() {
        try {
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            //sqLiteDatabase.beginTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //close DataBase
    public void closeDataBase() {

        try {
            dataBaseHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void addTrackingNoConstraint(List<StoneDimention> stoneDimentionList) {
        openDataBase();

        long rowId;
        ContentValues values;
        for (StoneDimention stoneDimention: stoneDimentionList) {
            values = new ContentValues();
            values.put(DataBaseConstant.COLUMN_STONE_NUMBER, stoneDimention.getNoOfNug());
            values.put(DataBaseConstant.COLUMN_STONE_LENGTH, stoneDimention.getLengthOfNug());
            values.put(DataBaseConstant.COLUMN_STONE_WEIGHT, stoneDimention.getHeightOfNug());
            values.put(DataBaseConstant.COLUMN_STONE_SQUARE_FT, stoneDimention.getSqureFeet());
            rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_NAME_STONE_DATA, null, values);

            Log.e(TAG, "" + values + "  " + rowId);
        }
    }

}
