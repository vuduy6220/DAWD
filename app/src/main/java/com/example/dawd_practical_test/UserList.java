package com.example.dawd_practical_test;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class UserList extends AppCompatActivity {
    private DBHelper db;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    public UserList() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        db = new DBHelper(this);
        ListView lvProduct = (ListView) findViewById(R.id.lvProduct);

        cursor = db.getAllProducts();

        adapter = new SimpleCursorAdapter(this, R.layout.item_product, cursor, new String[]{
                DBHelper.ID, DBHelper.NAME, DBHelper.QUANTITY
        }, new int[]{
                R.id.tvID, R.id.tvName, R.id.tvQuantity
        }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lvProduct.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cursor = db.getAllProducts();
        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
        db.close();
    }
}