package testlearn.shanxue.edu.shanxue01.test;

import android.util.Log;
import testlearn.shanxue.edu.shanxue01.models.RhesisModel;
import testlearn.shanxue.edu.shanxue01.models.StudyNodeModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;
import testlearn.shanxue.edu.shanxue01.models.UserLearnRecordModel;

import java.util.List;

public class StudyTest {
    private static final String TAG = "test.Study";


    public static void testModelList(UserInfoModle userInfoModle, List<StudyNodeModel> studyNodeModelList, int surplus) {


        Log.i(TAG,"信息验证...");
        if(studyNodeModelList != null){
            Log.i(TAG,"study_id check----------" + studyNodeModelList.get(surplus-1).getStudy_ID());

            Log.i(TAG,"第一个词条题目： " + studyNodeModelList.get(surplus-1).getRhesis_title());
            Log.i(TAG,"用户学习纪录信息： " + studyNodeModelList.get(0).getStudy_item_ID());
            Log.i(TAG,"用户学习节点信息： " + studyNodeModelList.get(0).getStudy_node());
            Log.i(TAG,"rhesis_ID： " + studyNodeModelList.get(0).getRhesis_ID());
            Log.i(TAG,"all： " + studyNodeModelList.get(0).toString());
            if (userInfoModle !=null){
                Log.i(TAG,"用户信息： " + userInfoModle.getNickName());
            }else{
                Log.i(TAG,"用户信息包为空包...");
            }
        }else{
            Log.i(TAG,"用户学习纪录包为空包...");
        }
        Log.i(TAG,"验证完毕！");
    }

    public static void testModel(UserInfoModle userInfoModle){
        Log.i(TAG,"用户信息验证...");
        Log.i(TAG,"ID: " + userInfoModle.getID());
        Log.i(TAG,"password: " + userInfoModle.getPassword());
        Log.i(TAG,"nickName: " + userInfoModle.getNickName());
        Log.i(TAG,"portrait: " + userInfoModle.getPortrait());
        Log.i(TAG,"sex: "+ userInfoModle.getSex());
        Log.i(TAG,"birthday: " + userInfoModle.getBirthday());
        Log.i(TAG,"region: " + userInfoModle.getRegion());
        Log.i(TAG,"school: " + userInfoModle.getSchool());
        Log.i(TAG,"personalResume: " + userInfoModle.getPersonalResume());
        Log.i(TAG,"updateTime: "  + userInfoModle.getUpdateTime());
        Log.i(TAG,"createDate: " + userInfoModle.getCreateDate());
        Log.i(TAG,"userLevel: " + userInfoModle.getUserLevel());
        Log.i(TAG,"uploadEntry: " + userInfoModle.getUploadEntry());
        Log.i(TAG,"receiveLike: " + userInfoModle.getReceiveLike());
        Log.i(TAG,"others: " + userInfoModle.getOthers());
    }

    public static void testModel(UserLearnRecordModel userLearnRecordModel){
        Log.i(TAG,"学习记录验证...");
        Log.i(TAG,"study_ID: " + userLearnRecordModel.getStudy_ID());
        Log.i(TAG,"study_item_ID: " + userLearnRecordModel.getStudy_item_ID());
        Log.i(TAG,"ID: " + userLearnRecordModel.getID());
        Log.i(TAG,"study_createDate: " + userLearnRecordModel.getStudy_createDateTime());
        Log.i(TAG,"study_latestStudyTime: " + userLearnRecordModel.getStudy_latestStudyTime());
        Log.i(TAG,"study_nextDateTime: " + userLearnRecordModel.getStudy_nextDateTime());
        Log.i(TAG,"study_node: " + userLearnRecordModel.getStudy_node());
        Log.i(TAG,"hasStudyed: " + userLearnRecordModel.getHasStudyed());

    }

    public static void testModel(RhesisModel rhesisModel){
        Log.i(TAG,"词条信息验证...");
        Log.i(TAG,"rhesis_ID: " + rhesisModel.getRhesis_ID());
        Log.i(TAG,"book_ID: " + rhesisModel.getBook_ID());
        Log.i(TAG,"rhesis_enable_flag: " + rhesisModel.getRhesis_enable_flag());
        Log.i(TAG,"rhesis_sentance: " + rhesisModel.getRhesis_sentance());
        Log.i(TAG,"rhesis_title: " + rhesisModel.getRhesis_title());
        Log.i(TAG,"rhesis_dynasty: " + rhesisModel.getRhesis_dynasty());
        Log.i(TAG,"rhesis_author: " + rhesisModel.getRhesis_author());
        Log.i(TAG,"rhesis_type: " + rhesisModel.getRhesis_type());
        Log.i(TAG,"rhesis_text: " + rhesisModel.getRhesis_text());
        Log.i(TAG,"rhesis_transiation: " + rhesisModel.getRhesis_transiation());
        Log.i(TAG,"rhesis_comment: " + rhesisModel.getRhesis_comment());
        Log.i(TAG,"rhesis_appreciate: " + rhesisModel.getRhesis_appreciate());
        Log.i(TAG,"rhesis_evaluationMode: " + rhesisModel.getRhesis_evaluationMode());
        Log.i(TAG,"rhesis_questions: " + rhesisModel.getRhesis_questions());
        Log.i(TAG,"rhesis_creator: " + rhesisModel.getRhesis_creator());
        Log.i(TAG,"rhesis_date: " + rhesisModel.getRhesis_date());
        Log.i(TAG,"rhesis_updator: " + rhesisModel.getRhesis_updator());
        Log.i(TAG,"rhesis_updateDate: " + rhesisModel.getRhesis_updateDate());
        Log.i(TAG,"rhesis_difficulty: " + rhesisModel.getRhesis_difficulty());
        Log.i(TAG,"rhesis_like_number: " + rhesisModel.getRhesis_like_number());
    }

    public static void testNextTimeLearnRecord(UserLearnRecordModel userLearnRecordModel){
        Log.i(TAG,"study_ID: " + userLearnRecordModel.getStudy_ID());
        Log.i(TAG,"nextStudyTime: " + userLearnRecordModel.getStudy_nextDateTime());
    }

    public static void textNextTimeLearnRecordList(List<UserLearnRecordModel> userLearnRecordModelList){
        for(int i = 0; i<userLearnRecordModelList.size();i++){
            Log.i(TAG,"study_ID: " + userLearnRecordModelList.get(i).getStudy_ID());
            Log.i(TAG,"nextStudyTime: " + userLearnRecordModelList.get(i).getStudy_nextDateTime());
        }
    }
}
