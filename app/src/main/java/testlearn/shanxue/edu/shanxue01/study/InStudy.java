package testlearn.shanxue.edu.shanxue01.study;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import testlearn.shanxue.edu.shanxue01.R;
import testlearn.shanxue.edu.shanxue01.models.StudyNodeModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;
import testlearn.shanxue.edu.shanxue01.test.StudyTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InStudy extends AppCompatActivity implements Serializable {
    private static final String TAG = "InStudy.activity";
    private final static String TAG_FRAGMENT_DETIAL = "TAG_FRAGMENT_DETIAL";
    private final static String TAG_FRAGMENT_CHECK = "TAG_FRAGMENT_CHECK";
    private boolean isCheck;
    private int surplus=5; //剩余学习词条数量

    private UserInfoModle userInfoModle;
    private List<StudyNodeModel> studyNodeModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreat");
        setContentView(R.layout.activity_in_study);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Log.i(TAG,"获取到信息，开始拆包...................................");

        Bundle bundleGet = this.getIntent().getExtras();

        userInfoModle = (UserInfoModle) bundleGet.getSerializable("userinfo");
        studyNodeModelList = (ArrayList)bundleGet.getSerializable("studyNodeList");

        surplus = bundleGet.getInt("surplus");

        StudyTest.testModelList(userInfoModle,studyNodeModelList,surplus);

        Log.i(TAG,"信息获取完毕！");

        isCheck = true;
        Bundle bundle = new Bundle();
        bundle.putInt("surplus",--surplus);//minus one before check
        bundle.putSerializable("studyNodeList",(ArrayList)studyNodeModelList);

        showFragment(bundle,isCheck);

    }


    private void showFragment(Bundle bundle,Boolean isCheck) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isCheck==true){
            final CheckFragment checkFragment = new CheckFragment();
            checkFragment.setArguments(bundle);
            transaction.replace(R.id.in_study, checkFragment, TAG_FRAGMENT_CHECK);
        }else {
            final DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(bundle);
            transaction.replace(R.id.in_study, detailFragment,TAG_FRAGMENT_DETIAL);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
//        final CheckFragment checkFragment = (CheckFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_CHECK);
//        final DetailFragment detailFragment = (DetailFragment)getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_DETIAL);
//        if (checkFragment.allowBackPressed()) { // and then you define a method allowBackPressed with the logic to allow back pressed or not

            finishStudy(surplus);
//        }
    }

    public void finishStudy(int surplus){
        //TODO:write study data to a file
        finish();
    }
}
