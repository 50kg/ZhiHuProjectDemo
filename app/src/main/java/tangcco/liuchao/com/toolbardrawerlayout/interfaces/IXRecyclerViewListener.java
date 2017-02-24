package tangcco.liuchao.com.toolbardrawerlayout.interfaces;

import android.widget.Toast;

import tangcco.liuchao.com.toolbardrawerlayout.adapter.HomeListviewAdapter;

/**
 * Created by sanji on 2017/1/23.
 */

public interface IXRecyclerViewListener {
    //点击事件
    void onItemClick(HomeListviewAdapter.ViewHolder view, int position);

}
