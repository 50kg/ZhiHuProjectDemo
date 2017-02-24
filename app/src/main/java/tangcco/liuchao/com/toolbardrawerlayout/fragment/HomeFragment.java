package tangcco.liuchao.com.toolbardrawerlayout.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import tangcco.liuchao.com.toolbardrawerlayout.ContentActivity;
import tangcco.liuchao.com.toolbardrawerlayout.R;
import tangcco.liuchao.com.toolbardrawerlayout.adapter.HomeListviewAdapter;
import tangcco.liuchao.com.toolbardrawerlayout.db.DBHelper;
import tangcco.liuchao.com.toolbardrawerlayout.entity.InfoCard;
import tangcco.liuchao.com.toolbardrawerlayout.interfaces.ISetDataListener;
import tangcco.liuchao.com.toolbardrawerlayout.interfaces.IXRecyclerViewListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private XRecyclerView xRecyclerView;
    private List<InfoCard> list;
    private HomeListviewAdapter adapter;
    private Handler mHandler;

    private FloatingActionButton home_floating;

    private ISetDataListener dataListener;
    private int page = 1;//当前页
    private DBHelper helper;
    private static final String TAG = "HomeFragment";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dataListener = (ISetDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        xRecyclerView = (XRecyclerView) view.findViewById(R.id.xRecyclerView);
        home_floating = (FloatingActionButton) view.findViewById(R.id.home_floating);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        xRecyclerView.setLayoutManager(manager);

        list = new ArrayList<>();
        mHandler = new Handler();
        helper = new DBHelper(getContext());//数据库方法类

        addData();
        initxRecyclerView();
        addListener();
        return view;
    }

    private void initxRecyclerView() {
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.SquareSpin);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        xRecyclerView.setArrowImageView(R.drawable.cherry_pic_xiala);

    }

    private void addData() {
        list.addAll(helper.showPage(page));
        adapter = new HomeListviewAdapter(list);
        xRecyclerView.setAdapter(adapter);
    }

//        1、数据源没有更新，调用notifyDataSetChanged无效。
//        2、数据源更新了，但是它指向新的引用，调用notifyDataSetChanged无效。
//        3、数据源更新了，但是adpter没有收到消息通知，无法动态更新列表。

    private void addListener() {

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            //下拉监听
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //耗时两秒做什么
                        //清空数据
                        list.clear();
                        page = 1;//刷新后page重新为1
                        addData();
                        Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                        xRecyclerView.refreshComplete();
                    }
                }, 2000);
            }

            //上拉加载
            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++; //翻页
                        list.addAll(helper.showPage(page));//下拉加载必须是addAll 要保持同一个对象
                        adapter.notifyDataSetChanged();
                        xRecyclerView.loadMoreComplete();
                    }
                }, 2000);
            }
        });

        //单击事件
        adapter.setmOnItemClickListener(new IXRecyclerViewListener() {
            @Override
            public void onItemClick(HomeListviewAdapter.ViewHolder view, int position) {
                InfoCard card = list.get(position - 1);
                dataListener.getData(card.get_id());
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                startActivity(intent);
            }
        });

        home_floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "提问", Snackbar.LENGTH_SHORT)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
            }
        });

    }


}
