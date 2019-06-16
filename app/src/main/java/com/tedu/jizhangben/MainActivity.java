package com.tedu.jizhangben;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<CostBean> mCostBeanList;
    private DatabaseHelper mDatabaseHelper;
    private CostListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabaseHelper=new DatabaseHelper(this);
        mCostBeanList=new ArrayList<>();
        ListView costList=findViewById(R.id.lv_main);
        costList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                Button button=findViewById(R.id.up);
//                button.setTag(position);
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//                        LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
//                        View viewDialog=inflater.inflate(R.layout.new_cost_data,null);
//                        final EditText title=viewDialog.findViewById(R.id.et_cost_title);
//                        final EditText money=viewDialog.findViewById(R.id.et_cost_money);
//                        final DatePicker date=viewDialog.findViewById(R.id.dp_cost_date);
//                        builder.setTitle("Update Const");
//                        builder.setView(viewDialog);
//                        builder.setPositiveButton("OK", new  DialogInterface.OnClickListener(){
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            CostBean costBean=new CostBean();
//                            CostBean bean=mCostBeanList.get(position);
//                            costBean.costTitle=title.getText().toString();
//                            costBean.costMoney=money.getText().toString();
//                            costBean.costDate=date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDayOfMonth();
//                            mDatabaseHelper.updateDate(costBean,bean);
//                            mCostBeanList.set(position,costBean);
//                            mAdapter.notifyDataSetChanged();
//                        }
//                        });
//                        builder.setNegativeButton("Cancel",null);
//                        builder.create().show();
//                    }
//                });
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
                CostBean bean=mCostBeanList.get(position);
                View viewDialog=inflater.inflate(R.layout.new_cost_data,null);
                final EditText title=viewDialog.findViewById(R.id.et_cost_title);
                final EditText money=viewDialog.findViewById(R.id.et_cost_money);
                final DatePicker date=viewDialog.findViewById(R.id.dp_cost_date);
                title.setText(bean.costTitle);
                money.setText(bean.costMoney);
                builder.setTitle("Update Const");
                builder.setView(viewDialog);
                builder.setPositiveButton("OK", new  DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CostBean costBean=new CostBean();
                        CostBean bean=mCostBeanList.get(position);
                        costBean.costTitle=title.getText().toString();
                        costBean.costMoney=money.getText().toString();
                        costBean.costDate=date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDayOfMonth();
                        mDatabaseHelper.updateDate(costBean,bean);
                        mCostBeanList.set(position,costBean);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();
            }

        }

//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//                LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
//                View viewDialog=inflater.inflate(R.layout.new_cost_data,null);
//                final EditText title=viewDialog.findViewById(R.id.et_cost_title);
//                final EditText money=viewDialog.findViewById(R.id.et_cost_money);
//                final DatePicker date=viewDialog.findViewById(R.id.dp_cost_date);
//                builder.setTitle("New Const");
//                builder.setView(viewDialog);
//                builder.setPositiveButton("OK", new  DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        CostBean costBean=new CostBean();
//                        costBean.costTitle=title.getText().toString();
//                        costBean.costMoney=money.getText().toString();
//                        costBean.costDate=date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDayOfMonth();
//                        mDatabaseHelper.updateDate(costBean);
//                        mCostBeanList.add(costBean);
//                        mAdapter.notifyDataSetChanged();
//                    }
//                });
//                builder.setNegativeButton("Cancel",null);
//                builder.create().show();
//            }
        );
        costList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view,int position, long id) {
                DeleteDialog(position);
                return true;
            }
        });
        initCostData();
        mAdapter=new CostListAdapter(this,mCostBeanList);
        costList.setAdapter(mAdapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
                View viewDialog=inflater.inflate(R.layout.new_cost_data,null);
                final EditText title=viewDialog.findViewById(R.id.et_cost_title);
                final EditText money=viewDialog.findViewById(R.id.et_cost_money);
                final DatePicker date=viewDialog.findViewById(R.id.dp_cost_date);
                builder.setTitle("New Const");
                builder.setView(viewDialog);
                builder.setPositiveButton("OK", new  DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CostBean costBean=new CostBean();
                        costBean.costTitle=title.getText().toString();
                        costBean.costMoney=money.getText().toString();
                        costBean.costDate=date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDayOfMonth();
                        mDatabaseHelper.insertCost(costBean);
                        mCostBeanList.add(costBean);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();
            }
        });
    }

    private <ShowActivity> void DeleteDialog(final int position ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("确定删除该记录？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CostBean costBean=mCostBeanList.get(position);
                mDatabaseHelper.deleteDate(costBean);
                if(mCostBeanList.remove(position)!=null){
                    System.out.println("success");
                }else {
                    System.out.println("failed");
                }
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "删除列表项", Toast.LENGTH_SHORT).show();
            }

        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }


    private void initCostData() {
//       mDatabaseHelper.deleteAllData();
//        for (int i=0;i<6;i++) {
//            CostBean bean=new CostBean();
//            bean.costTitle=i+"AAA";
//            bean.costDate="06-3";
//            bean.costMoney="100";
//            mDatabaseHelper.insertCost(bean);
//        }
        Cursor cursor= mDatabaseHelper.getAllCostData();
        if (cursor!=null){
            while (cursor.moveToNext()){
                CostBean costBean=new CostBean();
                costBean.costTitle=cursor.getString(cursor.getColumnIndex("cost_title"));
                costBean.costDate=cursor.getString(cursor.getColumnIndex("cost_date"));
                costBean.costMoney=cursor.getString(cursor.getColumnIndex("cost_money"));
                mCostBeanList.add(costBean);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
