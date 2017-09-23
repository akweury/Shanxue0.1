package testlearn.shanxue.edu.shanxue01.models;

import java.io.Serializable;
import java.util.List;

public class JsonModel implements Serializable{
    private List<RhesisModel> rhesisModelList;
    private List<UserLearnRecordModel> userLearnRecordModelList;
    private UserInfoModle userInfoModle;

    public List<RhesisModel> getRhesisModelList() {
        return rhesisModelList;
    }

    public void setRhesisModelList(List<RhesisModel> rhesisModelList) {
        this.rhesisModelList = rhesisModelList;
    }

    public List<UserLearnRecordModel> getUserLearnRecordModelList() {
        return userLearnRecordModelList;
    }

    public void setUserLearnRecordModelList(List<UserLearnRecordModel> userLearnRecordModelList) {
        this.userLearnRecordModelList = userLearnRecordModelList;
    }

    public UserInfoModle getUserInfoModle() {
        return userInfoModle;
    }

    public void setUserInfoModle(UserInfoModle userInfoModle) {
        this.userInfoModle = userInfoModle;
    }
}
