package com.example.mohamednasser.mal_final_phase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public  class MainActivity extends AppCompatActivity implements MovieListener, MainFragment.OnFragmentInteractionListener, DetailedFragment.OnFragmentInteractionListener {
    boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main);
        FrameLayout twoPane = (FrameLayout) findViewById(R.id.frame_layout_main);
        if(null == twoPane)
            mTwoPane = false;
        else mTwoPane = true;

 /*
 in both tow pane and one pane we have to set the first fragment
  */
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        mainFragment.setMovieListener(this);
        // this is accepted as the MainActivity implements MovieListener
        fragmentTransaction.replace(R.id.frame_layout_main,mainFragment);
        Log.v("#$#$#$#$#$3","IN activity main");
        fragmentTransaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingActivity.class)); // start the setting activity if the setting is selected
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void setMovie(Image image) {

        if(mTwoPane)
        // two pane ui
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment detailedFragment = new DetailedFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Image", image);
            Log.v("55555555555555555555","object will be passed"+image.getPath());
            detailedFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_layout_main,detailedFragment);
            fragmentTransaction.commit();
        }
        else
        // in one pane ui we gonna
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment detailedFragment = new DetailedFragment();
            Bundle bundle = new Bundle();
            String tag =  null;
            fragmentTransaction.addToBackStack(tag);
            bundle.putParcelable("Image", image);
            Log.v("55555555555555555555","object will be passed"+image.getPath());
            detailedFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_layout_main,detailedFragment);
            fragmentTransaction.commit();
        }


    }
}