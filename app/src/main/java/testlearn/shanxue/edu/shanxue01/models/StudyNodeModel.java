package testlearn.shanxue.edu.shanxue01.models;

import android.util.Log;
import testlearn.shanxue.edu.shanxue01.control.NodeUtil;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudyNodeModel implements Serializable {

    private static final String TAG = "StudyNodeModel";

    private String rhesis_ID;
    private String book_ID;
    private int rhesis_enable_flag;
    private String rhesis_sentance;
    private String rhesis_title;
    private String rhesis_dynasty;
    private String rhesis_author;
    private String rhesis_type;
    private String rhesis_text;
    private String rhesis_transiation;
    private String rhesis_comment;
    private String rhesis_appreciate;
    private int rhesis_evaluationMode;
    private String rhesis_questions;
    private String rhesis_creator;
    private String rhesis_date;
    private String rhesis_updator;
    private String rhesis_updateDate;
    private int rhesis_difficulty;
    private int rhesis_like_number;

    private String study_ID;
    private String study_item_ID;
    private String ID;
    private String study_creatorDate;
    private int study_node;
    private String study_nextDateTime;
    private String study_latestStudyTime;
    private int hasStudyed;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public String getRhesis_ID() {
        return rhesis_ID;
    }

    public void setRhesis_ID(String rhesis_ID) {
        this.rhesis_ID = rhesis_ID;
    }

    public String getStudy_item_ID() {
        return study_item_ID;
    }

    public void setStudy_item_ID(String study_item_ID) {
        this.study_item_ID = study_item_ID;
    }

    public String getBook_ID() {
        return book_ID;
    }

    public void setBook_ID(String book_ID) {
        this.book_ID = book_ID;
    }

    public int getRhesis_enable_flag() {
        return rhesis_enable_flag;
    }

    public void setRhesis_enable_flag(int rhesis_enable_flag) {
        this.rhesis_enable_flag = rhesis_enable_flag;
    }

    public String getRhesis_sentance() {
        return rhesis_sentance;
    }

    public void setRhesis_sentance(String rhesis_sentance) {
        this.rhesis_sentance = rhesis_sentance;
    }

    public String getRhesis_title() {
        return rhesis_title;
    }

    public void setRhesis_title(String rhesis_title) {
        this.rhesis_title = rhesis_title;
    }

    public String getRhesis_dynasty() {
        return rhesis_dynasty;
    }

    public void setRhesis_dynasty(String rhesis_dynasty) {
        this.rhesis_dynasty = rhesis_dynasty;
    }

    public String getRhesis_author() {
        return rhesis_author;
    }

    public void setRhesis_author(String rhesis_author) {
        this.rhesis_author = rhesis_author;
    }

    public String getRhesis_type() {
        return rhesis_type;
    }

    public void setRhesis_type(String rhesis_type) {
        this.rhesis_type = rhesis_type;
    }

    public String getRhesis_text() {
        return rhesis_text;
    }

    public void setRhesis_text(String rhesis_text) {
        this.rhesis_text = rhesis_text;
    }

    public String getRhesis_transiation() {
        return rhesis_transiation;
    }

    public void setRhesis_transiation(String rhesis_transiation) {
        this.rhesis_transiation = rhesis_transiation;
    }

    public String getRhesis_comment() {
        return rhesis_comment;
    }

    public void setRhesis_comment(String rhesis_comment) {
        this.rhesis_comment = rhesis_comment;
    }

    public String getRhesis_appreciate() {
        return rhesis_appreciate;
    }

    public void setRhesis_appreciate(String rhesis_appreciate) {
        this.rhesis_appreciate = rhesis_appreciate;
    }

    public int getRhesis_evaluationMode() {
        return rhesis_evaluationMode;
    }

    public void setRhesis_evaluationMode(int rhesis_evaluationMode) {
        this.rhesis_evaluationMode = rhesis_evaluationMode;
    }

    public String getRhesis_questions() {
        return rhesis_questions;
    }

    public void setRhesis_questions(String rhesis_questions) {
        this.rhesis_questions = rhesis_questions;
    }

    public String getRhesis_creator() {
        return rhesis_creator;
    }

    public void setRhesis_creator(String rhesis_creator) {
        this.rhesis_creator = rhesis_creator;
    }

    public String getRhesis_date() {
        return rhesis_date;
    }

    public void setRhesis_date(String rhesis_date) {
        this.rhesis_date = rhesis_date;
    }

    public String getRhesis_updator() {
        return rhesis_updator;
    }

    public void setRhesis_updator(String rhesis_updator) {
        this.rhesis_updator = rhesis_updator;
    }

    public String getRhesis_updateDate() {
        return rhesis_updateDate;
    }

    public void setRhesis_updateDate(String rhesis_updateDate) {
        this.rhesis_updateDate = rhesis_updateDate;
    }

    public int getRhesis_difficulty() {
        return rhesis_difficulty;
    }

    public void setRhesis_difficulty(int rhesis_difficulty) {
        this.rhesis_difficulty = rhesis_difficulty;
    }

    public int getRhesis_like_number() {
        return rhesis_like_number;
    }

    public void setRhesis_like_number(int rhesis_like_number) {
        this.rhesis_like_number = rhesis_like_number;
    }

    public String getStudy_ID() {
        return study_ID;
    }

    public void setStudy_ID(String study_ID) {
        this.study_ID = study_ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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


    public void setRhesisPart(RhesisModel rhesisMoodel) {
        this.rhesis_ID = rhesisMoodel.getRhesis_ID();
        this.book_ID = rhesisMoodel.getBook_ID();
        this.rhesis_enable_flag = rhesisMoodel.getRhesis_enable_flag();
        this.rhesis_sentance = rhesisMoodel.getRhesis_sentance();
        this.rhesis_title = rhesisMoodel.getRhesis_title();
        this.rhesis_dynasty = rhesisMoodel.getRhesis_dynasty();
        this.rhesis_author = rhesisMoodel.getRhesis_author();
        this.rhesis_type = rhesisMoodel.getRhesis_type();
        this.rhesis_text = rhesisMoodel.getRhesis_text();
        this.rhesis_transiation = rhesisMoodel.getRhesis_transiation();
        this.rhesis_comment = rhesisMoodel.getRhesis_comment();
        this.rhesis_appreciate = rhesisMoodel.getRhesis_appreciate();
        this.rhesis_evaluationMode = rhesisMoodel.getRhesis_evaluationMode();
        this.rhesis_questions = rhesisMoodel.getRhesis_questions();
        this.rhesis_creator = rhesisMoodel.getRhesis_creator();
        this.rhesis_date = rhesisMoodel.getRhesis_date();
        this.rhesis_updator = rhesisMoodel.getRhesis_updator();
        this.rhesis_updateDate = rhesisMoodel.getRhesis_updateDate();
        this.rhesis_difficulty = rhesisMoodel.getRhesis_difficulty();
        this.rhesis_like_number = rhesisMoodel.getRhesis_like_number();
    }

    public void setUserLearnRecordPart(UserLearnRecordModel userLearnRecordModel) {
        this.study_ID = userLearnRecordModel.getStudy_ID();
        this.ID = userLearnRecordModel.getID();
        this.study_item_ID = userLearnRecordModel.getStudy_item_ID();
        this.study_creatorDate = userLearnRecordModel.getStudy_creatorDate();
        this.study_node = userLearnRecordModel.getStudy_node();
        this.study_nextDateTime = userLearnRecordModel.getStudy_nextDateTime();
        this.study_latestStudyTime = userLearnRecordModel.getStudy_latestStudyTime();
        this.hasStudyed = userLearnRecordModel.getHasStudyed();
    }

    public void initUserLearnRecordPart() {

//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        this.study_ID = null;
        this.ID = null;
        this.study_creatorDate = dateFormat.format(date);
        this.study_latestStudyTime = dateFormat.format(date);
        this.study_nextDateTime = dateFormat.format(date);
        this.study_node = 0;
        this.hasStudyed = 0;
    }

    public void minus() {

        Log.i(TAG, "节点降低!");
        Date date = new Date();
        Log.i(TAG, "更新前，最近学习时间为：" + this.study_latestStudyTime);
        this.study_latestStudyTime = dateFormat.format(date);
        Log.i(TAG, "更新后，最近学习时间为：" + this.study_latestStudyTime);

        StudyNodeModel studyNodeModel = NodeUtil.minus(this);
        Log.i(TAG, "更新前，学习节点为：" + this.study_node);
        this.study_node = studyNodeModel.getStudy_node();
        Log.i(TAG, "更新后，学习节点为：" + this.study_node);

        Log.i(TAG, "更新前，下一次学习时间为：" + this.study_nextDateTime);
        this.study_nextDateTime = studyNodeModel.getStudy_nextDateTime();
        Log.i(TAG, "更新后，下一次学习时间为：" + this.study_nextDateTime);
    }


    public void plus() {
        Log.i(TAG, "节点增长！");
        Date date = new Date();
        Log.i(TAG, "更新前，最近学习时间为：" + this.study_latestStudyTime);
        this.study_latestStudyTime = dateFormat.format(date);
        Log.i(TAG, "更新后，最近学习时间为：" + this.study_latestStudyTime);

        StudyNodeModel studyNodeModel = NodeUtil.plus(this);
        Log.i(TAG, "更新前，学习节点为：" + this.study_node);
        this.study_node = studyNodeModel.getStudy_node();
        Log.i(TAG, "更新后，学习节点为：" + this.study_node);


        Log.i(TAG, "更新前，下一次学习时间为：" + this.study_nextDateTime);
        this.study_nextDateTime = studyNodeModel.getStudy_nextDateTime();
        Log.i(TAG, "更新后，下一次学习时间为：" + this.study_nextDateTime);
        if (this.study_node > 6) {
            this.hasStudyed = 1;
        }

    }

    public void updateUserLearnRecordPart(UserLearnRecordModel userLearnRecordModel) {
        this.study_latestStudyTime = userLearnRecordModel.getStudy_latestStudyTime();
        this.study_nextDateTime = userLearnRecordModel.getStudy_nextDateTime();
        this.study_node = userLearnRecordModel.getStudy_node();
        this.hasStudyed = userLearnRecordModel.getHasStudyed();
    }
}
