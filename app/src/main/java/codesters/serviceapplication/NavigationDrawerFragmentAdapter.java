package codesters.serviceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by RAVI on 01-Mar-15.
 */
public class NavigationDrawerFragmentAdapter extends RecyclerView.Adapter<NavigationDrawerFragmentAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    List<DrawerItem> data = Collections.emptyList();
    private SharedPreferences sharedPreferences;
    private Context context;

    public NavigationDrawerFragmentAdapter(Context context, List<DrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        sharedPreferences = context.getSharedPreferences(LoginActivity.MYPREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DrawerItem current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconID);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public DrawerItem getItem(int i) {
        return data.get(i);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView icon;
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
        }

        @Override
        public void onClick(View v) {

            DrawerItem item = getItem(getPosition());
            switch (item.title) {
                case "Home":
                    HomeActivity.myWebView.loadUrl("http://hom24x7.somee.com");
                    NavigationDrawerFragment.mDrawerLayout.closeDrawer(Gravity.LEFT);
                    break;
                case "Food":
                    HomeActivity.myWebView.loadUrl("http://www.google.com");
                    NavigationDrawerFragment.mDrawerLayout.closeDrawer(Gravity.LEFT);
                    break;
                case "Housekeeping":
                    HomeActivity.myWebView.loadUrl("http://www.yahoo.com");
                    NavigationDrawerFragment.mDrawerLayout.closeDrawer(Gravity.LEFT);
                    break;
                case "Health & Medical":
                    break;
                case "Transport":
                    break;
                case "Extra Service":
                    break;
                case "Feedback":
                    break;
                case "Logout": {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
            }
        }
    }
}

