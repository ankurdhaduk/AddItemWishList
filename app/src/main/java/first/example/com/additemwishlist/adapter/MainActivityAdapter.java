package first.example.com.additemwishlist.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import first.example.com.additemwishlist.MainActivity;
import first.example.com.additemwishlist.R;
import first.example.com.additemwishlist.database.DataBaseHalper;
import first.example.com.additemwishlist.databinding.ViewProductBinding;
import first.example.com.additemwishlist.model.ProductModel;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<ProductModel> productModels;

    public MainActivityAdapter(MainActivity mainActivity, ArrayList<ProductModel> productArrayList) {
        this.mContext = mainActivity;
        this.productModels = productArrayList;
    }

    @NonNull
    @Override
    public MainActivityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewProductBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.view_product, viewGroup, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.binding.setProduct(new ProductModel(productModels.get(i).getId(), productModels.get(i).getProductName(), productModels.get(i).getPrice(), productModels.get(i).getImage(), productModels.get(i).getCartStatus(), productModels.get(i).getWishStatus()));
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ViewProductBinding binding;

        public MyViewHolder(ViewProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setDatabase(new DataBaseHalper(mContext));
        }
    }
}
