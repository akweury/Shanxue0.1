package testlearn.shanxue.edu.shanxue01.models;

import java.io.Serializable;

public class UserLearnRecordModel implements Serializable{
    private String study_ID;
    private String study_item_ID;
    private String ID;
    private String study_createDateTime;
    private int study_node;
    private String study_nextDateTime;
    private String study_latestStudyTime;
    private int hasStudyed;
    public String getStudy_ID() {
        return study_ID;
    }

    public void setStudy_ID(String study_ID) {
        this.study_ID = study_ID;
    }

    public String getStudy_item_ID() {
        return study_item_ID;
    }

    public void setStudy_item_ID(String study_item_ID) {
        this.study_item_ID = study_item_ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStudy_createDateTime() {
        return study_createDateTime;
    }

    public void setStudy_createDateTime(String study_createDateTime) {
        this.study_createDateTime = study_createDateTime;
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

    public void setUserLearnRecord(StudyNodeModel studyNodeModel) {
        this.study_ID = studyNodeModel.getStudy_ID();
        this.ID = studyNodeModel.getID();
        this.study_item_ID = studyNodeModel.getStudy_item_ID();
        this.study_createDateTime = studyNodeModel.getStudy_creatorDate();
        this.study_node = studyNodeModel.getStudy_node();
        this.study_nextDateTime = studyNodeModel.getStudy_nextDateTime();
        this.study_latestStudyTime = studyNodeModel.getStudy_latestStudyTime();
        this.hasStudyed = studyNodeModel.getHasStudyed();
    }
}
