package com.tedu.jizhangben;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COST_TITLE = "cost_title";
    public static final String COST_DATE = "cost_date";
    public static final String COST_MONEY = "cost_money";
    public static final String IMOOC_COST = "imooc_cost";

    public DatabaseHelper(Context context){
        super(context,"JizhangBen",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists imooc_cost(id integer primary key , cost_title vachar , cost_date vachar , cost_money vachar )");
    }
    public void insertCost(CostBean costBean){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COST_TITLE,costBean.costTitle);
        cv.put(COST_DATE,costBean.costDate);
        cv.put(COST_MONEY,costBean.costMoney);
        database.insert(IMOOC_COST,null,cv);
    }
    public Cursor getAllCostData(){
        SQLiteDatabase database=getWritableDatabase();
        return database.query(IMOOC_COST,null,null,null,null,null,"cost_date ASC");
    }
    public void deleteAllData(){
        SQLiteDatabase database=getWritableDatabase();
        database.delete(IMOOC_COST,null,null);
    }
    public void deleteDate(CostBean costBean){
        SQLiteDatabase database=getWritableDatabase();
        database.delete(IMOOC_COST,COST_TITLE+"=? and "+COST_DATE+"=? and "+COST_MONEY +"=?",new String[]{costBean.costTitle,costBean.costDate,costBean.costMoney});
    }
    public void updateDate(CostBean costBean,CostBean bean){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COST_TITLE,costBean.costTitle);
        values.put(COST_DATE,costBean.costDate);
        values.put(COST_MONEY,costBean.costMoney);
        database.update(IMOOC_COST,values,COST_TITLE+"=? and "+COST_DATE+"=? and "+COST_MONEY +"=?",new String[]{bean.costTitle,bean.costDate,bean.costMoney});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
