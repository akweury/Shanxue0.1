package testlearn.shanxue.edu.shanxue01.models;

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



    private String study_creatorDate;
    private int study_node;
    private String study_nextDateTime;
    private String study_latestStudyTime;
    private int hasStudyed;

    private String momo_log = "log:";

    public static String getTAG() {
        return TAG;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getStudy_creatorDate() {
        return study_creatorDate;
    }

    public void setStudy_creatorDate(String study_creatorDate) {
        this.study_creatorDate = study_creatorDate;
    }

    public int getStudy_node() {
        return study_node;
    }

    public void setStudy_node(int study_node) {
        this.study_node = study_node;
    }

    public String getStudy_nextDateTime() {
        return study_nextDateTime;
    }

    public void setStudy_nextDateTime(String study_nextDateTime) {
        this.study_nextDateTime = study_nextDateTime;
    }

    public String getStudy_latestStudyTime() {
        return study_latestStudyTime;
    }

    public void setStudy_latestStudyTime(String study_latestStudyTime) {
        this.study_latestStudyTime = study_latestStudyTime;
    }

    public int getHasStudyed() {
        return hasStudyed;
    }

    public void setHasStudyed(int hasStudyed) {
        this.hasStudyed = hasStudyed;
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

}
