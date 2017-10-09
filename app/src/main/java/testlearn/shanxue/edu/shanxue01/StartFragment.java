package testlearn.shanxue.edu.shanxue01;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;
import testlearn.shanxue.edu.shanxue01.control.*;
import testlearn.shanxue.edu.shanxue01.models.*;
import testlearn.shanxue.edu.shanxue01.study.InStudy;
import testlearn.shanxue.edu.shanxue01.test.StudyTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class StartFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "StartFragment";

    private int rhesisSurplus;
    private int momoSurplus;
    private int totalSurplus;
    private ProgressDialog dialog;
    private Intent intentActivity;
    private List<StudyNodeModel> studyNodeModelList = new ArrayList<>();

    private boolean hasOnline = false;
    private boolean hasClicked = false;
    private boolean hasClear = false;
    private String json_learn;
    private boolean hasError = false;

    public JsonModel jsonModel;
    public List<RhesisModel> rhesisModelList = new ArrayList<>();
    public UserInfoModle userInfoModle;
    public List<UserLearnRecordModel> userLearnRecordModelList = new ArrayList<>();

    private MyDBHelper myDBHelper;
    private SQLiteDatabase dbRead, dbWrite;
    private List<MomoModel> momoModelList = new ArrayList<>();
    private String typeOrder;

    private SearchView searchView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        dialog = new ProgressDialog(getContext());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("善学，改变从学习方法开始...");

        intentActivity = new Intent(getActivity(), InStudy.class);
        init();

    }

    private void init() {

        Log.i(TAG, "信息初始化...");

        Log.i(TAG, "初始文件夹...");
        initFolder(FileUtil.FOLDER_URL);

        Log.i(TAG, "初始用户信息...");
        initFile();

        Log.i(TAG, "init()执行完毕");


    }

    private void initFolder(String folderUrl) {
        //folder url
        File folder = new File(folderUrl);
        if (!folder.exists()) {
            Log.i(TAG, "用户文件夹不存在，创建用户文件夹");
            folder.mkdirs();
        } else {
            Log.i(TAG, "用户文件夹存在！");
        }
    }

    private void initFile() {

        if (ConnectUtil.isOnline(getContext())) {
            if (ConnectUtil.isOnline(getContext())) {
                int study_num = 4;
                int book_ID = 1;
                int ID = 1;
                DataUtil.sendGet(FileUtil.URL_USERS + "?ID=" + ID + "&book_ID=" + book_ID + "&study_num=" + study_num, getActivity(), new OnDataResponseListener() {
                    @Override
                    public void onResponse(String data) {
                        json_learn = data;
                        if (json_learn.contains("No such file or directory")) {

                            Toast.makeText(getActivity(), "Background database has some problems!", Toast.LENGTH_SHORT).show();
                            hasError = true;
                            return;
                        }
                        setData();
                    }
                });
                hasOnline = true;
            } else {
                Toast.makeText(getContext(),"No Internet!",Toast.LENGTH_SHORT).show();
                hasOnline = false;
            }
            hasOnline = true;
        } else {
            Toast.makeText(getContext(),"No Internet!",Toast.LENGTH_SHORT).show();
            hasOnline = false;
        }

        initMomoInfo();


    }

    private void initTypeList() {
        typeOrder = "momo,rhesis";
        Log.i(TAG, " (initType) rhesisSurplus : " + rhesisSurplus);
        Log.i(TAG, " (initType) momoSurplus : " + momoSurplus);
        totalSurplus = rhesisSurplus + momoSurplus;
    }

    private void initMomoInfo() {
        myDBHelper = new MyDBHelper(getActivity());
        dbRead = myDBHelper.getReadableDatabase();
        dbWrite = myDBHelper.getWritableDatabase();

        momoModelList = myDBHelper.loadMomo2Model(dbRead, momoModelList);

        momoModelList = filterStudyMomo(momoModelList);
        momoSurplus = momoModelList.size();
        Log.i(TAG, "momoInfo size: " + momoModelList.size());

    }

    private List<MomoModel> filterStudyMomo(List<MomoModel> momoModelList) {

        List<MomoModel> finalMomoList = new ArrayList<>();
        for (int i = 0; i < momoModelList.size(); i++) {
            if (momoModelList.get(i).getMomo_enableFlag() == 1) {
                finalMomoList.add(momoModelList.get(i));
            }
        }
        return finalMomoList;
    }

    private void initFileForce() {
        Log.i(TAG, "强制更新用户信息！");
        File file = new File(FileUtil.FILE_URL_RHESIS);
        if (file.exists())
            file.delete();
        file = new File(FileUtil.FILE_URL_USER);
        if (file.exists())
            file.delete();
        file = new File(FileUtil.FILE_URL_USER_LEARN_RECORD);
        if (file.exists())
            file.delete();
        file = new File(FileUtil.FILE_URL_UPLOAD_LEARN_RECORD);
        if (file.exists())
            file.delete();


//        if (ConnectUtil.isOnline(getContext())) {
//            int study_num = 4;
//            int book_ID = 1;
//            int ID = 1;
//            DataUtil.sendGet(FileUtil.URL_USERS + "?ID=" + ID + "&book_ID=" + book_ID + "&study_num=" + study_num, getActivity(), new OnDataResponseListener() {
//                @Override
//                public void onResponse(String data) {
//                    json_learn = data;
//                    if (json_learn.contains("No such file or directory")) {
//
//                        Toast.makeText(getActivity(), "Background database has some problems!", Toast.LENGTH_SHORT).show();
//                        hasError = true;
//                        return;
//                    }
//                    setData();
//                }
//            });
//            hasOnline = true;
//        } else {
//            Toast.makeText(getContext(),"No Internet!",Toast.LENGTH_SHORT).show();
//            hasOnline = false;
//        }
//        initMomoInfo();

        initFile();


//        JSONTask jsonTask = new JSONTask();
//        jsonTask.execute(FileUtil.URL_USERS + "?ID=" + ID + "&book_ID=" + book_ID + "&study_num=" + study_num);
    }


    private void setData() {

        Log.i(TAG, "json_learn: " + json_learn);

        if (json_learn != null) {
            jsonModel = DataUtil.json2LearnPackage(json_learn);
            userInfoModle = jsonModel.getUserInfoModle();
            rhesisModelList = jsonModel.getRhesisModelList();
            userLearnRecordModelList = jsonModel.getUserLearnRecordModelList();
            if (rhesisModelList != null) {
                rhesisSurplus = rhesisModelList.size();
            } else {
                Toast.makeText(getActivity(), "0 rhesis!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "get learn record failed!", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "can't get json from internet...");
        }

        Log.i(TAG, "rhesisSurplus: " + rhesisSurplus);


        //获取SharedPreferences对象
        Context ct = getActivity();
        SharedPreferences sp = ct.getSharedPreferences("user", MODE_PRIVATE);
        //存入数据
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("nickname", userInfoModle.getNickName());
        editor.putString("ID", userInfoModle.getID());
        editor.apply();

        //返回STRING_KEY的值，定义key值错误或者此key无对应value值的话返回""
        Log.d("look_sharePre", sp.getString("nickname", ""));
        //如果OTHER_KEY不存在，定义key值错误或者此key无对应value值的返回值为"other"
        Log.d("look_sharePre", sp.getString("ID", "other"));


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_start, container, false);
        Log.i(TAG, "onCreatView");
        view.findViewById(R.id.btn2StartStudy).setOnClickListener(this);



        searchView = view.findViewById(R.id.svStudySearch);
        if(!hasOnline){
            searchView.setQueryHint("No Internet,please check!");
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        init();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestory");

    }

    @Override
    public void onClick(View view) {

        initTypeList();
        Log.i(TAG, "totalSurplus: " + totalSurplus);

        if (totalSurplus > 0 || (hasOnline && !hasError)) {
            if (rhesisSurplus > 0) {
                StudyTest.textNextTimeLearnRecordList(userLearnRecordModelList);

            }

            if (hasClicked) {
                Log.i(TAG, "重新生成intentActivity");

                intentActivity = new Intent(getActivity(), InStudy.class);

                initFileForce();
            }

            switch (view.getId()) {
                case R.id.btn2StartStudy:
                    Log.i(TAG, "Start study: onClick 点击开始学习！");
                    //https://stackoverflow.com/questions/3818745/androidruntime-error-parcel-unable-to-marshal-value
                    Log.i(TAG, "开始读取信息...");

                    initFileForce();

                    if (rhesisSurplus != 0) {
                        test();
                        merge2StudyNodeModel();
                        StudyTest.testModelList(userInfoModle, studyNodeModelList, rhesisSurplus);
                        Log.i(TAG, "study_id check----------" + studyNodeModelList.get(0).getStudy_ID());
                    } else {
                        Log.i(TAG, "rhesisSurplus is: " + rhesisSurplus);
                    }


                    Bundle bundle = new Bundle();
                    bundle.putInt("rhesisSurplus", rhesisSurplus);
                    bundle.putInt("momoSurplus", momoSurplus);
                    bundle.putString("typeOrder", typeOrder);
                    bundle.putSerializable("userinfo", userInfoModle);
                    bundle.putSerializable("studyNodeList", (ArrayList) studyNodeModelList);
                    bundle.putSerializable("momoList", (ArrayList) momoModelList);

                    Log.i(TAG,"momoNode nodeis : " + momoModelList.get(0).getStudy_node());

                    intentActivity.putExtras(bundle);

                    Log.i(TAG, "词条信息打包完毕！发送!");

                    startActivity(intentActivity);

                    hasClicked = true;
                    break;
            }
        } else {
            Toast.makeText(getActivity(), "No Entry!", Toast.LENGTH_SHORT).show();

        }
    }

    private void test() {
        Log.i(TAG, "userLearnRecordModelList size is: " + userLearnRecordModelList.size());
        Log.i(TAG, "rhesisModelList size is: " + rhesisModelList.size());


    }


    private void merge2StudyNodeModel() {
        Log.i(TAG, "词条与学习信息开始合并...");

        for (int i = 0; i < rhesisModelList.size(); i++) {

            StudyNodeModel studyNodeModel = new StudyNodeModel();

            studyNodeModel.setRhesisPart(rhesisModelList.get(i));

            studyNodeModel.setID(userInfoModle.getID());
            if (i < userLearnRecordModelList.size()) {
                studyNodeModel.setUserLearnRecordPart(userLearnRecordModelList.get(i));
                Log.i(TAG, "词条 + 学习信息 合并！");
                Log.i(TAG, "study_Node+++++++++++++++++++" + userLearnRecordModelList.get(i).getStudy_node());
                if (hasClicked) {
                    Log.i(TAG, "重新开始，进行更新学习内容！");
                    studyNodeModel.updateUserLearnRecordPart(userLearnRecordModelList.get(i));
                    Log.i(TAG, "study_Node================" + studyNodeModel.getStudy_node());
                }
            } else {
                studyNodeModel.initUserLearnRecordPart();
                Log.i(TAG, "词条 + 创建学习信息 合并！");
            }


            if (!hasClear && hasClicked) {
                studyNodeModelList.clear();
                hasClear = true;
            }
            studyNodeModelList.add(studyNodeModel);
            Log.i(TAG, "studoNodeModeList size is " + studyNodeModelList.size());
            Log.i(TAG, "node-----------------: " + studyNodeModelList.get(i).getStudy_node());
            Log.i(TAG, "creatorDate: " + studyNodeModelList.get(i).getStudy_creatorDate());
            Log.i(TAG, "latestStudyTime: " + studyNodeModelList.get(i).getStudy_latestStudyTime());
            Log.i(TAG, "nextDateTime: " + studyNodeModelList.get(i).getStudy_nextDateTime());
        }
        Log.i(TAG, "词条与学习信息合并成功！");

    }


}
