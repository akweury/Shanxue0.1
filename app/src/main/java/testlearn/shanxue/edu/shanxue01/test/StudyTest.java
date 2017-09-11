package testlearn.shanxue.edu.shanxue01.test;

import android.util.Log;
import testlearn.shanxue.edu.shanxue01.models.StudyNodeModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;

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
}
