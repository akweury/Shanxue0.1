package testlearn.shanxue.edu.shanxue01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import testlearn.shanxue.edu.shanxue01.models.StudyNodeModel;
import testlearn.shanxue.edu.shanxue01.models.UserLearnRecordModel;
import testlearn.shanxue.edu.shanxue01.study.InStudy;
import testlearn.shanxue.edu.shanxue01.control.DataUtil;
import testlearn.shanxue.edu.shanxue01.control.FileUtil;
import testlearn.shanxue.edu.shanxue01.models.RhesisModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;
import testlearn.shanxue.edu.shanxue01.test.StudyTest;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StartFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "StartFragment";

    private int surplus;
    private boolean finishJsonLoad = false;
    private ProgressDialog dialog;
    private Intent intentActivity;
    private boolean hasClicked = false;

    private List<RhesisModel> rhesisModelList;
    private UserInfoModle userInfoModle;
    private List<UserLearnRecordModel> userLearnRecordModelList;
    private List<StudyNodeModel> studyNodeModelList = new ArrayList<StudyNodeModel>();
    private boolean hasClear = false;

    private int ID=1;
    private int book_ID=1;
    private int study_num=14;

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public void setRhesisModelList(List<RhesisModel> rhesisModelList) {
        this.rhesisModelList = rhesisModelList;
    }

    public List<RhesisModel> getRhesisModelList() {
        return rhesisModelList;
    }


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
        initFile(FileUtil.FILE_URL_USER);

        Log.i(TAG, "初始词条信息...");
        initFile(FileUtil.FILE_URL_RHESIS);

        Log.i(TAG, "初始学习纪录信息...");
        initFile(FileUtil.FILE_URL_USER_LEARN_RECORD);

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

    private void initFile(String FILE_URL) {
        File file = new File(FILE_URL);

        if (!file.exists()) {

            Log.i(TAG, "信息不存在，下载信息（Json）文件" + "信息存储在本地");
            //download,saving information to object
            if (FILE_URL == FileUtil.FILE_URL_USER) {
                //https://stackoverflow.com/questions/3075009/android-how-can-i-pass-parameters-to-asynctasks-onpreexecute
                JSONTaskUserInfo jsonTaskUserInfo = new JSONTaskUserInfo();
                jsonTaskUserInfo.execute(FileUtil.URL_USERS + "?ID=" + ID);
//                new JSONTaskUserInfo().execute(FileUtil.URL_USERS);
            } else if (FILE_URL == FileUtil.FILE_URL_RHESIS) {
                //?ID=1&book_ID=1&study_num=14
                JSONTaskRhesis jsonTaskRhesis = new JSONTaskRhesis();
                jsonTaskRhesis.execute(FileUtil.URL_RHESIS + "?ID=" + ID + "&book_ID="+book_ID + "&study_num=" + study_num);
//                new JSONTaskRhesis().execute(FileUtil.URL_RHESIS);
            } else if (FILE_URL == FileUtil.FILE_URL_USER_LEARN_RECORD) {
                JSONTaskStudyNode jsonTaskStudyNode = new JSONTaskStudyNode();
                jsonTaskStudyNode.execute(FileUtil.URL_RHESIS + "?ID=" + ID + "&book_ID="+book_ID + "&study_num=" + study_num);
//                new JSONTaskStudyNode().execute(FileUtil.URL_USER_LEARN_RECORD);
            }
        } else {
            Log.i(TAG, "文件已存在, " + "文件名为" + file.getName() + "absolute path: " + file.getAbsolutePath());
        }

        return;


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


        if (hasClicked == true){
            Log.i(TAG,"重新生成intentActivity");
            intentActivity = new Intent(getActivity(), InStudy.class);
            init();
        }
        switch (view.getId()) {
            case R.id.btn2StartStudy:
                Log.i(TAG, "Start study: onClick 点击开始学习！");
                //https://stackoverflow.com/questions/3818745/androidruntime-error-parcel-unable-to-marshal-value
                Log.i(TAG, "开始读取信息...");


                //load data
                setRhesisModelList((List<RhesisModel>) DataUtil.loadData(FileUtil.FILE_NAME_RHESIS));
                userInfoModle = (UserInfoModle) DataUtil.loadData(FileUtil.FILE_NAME_USER);
                userLearnRecordModelList = (List<UserLearnRecordModel>) DataUtil.loadData(FileUtil.FILE_NAME_USER_LEARN_RECORD);

                Log.i(TAG,"study_id check----------" + userLearnRecordModelList.get(0).getStudy_ID());

                if (getRhesisModelList() != null && userInfoModle.getNickName() != null && userLearnRecordModelList.get(0).getStudy_nextDateTime() != null) {
                    Log.i(TAG, "学习--所需信息检查正常");
                }

                surplus = rhesisModelList.size();

                Log.i(TAG,"userLearnRecordModelList size is: " + userLearnRecordModelList.size());
                Log.i(TAG,"rhesisModelList size is: " + rhesisModelList.size());

                merge2StudyNodeModel();

                StudyTest.testModelList(userInfoModle,studyNodeModelList,surplus);
                Log.i(TAG,"study_id check----------" + studyNodeModelList.get(0).getStudy_ID());

                Bundle bundle = new Bundle();
                bundle.putInt("surplus", surplus);
//                bundle.putSerializable("rhesisModelList", (ArrayList) rhesisModelList);
//                bundle.putSerializable("studyNodeModelList", (ArrayList) userLearnRecordModelList);
                bundle.putSerializable("userinfo", userInfoModle);
                bundle.putSerializable("studyNodeList", (ArrayList) studyNodeModelList);
                intentActivity.putExtras(bundle);
                Log.i(TAG, "词条信息打包完毕！");
                Log.i(TAG, "发送!");

                startActivity(intentActivity);

                hasClicked = true;
                break;
        }


    }


    private void merge2StudyNodeModel() {
        Log.i(TAG, "词条与学习信息开始合并...");

        for (int i = 0; i < rhesisModelList.size(); i++) {

            StudyNodeModel studyNodeModel = new StudyNodeModel();

            studyNodeModel.setRhesisPart(rhesisModelList.get(i));
            if (i < userLearnRecordModelList.size()) {
                studyNodeModel.setUserLearnRecordPart(userLearnRecordModelList.get(i));
                Log.i(TAG, "词条 + 学习信息 合并！");
                Log.i(TAG,"study_Node+++++++++++++++++++" + userLearnRecordModelList.get(i).getStudy_node());
                if(hasClicked == true){
                    Log.i(TAG,"重新开始，进行更新学习内容！");
                    studyNodeModel.updateUserLearnRecordPart(userLearnRecordModelList.get(i));
                    Log.i(TAG,"study_Node================" + studyNodeModel.getStudy_node());
                }
            } else {
                studyNodeModel.initUserLearnRecordPart();
                Log.i(TAG, "词条 + 创建学习信息 合并！");
            }


            if(hasClear==false && hasClicked ==true){
                studyNodeModelList.clear();
                hasClear = true;
            }
            studyNodeModelList.add(studyNodeModel);
            Log.i(TAG,"studoNodeModeList size is " + studyNodeModelList.size());
            //TODO:init study_ID in database...maybe
            Log.i(TAG, "node-----------------: " + studyNodeModelList.get(i).getStudy_node());
            Log.i(TAG, "creatorDate: " + studyNodeModelList.get(i).getStudy_creatorDate());
            Log.i(TAG, "latestStudyTime: " + studyNodeModelList.get(i).getStudy_latestStudyTime());
            Log.i(TAG, "nextDateTime: " + studyNodeModelList.get(i).getStudy_nextDateTime());
        }
        Log.i(TAG, "词条与学习信息合并成功！");

    }


    public class JSONTaskUserInfo extends AsyncTask<String, String, UserInfoModle> implements Serializable {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "user onPreExecute");
        }

        @Override
        protected void onPostExecute(UserInfoModle result) {
            super.onPostExecute(userInfoModle);
            userInfoModle = result;
            finishJsonLoad = true;
        }

        @Override
        protected UserInfoModle doInBackground(String... urls) {

            try {
                Log.i(TAG, "开始下载用户文件...");

                String finalJson = DataUtil.downloadJson(urls);
                Log.i(TAG, "用户Json文件下载完毕！");

                JSONObject parentObject = new JSONObject(finalJson);

                Gson gson = new Gson();
                UserInfoModle userInfoModle = gson.fromJson(parentObject.getString("user_info"), UserInfoModle.class);

                FileUtil.writeObj2File(userInfoModle, FileUtil.FILE_NAME_USER);
                Log.i(TAG, "个人信息储存完毕！");

                return userInfoModle;

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public class JSONTaskRhesis extends AsyncTask<String, String, List<RhesisModel>> implements Serializable {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute(List<RhesisModel> result) {
            super.onPostExecute(result);
            Log.i(TAG, "Start Study: （词条）onPostExecute");
            Log.i(TAG, "start study: 将词条数据存入对象...");
            setRhesisModelList(result);
            Log.i(TAG, "start study: 词条数据存入完毕!");

//            testRhesisModelList();

            dialog.dismiss();
        }

        @Override
        protected List<RhesisModel> doInBackground(String... urls) {


            try {
                Log.i(TAG, "开始下载词条数据...");
                String finalJson = DataUtil.downloadJson(urls);
                JSONObject parentObject = null;
                parentObject = new JSONObject(finalJson);

                JSONArray parentArray = parentObject.getJSONArray("rhesis");
                List<RhesisModel> rhesisModelList = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    RhesisModel rhesisModel = gson.fromJson(finalObject.toString(), RhesisModel.class);
                    rhesisModelList.add(rhesisModel);
                }

                System.out.println(rhesisModelList);

                FileUtil.writeObj2File(rhesisModelList, FileUtil.FILE_NAME_RHESIS);

                Log.i(TAG, "词条信息储存完毕！");

                surplus = rhesisModelList.size();

                return rhesisModelList;

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public class JSONTaskStudyNode extends AsyncTask<String, String, List<UserLearnRecordModel>> implements Serializable {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<UserLearnRecordModel> doInBackground(String... urls) {

            try {
                Log.i(TAG, "开始下载学习节点数据...");
                String finalJson = DataUtil.downloadJson(urls);
                JSONObject parentObject = null;
                parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("learn_record");

                List<UserLearnRecordModel> userLearnRecordModelList = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    UserLearnRecordModel userLearnRecordModel = gson.fromJson(finalObject.toString(), UserLearnRecordModel.class);
                    userLearnRecordModelList.add(userLearnRecordModel);
                }
                Log.i(TAG,"study_id check----------" + userLearnRecordModelList.get(0).getStudy_ID());

                System.out.println(userLearnRecordModelList);
                FileUtil.writeObj2File(userLearnRecordModelList, FileUtil.FILE_NAME_USER_LEARN_RECORD);
                Log.i(TAG, "学习节点信息储存完毕！");


                return userLearnRecordModelList;

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<UserLearnRecordModel> result) {
            super.onPostExecute(result);

            Log.i(TAG, "Start Study: （学习节点）onPostExecute");
            Log.i(TAG, "start study: 将学习节点数据存入对象...");
            userLearnRecordModelList = result;
            Log.i(TAG, "start study: 学习节点数据存入对象完毕!");

            Log.i(TAG, "userLearnRecordModel: " + userLearnRecordModelList.get(0).getStudy_nextDateTime());
            Log.i(TAG, "首页初始化完毕！");
        }
    }

}
