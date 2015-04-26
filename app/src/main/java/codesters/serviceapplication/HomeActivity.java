package codesters.serviceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class HomeActivity extends ActionBarActivity {
    private SharedPreferences sharedPreferences;
    private Toolbar toolbar;
    private NavigationDrawerFragment drawerFragment;
    private boolean backPressedToExitOnce = false;
    private Toast toast = null;

    public static WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawerLayout), toolbar);


        myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://hom24x7.somee.com");
    }

    @Override
    protected void onResume() {
        sharedPreferences = getSharedPreferences(LoginActivity.MYPREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(LoginActivity.UNAME)  && sharedPreferences.contains(LoginActivity.PWD)){
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        }
        super.onResume();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
