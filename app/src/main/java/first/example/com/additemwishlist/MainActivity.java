package first.example.com.additemwishlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;

import first.example.com.additemwishlist.adapter.MainActivityAdapter;
import first.example.com.additemwishlist.database.DataBaseHalper;
import first.example.com.additemwishlist.databinding.ActivityMainBinding;
import first.example.com.additemwishlist.model.ProductModel;

public class MainActivity extends AppCompatActivity {

    private DataBaseHalper db;
    private ArrayList<ProductModel> productArrayList;
    ActivityMainBinding binding;
    MainActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setData(true);
        Stetho.initializeWithDefaults(this);
        db = new DataBaseHalper(MainActivity.this);
        setOnClick();
    }

    private void setOnClick() {
        binding.btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvNoData.setText("please wait...");
                binding.setData(false);
                productArrayList = new ArrayList<>();
                binding.rvTop.setAdapter(null);
                for (int i = 0; i < 10; i++) {
                    db.insertProduct("Name" + i, "https://www.askideas.com/media/13/Enjoy-Text-Image.jpg", "1" + i, "0", "0");
                }
                //get data from sqlite table
                productArrayList = db.getAllProductList(0, 0);
                adapter = new MainActivityAdapter(MainActivity.this, productArrayList);
                binding.rvTop.setAdapter(adapter);


                if (productArrayList.size() != 0){
                    binding.setData(true);
                } else {
                    binding.tvNoData.setText("no data found");
                }
            }
        });
        binding.btnWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvNoData.setText("please wait...");
                binding.setData(false);
                productArrayList = new ArrayList<>();
                binding.rvTop.setAdapter(null);
                productArrayList = db.getAllProductListType(1, -1);
                adapter = new MainActivityAdapter(MainActivity.this, productArrayList);
                binding.rvTop.setAdapter(adapter);
                if (productArrayList.size() != 0){
                    binding.setData(true);
                } else {
                    binding.tvNoData.setText("no data found");
                }
            }
        });
        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvNoData.setText("please wait...");
                binding.setData(false);
                productArrayList = new ArrayList<>();
                binding.rvTop.setAdapter(null);
                productArrayList = db.getAllProductListType(-1, 1);
                adapter = new MainActivityAdapter(MainActivity.this, productArrayList);
                binding.rvTop.setAdapter(adapter);
                if (productArrayList.size() != 0){
                    binding.setData(true);
                } else {
                    binding.tvNoData.setText("no data found");
                }
            }
        });
    }
}
