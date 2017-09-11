package testlearn.shanxue.edu.shanxue01;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import testlearn.shanxue.edu.shanxue01.book.BookFragment;
import testlearn.shanxue.edu.shanxue01.chart.ChartFragment;
import testlearn.shanxue.edu.shanxue01.create.CreateFragment;
import testlearn.shanxue.edu.shanxue01.me.MeFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private boolean isStart = false;
    private boolean isChart = false;
    private boolean isBook = false;
    private boolean isCreate = false;
    private boolean isMe = false;

    MenuItem prevMenuItem;
    private ViewPager viewPagerBottom;
    private StartFragment startFragment;
    private ChartFragment chartFragment;
    private BookFragment bookFragment;
    private CreateFragment createFragment;
    private MeFragment meFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPagerBottom = (ViewPager)findViewById(R.id.viewPager);

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        //        http://droidmentor.com/bottomnavigationview-with-viewpager-android
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        viewPagerBottom.setCurrentItem(0);
//                        if(isStart){
//                            getSupportFragmentManager().findFragmentByTag("start");
//                            Log.i(TAG,"find--Start--FragmentByTag!");
//                        }else {
//                            StartFragment startFragment = new StartFragment();
//                            //https://stackoverflow.com/questions/17154341/can-fragment-be-created-with-only-one-instance
//                            getSupportFragmentManager().beginTransaction()
//                                    .addToBackStack(null)
//                                    .replace(R.id.chartContainers, startFragment, "start")
//                                    .commit();
//                            Log.i(TAG,"new a StartFragment");
//                            isStart = true;
//                        }
                        break;
                    case R.id.ic_multiline_chart:
                        viewPagerBottom.setCurrentItem(1);
                        break;

                    case R.id.ic_books:
                        viewPagerBottom.setCurrentItem(2);
                        break;

                    case R.id.ic_create:
                        viewPagerBottom.setCurrentItem(3);
                        break;

                    case R.id.ic_person:
                        viewPagerBottom.setCurrentItem(4);
                        break;

                }



                return false;
            }
        });

        viewPagerBottom.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(prevMenuItem != null){
                    prevMenuItem.setChecked(false);
                }else{
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.i("page","onPageSelected" + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPagerBottom(viewPagerBottom);


    }

    private void setupViewPagerBottom(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        startFragment = new StartFragment();
        chartFragment = new ChartFragment();
        bookFragment = new BookFragment();
        createFragment = new CreateFragment();
        meFragment = new MeFragment();
        adapter.addFragment(startFragment);
        adapter.addFragment(chartFragment);
        adapter.addFragment(bookFragment);
        adapter.addFragment(createFragment);
        adapter.addFragment(meFragment);
        viewPager.setAdapter(adapter);
    }
}
