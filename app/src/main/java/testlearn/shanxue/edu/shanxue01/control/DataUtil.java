package testlearn.shanxue.edu.shanxue01.control;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import testlearn.shanxue.edu.shanxue01.models.JsonModel;
import testlearn.shanxue.edu.shanxue01.models.RhesisModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;
import testlearn.shanxue.edu.shanxue01.models.UserLearnRecordModel;
import testlearn.shanxue.edu.shanxue01.test.StudyTest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataUtil {
    private static final String TAG = "control.DataUtil";
    public static final String REQUESTTAG = "string request first";


    private static RequestQueue mRequestQueue ;
    private static StringRequest stringRequest;
    private DiskBasedCache mCache;
    private com.android.volley.Network mNetwork;

    public static String jsonData;

    public static String downloadJson(String[] urls) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            Log.i(TAG, "下载文件中...");
            URL url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();

            if (finalJson != null && finalJson.startsWith("\ufeff")) {
                finalJson = finalJson.substring(1);
            }


            Log.i(TAG, "下载结束，返回结果！");
            return finalJson;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public interface VolleyCallback{
        void onSuccess(String result);
    }

    public static RequestQueue sendGet(String url, Activity activity, final OnDataResponseListener listener) {
        Log.i(TAG,"sendGet");
        Log.i(TAG,"url: " + url);
        final String[] jsonString = new String[1];
//        mRequestQueue = Volley.newRequestQueue(getActivity());

//        mCache = new DiskBasedCache(getActivity().getCacheDir(),4*1024*1024);

//        mNetwork = new BasicNetwork(new HurlStack());

//        mRequestQueue = new RequestQueue(mCache,mNetwork);

//        mRequestQueue.start();

        mRequestQueue = VolleySingleton.getInstance(activity.getApplicationContext()).getRequestQueue(activity.getApplicationContext());

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response: " + response);

                jsonData = response;
                listener.onResponse(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error: " + error.toString());
            }
        });
        stringRequest.setTag(REQUESTTAG);


        mRequestQueue.add(stringRequest);

        return mRequestQueue;


    }

    public static void sendPost(final String postKey, final String postValue, String url, final Activity activity){

        Log.i(TAG,"开始 Post 数据");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"onResponse");

                Toast.makeText(activity.getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                Log.i(TAG,response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"onErrorResponse");

                Toast.makeText(activity,error+"",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put(postKey,postValue);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);

    }


    public static Object loadData(String fileName) {
        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FileUtil.FOLDER_URL + File.separator + fileName));
            if (fileName == FileUtil.FILE_NAME_USER) {

                Object object = ois.readObject();
                ois.close();
                UserInfoModle userInfoModle = (UserInfoModle) object;
                Log.i(TAG, "用户信息获取完毕, 用户名: " + userInfoModle.getNickName());
                return object;
            } else if (fileName == FileUtil.FILE_NAME_RHESIS) {
                Object object = ois.readObject();
                ois.close();
                List<RhesisModel> rhesisModelList = ((List<RhesisModel>) object);
                Log.i(TAG, "词条信息读取完毕，第一个词条为： " + rhesisModelList.get(0).getRhesis_title());
                return object;
            } else if (fileName == FileUtil.FILE_NAME_USER_LEARN_RECORD){
                Object object = ois.readObject();
                ois.close();
                List<UserLearnRecordModel> userLearnRecordModelList = ((List<UserLearnRecordModel>)object);
                Log.i(TAG,"用户学习纪录信息读取完毕，第一个词条 node 为： " + userLearnRecordModelList.get(0).getStudy_node());
                return object;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String transLearnRecords2Json(List<UserLearnRecordModel> userLearnRecordModelList) {

        String string = "{\"learn_record\": [";
        Gson gson = new Gson();
        //transfer learn-record-list to json
        for(int i=0;i<userLearnRecordModelList.size();i++){
            string += gson.toJson(userLearnRecordModelList.get(i));
            if(i != userLearnRecordModelList.size() - 1){
                string += ",";
            }
        }
        string += "]}";
        return string;

    }

    public static JsonModel json2LearnPackage(String json_learnPackage){
        Log.i(TAG,"json数据包内容为：" + json_learnPackage);

        JSONObject parentObject = null;
        try {
            parentObject = new JSONObject(json_learnPackage);

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

            JsonModel jsonModel = new JsonModel();
            jsonModel.setUserInfoModle(userInfoModle);
            jsonModel.setRhesisModelList(rhesisModelList);
            jsonModel.setUserLearnRecordModelList(userLearnRecordModelList);

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
            return jsonModel;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

