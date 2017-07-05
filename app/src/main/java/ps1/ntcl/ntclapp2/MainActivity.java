package ps1.ntcl.ntclapp2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ps1.ntcl.ntclapp2.navitems.nav_op1;
import ps1.ntcl.ntclapp2.navitems.nav_op2;
import ps1.ntcl.ntclapp2.navitems.nav_op3;
import ps1.ntcl.ntclapp2.navitems.nav_subop1;
import ps1.ntcl.ntclapp2.navitems.nav_subop2;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Boolean exit = false;//this is to check exit condition
    int current_frag_id;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        FragmentManager sFm = getSupportFragmentManager();
        FragmentManager fragmentManager = getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (exit)
            System.exit(0);
        else {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawers();
                exit = false;
                return;
            }
            else {
                if (current_frag_id == R.id.nav_op1) {
                    Toast.makeText(MainActivity.this, "Press again to exit", Toast.LENGTH_SHORT).show();
                    exit = true;
                    return;
                }
                else {
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new nav_op1()).commit();
                    setTitle(getString(R.string.nav_op1));
                    current_frag_id = R.id.nav_op1;
                    navigationView.setCheckedItem(R.id.nav_op1);
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager sFm = getSupportFragmentManager();
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        exit = false;

        int id = item.getItemId();

        if (id == R.id.nav_op1) {
            setTitle(getString(R.string.nav_op1));
            fragment = new nav_op1();
            navigationView.setCheckedItem(R.id.nav_op1);
        }
        else if (id == R.id.nav_op2) {
            setTitle(getString(R.string.nav_op2));
            fragment = new nav_op2();
            navigationView.setCheckedItem(R.id.nav_op2);
        }
        else if (id == R.id.nav_op3) {
            setTitle(getString(R.string.nav_op3));
            fragment = new nav_op3();
            navigationView.setCheckedItem(R.id.nav_op3);
        }

        else if (id == R.id.nav_subop1) {
            setTitle(getString(R.string.nav_subop1));
            fragment = new nav_subop1();
            navigationView.setCheckedItem(R.id.nav_subop1);
        }
        else if (id == R.id.nav_subop2) {
            setTitle(getString(R.string.nav_subop2));
            fragment = new nav_subop2();
            navigationView.setCheckedItem(R.id.nav_subop1);
        }


        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        current_frag_id = id;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
