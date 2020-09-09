package com.example.dawd_practical_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edName;
    private EditText edQuantity;
    private Button btAddProduct;
    private DBHelper db;
    private Button btGetAllProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    public void initView() {
        edName = (EditText) findViewById(R.id.edName);
        edQuantity = (EditText) findViewById(R.id.edQuantity);
        btAddProduct = (Button) findViewById(R.id.btAddProduct);
        btAddProduct.setOnClickListener(this);
        btGetAllProducts = (Button) findViewById(R.id.btGetAllProducts);
        btGetAllProducts.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btAddProduct) {
            onAddProduct();
        }
        if (view == btGetAllProducts) {
            onGetAllProducts();
        }
    }

    private void onGetAllProducts() {
        Intent intent = new Intent(MainActivity.this, UserList.class);
        startActivity(intent);
    }

    private void onAddProduct() {
        if(edName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter product name", Toast.LENGTH_LONG).show();
            return;
        }

        if(edQuantity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter product quantity", Toast.LENGTH_LONG).show();
            return;
        }

        String isAdd = db.addProducts(edName.getText().toString(), edQuantity.getText().toString());
        Toast.makeText(this, isAdd, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainActivity.this, UserList.class);
        startActivity(intent);
    }
}