package com.example.shopauto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner1);
        number = (EditText) findViewById(R.id.num);

        MainActivity intasnce = this;
        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID()
                .enqueue(new Callback<List<Currency>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Currency>> call, @NonNull Response<List<Currency>> response) {
                        List<Currency> post = response.body();
                        ArrayAdapter<String> adapter;
                        List<String> arr= new ArrayList<String>();
                        for(int i = 0; i < post.size(); i++){
                            arr.add(post.get(i).getCcy().toString());
                            Toast.makeText(intasnce,post.get(0).getBuy(), Toast.LENGTH_LONG).show();
                        }
                        adapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_spinner_item, arr);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        Toast.makeText(intasnce,post.get(0).getBuy(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Currency>> call, @NonNull Throwable t) {

                        t.printStackTrace();
                    }
                });
    }

    public void onClickSell(View view){
    int id = (int) spinner.getSelectedItemId();
    double count = Integer.parseInt(number.getText().toString());

        MainActivity intasnce = this;
        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID()
                .enqueue(new Callback<List<Currency>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Currency>> call, @NonNull Response<List<Currency>> response) {
                        List<Currency> post = response.body();
                        double selled = count * Double.parseDouble( post.get(id).getSale());
                        Toast.makeText(intasnce,"SELL " + count +" "+ spinner.getSelectedItem() + " = "+ selled + " UAH", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Currency>> call, @NonNull Throwable t) {

                        t.printStackTrace();
                    }
                });


    }
    public void onClickBuy(View view) {
        int id = (int) spinner.getSelectedItemId();
        double count = Integer.parseInt(number.getText().toString());

        MainActivity intasnce = this;
        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID()
                .enqueue(new Callback<List<Currency>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Currency>> call, @NonNull Response<List<Currency>> response) {
                        List<Currency> post = response.body();
                        double selled = count / Double.parseDouble(post.get(id).getBuy());
                        Toast.makeText(intasnce, "BUY " + count + " UAH" + " = " + selled + spinner.getSelectedItem() , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Currency>> call, @NonNull Throwable t) {

                        t.printStackTrace();
                    }

                });
    }
}