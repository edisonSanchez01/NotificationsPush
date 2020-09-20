package com.edisondeveloper.petagram.Vista.ConfigPerfilActivity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edisondeveloper.petagram.Presentador.ConfigPerfilActivity.ConfigPerfilActivityPresenter;
import com.edisondeveloper.petagram.R;

public class ConfigPerfilActivity extends AppCompatActivity implements IConfigPerfilActivityView {

    private EditText editTextName;
    private Button btnSaveChanges;
    private ConfigPerfilActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_perfil);
        editTextName = findViewById(R.id.editTextNamePerfil);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        presenter = new ConfigPerfilActivityPresenter(this, this);
        Toolbar actionBar = findViewById(R.id.action_bar);
        setSupportActionBar(actionBar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.button_back);
        }
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validarNombre(editTextName.getText().toString());
            }
        });
    }

    @Override
    public void showError(String error) {
        editTextName.setError(error);
    }


}