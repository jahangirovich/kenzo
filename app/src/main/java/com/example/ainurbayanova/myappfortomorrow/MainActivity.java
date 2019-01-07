package com.example.ainurbayanova.myappfortomorrow;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.ainurbayanova.myappfortomorrow.Model.ModelTask;
import com.example.ainurbayanova.myappfortomorrow.Others.TabbedPage;
import com.example.ainurbayanova.myappfortomorrow.fragments.AddingTaskDialogFragment;
import com.example.ainurbayanova.myappfortomorrow.fragments.DoneFragment;
import com.example.ainurbayanova.myappfortomorrow.fragments.GoalFragment;
import com.example.ainurbayanova.myappfortomorrow.fragments.SplashFragment;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements AddingTaskDialogFragment.AddingTaskInterface {
    Button btn;
    EditText title;
    EditText text;
    FragmentManager fragmentManager;
    public static FloatingActionButton fab;
    PreferenceHelper preferenceHelper;
    GoalFragment goalFragment;
    DoneFragment doneFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceHelper.getInstance().init(getApplicationContext());
        preferenceHelper = PreferenceHelper.getInstance();
        fragmentManager = getSupportFragmentManager();
        fab = findViewById(R.id.fab);
        runSplash();
        init();
    }

    public void init(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reminder");
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Goal"));
        tabLayout.addTab(tabLayout.newTab().setText("Done"));

        final ViewPager viewPager = findViewById(R.id.viewPager);
        TabbedPage tabbedPage = new TabbedPage(fragmentManager,2);

        viewPager.setAdapter(tabbedPage);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        doneFragment = (DoneFragment) tabbedPage.getItem(TabbedPage.DONE_TASK_FRAGMENT);
        goalFragment = (GoalFragment) tabbedPage.getItem(TabbedPage.CURRENT_TASK_FRAGMENT);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment addingTaskDialogFragment = new AddingTaskDialogFragment();
                addingTaskDialogFragment.show(fragmentManager,"AddingTaskDialogFragment");
            }
        });
    }

    public void runSplash(){
        if (!preferenceHelper.getBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE)){
            SplashFragment splashFragment = new SplashFragment();

            fragmentManager.beginTransaction()
                    .replace(R.id.content,splashFragment)
                    .addToBackStack(null)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check,menu);
        MenuItem menuItem = menu.findItem(R.id.hi);
        menuItem.setChecked(preferenceHelper.getBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(!item.isChecked());
        preferenceHelper.putBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE,item.isChecked());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTaskAdded(ModelTask modelTask) {
        goalFragment.addTask(modelTask);

    }

    @Override
    public void onTaskAddingCancel() {
        Toast.makeText(this,"Good By",Toast.LENGTH_SHORT).show();
    }
}
