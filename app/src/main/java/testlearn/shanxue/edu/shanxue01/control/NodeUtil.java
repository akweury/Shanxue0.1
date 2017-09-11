package testlearn.shanxue.edu.shanxue01.control;

import testlearn.shanxue.edu.shanxue01.models.StudyNodeModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NodeUtil {
    private static final String TAG = "NodeUtil";


    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static StudyNodeModel minus(StudyNodeModel studyNodeModel) {


        Date date = new Date();
        int node = studyNodeModel.getStudy_node();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        switch (node){
            case 0:
                calendar.add(Calendar.MINUTE,1);break;
            case 1:
                calendar.add(Calendar.MINUTE,1);break;
            case 2:
                calendar.add(Calendar.MINUTE,60);break;
            case 3:
                calendar.add(Calendar.MINUTE,525);break;
            case 4:
                calendar.add(Calendar.MINUTE,1440);break;
            case 5:
                calendar.add(Calendar.MINUTE,2880);break;
            case 6:
                calendar.add(Calendar.MINUTE,8640);break;
            default:
                calendar.add(Calendar.MINUTE,8640);break;
        }
        if (node>0){
            --node;
        }

        //update study_node
        studyNodeModel.setStudy_node(node);
        //update study_nextDateTime
        date = calendar.getTime();
        studyNodeModel.setStudy_nextDateTime(dateFormat.format(date));


        return studyNodeModel;
    }

    public static StudyNodeModel plus(StudyNodeModel studyNodeModel) {


        Date date = new Date();
        int node = studyNodeModel.getStudy_node();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        switch (node){
            case 0:
                calendar.add(Calendar.MINUTE,1);break;
            case 1:
                calendar.add(Calendar.MINUTE,60);break;
            case 2:
                calendar.add(Calendar.MINUTE,525);break;
            case 3:
                calendar.add(Calendar.MINUTE,1440);break;
            case 4:
                calendar.add(Calendar.MINUTE,2880);break;
            case 5:
                calendar.add(Calendar.MINUTE,8640);break;
            case 6:
                calendar.add(Calendar.MINUTE,44640);break;
            default:
                calendar.add(Calendar.MINUTE,44640);break;
        }
        if (node>=0){
            ++node;
        }

        //update study_node
        studyNodeModel.setStudy_node(node);
        //update study_nextDateTime
        date = calendar.getTime();
        studyNodeModel.setStudy_nextDateTime(dateFormat.format(date));


        return studyNodeModel;
    }
}
