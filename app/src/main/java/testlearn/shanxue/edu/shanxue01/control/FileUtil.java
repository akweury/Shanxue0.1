package testlearn.shanxue.edu.shanxue01.control;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileUtil {
    private static final String TAG = "control.FileUtil";

    public static final String FOLDER_NAME = "user";

    public static final String FILE_NAME_USER = "userfile.txt";
    public static final String FILE_NAME_RHESIS = "rhesis.txt";
    public static final String FILE_NAME_USER_LEARN_RECORD = "userlearnrecord.txt";


    final static public String FOLDER_URL = Environment.getExternalStorageDirectory() + File.separator + FileUtil.FOLDER_NAME;

    final static public String FILE_URL_USER = Environment.getExternalStorageDirectory() + File.separator + FileUtil.FOLDER_NAME + File.separator + FileUtil.FILE_NAME_USER;
    final static public String FILE_URL_RHESIS = Environment.getExternalStorageDirectory() + File.separator + FileUtil.FOLDER_NAME + File.separator + FileUtil.FILE_NAME_RHESIS;
    final static public String FILE_URL_USER_LEARN_RECORD = Environment.getExternalStorageDirectory() + File.separator + FileUtil.FOLDER_NAME + File.separator + FileUtil.FILE_NAME_USER_LEARN_RECORD;


    public static final String URL_USERS = "http://test20170831.gz.bcebos.com/User.json?authorization=bce-auth-v1%2Fb99708eafdc1471d8e33b6b5e9f58208%2F2017-08-31T06%3A22%3A24Z%2F-1%2Fhost%2F5df9b739e4cab124613fd0eec2dbd433ff94fbf3154250b2346cce5b92e74b32";
    public static final String URL_RHESIS = "http://test20170831.gz.bcebos.com/RhesisShort.json?authorization=bce-auth-v1%2Fb99708eafdc1471d8e33b6b5e9f58208%2F2017-09-03T09%3A03%3A31Z%2F-1%2Fhost%2Fe12b44d5e84cb81457489d6a3dcf6b5fdbf763752dbb7ce586bdb15cd84e90cd";
    public static final String URL_USER_LEARN_RECORD = "http://test20170831.gz.bcebos.com/StudyNodeShort.json?authorization=bce-auth-v1%2Fb99708eafdc1471d8e33b6b5e9f58208%2F2017-09-03T09%3A03%3A01Z%2F-1%2Fhost%2Feba352d201a38fe41a41d75b56faad834bef93be9e5a4cd6a935cc17b0b10a17";

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




}
