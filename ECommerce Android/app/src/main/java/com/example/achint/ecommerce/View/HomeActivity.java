package com.example.achint.ecommerce.View;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.achint.ecommerce.Controller.MainController;
import com.example.achint.ecommerce.Adapter.ProductAdapter;
import com.example.achint.ecommerce.Interface.ProductInteface;
import com.example.achint.ecommerce.Model.ProductData;
import com.example.achint.ecommerce.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements ProductAdapter.IAdapterCommunicator {

    private ProductInteface productApi;
    private RecyclerView productRecycler;
    private List<ProductData> productList = new ArrayList<>();
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_products);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productRecycler = findViewById(R.id.rv_product_list);
        productAdapter = new ProductAdapter(productList,  this);
        productRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        productRecycler.setAdapter(productAdapter);

        productApi = MainController.getInstance().getClientForProducts("http://10.177.1.250:8081/products/").create(ProductInteface.class);
        getAllProductDetails();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_launcher_drawer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void getAllProductDetails() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        Call<ProductData[]> call = productApi.getAllProducts();
        call.enqueue(new Callback<ProductData[]>() {
            @Override
            public void onResponse(Call<ProductData[]> call, Response<ProductData[]> response) {
                if (200 == response.code()) {
                    productList.addAll(Arrays.asList(response.body()));
                    productAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                    Toast.makeText(HomeActivity.this, "Worked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductData[]> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, "Failed to fetch", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void deleteItem(int position) {
        //TODO
    }
}
