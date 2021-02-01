package com.study.gongteacher.ui.searchaddress;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.study.gongteacher.data.DataManager;
import com.study.gongteacher.data.dto.Address;
import com.study.gongteacher.databinding.ActivitySearchAddressBinding;
import com.study.gongteacher.ui.base.BaseActivity;
import com.study.gongteacher.ui.login.LoginViewModel;
import com.study.gongteacher.ui.login.LoginViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchAddressActivity extends BaseActivity<SearchAddressViewModel> {



    private ActivitySearchAddressBinding binding;
    private SearchAddressAdapter addressAdapter;

    @NonNull
    @Override
    protected SearchAddressViewModel createViewModel() {
        SearchAddressViewModelFactory factory = new SearchAddressViewModelFactory();
        return ViewModelProviders.of(this, factory).get(SearchAddressViewModel.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchAddressBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        init();
    }

    private void init(){


        //주소 리사이클러뷰 초기화
        binding.rvAddress.setLayoutManager(new LinearLayoutManager(this));
        binding.rvAddress.setHasFixedSize(true);
        binding.rvAddress.setAdapter(addressAdapter);



        //옵저버 초기화
        initObserver();
        //리스너 초기화
        initListener();

    }


    //버튼클릭 초기화 함수
    private void initListener(){

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.svSearchWord.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.doSearchAddress(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.doSearchAddress(newText);
                return false;
            }
        });


        binding.rvAddress.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
                if (lastVisibleItemPosition == itemTotalCount) {
                    //리사이클러 뷰 마지막
                }
            }
        });

    }

    //옵저버 초기화 함수
    private void initObserver(){
        viewModel.getAddressLiveData().observe(this, new Observer<List<Address>>() {
            @Override
            public void onChanged(List<Address> addresses) {
                Address address = new Address();
                address.setLocation("hello");
                addresses.add(address);
                addressAdapter.notifyItemInserted(addresses.size());
            }
        });


    }





}