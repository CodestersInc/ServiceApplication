package codesters.serviceapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;


public class FeedbackActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private NavigationDrawerFragment drawerFragment;
    private boolean backPressedToExitOnce = false;
    private Toast toast = null;
    private RatingBar ratingBar1, ratingBar2, ratingBar3, ratingBar4;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        addListenerOnButton();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawerLayout), toolbar);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void addListenerOnButton() {
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);
        ratingBar4 = (RatingBar) findViewById(R.id.ratingBar4);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        ratingBar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FeedbackActivity.this,
                        String.valueOf(ratingBar1.getRating()),
                        Toast.LENGTH_SHORT).show();

                Toast.makeText(FeedbackActivity.this,
                        String.valueOf(ratingBar2.getRating()),
                        Toast.LENGTH_SHORT).show();

                Toast.makeText(FeedbackActivity.this,
                        String.valueOf(ratingBar3.getRating()),
                        Toast.LENGTH_SHORT).show();

                Toast.makeText(FeedbackActivity.this,
                        String.valueOf(ratingBar4.getRating()),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
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

    private void killToast() {
        if (this.toast != null) {
            this.toast.cancel();
        }
    }
}
