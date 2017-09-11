package testlearn.shanxue.edu.shanxue01.chart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import testlearn.shanxue.edu.shanxue01.*;


public class ChartFragment extends Fragment {

    private static final String TAG = "ChartFragment";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    private Button btnTEST;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart,container,false);
        Log.i(TAG,"onCreatView");
        ;
        mSectionsPageAdapter = new SectionsPageAdapter(getFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.chartContainer);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_timeline).setText("EFC");
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_timelapse).setText("Learneing");
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_thumb_up).setText("Progress");




        btnTEST = (Button) view.findViewById(R.id.btnTest);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON ChartFragment",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");

    }


    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getFragmentManager());
        adapter.addFragment(new Tab1Fragment());
        adapter.addFragment(new Tab2Fragment());
        adapter.addFragment(new Tab3Fragment());
        viewPager.setAdapter(adapter);

    }
}
