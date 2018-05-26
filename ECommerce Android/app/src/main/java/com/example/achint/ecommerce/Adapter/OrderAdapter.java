package com.example.achint.ecommerce.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.achint.ecommerce.Model.OrderModel;
import com.example.achint.ecommerce.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private List<OrderModel> mOrders;
    private Context mContext;
    private IAdapterCommunicator iAdapterCommunicator;

    public OrderAdapter(List<OrderModel> orderList, IAdapterCommunicator homeActivity) {
        mOrders = orderList;
        this.iAdapterCommunicator = homeActivity;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_history, parent, false);
        mContext = parent.getContext();
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderModel product  = mOrders.get(position);
        holder.productName.setText(product.getProductId());
        holder.productPrice.setText(String.valueOf(product.getTotalCost()));
        SimpleDateFormat orderDateFormatter = new SimpleDateFormat("dd - MMM - yyyy");
        holder.productDate.setText(String.valueOf(orderDateFormatter.format(product.getOrderDate())));
        Glide.with(holder.productImage.getContext()).load(product.getProductUrl()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView productImage;
        TextView productDate;

        public ViewHolder(View itemView) {
            super(itemView);
            productDate = itemView.findViewById(R.id.ordered_product_date);
            productImage = itemView.findViewById(R.id.ordered_product_image);
            productName = itemView.findViewById(R.id.ordered_product_name);
            productPrice = itemView.findViewById(R.id.ordered_product_price);
        }
    }

    public interface IAdapterCommunicator {
        void deleteItem(int position);
    }
}
