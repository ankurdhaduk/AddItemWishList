package first.example.com.additemwishlist.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import first.example.com.additemwishlist.BR;
import first.example.com.additemwishlist.R;

public class ProductModel extends BaseObservable {
    private String id, productName, image, price, wishStatus, cartStatus;

    public ProductModel(String id, String productName, String price, String image, String cartStatus, String wishStatus) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.cartStatus = cartStatus;
        this.wishStatus = wishStatus;
    }

    public ProductModel() {

    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Bindable
    public String getWishStatus() {
        return wishStatus;
    }

    @Bindable
    public void setWishStatus(String wishStatus) {
        this.wishStatus = wishStatus;
        notifyPropertyChanged(BR.wishStatus);
    }

    @Bindable
    public String getCartStatus() {
        return cartStatus;
    }

    @Bindable
    public void setCartStatus(String cartStatus) {
        this.cartStatus = cartStatus;
        notifyPropertyChanged(BR.cartStatus);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
