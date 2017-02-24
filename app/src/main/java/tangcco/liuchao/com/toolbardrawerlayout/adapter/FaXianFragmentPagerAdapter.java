package tangcco.liuchao.com.toolbardrawerlayout.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.util.List;

import tangcco.liuchao.com.toolbardrawerlayout.R;

/**
 * Created by sanji on 2016/12/17.
 */

public class FaXianFragmentPagerAdapter extends FragmentPagerAdapter {

    //发现
    private List<Fragment> fragmentList;
    private String[] titles;
    private Context context;
    private int[] imageResId = {
            R.drawable.ic_drawer_collect_normal,
            R.drawable.ic_drawer_draft_normal,
            R.drawable.ic_drawer_follow_normal
    };

    public FaXianFragmentPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        //标题
//        return titles[position];

        //标题+图片
        Drawable image = ContextCompat.getDrawable(context, imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString("   " + titles[position]);
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
