package first.example.com.additemwishlist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import first.example.com.additemwishlist.model.ProductModel;

public class DataBaseHalper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "product";
    private static final String PRODUCT_TABLE = "product_table";
    private static final int VERSION = 1;
    private static final String KEY_ID = "id";
    private static final String KEY_PRODUCT = "product_name";
    private static final String KEY_IMAGE = "image_url";
    private static final String KEY_PRICE = "price";
    private static final String KEY_WISHLIST = "wish_list";
    private static final String KEY_CART = "cart";

    public DataBaseHalper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String signUpTable = "CREATE TABLE " + PRODUCT_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PRODUCT + " TEXT," + KEY_IMAGE + " TEXT," + KEY_WISHLIST + " TEXT," + KEY_CART + " TEXT," + KEY_PRICE + " TEXT" + ")";
        db.execSQL(signUpTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        onCreate(db);
    }

    public void insertProduct(String strProduct, String strImage, String price, String wishlist_status, String cart) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PRODUCT, strProduct);
        values.put(KEY_IMAGE, strImage);
        values.put(KEY_PRICE, price);
        values.put(KEY_WISHLIST, wishlist_status);
        values.put(KEY_CART, cart);

        db.insert(PRODUCT_TABLE, null, values);
        db.close();
    }

    public ArrayList<ProductModel> getAllProductList(int wishStatus, int cartStatus) {
        ArrayList<ProductModel> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PRODUCT_TABLE + " WHERE " + KEY_WISHLIST + "= " + wishStatus + " AND " + KEY_CART + " = " + cartStatus;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ProductModel contact = new ProductModel();
                contact.setId(cursor.getString(cursor.getColumnIndex(KEY_ID)));
                contact.setProductName(cursor.getString(cursor.getColumnIndex(KEY_PRODUCT)));
                contact.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                contact.setPrice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                contact.setWishStatus(cursor.getString(cursor.getColumnIndex(KEY_WISHLIST)));
                contact.setCartStatus(cursor.getString(cursor.getColumnIndex(KEY_CART)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public ArrayList<ProductModel> getAllProductListType(int wishStatus, int cartStatus) {
        ArrayList<ProductModel> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PRODUCT_TABLE + " WHERE " + KEY_WISHLIST + "= " + wishStatus + " OR " + KEY_CART + " = " + cartStatus;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ProductModel contact = new ProductModel();
                contact.setId(cursor.getString(cursor.getColumnIndex(KEY_ID)));
                contact.setProductName(cursor.getString(cursor.getColumnIndex(KEY_ID)));
                contact.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                contact.setPrice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                contact.setWishStatus(cursor.getString(cursor.getColumnIndex(KEY_WISHLIST)));
                contact.setCartStatus(cursor.getString(cursor.getColumnIndex(KEY_CART)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    public void updateWishlist(String status, ProductModel product) {
        product.setWishStatus("1");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WISHLIST, status);
        db.update(PRODUCT_TABLE, values, KEY_ID + "=" + product.getId(), null);
    }
    public void updateCartlist(String status, ProductModel product) {
        Log.e("clickonCart: " , product.getProductName());
        product.setCartStatus(status);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CART, status);
        db.update(PRODUCT_TABLE, values, KEY_ID + "=" + product.getId(), null);
    }
}
