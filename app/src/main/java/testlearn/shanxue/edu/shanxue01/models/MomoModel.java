package testlearn.shanxue.edu.shanxue01.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import testlearn.shanxue.edu.shanxue01.control.MyDBHelper;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MomoModel extends NodeModel implements Serializable {

    private static final String TAG = "MomoModel";
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private int momo_ID;
    private String momo_type;
    private String momo_label;
    private int momo_enableFlag;
    private String momo_text;
    private String momo_hintMain;
    private String momo_hintOthers;
    private String momo_creator;


    private String momo_log = "log:";
    private MyDBHelper myDBHelper;
    private SQLiteDatabase dbRead, dbWrite;

    public static String getTAG() {
        return TAG;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public int getMomo_ID() {
        return momo_ID;
    }

    public void setMomo_ID(int momo_ID) {
        this.momo_ID = momo_ID;
    }

    public String getMomo_type() {
        return momo_type;
    }

    public void setMomo_type(String momo_type) {
        this.momo_type = momo_type;
    }

    public String getMomo_label() {
        return momo_label;
    }

    public void setMomo_label(String momo_label) {
        this.momo_label = momo_label;
    }

    public int getMomo_enableFlag() {
        return momo_enableFlag;
    }

    public void setMomo_enableFlag(int momo_enableFlag) {
        this.momo_enableFlag = momo_enableFlag;
    }

    public String getMomo_text() {
        return momo_text;
    }

    public void setMomo_text(String momo_text) {
        this.momo_text = momo_text;
    }

    public String getMomo_hintMain() {
        return momo_hintMain;
    }

    public void setMomo_hintMain(String momo_hintMain) {
        this.momo_hintMain = momo_hintMain;
    }

    public String getMomo_hintOthers() {
        return momo_hintOthers;
    }

    public void setMomo_hintOthers(String momo_hintOthers) {
        this.momo_hintOthers = momo_hintOthers;
    }

    public String getMomo_creator() {
        return momo_creator;
    }

    public void setMomo_creator(String momo_creator) {
        this.momo_creator = momo_creator;
    }

    public String getMomo_log() {
        return momo_log;
    }

    public void setMomo_log(String momo_log) {
        this.momo_log += momo_log + "\n";
    }


    /**
     * Updates the momo information to the Database
     *
     * @param i The flag of the node operation,
     *          0 is minus a node,
     *          1 is plus a node,
     *          2 is zero a node.
     */
    public void updateMomo(Context context, int i) {
        if (i == 0) {
            super.minus();
        } else if (i == 1) {
            super.plus();
        } else if (i == 2) {
            super.zero();
        }
        Log.i(TAG, "updateMomo 最近学习时间为：" + this.getStudy_latestStudyTime());
        Log.i(TAG, "updateMomo id：" + this.getMomo_ID());
        Log.i(TAG, "updateMomo node：" + this.getStudy_node());

        myDBHelper = new MyDBHelper(context);
        dbWrite = myDBHelper.getWritableDatabase();
        myDBHelper.update(dbWrite, this);
        myDBHelper.close();
    }

}
