package testlearn.shanxue.edu.shanxue01.study;

import android.content.Context;
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
import testlearn.shanxue.edu.shanxue01.control.OnDataResponseListener;
import testlearn.shanxue.edu.shanxue01.models.MomoModel;
import testlearn.shanxue.edu.shanxue01.models.StudyNodeModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CheckFragment extends Fragment implements Serializable, View.OnClickListener {

    private static final String TAG = "CheckFragment";


    private int node = 0;

    private LinearLayout linearLayoutParent;
    private List<EditText> allEdsList = new ArrayList<EditText>();
    private List<String> ansList = new ArrayList<String>();  //答案
    private Boolean isFirstWrong = true;

    private int surplus = 0;
    private String entrytype;
    private ArrayList studyNodeList;
    private List<MomoModel> momoModelList;
    private List<StudyNodeModel> studyNodeModelList;

    private OnDataResponseListener mListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "CheckFragment: onCreate");
        receiveData();

    }


    private void receiveData() {
        Log.i(TAG, "receiveData");
        Bundle bundle = getArguments();
        if (bundle != null) {
            surplus = bundle.getInt("surplus");
            studyNodeList = (ArrayList) bundle.getSerializable("studyList");
            Log.i(TAG, "(ReceiveData) Surplus is " + surplus);
        } else {
            Log.i(TAG, "(Check)surplus is null");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_check, container, false);
        Log.i(TAG, "CheckFragment onCreatView");

        showEntry(rootView);
        drawParameters(rootView);

        rootView.findViewById(R.id.btnSendValue).setOnClickListener(this);
        return rootView;
    }

    private void showEntry(View rootView) {
        Log.i(TAG, "checkType");
        linearLayoutParent = rootView.findViewById(R.id.llCheck);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(800, LinearLayout.LayoutParams.WRAP_CONTENT);

        if (studyNodeList.get(0) instanceof MomoModel) {
            entrytype = "momo";
            momoModelList = (List<MomoModel>) studyNodeList;
            showMomo(lp);
        }
        if (studyNodeList.get(0) instanceof StudyNodeModel) {
            entrytype = "rhesis";
            studyNodeModelList = (List<StudyNodeModel>) studyNodeList;
            showRhesis(lp);
        }
    }

    private String[] splitRhesis(List<StudyNodeModel> studyNodeModelList) {

        String rhesisSplit[];
        rhesisSplit = studyNodeModelList.get(surplus).getRhesis_sentance().split("，|。|？|\n");
        List<String> list = new ArrayList<>();


        for (String string : rhesisSplit) {
            if (string != null && string.length() > 0 && !string.contains("\\n")) {
                list.add(string);
            }
        }

        for (String aRhesisSplit : rhesisSplit) {
            Log.i(TAG, "去掉空值前，背诵名句为: " + aRhesisSplit);
        }

        rhesisSplit = list.toArray(new String[list.size()]);

        for (String aRhesisSplit : rhesisSplit) {
            Log.i(TAG, "去掉空值后，背诵名句为: " + aRhesisSplit);
        }
        return rhesisSplit;
    }

    private void showRhesis(LinearLayout.LayoutParams lp) {
        Log.i(TAG, "showRhesis");

        String[] rhesisSplit;
        rhesisSplit = splitRhesis(studyNodeModelList);

        int numOfBlank;

        Random random = new Random(rhesisSplit.length);
        //TODO:only one line's situation
        Boolean boolQuesArray[] = new Boolean[rhesisSplit.length];
        for (int i = 0; i < boolQuesArray.length; i++) {
            boolQuesArray[i] = false;
        }
        if (rhesisSplit.length == 2) {
            numOfBlank = 1;
            int randomInt = random.nextInt(numOfBlank + 1);
            Log.i(TAG, "randomInt: " + randomInt);
            boolQuesArray[randomInt] = true;

        } else if (rhesisSplit.length > 2) {

            //determine number of blankline
            for (int i = 0; i < 10; i++) {
                numOfBlank = random.nextInt(rhesisSplit.length - 1) + 1;
                Log.i(TAG, "numOfBlank is: " + numOfBlank);

            }
            numOfBlank = random.nextInt(rhesisSplit.length - 1) + 1;

            //detinmine which line(s) is(are) blank
            int randomInt2;
            for (int i = 0; i < numOfBlank; i++) {
                do {
                    randomInt2 = random.nextInt(numOfBlank + 1);
                    Log.i(TAG, "ramdomInt2 is : " + randomInt2);

                } while (boolQuesArray[randomInt2]);
                boolQuesArray[randomInt2] = true;
                for (int j = 0; j < boolQuesArray.length; j++) {
                    Log.i(TAG, "boolQues: " + j + " : " + boolQuesArray[j]);
                }
            }

        }

        for (int i = 0; i < boolQuesArray.length; i++) {
            Log.i(TAG, "boolquesarray: " + i + " : " + boolQuesArray[i]);
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
                e.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
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

        node = studyNodeModelList.get(surplus).getStudy_node();

        Log.i(TAG, "一共有" + allEdsList.size() + " 条横线 ");
        for (int i = 0; i < ansList.size(); i++) {
            Log.i(TAG, "答案" + i + " : " + ansList.get(i));
        }
    }

    private void showMomo(LinearLayout.LayoutParams lp) {
        Log.i(TAG, "showMomo");

        EditText e = new EditText(getActivity());
        e.setTextSize(30);
        e.setLayoutParams(lp);
        e.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        e.setSingleLine(true);
        e.requestFocus();
        allEdsList.add(e);
        ansList.add(momoModelList.get(surplus).getMomo_text());
        linearLayoutParent.addView(e);

        TextView a = new TextView(getActivity());
        a.setTextColor(R.color.colorPink);
        a.setTextSize(30);
        a.setGravity(Gravity.CENTER);
        a.setLayoutParams(lp);
        Log.i(TAG,"momoHint: " + momoModelList.get(surplus).getMomo_hintMain());
        a.setText(momoModelList.get(surplus).getMomo_hintMain());
        linearLayoutParent.addView(a);

        node = momoModelList.get(surplus).getStudy_node();

        Log.i(TAG, "一共有" + allEdsList.size() + " 条横线 ");

        for (int i = 0; i < ansList.size(); i++) {
            Log.i(TAG, "答案" + i + " : " + ansList.get(i));
        }

    }


    private void drawParameters(View rootView) {

        TextView tvCheckEntryNode = rootView.findViewById(R.id.tvCheckEntryNode);
        TextView tvCheckSurplus = rootView.findViewById(R.id.tvCheckSurplus);

        String nodeStr = "复习节点: " + node;
        String surplusStr = "剩余词条: " + surplus;

        tvCheckEntryNode.setText(nodeStr);
        tvCheckSurplus.setText(surplusStr);

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
        Log.i(TAG, "onClick");
        if (checkAns()) {
            putInBundle();
        }
    }

    private boolean checkAns() {

        for (int i = 0; i < allEdsList.size(); i++) {
            Log.i(TAG, "allEdsList " + i + ": " + allEdsList.get(i).getText().toString());
        }

        List<Boolean> ansFlagList = new ArrayList<>();  //标记是否为填空行的数组
        ansFlagList = checkAnsByLines(ansFlagList);

        if (!(ansFlagList.contains(false))) {
            //传递参数 剩余词条数目

            if (entrytype.equals("rhesis")) {
                studyNodeModelList.get(surplus).plus();
            }
            if (entrytype.equals("momo")) {
                momoModelList.get(surplus).plus();
            }
            return true;

        } else {
            if (isFirstWrong) {
                if (entrytype.equals("rhesis")) {
                    studyNodeModelList.get(surplus).minus();
                }
                if (entrytype.equals("momo")) {
                    momoModelList.get(surplus).minus();
                }
                isFirstWrong = false;
            }
            Log.i(TAG, "答案有误。");
            return false;
        }
    }

    private List<Boolean> checkAnsByLines(List<Boolean> ansFlagList) {
        for (int i = 0; i < allEdsList.size(); i++) {

            Log.i(TAG, "i 为：" + i);
            Log.i(TAG, "提交内容为：" + allEdsList.get(i).getText().toString());
            Log.i(TAG, "答案为: " + ansList.get(i));
            if (!(allEdsList.get(i).getText().toString().trim()).equals(ansList.get(i))) {
                allEdsList.get(i).getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                allEdsList.get(i).setTextColor(R.color.colorOrange);
//                textFlag.setText("第" + (i+1) + "条答案有误");
                Log.i(TAG, "第 " + i + " 条答案有误");
                ansFlagList.add(false);
            } else {
                Log.i(TAG, "第 " + i + " 条答案正确");
                ansFlagList.add(true);
            }
        }
        return ansFlagList;
    }

    private void putInBundle() {

        Bundle bundle = new Bundle();

        if (entrytype.equals("momo")) {
            if (surplus == 0) {
                mListener.onResponse("s");
            } else {
                surplus--;
                bundle.putInt("surplus", surplus); //put surplus entries number
                bundle.putSerializable("studyList", (ArrayList) momoModelList); // put surplus entries
                Log.i(TAG, "momoSurplus is: " + surplus);
                showFragment(bundle);
            }
        }
        if (entrytype.equals("rhesis")) {

            bundle.putInt("surplus", surplus); //put surplus entries number
            bundle.putSerializable("studyList", (ArrayList) studyNodeModelList); // put surplus entries
            Log.i(TAG, "rhesisSurplus is: " + surplus);
            showFragment(bundle);
        }

    }


    @Override
    public void onAttach(Context context) {
        Log.i(TAG,"onAttach");
        super.onAttach(context);
        try {
            mListener = (OnDataResponseListener)context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement OnDataResponseListener");
        }
    }


    private void showFragment(Bundle bundle) {

        if (entrytype.equals("momo")) {
            CheckFragment checkFragment = new CheckFragment();
            checkFragment.setArguments(bundle);
            String fragName = "checkFragment" + String.valueOf(surplus);
            getFragmentManager().beginTransaction()
                    .addToBackStack(fragName)
                    .replace(R.id.in_study, checkFragment)
                    .commit();

        }
        if (entrytype.equals("rhesis")) {
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(bundle);
            String fragName = "checkFragment" + String.valueOf(surplus);
            getFragmentManager().beginTransaction()
                    .addToBackStack(fragName)
                    .replace(R.id.in_study,detailFragment)
                    .commit();
        }
    }


    public boolean allowBackPressed() {
        return true;
    }
}
