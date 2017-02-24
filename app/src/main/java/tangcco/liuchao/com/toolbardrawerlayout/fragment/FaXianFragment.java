package tangcco.liuchao.com.toolbardrawerlayout.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import tangcco.liuchao.com.toolbardrawerlayout.R;
import tangcco.liuchao.com.toolbardrawerlayout.adapter.FaXianFragmentPagerAdapter;
import tangcco.liuchao.com.toolbardrawerlayout.fragment.faxian.FaxianHotFragment;
import tangcco.liuchao.com.toolbardrawerlayout.fragment.faxian.FaxianShoucangFragment;
import tangcco.liuchao.com.toolbardrawerlayout.fragment.faxian.FaxianTuijianFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FaXianFragment extends Fragment {


    public FaXianFragment() {
        // Required empty public constructor
    }

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String[] titles = {"最热", "推荐", "收藏"};
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fa_xian, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);


        fragmentList.add(new FaxianHotFragment());
        fragmentList.add(new FaxianTuijianFragment());
        fragmentList.add(new FaxianShoucangFragment());


        FaXianFragmentPagerAdapter adapter = new FaXianFragmentPagerAdapter(getFragmentManager(), getContext(), fragmentList, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        return view;
    }


}
