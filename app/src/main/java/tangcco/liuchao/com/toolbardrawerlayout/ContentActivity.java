package tangcco.liuchao.com.toolbardrawerlayout;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import tangcco.liuchao.com.toolbardrawerlayout.db.DBHelper;
import tangcco.liuchao.com.toolbardrawerlayout.entity.InfoCard;

public class ContentActivity extends AppCompatActivity {
    private Toolbar toolbarBack;
    public InfoCard infoCard;
    public static int _id;//首页传过来的ID
    private TextView tv_content_title, tv_content_count, tv_content_content;
    private ImageView iv_content_count;
    private boolean falgShoucang;
    private boolean falgCount;
    private ScrollView myScrollView;


    private DBHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        helper = new DBHelper(this);
        toolbarBack = (Toolbar) findViewById(R.id.toolbarBack);
        toolbarBack.setTitle("回答");
        setSupportActionBar(toolbarBack);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置返回键可用

        initView();//初始化
        addData();
        initContent();//布局填充
        initListener();

    }

    private void addData() {
        infoCard = helper.showPageContent(_id);
    }

    private void initListener() {

        toolbarBack.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.content_menu_shoucang:
                        if (falgShoucang) {
                            boolean b = helper.updateShoucang(infoCard.get_id(), 0);
                            if (b) {
                                item.setIcon(R.mipmap.ic_favorite_border_icon);
                                infoCard.setShoucang(0);
                                falgShoucang = false;
                            }
                        } else {
                            boolean b = helper.updateShoucang(infoCard.get_id(), 1);
                            if (b) {
                                item.setIcon(R.mipmap.ic_favorite_icon);
                                infoCard.setShoucang(1);
                                falgShoucang = true;
                                Snackbar.make(myScrollView, "已收藏", Snackbar.LENGTH_SHORT)
                                        .setAction("确定", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                            }
                                        }).show();

                            }
                        }
                        break;
                }
                return false;
            }
        });

        //点赞图片的监听
        iv_content_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zanListener();
            }
        });

        //点赞文字的监听
        tv_content_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zanListener();
            }
        });


    }

    private void zanListener() {
        if (falgCount == true) {
            //已经点赞
            //把数据库改成--
            boolean b = helper.updateCount(infoCard.get_id(), infoCard.getCount() - 1);
            if (b) {
                falgCount = false;
                iv_content_count.setImageResource(R.drawable.yizan);
                tv_content_count.setText(String.valueOf(infoCard.getCount()));
                return;
            }
        } else {
            boolean b = helper.updateCount(infoCard.get_id(), infoCard.getCount() + 1);
            if (b) {
                //没有点赞
                //把数据库++
                falgCount = true;
                iv_content_count.setImageResource(R.drawable.zan);
                tv_content_count.setText(String.valueOf(infoCard.getCount() + 1));
                return;
            }
        }
    }

    private void initView() {
        tv_content_count = (TextView) findViewById(R.id.tv_content_count);
        tv_content_title = (TextView) findViewById(R.id.tv_content_title);
        tv_content_content = (TextView) findViewById(R.id.tv_content_content);//点赞的文字
        iv_content_count = (ImageView) findViewById(R.id.iv_content_count);//点赞的图片
        myScrollView = (ScrollView) findViewById(R.id.myScrollView);
    }

    private void initContent() {
        tv_content_title.setText(infoCard.getTitle());//标题
        tv_content_count.setText(String.valueOf(infoCard.getCount()));//点赞
        tv_content_content.setText(infoCard.getContentAll());//正文
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_content, menu);

        //设置初始化收藏
        int shoucang = infoCard.getShoucang();//0是没有收藏，1是收藏

        if (shoucang == 0) {
            menu.getItem(0).setIcon(R.mipmap.ic_favorite_border_icon);
            falgShoucang = false;
        } else {
            menu.getItem(0).setIcon(R.mipmap.ic_favorite_icon);
            falgShoucang = true;
        }

        return super.onCreateOptionsMenu(menu);
    }



    /**
     * 左上角返回监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
