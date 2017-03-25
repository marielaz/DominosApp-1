package karikuncheva.dominosapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.DropBoxManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;

/**
 * Created by Patarinski on 3/25/2017.
 */

public class CartAdapter extends ArrayAdapter<String>{

    private Activity activity;
    private User user;
    private ArrayList<Product> productsInCart ;


    public CartAdapter(Activity activity, User user){
        super(activity, R.layout.single_row_cart);
        this.activity = activity;
        this.user = user;
        this.productsInCart =new ArrayList<Product>();
        for (Map.Entry<Product.ProductType, HashSet<Product>> products : user.getCart().getProducts().entrySet()){
            for(Product p1 : products.getValue()){
               this.productsInCart.add(p1);
            }
        }
    }


    @Override
    public int getCount() {
        return productsInCart.size();
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //convert xml to java with inflater
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_cart, parent, false);

//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_row_pizza, parent, false);
//        }
        for (Map.Entry<Product.ProductType, HashSet<Product>> products : user.getCart().getProducts().entrySet()){
            for(Product p1 : products.getValue()){
                productsInCart.add(p1);
            }
        }
        ImageView image_in_cart = (ImageView) row.findViewById(R.id.image_in_cart);
        TextView p_name_in_cart = (TextView) row.findViewById(R.id.p_name_in_cart);
        image_in_cart.setImageResource(productsInCart.get(position).getImageId());
        p_name_in_cart.setText(productsInCart.get(position).getName());

        return row;
    }
}