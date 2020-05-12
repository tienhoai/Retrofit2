package com.example.retrofit2.pk.ui.product;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.retrofit2.R;
import com.example.retrofit2.pk.adapter.ProductAdapterRv;
import com.example.retrofit2.pk.model.ProductData;
import com.example.retrofit2.pk.retrofit2.ApiClient;
import com.example.retrofit2.pk.retrofit2.DataClient;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {
    private ProductViewModel mViewModel;
    private RecyclerView rvProduct;

    private void mapping(View view) {
        rvProduct = view.findViewById(R.id.rvProduct);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment, container, false);
        mapping(view);
        retrofitGetData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
    }

    private void retrofitGetData() {
        DataClient dataClient = ApiClient.apiGetData();
        Call<List<ProductData>> callBack = dataClient.getProduct();

        Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();
        callBack.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                List<ProductData> productDataList = response.body();
                fillProduct(productDataList);
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
//                Log.e("NTH retrofitGetData", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    private void fillProduct(List<ProductData> listProduct) {
        rvProduct.setHasFixedSize(true);
        rvProduct.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvProduct.setAdapter(new ProductAdapterRv(listProduct, getContext()));
    }
}
