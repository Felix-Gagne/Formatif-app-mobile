package com.example.formatif;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.formatif.databinding.ActivityErreurBinding;
import com.example.formatif.http.RetrofitUtil;
import com.example.formatif.http.Service;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Erreur extends AppCompatActivity {

    private ActivityErreurBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erreur);

        setTitle("Erreur Erreur Erreur");

        binding = ActivityErreurBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText mdp = binding.mdp;

        Service service = RetrofitUtil.get();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    service.checkPassword(mdp.getText().toString()).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response)
                        {
                            if(response.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                try
                                {
                                    String corpsErreur = response.errorBody().string();
                                    Log.i("RETROFIT", "Le code : " + response.code());
                                    Log.i("RETROFIT", "Le message : " + response.message());
                                    Log.i("RETROFIT", "Le corps : " + corpsErreur);
                                    if(corpsErreur.contains("Not Found")){
                                        Snackbar.make(view, "Veuillez entrer un mot de passe.", Snackbar.LENGTH_LONG)
                                                .show();
                                    }
                                    else if(corpsErreur.contains("DoitContenirUnPointExclamation")){
                                        Snackbar.make(view, "Votre mot de passe doit contenir un point d'exclamation", Snackbar.LENGTH_LONG)
                                                .show();
                                    }
                                    else if(corpsErreur.contains("DoitContenirLeMotPipo")){
                                        Snackbar.make(view, "Votre mot de passe doit contenir le mot Pipo", Snackbar.LENGTH_LONG)
                                                .show();
                                    }
                                }
                                catch(IOException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t)
                        {
                            Toast.makeText(getApplicationContext(), "Oh non! La communication au serveur à échoué!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
        });
    }
}