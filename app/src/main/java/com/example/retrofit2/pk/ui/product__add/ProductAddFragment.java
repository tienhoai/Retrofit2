package com.example.retrofit2.pk.ui.product__add;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.retrofit2.R;
import com.example.retrofit2.pk.retrofit2.ApiClient;
import com.example.retrofit2.pk.retrofit2.DataClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ProductAddFragment extends Fragment {
    private ProductAddViewModel mViewModel;
    private EditText etId, etPathImg, etName, etPrice;
    private ImageView ivAvatar;
    private Button btSelectImage, btCancel, btAdd;
    private int REQUEST_CODE_IMAGE = 46243;
    private String realPath = "";

    private void mapping(View view) {
        etId = view.findViewById(R.id.etId);
        etPathImg = view.findViewById(R.id.etPathImg);
        etName = view.findViewById(R.id.etName);
        etPrice = view.findViewById(R.id.etPrice);
        ivAvatar = view.findViewById(R.id.ivAvatar);
        btSelectImage = view.findViewById(R.id.btSelectImage);
        btCancel = view.findViewById(R.id.btCancel);
        btAdd = view.findViewById(R.id.btAdd);
    }

    public static ProductAddFragment newInstance() {
        return new ProductAddFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductAddViewModel.class);
        // TODO: Use the ViewModel
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_IMAGE);
    }

    private String getRealPathFromUri(Uri uri) {
        String path = null;
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    private void viewListener() {

//        ivAvatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectImage();
//            }
//        });
//
//        etPathImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectImage();
//            }
//        });

        btSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(realPath);
                String filePath = file.getAbsolutePath();
                String[] arrNameFile = filePath.split("\\.");
                filePath = arrNameFile[0] + "_" + System.currentTimeMillis() + "." + arrNameFile[1];
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                Toast.makeText(getActivity(), filePath, Toast.LENGTH_SHORT).show();
                MultipartBody.Part body = MultipartBody.Part.createFormData("fileToUpload", filePath, requestBody);
                retrofitPutData(body);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            realPath = getRealPathFromUri(uri);
            File file = new File(realPath);
            try {
                assert uri != null;
                InputStream inputStream = Objects.requireNonNull(getActivity()).getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivAvatar.setImageBitmap(bitmap);
                etPathImg.setText(file.getAbsoluteFile().toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void retrofitPutData(MultipartBody.Part body) {
        DataClient dataClient = ApiClient.apiGetData();
        Call<String> callBack = dataClient.uploadImage(body);

        callBack.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null) {
                    Log.e("Test", "response.body()");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Log.e("Loi upload", t.getMessage().toString());
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.product_add_fragment, container, false);
        mapping(view);
        viewListener();
        return view;
    }
}
