package testlearn.shanxue.edu.shanxue01.study;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import testlearn.shanxue.edu.shanxue01.R;
import testlearn.shanxue.edu.shanxue01.control.OnDataResponseListener;
import testlearn.shanxue.edu.shanxue01.models.MomoModel;
import testlearn.shanxue.edu.shanxue01.models.StudyNodeModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InStudy extends AppCompatActivity implements Serializable, OnDataResponseListener {
    private static final String TAG = "InStudy.activity";
    private final static String TAG_FRAGMENT_DETIAL = "TAG_FRAGMENT_DETIAL";
    private final static String TAG_FRAGMENT_CHECK = "TAG_FRAGMENT_CHECK";
    private boolean isCheck;
    private int nextOrder = 0;
    private String[] order;
    private int rhesisSurplus = 0; //剩余rhesis词条数量
    private int momoSurplus = 0; //剩余momo词条数量

    private String typeOrder;
    private UserInfoModle userInfoModle = new UserInfoModle();
    private List<StudyNodeModel> studyNodeModelList = new ArrayList<>();
    private List<MomoModel> momoModelList = new ArrayList<>();
    public static boolean beginType = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreat");
        setContentView(R.layout.activity_in_study);

        receiveData();

        order = typeOrder.split(",");


        while (!showNextFragment(order[nextOrder])) ;


    }

    private boolean showNextFragment(String nextOrder) {
        Log.i(TAG, "order is: " + nextOrder);
        switch (nextOrder) {
            case "momo":
                Log.i(TAG, "momo part-----------------------------");
                return show("momo");
            case "rhesis":
                Log.i(TAG, "rhesis part-----------------------------");
                return show("rhesis");
            default:
                finish();
                break;
        }
        return true;

    }

    private void receiveData() {
        Log.i(TAG, "获取到信息，开始拆包...................................");

        Bundle bundleGet = this.getIntent().getExtras();
        userInfoModle = (UserInfoModle) bundleGet.getSerializable("userinfo");
        studyNodeModelList = (ArrayList) bundleGet.getSerializable("studyNodeList");
        momoModelList = (ArrayList) bundleGet.getSerializable("momoList");


        Log.i(TAG,"momoNode nodeisssssss : " + momoModelList.get(0).getStudy_node());


        rhesisSurplus = bundleGet.getInt("rhesisSurplus");
        momoSurplus = bundleGet.getInt("momoSurplus");
        typeOrder = bundleGet.getString("typeOrder");
        if (userInfoModle != null) {
            Log.i(TAG, "userinfo nickname: " + userInfoModle.getNickName());
            Log.i(TAG, "momo node list size: " + momoModelList.size());
            Log.i(TAG, "study node list size: " + studyNodeModelList.size());
            Log.i(TAG, "rhesis surplus: " + rhesisSurplus);
            Log.i(TAG, "momo surplus: " + momoSurplus);
            Log.i(TAG, "type order: " + typeOrder);
        }


    }

    private boolean show(String entryType) {
        switch (entryType) {
            case "momo":
                if (!showEntry(momoSurplus, "momo")) {
                    return false;
                }
                break;
            case "rhesis":
                if (!showEntry(rhesisSurplus, "rhesis")) {
                    return false;
                }
                break;
        }
        return true;

    }

    private boolean showEntry(int surplus, String nodeType) {
        Log.i(TAG, "(showEntry) surplus: " + surplus);
        if (surplus == 0) {
            if (nextOrder < order.length - 1){
                nextOrder++;
            }else {
                Toast.makeText(this,"no entry to study",Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;
        } else {
            switch (nodeType) {
                case "momo":
                    Log.i(TAG, "showMomo");
                    isCheck = true;
                    momoSurplus--;//minus one before check
                    putInBundle(momoSurplus, (ArrayList) momoModelList);
                    break;
                case "rhesis":
                    Log.i(TAG, "showRhesis");
                    isCheck = true;
                    rhesisSurplus--;//minus one before check

                    putInBundle(rhesisSurplus, (ArrayList) studyNodeModelList);
                    break;
                default:
                    break;
            }
            return true;
        }

    }

    private void putInBundle(int surplus, ArrayList studyList) {
        Bundle bundle = new Bundle();
        bundle.putInt("surplus", surplus);
        bundle.putSerializable("studyList", studyList);
        showFragment(bundle, isCheck);

    }

    private void showFragment(Bundle bundle, Boolean isCheck) {

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isCheck) {
            final CheckFragment checkFragment = new CheckFragment();
            checkFragment.setArguments(bundle);
            transaction.replace(R.id.in_study, checkFragment, TAG_FRAGMENT_CHECK);
        } else {
            final DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(bundle);
            transaction.replace(R.id.in_study, detailFragment, TAG_FRAGMENT_DETIAL);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
//        final CheckFragment checkFragment = (CheckFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_CHECK);
//        final DetailFragment detailFragment = (DetailFragment)getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_DETIAL);
//        if (checkFragment.allowBackPressed()) { // and then you define a method allowBackPressed with the logic to allow back pressed or not

        finishStudy(rhesisSurplus);
//        }
    }

    void finishStudy(int rhesisSurplus) {
        //TODO:write study data to a file
        finish();
    }

    @Override
    public void onResponse(String data) {
        if (!showNextFragment(order[++nextOrder])) {
            finish();
        }
    }
}
