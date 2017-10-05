package testlearn.shanxue.edu.shanxue01.models;

import java.io.Serializable;

public class MomoModel implements Serializable {
    private int momo_ID;
    private String momo_type;
    private String momo_label;
    private int momo_enableFlag;
    private String momo_text;
    private String momo_hintMain;
    private String momo_hintOthers;
    private String momo_creator;
    private String momo_createDateTime;
    private String momo_updateTime;
    private String momo_latestStudyTime;
    private int momo_study_node;
    private String momo_log = "log:";

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

    public String getMomo_createDateTime() {
        return momo_createDateTime;
    }

    public void setMomo_createDateTime(String momo_createDateTime) {
        this.momo_createDateTime = momo_createDateTime;
    }

    public String getMomo_updateTime() {
        return momo_updateTime;
    }

    public void setMomo_updateTime(String momo_updateTime) {
        this.momo_updateTime = momo_updateTime;
    }

    public String getMomo_latestStudyTime() {
        return momo_latestStudyTime;
    }

    public void setMomo_latestStudyTime(String momo_latestStudyTime) {
        this.momo_latestStudyTime = momo_latestStudyTime;
    }

    public int getMomo_study_node() {
        return momo_study_node;
    }

    public void setMomo_study_node(int momo_study_node) {
        this.momo_study_node = momo_study_node;
    }

    public String getMomo_log() {
        return momo_log;
    }

    public void setMomo_log(String momo_log) {
        this.momo_log += momo_log + "\n";
    }
}
