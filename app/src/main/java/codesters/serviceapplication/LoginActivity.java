package codesters.serviceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private boolean backPressedToExitOnce = false;
    private Toast toast = null;
    private Toolbar toolbar;

    public static final String UNAME = "nameKey";
    public static final String PWD = "pwdKey";
    public static final String MYPREFERENCES = "MyPrefs";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Welcome");

        sharedPreferences = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        if(sharedPreferences.contains(UNAME) && sharedPreferences.contains(PWD)){
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            Intent i = new Intent(this, HomeActivity.class);
            EditText t1 = (EditText) findViewById(R.id.edtUname);
            EditText t2 = (EditText) findViewById(R.id.edtPwd);
            String uname = t1.getText().toString();
            String pwd = t2.getText().toString();
            if (uname.equals("ravi") && pwd.equals("123")) {
                Editor editor = sharedPreferences.edit();
                editor.putString(UNAME, uname);
                editor.putString(PWD, pwd);
                editor.commit();
                i.putExtra("Param", uname);
                startActivity(i);
            } else {
                Toast t = Toast.makeText(this,
                        "Invalid UserName or Password..", Toast.LENGTH_LONG);
                t.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("EXIT", true);
            startActivity(intent);
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
