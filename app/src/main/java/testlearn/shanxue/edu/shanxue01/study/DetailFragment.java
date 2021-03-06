package testlearn.shanxue.edu.shanxue01.study;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import testlearn.shanxue.edu.shanxue01.R;
import testlearn.shanxue.edu.shanxue01.control.DataUtil;
import testlearn.shanxue.edu.shanxue01.control.FileUtil;
import testlearn.shanxue.edu.shanxue01.control.OnDataResponseListener;
import testlearn.shanxue.edu.shanxue01.models.StudyNodeModel;
import testlearn.shanxue.edu.shanxue01.models.UserLearnRecordModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DetailFragment extends Fragment implements Serializable, View.OnClickListener {
    private static final String TAG = "DetialFragment";

    private int surplus;
    private List<StudyNodeModel> studyNodeModelList;
    private List<UserLearnRecordModel> userLearnRecordModelList = new ArrayList<UserLearnRecordModel>();
    private String entryType;
    private OnDataResponseListener mListener;

    public DetailFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "DetailFragment onCreate");
        receiveData();
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_study_fragment, container, false);
        Log.i(TAG, "DetailFragment onCreatView");

        displayData(rootView);


//        rootView.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().popBackStack();
//            }
//        });
        rootView.findViewById(R.id.btnStopStudy).setOnClickListener(this);
        rootView.findViewById(R.id.btnNext).setOnClickListener(this);
        return rootView;
    }

    private void displayData(View rootView) {
        TextView tvDetialSurplusEntry = rootView.findViewById(R.id.tvDetialSurplusEntry);
        TextView tvDetialEntryNode = rootView.findViewById(R.id.tvDetialEntryNode);
        TextView tvRhesisTitle = rootView.findViewById(R.id.tvRhesisTitle);
        TextView tvRhesisTransiation = rootView.findViewById(R.id.tvRhesisTransiation);
        TextView tvRhesisComment = rootView.findViewById(R.id.tvRhesisComment);
        TextView tvRhesisAppreciate = rootView.findViewById(R.id.tvRhesisAppreciate);
        TextView tvRhesisAuthor = rootView.findViewById(R.id.tvRhesisAuthor);
        TextView tvRhesisDynasty = rootView.findViewById(R.id.tvRhesisDynasty);
        Button btnNext = rootView.findViewById(R.id.btnNext);

        String entryNodeStr = "当前节点: " + String.valueOf(studyNodeModelList.get(surplus).getStudy_node());
        tvDetialEntryNode.setText(entryNodeStr);
        String surplusStr = "剩余词条: " + String.valueOf(surplus);
        tvDetialSurplusEntry.setText(surplusStr);

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.llDetialText);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(850, LinearLayout.LayoutParams.WRAP_CONTENT);


        String[] textSplit = studyNodeModelList.get(surplus).getRhesis_text().split("((?<=。)|(?<=？)|(?<=！)|(?=\n)|(?=\r))");
        //remove null
        List<String> list = new ArrayList<String>();
        for (String string : textSplit) {
            if (string != null && string.length() > 0 && !string.contains("\\n")) {

                list.add(string);
            }
        }

        for (int i = 0; i < textSplit.length; i++) {
            Log.i(TAG, "去掉空值前，一句分别为: " + textSplit[i]);
        }

        textSplit = list.toArray(new String[list.size()]);

        for (int i = 0; i < textSplit.length; i++) {
            Log.i(TAG, "去掉空值后，一句分别为: " + textSplit[i]);
        }
        Resources resources = getContext().getResources();

        String sentence = studyNodeModelList.get(surplus).getRhesis_sentance();
        sentence = sentence.replace("\n", "").replace("\r", "");
        Log.i(TAG, "名句为: " + sentence);

        for (int i = 0; i < textSplit.length; i++) {
            TextView a = new TextView(getActivity());
            a.setTextSize(17);
            a.setGravity(Gravity.CENTER);
            a.setScaleX((float) 1.1);
            a.setText(textSplit[i] + "\n");
            a.setId(i);
            if (textSplit[i].contains(sentence)) {
                a.setTextColor(resources.getColorStateList(R.color.colorPink));
            } else {
                a.setTextColor(resources.getColorStateList(R.color.colorBlack));
            }
            linearLayout.addView(a);

        }

        Log.i(TAG, "rhesis Transiation: " + studyNodeModelList.get(surplus).getRhesis_transiation());
        Log.i(TAG, "rhesis text: " + studyNodeModelList.get(surplus).getRhesis_text());

        tvRhesisTitle.setText(studyNodeModelList.get(surplus).getRhesis_title());
        tvRhesisDynasty.setText(studyNodeModelList.get(surplus).getRhesis_dynasty());
        tvRhesisAuthor.setText(studyNodeModelList.get(surplus).getRhesis_author());
        tvRhesisTransiation.setText(studyNodeModelList.get(surplus).getRhesis_transiation());
        tvRhesisComment.setText(studyNodeModelList.get(surplus).getRhesis_comment());
        tvRhesisAppreciate.setText(studyNodeModelList.get(surplus).getRhesis_appreciate());
        btnNext.setText("下一个");

    }


    private void receiveData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            surplus = bundle.getInt("surplus");
            studyNodeModelList = (List<StudyNodeModel>) bundle.getSerializable("studyList");

            Log.i(TAG, "surplus is " + surplus);
            Log.i(TAG, "this rhesis title: " + studyNodeModelList.get(surplus).getRhesis_title());
        } else {
            Log.i(TAG, "surplus is null");
        }
    }

    @Override
    public void onStart() {
        Log.i(TAG, "DetailFragment onStart");
        super.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "DetailFragment onPause");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "DetailFragment onResume");

    }

    @Override
    public void onClick(View view) {
        //https://stackoverflow.com/questions/1504160/android-use-a-switch-statement-with-setonclicklistener-onclick-for-more-than-1
        switch (view.getId()) {
            case R.id.btnNext:
                continueStudy();
                break;
            case R.id.btnStopStudy:
                finishDetailFragment();
                break;
        }
    }

    private void finishDetailFragment() {
        //https://stackoverflow.com/questions/12659747/call-an-activity-method-from-a-fragment
        ((InStudy) getActivity()).finishStudy(surplus);
    }

    private void continueStudy() {
        Log.i(TAG, "点击了按钮--下一个");


        Log.i(TAG, "study_id check----------" + studyNodeModelList.get(surplus).getStudy_ID());

        CheckFragment checkFragment = new CheckFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putInt("surplus", --surplus);
        bundle.putSerializable("studyList", (ArrayList) studyNodeModelList);

        checkFragment.setArguments(bundle);
        Log.i(TAG, "DetialFragment: surplus is: " + surplus);

        if (surplus >= 0) {

            String fragmentname = "detail" + String.valueOf(surplus);
            fragmentTransaction.replace(R.id.in_study, checkFragment);
            fragmentTransaction.addToBackStack(fragmentname).commit();

        } else {

            Log.i(TAG, "从节点对象中提取学习记录...");

            for (int i = 0; i < studyNodeModelList.size(); i++) {
                UserLearnRecordModel userLearnRecordModel = new UserLearnRecordModel();

                userLearnRecordModel.setUserLearnRecord(studyNodeModelList.get(i));

                userLearnRecordModelList.add(userLearnRecordModel);
                Log.i(TAG, "study_id: " + userLearnRecordModelList.get(i).getStudy_ID());
                Log.i(TAG, "creatorDate: " + userLearnRecordModelList.get(i).getStudy_createDateTime());
                Log.i(TAG, "latestStudyTime: " + userLearnRecordModelList.get(i).getStudy_latestStudyTime());
                Log.i(TAG, "nextDateTime: " + userLearnRecordModelList.get(i).getStudy_nextDateTime());
                Log.i(TAG, "studyNode: " + userLearnRecordModelList.get(i).getStudy_node());
            }
            Log.i(TAG, "学习记录提取成功！");

            //back to startstudy.activity
            //https://stackoverflow.com/questions/4038479/android-go-back-to-previous-activity
            FileUtil.writeObj2File(userLearnRecordModelList, FileUtil.FILE_NAME_UPLOAD_LEARN_RECORD);
            Log.i(TAG, "上传用户学习记录...");


            FileUtil.writeObj2File(userLearnRecordModelList, FileUtil.FILE_NAME_USER_LEARN_RECORD);

            String json_LearnRecords = DataUtil.transLearnRecords2Json(userLearnRecordModelList);

            Log.i(TAG, "json_LearnRecords: " + json_LearnRecords);

            DataUtil.sendPost("learn_record", json_LearnRecords, FileUtil.URL_UPLOAD_LEARN_RECORD, getActivity());

            mListener.onResponse("rhesis");
        }
    }

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach");
        super.onAttach(context);
        try {
            mListener = (OnDataResponseListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnDataResponseListener");
        }
    }
}
