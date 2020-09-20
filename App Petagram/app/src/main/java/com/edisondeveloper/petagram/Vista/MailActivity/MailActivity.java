package com.edisondeveloper.petagram.Vista.MailActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.edisondeveloper.petagram.Presentador.MailActivity.MailActivityPresenter;
import com.edisondeveloper.petagram.R;

public class MailActivity extends AppCompatActivity implements IMailActivityView {

    private Button send;
    private EditText name;
    private EditText email;
    private EditText message;
    private MailActivityPresenter mailActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        send = findViewById(R.id.boton_enviar);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        message = findViewById(R.id.msg);
        mailActivityPresenter = new MailActivityPresenter(this, this);
        Toolbar toolbar = findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.button_back);
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mailActivityPresenter.validacion(name, email, message);
            }
        });

    }

}