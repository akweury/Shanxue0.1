package testlearn.shanxue.edu.shanxue01;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import testlearn.shanxue.edu.shanxue01.control.DataUtil;
import testlearn.shanxue.edu.shanxue01.control.FileUtil;
import testlearn.shanxue.edu.shanxue01.control.OnDataResponseListener;
import testlearn.shanxue.edu.shanxue01.models.*;
import testlearn.shanxue.edu.shanxue01.study.InStudy;
import testlearn.shanxue.edu.shanxue01.test.StudyTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class StartFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "StartFragment";

    private int surplus;
    private ProgressDialog dialog;
    private Intent intentActivity;
    private List<StudyNodeModel> studyNodeModelList = new ArrayList<StudyNodeModel>();
    private boolean hasClicked = false;
    private boolean hasClear = false;
    private boolean hasError = false;
    private String json_learn;

    public JsonModel jsonModel;
    public List<RhesisModel> rhesisModelList;
    public UserInfoModle userInfoModle;
    public List<UserLearnRecordModel> userLearnRecordModelList;




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
//        File file = new File(FILE_URL);

//        if (!file.exists()) {
//
//            Log.i(TAG, "信息不存在，下载信息（Json）文件" + "信息存储在本地");
//            initLearnInfo();
//        } else {
//            Log.i(TAG, "文件已存在, " + "文件名为" + file.getName() + "absolute path: " + file.getAbsolutePath());
//        }

        initLearnInfo();

    }

    private void initFileForce(){
        Log.i(TAG,"强制更新用户信息！");
        File file = new File(FileUtil.FILE_URL_RHESIS);
        if(file.exists())
            file.delete();
        file = new File(FileUtil.FILE_URL_USER);
        if (file.exists())
            file.delete();
        file = new File(FileUtil.FILE_URL_USER_LEARN_RECORD);
        if(file.exists())
            file.delete();
        file = new File(FileUtil.FILE_URL_UPLOAD_LEARN_RECORD);
        if(file.exists())
            file.delete();


        initLearnInfo();


//        JSONTask jsonTask = new JSONTask();
//        jsonTask.execute(FileUtil.URL_USERS + "?ID=" + ID + "&book_ID=" + book_ID + "&study_num=" + study_num);
    }

    private void initLearnInfo() {
        int study_num = 4;
        int book_ID = 1;
        int ID = 1;
        DataUtil.sendGet(FileUtil.URL_USERS + "?ID=" + ID + "&book_ID=" + book_ID + "&study_num=" + study_num, getActivity(), new OnDataResponseListener() {
            @Override
            public void onResponse(String data) {
                json_learn = data;
                if(json_learn.contains("No such file or directory")){

                    Toast.makeText(getActivity(),"Database has some problems!",Toast.LENGTH_SHORT).show();
                    hasError = true;
                    return;
                }
                setData();
            }
        });


    }

    private void setData(){

        Log.i(TAG,"json_learn: " + json_learn);

        if(json_learn != null){
            jsonModel = DataUtil.json2LearnPackage(json_learn);
            userInfoModle = jsonModel.getUserInfoModle();
            rhesisModelList = jsonModel.getRhesisModelList();
            userLearnRecordModelList = jsonModel.getUserLearnRecordModelList();
            if (rhesisModelList != null) {
                surplus = rhesisModelList.size();
            }else {
                Toast.makeText(getActivity(),"0 rhesis!",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(),"get learn record failed!",Toast.LENGTH_SHORT).show();
            Log.i(TAG,"can't get json from internet...");
        }


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

        if (!hasError){

            StudyTest.textNextTimeLearnRecordList(userLearnRecordModelList);

            if (hasClicked){
                Log.i(TAG,"重新生成intentActivity");

                intentActivity = new Intent(getActivity(), InStudy.class);

                initFileForce();
            }

            switch (view.getId()) {
                case R.id.btn2StartStudy:
                    Log.i(TAG, "Start study: onClick 点击开始学习！");
                    //https://stackoverflow.com/questions/3818745/androidruntime-error-parcel-unable-to-marshal-value
                    Log.i(TAG, "开始读取信息...");

                    initFileForce();

                    test();

                    merge2StudyNodeModel();

                    StudyTest.testModelList(userInfoModle,studyNodeModelList,surplus);
                    Log.i(TAG,"study_id check----------" + studyNodeModelList.get(0).getStudy_ID());

                    Bundle bundle = new Bundle();
                    bundle.putInt("surplus", surplus);
                    bundle.putSerializable("userinfo", userInfoModle);
                    bundle.putSerializable("studyNodeList", (ArrayList) studyNodeModelList);
                    intentActivity.putExtras(bundle);
                    Log.i(TAG, "词条信息打包完毕！发送!");

                    startActivity(intentActivity);

                    hasClicked = true;
                    break;
            }
        }else{
            Toast.makeText(getActivity(),"Data load failed!",Toast.LENGTH_SHORT).show();
        }
    }

    private void test() {
        Log.i(TAG,"userLearnRecordModelList size is: " + userLearnRecordModelList.size());
        Log.i(TAG,"rhesisModelList size is: " + rhesisModelList.size());

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
                Log.i(TAG,"study_Node+++++++++++++++++++" + userLearnRecordModelList.get(i).getStudy_node());
                if(hasClicked){
                    Log.i(TAG,"重新开始，进行更新学习内容！");
                    studyNodeModel.updateUserLearnRecordPart(userLearnRecordModelList.get(i));
                    Log.i(TAG,"study_Node================" + studyNodeModel.getStudy_node());
                }
            } else {
                studyNodeModel.initUserLearnRecordPart();
                Log.i(TAG, "词条 + 创建学习信息 合并！");
            }


            if(!hasClear && hasClicked){
                studyNodeModelList.clear();
                hasClear = true;
            }
            studyNodeModelList.add(studyNodeModel);
            Log.i(TAG,"studoNodeModeList size is " + studyNodeModelList.size());
            Log.i(TAG, "node-----------------: " + studyNodeModelList.get(i).getStudy_node());
            Log.i(TAG, "creatorDate: " + studyNodeModelList.get(i).getStudy_creatorDate());
            Log.i(TAG, "latestStudyTime: " + studyNodeModelList.get(i).getStudy_latestStudyTime());
            Log.i(TAG, "nextDateTime: " + studyNodeModelList.get(i).getStudy_nextDateTime());
        }
        Log.i(TAG, "词条与学习信息合并成功！");

    }

    //    public class JSONTaskRhesis extends AsyncTask<String, String, List<RhesisModel>> implements Serializable {
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            dialog.show();
//        }
//
//        @Override
//        protected void onPostExecute(List<RhesisModel> result) {
//            super.onPostExecute(result);
//            Log.i(TAG, "Start Study: （词条）onPostExecute");
//            Log.i(TAG, "start study: 将词条数据存入对象...");
//            setRhesisModelList(result);
//            Log.i(TAG, "start study: 词条数据存入完毕!");
//
////            testRhesisModelList();
//
//            dialog.dismiss();
//        }
//
//        @Override
//        protected List<RhesisModel> doInBackground(String... urls) {
//
//
//            try {
//                Log.i(TAG, "开始下载词条数据...");
//                String finalJson = DataUtil.downloadJson(urls);
//                JSONObject parentObject = null;
//                parentObject = new JSONObject(finalJson);
//
//
//                JSONArray parentArray = parentObject.getJSONArray("rhesis");
//                List<RhesisModel> rhesisModelList = new ArrayList<>();
//                Gson gson = new Gson();
//                for (int i = 0; i < parentArray.length(); i++) {
//                    JSONObject finalObject = parentArray.getJSONObject(i);
//                    RhesisModel rhesisModel = gson.fromJson(finalObject.toString(), RhesisModel.class);
//                    rhesisModelList.add(rhesisModel);
//                }
//
//                System.out.println(rhesisModelList);
//
//                FileUtil.writeObj2File(rhesisModelList, FileUtil.FILE_NAME_RHESIS);
//
//                Log.i(TAG, "词条信息储存完毕！");
//
//                surplus = rhesisModelList.size();
//
//                return rhesisModelList;
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }


//    public class JSONTaskStudyNode extends AsyncTask<String, String, List<UserLearnRecordModel>> implements Serializable {
//
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected List<UserLearnRecordModel> doInBackground(String... urls) {
//
//            try {
//                Log.i(TAG, "开始下载学习节点数据...");
//                String finalJson = DataUtil.downloadJson(urls);
//                JSONObject parentObject = null;
//                parentObject = new JSONObject(finalJson);
//                JSONArray parentArray = parentObject.getJSONArray("learn_record");
//
//                List<UserLearnRecordModel> userLearnRecordModelList = new ArrayList<>();
//                Gson gson = new Gson();
//                for (int i = 0; i < parentArray.length(); i++) {
//                    JSONObject finalObject = parentArray.getJSONObject(i);
//                    UserLearnRecordModel userLearnRecordModel = gson.fromJson(finalObject.toString(), UserLearnRecordModel.class);
//                    userLearnRecordModelList.add(userLearnRecordModel);
//                }
//                Log.i(TAG,"study_id check----------" + userLearnRecordModelList.get(0).getStudy_ID());
//
//                System.out.println(userLearnRecordModelList);
//                FileUtil.writeObj2File(userLearnRecordModelList, FileUtil.FILE_NAME_USER_LEARN_RECORD);
//                Log.i(TAG, "学习节点信息储存完毕！");
//
//
//                return userLearnRecordModelList;
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(List<UserLearnRecordModel> result) {
//            super.onPostExecute(result);
//
//            Log.i(TAG, "Start Study: （学习节点）onPostExecute");
//            Log.i(TAG, "start study: 将学习节点数据存入对象...");
//            userLearnRecordModelList = result;
//            Log.i(TAG, "start study: 学习节点数据存入对象完毕!");
//
//            Log.i(TAG, "userLearnRecordModel: " + userLearnRecordModelList.get(0).getStudy_nextDateTime());
//            Log.i(TAG, "首页初始化完毕！");
//        }
//    }
}
