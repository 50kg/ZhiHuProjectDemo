package tangcco.liuchao.com.toolbardrawerlayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import tangcco.liuchao.com.toolbardrawerlayout.fragment.FaXianFragment;
import tangcco.liuchao.com.toolbardrawerlayout.fragment.HomeFragment;
import tangcco.liuchao.com.toolbardrawerlayout.fragment.ShouCangFragment;
import tangcco.liuchao.com.toolbardrawerlayout.interfaces.ISetDataListener;

public class MainActivity extends AppCompatActivity implements ISetDataListener {
    //声明相关变量
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    private HomeFragment homeFragment;
    private ShouCangFragment shouCangFragment;
    private FaXianFragment faXianFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();//初始化ToolBar
        initDrawer();//初始化侧滑栏
        initNavigation();
        addMenuListener();//监听

        initIndexFragment();//Fragment默认显示主页 和 侧滑栏里的内容
    }

    private void initNavigation() {
        navigationView = (NavigationView) this.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_home://首页
                        homeFragment = new HomeFragment();
                        transaction.replace(R.id.center_linearLayout, homeFragment);
                        break;
                    case R.id.nav_faxian://发现
                        faXianFragment = new FaXianFragment();
                        transaction.replace(R.id.center_linearLayout, faXianFragment);
                        break;
                    case R.id.nav_guanzhu://关注
                        break;
                    case R.id.nav_shoucang://收藏
                        shouCangFragment = new ShouCangFragment();
                        transaction.replace(R.id.center_linearLayout, shouCangFragment);
                        break;
                    case R.id.nav_yuanzhuo://圆桌
                        break;
                    case R.id.nav_sixin://私信
                        break;
                }
                transaction.commit();
                toolbar.setTitle(item.getTitle());
                item.setChecked(true);//选择后，才变色
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }


    //如果打开菜单，按返回键，先退出菜单再退出程序
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("首页");//设置Toolbar标题
        toolbar.inflateMenu(R.menu.toolbar_menu_home);
    }

    private void initDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        //初始化
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        //显示Toggle
        mToggle.syncState();
        //设置左上角动画
        mDrawerLayout.addDrawerListener(mToggle);
    }

    private void addMenuListener() {
        //设置菜单监听
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });
    }

    private void initIndexFragment() {
        //Fragment
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //主页面内容的
        HomeFragment homeFragment = new HomeFragment();
        transaction.add(R.id.center_linearLayout, homeFragment);
        transaction.commit();

        //默认选中menu第一个 首页
        navigationView.getMenu().getItem(0).setChecked(true);

    }

    @Override
    public void getData(int _id) {
        //从HomeFragment传值给正文Activity
        ContentActivity._id = _id;
    }

}
