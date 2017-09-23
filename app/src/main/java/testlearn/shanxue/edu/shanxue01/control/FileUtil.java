package testlearn.shanxue.edu.shanxue01.control;

import android.os.Environment;
import android.util.Log;

import java.io.*;

public class FileUtil {
    private static final String TAG = "control.FileUtil";

    public static final String FOLDER_NAME = "user";

    public static final String FILE_NAME_USER = "userfile.txt";
    public static final String FILE_NAME_RHESIS = "rhesis.txt";
    public static final String FILE_NAME_USER_LEARN_RECORD = "userlearnrecord.txt";
    public static final String FILE_NAME_UPLOAD_LEARN_RECORD = "uploadlearnrecord.txt";
    public static final String FILE_NAME_TEST = "android_test.txt";


    final static public String FOLDER_URL = Environment.getExternalStorageDirectory() + File.separator + FileUtil.FOLDER_NAME;

    final static public String FILE_URL_USER = Environment.getExternalStorageDirectory() + File.separator + FileUtil.FOLDER_NAME + File.separator + FileUtil.FILE_NAME_USER;
    final static public String FILE_URL_RHESIS = Environment.getExternalStorageDirectory() + File.separator + FileUtil.FOLDER_NAME + File.separator + FileUtil.FILE_NAME_RHESIS;
    final static public String FILE_URL_USER_LEARN_RECORD = Environment.getExternalStorageDirectory() + File.separator + FileUtil.FOLDER_NAME + File.separator + FileUtil.FILE_NAME_USER_LEARN_RECORD;
    final static public String FILE_URL_UPLOAD_LEARN_RECORD = Environment.getExternalStorageDirectory() + File.separator + FileUtil.FOLDER_NAME + File.separator + FileUtil.FILE_NAME_UPLOAD_LEARN_RECORD;


//    public static final String URL_USERS = "http://test20170831.gz.bcebos.com/User.json?authorization=bce-auth-v1%2Fb99708eafdc1471d8e33b6b5e9f58208%2F2017-09-13T03%3A45%3A35Z%2F-1%2Fhost%2F4303760ee6089a252b7fb71d68a41e044d258144a283672d6c5dedad928f7cae";
//    public static final String URL_RHESIS = "http://test20170831.gz.bcebos.com/RhesisShort.json?authorization=bce-auth-v1%2Fb99708eafdc1471d8e33b6b5e9f58208%2F2017-09-13T03%3A46%3A12Z%2F-1%2Fhost%2Fe3561f4e4fac149f5cc5d3722e4a51b92ccb13b9867daa1743b405d3cab2e58f";
//    public static final String URL_USER_LEARN_RECORD = "http://test20170831.gz.bcebos.com/StudyNodeShort.json?authorization=bce-auth-v1%2Fb99708eafdc1471d8e33b6b5e9f58208%2F2017-09-13T03%3A46%3A44Z%2F-1%2Fhost%2F29a85e290ce485fdd662d608ce9b8cea93325a8921fae890b0205d905deb954e";
    public static final String URL_USERS = "http://180.76.185.31/shanxue/json4learnrecord.php";
    public static final String URL_UPLOAD_LEARN_RECORD = "http://180.76.185.31/shanxue/receiveJson.php";
    public static final String URL_TEST = "http://180.76.185.31/shanxue/test.php";
//    public static final String URL_RHESIS = "http://180.76.185.31/shanxue/json4learnrecord.php";
//    public static final String URL_USER_LEARN_RECORD = "http://180.76.185.31/shanxue/json4learnrecord.php";

    public static void writeObj2File(Object object,String fileName) {

        try {
            File file = new File(FileUtil.FOLDER_URL + File.separator + fileName);
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(new FileOutputStream(FileUtil.FOLDER_URL + File.separator + fileName));
            oos.writeObject(object);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "文件储存成功！");
    }


    public static void writeString2File(String string,String fileName) {
        Log.i(TAG,"开始将string写入文件 "+fileName+" 中!");
        try {
            File file = new File(FileUtil.FOLDER_URL + File.separator + fileName);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(string);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG,"文件 "+fileName+" 写入完毕!");

    }



}
