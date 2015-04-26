package codesters.serviceapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerFragment extends Fragment {

    private RecyclerView reclylerView;
    private ActionBarDrawerToggle mDrawerToggle;
    public static DrawerLayout mDrawerLayout;
    private NavigationDrawerFragmentAdapter adapter;
    private View containerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    public static List<DrawerItem> getData() {
        List<DrawerItem> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_home_modern,
                R.drawable.ic_food,
                R.drawable.ic_housekeeping,
                R.drawable.ic_hospital,
                R.drawable.ic_car,
                R.drawable.ic_plus_network,
                R.drawable.ic_feedback,
                R.drawable.ic_logout};
        String[] titles = {"Home", "Food", "Housekeeping", "Health & Medical", "Transport", "Extra Service", "Feedback", "Logout"};
        for (int i = 0; i < titles.length; i++) {
            DrawerItem current = new DrawerItem(icons[i], titles[i]);
            data.add(current);
        }
        return data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        reclylerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new NavigationDrawerFragmentAdapter(getActivity(), getData());
        reclylerView.setAdapter(adapter);
        reclylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public void setUp(int fragmentID, DrawerLayout drawerLayout, Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentID);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
}
