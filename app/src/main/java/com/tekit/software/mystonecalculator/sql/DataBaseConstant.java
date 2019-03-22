package com.tekit.software.mystonecalculator.sql;

public class DataBaseConstant {

    // Database Version
    static final int DATABASE_VERSION = 2;

    // Database Name
    static final String DATABASE_NAME = "TransportSystem.db";


    // port data table

    static final String TABLE_NAME_STONE_DATA = "stone_dim";

    // column port data table....
    static final String COLUMN_STONE_ID = "stone_id";
    static final String COLUMN_STONE_NUMBER = "stone_number";
    static final String COLUMN_STONE_LENGTH = "stone_length";
    static final String COLUMN_STONE_WEIGHT = "stone_weight";
    static final String COLUMN_STONE_SQUARE_FT="stone_SQUARE_ft";

    // create table port data .....

    final static String CREATE_TABLE_STONE = "CREATE TABLE " + TABLE_NAME_STONE_DATA + " (" +
            COLUMN_STONE_ID+ " INTEGER NOT NULL," + COLUMN_STONE_NUMBER + " TEXT NOT NULL," +
            COLUMN_STONE_LENGTH + " TEXT NOT NULL, " +
            COLUMN_STONE_SQUARE_FT+"TEXT NOT NULL, " +
            COLUMN_STONE_WEIGHT + " TEXT NOT NULL " +

            "); ";

    // drop table port .......

    final static String DROP_PORT_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_STONE_DATA;


}
