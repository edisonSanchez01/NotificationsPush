package com.edisondeveloper.petagram.Vista.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.edisondeveloper.petagram.Modelo.Adapters.SingletonListPets;
import com.edisondeveloper.petagram.Modelo.Adapters.PagerAdapter;
import com.edisondeveloper.petagram.Presentador.MainActivity.MainActivityPresenter;
import com.edisondeveloper.petagram.Vista.AboutActivity;
import com.edisondeveloper.petagram.Vista.ConfigPerfilActivity.ConfigPerfilActivity;
import com.edisondeveloper.petagram.Vista.TopFiveActivity.TopFiveActivity;
import com.edisondeveloper.petagram.R;
import com.edisondeveloper.petagram.Vista.MailActivity.MailActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    public static SingletonListPets singletonListPets;
    private TabLayout tabs;
    private ViewPager viewPager;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singletonListPets = SingletonListPets.getListPets(this);
        Toolbar toolbar = findViewById(R.id.appBarLayout);
        viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tab_layout);
        setSupportActionBar(toolbar);
        presenter = new MainActivityPresenter(this, this);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        presenter.generateListFragments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public void configViewPagerAdapter(ArrayList<Fragment> fragments) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(0).setIcon(R.drawable.home);
        tabs.getTabAt(1).setIcon(R.drawable.perfil_dog);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_option:
                presenter.generateTopFive(singletonListPets.getListPets(), getContentResolver());
                Intent topActivity = new Intent(this, TopFiveActivity.class);
                startActivity(topActivity);
                break;
            case R.id.contacto:
                Intent mailActivity = new Intent(this, MailActivity.class);
                startActivity(mailActivity);
                break;
            case R.id.acerca:
                Intent aboutActivity = new Intent(this, AboutActivity.class);
                startActivity(aboutActivity);
                break;
            case R.id.config:
                Intent configPerfilActivity = new Intent(this, ConfigPerfilActivity.class);
                startActivity(configPerfilActivity);
                break;
            case R.id.notifications:
                presenter.sendTokenToServer();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}