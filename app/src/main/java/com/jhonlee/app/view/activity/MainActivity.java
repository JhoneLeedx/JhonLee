package com.jhonlee.app.view.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jhonlee.app.R;
import com.jhonlee.app.util.DimenUtils;
import com.jhonlee.app.view.fragment.AndroidFragment;
import com.jhonlee.app.view.fragment.ExpandFragment;
import com.jhonlee.app.view.fragment.HTMLFragment;
import com.jhonlee.app.view.fragment.IndexFragment;
import com.jhonlee.app.view.fragment.IosFragment;
import com.jhonlee.app.view.fragment.PictureFragment;
import com.jhonlee.app.view.fragment.VideoFragment;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;

/**
 * @author Jhon Lee
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;


    private IndexFragment iFragment;
    private PictureFragment pFragment;
    private AndroidFragment aFragment;
    private VideoFragment vFragment;
    private IosFragment iosFragment;
    private HTMLFragment hFragment;
    private ExpandFragment eFragment;
    private Fragment isFragment;                         //记录当前正在使用的fragment


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        initNavigationView();
        initFragment(savedInstanceState);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //取消提示框
            new AlertDialog.Builder(this)
                    .setMessage("是否退出应用？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.nav_camera:
                toolbar.setTitle("首页");
                if (iFragment==null){
                    iFragment = new IndexFragment();
                }
                switchContent(isFragment, iFragment);
                break;
            case R.id.nav_gallery:
                toolbar.setTitle("图片");
                if (pFragment == null) {
                    pFragment = new PictureFragment();
                }
                switchContent(isFragment, pFragment);
                break;
            case R.id.nav_slideshow:
                toolbar.setTitle("休息视频");
                if (vFragment==null)
                    vFragment = new VideoFragment();
                switchContent(isFragment, vFragment);
                break;
            case R.id.nav_manage:
                toolbar.setTitle("Android");
                if (aFragment == null){
                    aFragment = new AndroidFragment();
                }
                switchContent(isFragment, aFragment);
                break;
            case R.id.nav_ios:
                toolbar.setTitle("IOS");
                if (iosFragment == null){
                    iosFragment = new IosFragment();
                }
                switchContent(isFragment, iosFragment);
                break;
            case R.id.nav_share:
                toolbar.setTitle("拓展资源");
                if (eFragment==null)
                    eFragment = new ExpandFragment();
                switchContent(isFragment, eFragment);
                break;
            case R.id.nav_send:
                toolbar.setTitle("前端");

                if (hFragment == null){
                    hFragment = new HTMLFragment();
                }
                switchContent(isFragment, hFragment);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initNavigationView(){

       Menu menu =  navigationView.getMenu();
        Log.d("menu++++++++++++",menu.size()+"");
        MenuItem item0 = menu.getItem(0);
        MenuItem item1 = menu.getItem(1);
        MenuItem item2 = menu.getItem(2);
        MenuItem item3 = menu.getItem(3);
        MenuItem item4 = menu.getItem(4);
        item0.setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_view_comfy));
        item1.setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_mood));
        item2.setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_collection_video));
        item3.setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_android));
        item4.setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_apple));
    }

    /**
     * 为页面加载初始状态的fragment
     */
    public void initFragment(Bundle savedInstanceState) {
        //判断activity是否重建，如果不是，则不需要重新建立fragment.
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (iFragment == null) {
                iFragment = new IndexFragment();
            }
            isFragment = iFragment;
            ft.replace(R.id.content_main, iFragment).commit();
        }
    }

    /**
     * 当fragment进行切换时，采用隐藏与显示的方法加载fragment以防止数据的重复加载
     *
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to) {
        if (isFragment != to) {
            isFragment = to;
            FragmentManager fm = getSupportFragmentManager();
            //添加渐隐渐现的动画
            FragmentTransaction ft = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                ft.hide(from).add(R.id.content_main, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                ft.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

}
