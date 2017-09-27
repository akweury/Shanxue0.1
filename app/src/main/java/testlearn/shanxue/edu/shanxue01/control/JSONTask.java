package testlearn.shanxue.edu.shanxue01.control;

import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import testlearn.shanxue.edu.shanxue01.StartFragment;
import testlearn.shanxue.edu.shanxue01.models.JsonModel;
import testlearn.shanxue.edu.shanxue01.models.RhesisModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;
import testlearn.shanxue.edu.shanxue01.models.UserLearnRecordModel;
import testlearn.shanxue.edu.shanxue01.test.StudyTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JSONTask extends AsyncTask<String, String, JsonModel> implements Serializable {
    private static final String TAG = "JSONTask";
    private StartFragment startFragment;


    public JSONTask(StartFragment startFragment) {
        this.startFragment = startFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(TAG, "json onPreExecute");
    }

    @Override
    protected void onPostExecute(JsonModel result) {
        super.onPostExecute(startFragment.jsonModel);
        startFragment.userInfoModle = result.getUserInfoModle();
        startFragment.rhesisModelList = result.getRhesisModelList();
        startFragment.userLearnRecordModelList = result.getUserLearnRecordModelList();

    }

    @Override
    protected JsonModel doInBackground(String... urls) {

        try {
            Log.i(TAG, "开始下载json文件...");

            String finalJson = DataUtil.downloadJson(urls);
            Log.i(TAG, "用户Json文件下载完毕！");


            Log.i(TAG,"json数据包内容为：" + finalJson);

            JSONObject parentObject = new JSONObject(finalJson);
            JSONObject jsonObject = parentObject.getJSONObject("result");

            JSONArray rhesisArray = jsonObject.getJSONArray("rhesis");
            JSONArray learn_record = jsonObject.getJSONArray("learn_record");
            JSONObject user_info = jsonObject.getJSONObject("user_info");

            UserInfoModle userInfoModle = new UserInfoModle();
            List<UserLearnRecordModel> userLearnRecordModelList = new ArrayList<>();
            List<RhesisModel> rhesisModelList = new ArrayList<>();


            Gson gson = new Gson();


            for (int i = 0; i < learn_record.length(); i++) {
                JSONObject finalObject = learn_record.getJSONObject(i);
                UserLearnRecordModel userLearnRecordModel = gson.fromJson(finalObject.toString(), UserLearnRecordModel.class);
                userLearnRecordModelList.add(userLearnRecordModel);
            }

            for (int i = 0; i < rhesisArray.length(); i++) {
                JSONObject finalObject = rhesisArray.getJSONObject(i);
                RhesisModel rhesisModel = gson.fromJson(finalObject.toString(), RhesisModel.class);
                rhesisModelList.add(rhesisModel);
            }

            userInfoModle = gson.fromJson(user_info.toString(),UserInfoModle.class);

            Log.i(TAG,"userInfoModel nickname: " + userInfoModle.getNickName());
            Log.i(TAG,"userLearnRecordlist size: " + userLearnRecordModelList.size());
            Log.i(TAG,"rhesisModel size: " + rhesisModelList.size());

            startFragment.jsonModel = new JsonModel();
            startFragment.jsonModel.setUserInfoModle(userInfoModle);
            startFragment.jsonModel.setRhesisModelList(rhesisModelList);
            startFragment.jsonModel.setUserLearnRecordModelList(userLearnRecordModelList);

            FileUtil.writeObj2File(userInfoModle, FileUtil.FILE_NAME_USER);
            FileUtil.writeObj2File(userLearnRecordModelList, FileUtil.FILE_NAME_USER_LEARN_RECORD);
            FileUtil.writeObj2File(rhesisModelList, FileUtil.FILE_NAME_RHESIS);
            Log.i(TAG, "个人信息储存完毕！");

            Log.i(TAG,"开始信息测试...");
            StudyTest.testModel(userInfoModle);
            for (UserLearnRecordModel userLearnRecordModel : userLearnRecordModelList){
                StudyTest.testModel(userLearnRecordModel);
            }
            for(RhesisModel rhesisModel : rhesisModelList){
                StudyTest.testModel(rhesisModel);
            }



            return startFragment.jsonModel;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
