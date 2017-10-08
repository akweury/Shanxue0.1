package testlearn.shanxue.edu.shanxue01.models;

import android.util.Log;
import testlearn.shanxue.edu.shanxue01.control.NodeUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NodeModel {


    private static final String TAG = "StudyNodeModel";
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String study_creatorDate;
    private int study_node;
    private String study_nextDateTime;
    private String study_latestStudyTime;
    private int hasStudyed;

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

    public void minus(){
        Log.i(TAG, "节点降低!");
        Date date = new Date();
        Log.i(TAG, "更新前，最近学习时间为：" + this.study_latestStudyTime);
        this.study_latestStudyTime = dateFormat.format(date);
        Log.i(TAG, "更新后，最近学习时间为：" + this.study_latestStudyTime);

        NodeModel tempNodeModel = NodeUtil.minus(this);

        Log.i(TAG, "更新前，学习节点为：" + this.study_node);
        this.study_node = tempNodeModel.getStudy_node();
        Log.i(TAG, "更新后，学习节点为：" + this.study_node);

        Log.i(TAG, "更新前，下一次学习时间为：" + this.study_nextDateTime);
        this.study_nextDateTime = tempNodeModel.getStudy_nextDateTime();
        Log.i(TAG, "更新后，下一次学习时间为：" + this.study_nextDateTime);
    }

    public void plus() {
        Log.i(TAG, "节点增长！");
        Date date = new Date();
        Log.i(TAG, "更新前，最近学习时间为：" + this.study_latestStudyTime);
        this.study_latestStudyTime = dateFormat.format(date);
        Log.i(TAG, "更新后，最近学习时间为：" + this.study_latestStudyTime);

        NodeModel tempNodeModel = NodeUtil.plus(this);
        Log.i(TAG, "更新前，学习节点为：" + this.study_node);
        this.study_node = tempNodeModel.getStudy_node();
        Log.i(TAG, "更新后，学习节点为：" + this.study_node);


        Log.i(TAG, "更新前，下一次学习时间为：" + this.study_nextDateTime);
        this.study_nextDateTime = tempNodeModel.getStudy_nextDateTime();
        Log.i(TAG, "更新后，下一次学习时间为：" + this.study_nextDateTime);
        if (this.study_node > 6) {
            this.hasStudyed = 1;
        }

    }

}
