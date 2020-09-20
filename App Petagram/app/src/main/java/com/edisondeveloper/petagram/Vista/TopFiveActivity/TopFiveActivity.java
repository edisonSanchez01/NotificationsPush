package com.edisondeveloper.petagram.Vista.TopFiveActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.edisondeveloper.petagram.Modelo.Adapters.TopFiveAdapter;
import com.edisondeveloper.petagram.Modelo.ContratoTopFive.TablaTopFive;
import com.edisondeveloper.petagram.Presentador.TopFiveActivity.TopFiveActivityPresenter;
import com.edisondeveloper.petagram.R;

public class TopFiveActivity extends AppCompatActivity implements ITopFiveActivityView {

    private TopFiveAdapter adapter;
    private RecyclerView recyclerView;
    private TopFiveActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_five);
        Toolbar appBar = findViewById(R.id.appBar);
        setSupportActionBar(appBar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.button_back);
        }
        presenter = new TopFiveActivityPresenter(this, this);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new TopFiveAdapter(this, null);
        recyclerView.setAdapter(adapter);
        presenter.executeThread();
    }

    @Override
    public void updateRecyclerView(Cursor cursor) {
        adapter.changeCursor(cursor);
        recyclerView.setAdapter(adapter);
    }

}