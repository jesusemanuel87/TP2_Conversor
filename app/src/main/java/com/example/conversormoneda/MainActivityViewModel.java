package com.example.conversormoneda;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;

    private MutableLiveData<String> valorDolar = new MutableLiveData<>();
    private MutableLiveData<String> valorEuro = new MutableLiveData<>();
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }
    public LiveData<String> getValorDolar() {
        return valorDolar;
    }
    public LiveData<String> getValorEuro() {
        return valorEuro;
    }
    public void convertir(int dolar,int euro){
        if(euro==0 && dolar ==0){
            Toast.makeText(context,"Debe ingresar un valor",Toast.LENGTH_LONG).show();
            return;
        }
        if(dolar==0){
            valorEuro.setValue(String.valueOf(euro * 1.09));
        }
        else if(euro == 0) {
            valorDolar.setValue(String.valueOf(dolar * 0.92));
        }
    }
}