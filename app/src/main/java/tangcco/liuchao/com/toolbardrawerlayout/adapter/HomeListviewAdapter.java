package tangcco.liuchao.com.toolbardrawerlayout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tangcco.liuchao.com.toolbardrawerlayout.R;
import tangcco.liuchao.com.toolbardrawerlayout.entity.InfoCard;
import tangcco.liuchao.com.toolbardrawerlayout.interfaces.ISetDataListener;
import tangcco.liuchao.com.toolbardrawerlayout.interfaces.IXRecyclerViewListener;
import tangcco.liuchao.com.toolbardrawerlayout.view.CircleImageView;

/**
 * Created by sanji on 2016/12/10.
 */

public class HomeListviewAdapter extends RecyclerView.Adapter<HomeListviewAdapter.ViewHolder> {

    private List<InfoCard> list;

    public HomeListviewAdapter(List<InfoCard> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        final HomeListviewAdapter.ViewHolder viewHolder = new HomeListviewAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getLayoutPosition();
                mOnItemClickListener.onItemClick(viewHolder, position);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cator.setText(list.get(position).getCator());
        holder.title.setText(list.get(position).getTitle());
        holder.titleContent.setText(list.get(position).getTitleContent());
        holder.count.setText(String.valueOf(list.get(position).getCount()));
        holder.picture.setImageResource(list.get(position).getPicture());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cator, title, titleContent, count;
        CircleImageView picture;

        public ViewHolder(View itemView) {
            super(itemView);
            this.cator = (TextView) itemView.findViewById(R.id.item_cator);
            this.title = (TextView) itemView.findViewById(R.id.item_title);
            this.titleContent = (TextView) itemView.findViewById(R.id.item_content);
            this.count = (TextView) itemView.findViewById(R.id.item_count);
            this.picture = (CircleImageView) itemView.findViewById(R.id.item_image);
        }
    }


    //点击接口回调
    private IXRecyclerViewListener mOnItemClickListener;

    public void setmOnItemClickListener(IXRecyclerViewListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
