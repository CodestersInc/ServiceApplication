package codesters.serviceapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static codesters.serviceapplication.R.id.foodList;
import static codesters.serviceapplication.R.id.transportList;


public class TransportServiceActivity extends ActionBarActivity {

    private static List<TransportItem> transportItemList;
    private RecyclerView recyclerView;
    private TransportListAdapter transportListAdapter;
    private Toolbar toolbar;
    private NavigationDrawerFragment drawerFragment;
    private boolean backPressedToExitOnce = false;
    private Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_service);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(transportList);
        transportItemList = new ArrayList<TransportItem>();

        transportItemList.add(new TransportItem(R.drawable.ic_car, "Item1"));
        transportItemList.add(new TransportItem(R.drawable.ic_car, "Item2"));

        transportListAdapter = new TransportListAdapter(this, transportItemList);
        recyclerView.setAdapter(transportListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawerLayout), toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        killToast();
    }

    @Override
    public void onBackPressed() {
        if (backPressedToExitOnce) {
            startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } else {
            this.backPressedToExitOnce = true;
            this.toast = Toast.makeText(this, R.string.pressagain, Toast.LENGTH_SHORT);
            this.toast.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressedToExitOnce = false;
                }
            }, 2000);
        }
    }

    private void killToast(){
        if(this.toast!=null){
            this.toast.cancel();
        }
    }
}
