package testlearn.shanxue.edu.shanxue01.control;

import android.util.Log;
import testlearn.shanxue.edu.shanxue01.models.RhesisModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;
import testlearn.shanxue.edu.shanxue01.models.UserLearnRecordModel;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DataUtil {
    private static final String TAG = "control.DataUtil";

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

}
