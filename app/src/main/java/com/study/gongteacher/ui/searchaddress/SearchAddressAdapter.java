package com.study.gongteacher.ui.searchaddress;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.gongteacher.data.dto.Address;
import com.study.gongteacher.databinding.ItemSearchAddressBinding;

import java.util.ArrayList;

public class SearchAddressAdapter extends RecyclerView.Adapter<SearchAddressAdapter.CustomViewHolder> {
    private ArrayList<Address> addressList;


    public SearchAddressAdapter(ArrayList<Address> addressList) {
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public SearchAddressAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CustomViewHolder(ItemSearchAddressBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Address address = addressList.get(position);
        holder.binding.tvAddress.setText(address.getLocation());
        Log.d(" address bind ",address.getLocation());

    }

    @Override
    public int getItemCount() {
        return addressList == null? 0:addressList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private ItemSearchAddressBinding binding;

        public CustomViewHolder(ItemSearchAddressBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
