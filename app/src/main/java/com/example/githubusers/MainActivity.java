package com.example.githubusers;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.githubusers.Adapter.ListUserAdapter;
import com.example.githubusers.Api.Users;
import com.example.githubusers.Models.ItemsItem;
import com.example.githubusers.Models.UserModels;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static final String BASE_URL = "https://api.github.com/";

    private EditText edittextSearch;
    private RecyclerView recyclerview;
    private ImageButton buttonSearch;

    private ArrayList<ItemsItem> listUser = new ArrayList<>();
    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSearch    = findViewById(R.id.buttonSearch);
        edittextSearch  = findViewById(R.id.edittextSearch);
        recyclerview    = findViewById(R.id.recyclerview);

        SetRecylerView();
        ButtonClikListener();

    }
    //SetReyclerView
    private void SetRecylerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(new ListUserAdapter(this,listUser));
    }
    //On Button Search or Edittext listener
    private void ButtonClikListener() {
        buttonSearch.setOnClickListener(v -> GetUsers());
        edittextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void afterTextChanged(Editable s) {
                //cek if there any word type on edittext
                if (s.toString().length() > 0){
                    //Do Delay 1 Seconds and get Users
                    new Handler().postDelayed(() -> GetUsers(), 1000);
                }else {
                    //Clear the list if not type any word
                    listUser = new ArrayList<>();
                    SetRecylerView();
                }
            }
        });
    }

    //Inisialisasi Retrofit
    private void SetRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        users = retrofit.create(Users.class);
    }

    //handle Response
    private void GetUsers(){
        SetRequest();
        String query  = edittextSearch.getText().toString();
        Call<UserModels> call = users.GETUSERS(query);
        call.enqueue(new Callback<UserModels>() {
            @Override
            public void onResponse(@NonNull Call<UserModels> call, @NonNull Response<UserModels> response) {
                if (response.isSuccessful()){
                    UserModels models = response.body();
                    listUser = Objects.requireNonNull(models).getItems();
                    SetRecylerView();
                }else {
                    Toast.makeText(getApplicationContext(), ""+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<UserModels> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), ""+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}