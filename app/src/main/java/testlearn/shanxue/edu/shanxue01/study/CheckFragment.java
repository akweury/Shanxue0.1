package testlearn.shanxue.edu.shanxue01.study;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import testlearn.shanxue.edu.shanxue01.R;
import testlearn.shanxue.edu.shanxue01.models.StudyNodeModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CheckFragment extends Fragment implements Serializable, View.OnClickListener {

    private static final String TAG = "CheckFragment";



    private TextView textFlag;
    private TextView tvCheckEntryNode;
    private TextView tvCheckSurplus;

    private LinearLayout linearLayoutParent;
    private List<EditText> allEdsList = new ArrayList<EditText>();
    private List<String> ansList = new ArrayList<String>();  //答案
    private String[] rhesisSplit; //名句


    private int surplus = 0;
    private int node = 0;
    private List<StudyNodeModel> studyNodeModelList;

    private Boolean isFirstWrong = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "CheckFragment: onCreate");
        receiveData();

    }




    private void receiveData() {
        Log.i(TAG,"receiveData");
        Bundle bundle = getArguments();
        if (bundle != null) {

            if (bundle.getInt("surplus") != 0) {
                surplus = bundle.getInt("surplus");
                Log.i(TAG, "(Check) surplus is " + surplus);
            }
            if ((bundle.getSerializable("studyNodeList"))!=null){
                studyNodeModelList = (List<StudyNodeModel>)bundle.getSerializable("studyNodeList");
                Log.i(TAG, studyNodeModelList.get(0).getStudy_item_ID());
            }
        } else {
            Log.i(TAG, "(Check)surplus is null");
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState)  {


        View rootView = inflater.inflate(R.layout.fragment_check, container, false);

        Log.i(TAG, "CheckFragment onCreatView");

        splitRhesis();

        arrangeSentenceView(rootView);

        tvCheckEntryNode = rootView.findViewById(R.id.tvCheckEntryNode);
        tvCheckSurplus = rootView.findViewById(R.id.tvCheckSurplus);

        String nodeStr = "复习节点: " + String.valueOf(studyNodeModelList.get(surplus).getStudy_node());
        String surplusStr = "剩余词条: " + String.valueOf(surplus);

        tvCheckEntryNode.setText(nodeStr);
        tvCheckSurplus.setText(surplusStr);

        rootView.findViewById(R.id.btnSendValue).setOnClickListener(this);
        return rootView;
    }

    private void splitRhesis() {
        rhesisSplit = studyNodeModelList.get(surplus).getRhesis_sentance().split("，|。|？|\n");
        List<String> list = new ArrayList<String>();
        for (String string : rhesisSplit) {
            if (string != null && string.length() > 0) {
                list.add(string);
            }
        }
        rhesisSplit = list.toArray(new String[list.size()]);

        for (int i = 0; i < rhesisSplit.length; i++) {
            Log.i(TAG, "去掉空值后，背诵名句为: " + rhesisSplit[i]);
        }
    }


    private void arrangeSentenceView(View rootView) {

        linearLayoutParent = (LinearLayout) rootView.findViewById(R.id.llCheck);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(800, LinearLayout.LayoutParams.WRAP_CONTENT);

        Log.i(TAG, "onCreateView: rhesisSplit lenth: " + rhesisSplit.length);


        int numOfBlank;
        Random random = new Random(rhesisSplit.length);
        //TODO:only one line's situation
        Boolean boolQuesArray[] = new Boolean[rhesisSplit.length];
        for (int i=0;i<boolQuesArray.length;i++){
            boolQuesArray[i] = false;
        }
        if(rhesisSplit.length==2){
            numOfBlank = 1;
            int randomInt = random.nextInt(numOfBlank + 1);
            Log.i(TAG,"randomInt: " + randomInt);
            boolQuesArray[randomInt] = true;

        }else if(rhesisSplit.length > 2){

            //determine number of blankline
            for (int i = 0;i<10;i++){
                numOfBlank = random.nextInt(rhesisSplit.length - 1) + 1;
                Log.i(TAG,"numOfBlank is: " + numOfBlank);

            }
            numOfBlank = random.nextInt(rhesisSplit.length - 1) + 1;

            //detinmine which line(s) is(are) blank
            int randomInt2;
            for (int i=0;i<numOfBlank;i++){
                do {
                    randomInt2 = random.nextInt(numOfBlank + 1);
                    Log.i(TAG,"ramdomInt2 is : " + randomInt2);

                }while (boolQuesArray[randomInt2]);
                boolQuesArray[randomInt2] = true;
                for(int j = 0;j<boolQuesArray.length;j++){
                    Log.i(TAG,"boolQues: " + j + " : " + boolQuesArray[j]);
                }
            }

        }

        for (int i=0;i<boolQuesArray.length;i++){
            Log.i(TAG,"boolquesarray: " + i + " : " + boolQuesArray[i]);
        }

        //add Textview for question sentence
        for (int i = 0; i < rhesisSplit.length; i++) {
            if (boolQuesArray[i].equals(true)) {
                EditText e = new EditText(getActivity());
                e.setTextSize(30);
                e.setLayoutParams(lp);
                e.setId(i);
                e.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                e.setSingleLine(true);
                e.requestFocus();
                allEdsList.add(e);
                ansList.add(rhesisSplit[i]);
                linearLayoutParent.addView(e);

            } else {
                TextView a = new TextView(getActivity());
                a.setTextColor(R.color.colorPink);
                a.setTextSize(30);
                a.setGravity(Gravity.CENTER);
                a.setLayoutParams(lp);
                a.setText(rhesisSplit[i]);
                a.setId(i);
                linearLayoutParent.addView(a);
            }

        }
        Log.i(TAG,"一共有" + allEdsList.size() + " 条横线 ");

        for (int i=0;i<ansList.size();i++){
            Log.i(TAG,"答案" + i + " : " + ansList.get(i));
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "CheckFragment onStart");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "CheckFragment onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "CheckFragment onStop");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "CheckFragment onResume");
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "点击了按钮---提示");


        List<Boolean> ansFlagList = new ArrayList<Boolean>();  //标记是否为填空行的数组
        ansFlagList = checkAnsByLines(ansFlagList);

        if (!(ansFlagList.contains(false))) {
            //传递参数 剩余词条数目

            studyNodeModelList.get(surplus).plus();

            Bundle bundle = new Bundle();
            bundle.putInt("surplus", surplus);
            bundle.putSerializable("studyNodeList", (ArrayList) studyNodeModelList);
            Log.i(TAG, "surplus is: " + surplus);
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(bundle);
            String fragName = "checkFragment" + String.valueOf(surplus);
            getFragmentManager().beginTransaction()
                    .addToBackStack(fragName)
                    .replace(R.id.in_study, detailFragment)
                    .commit();
        }
        else {
            if(isFirstWrong){
                studyNodeModelList.get(surplus).minus();
                isFirstWrong = false;
            }
            Log.i(TAG,"答案有误。");
        }

    }

    private List<Boolean> checkAnsByLines(List<Boolean> ansFlagList) {
        for (int i = 0; i < allEdsList.size(); i++) {

            Log.i(TAG,"i 为：" + i);
            Log.i(TAG,"提交内容为：" + allEdsList.get(i).getText().toString());
            Log.i(TAG,"答案为: " + ansList.get(i));


            if (!(allEdsList.get(i).getText().toString()).equals(ansList.get(i))) {

                allEdsList.get(i).getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                allEdsList.get(i).setTextColor(R.color.colorOrange);
//                textFlag.setText("第" + (i+1) + "条答案有误");
                Log.i(TAG,"第 " + i + " 条答案有误");
                ansFlagList.add(false);
            } else {
                Log.i(TAG,"第 " + i + " 条答案正确");
                ansFlagList.add(true);
            }
        }

        return ansFlagList;
    }


    public boolean allowBackPressed() {
        return true;
    }
}
