package testlearn.shanxue.edu.shanxue01.create;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import testlearn.shanxue.edu.shanxue01.R;
import testlearn.shanxue.edu.shanxue01.control.DataUtil;

public class CreateFragment extends Fragment {
    private static final String TAG = "CreateFragment";

    private Button btnTEST;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create,container,false);
        Log.i(TAG,"onCreatView");
        btnTEST = (Button) view.findViewById(R.id.btnTest);

        //test code
        String tempString = "this is a test string.";


        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //send request and print the response using volley library
//                sendRequestAndPrintResponse();
                String testString = "{\n" +
                        "  \"learn_record\": [\n" +
                        "    {\n" +
                        "      \"study_ID\": \"S00001\",\n" +
                        "      \"study_item_ID\": \"E10100010001\",\n" +
                        "      \"ID\": \"U10001\",\n" +
                        "      \"study_createDate\": \"2017-08-18 00:00:00\",\n" +
                        "      \"study_latestStudyTime\": \"2017-08-18 21:45:20\",\n" +
                        "      \"study_nextDateTime\": \"2017-08-19 00:00:00\",\n" +
                        "      \"study_node\": 0,\n" +
                        "      \"hasStudyed\": 0\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"study_ID\": \"S00002\",\n" +
                        "      \"study_item_ID\": \"E10100010002\",\n" +
                        "      \"ID\": \"U10001\",\n" +
                        "      \"study_createDate\": \"2017/8/18 0:00\",\n" +
                        "      \"study_latestStudyTime\": \"2017-08-20 12:14:56\",\n" +
                        "      \"study_nextDateTime\": \"2017-08-20 13:12:43\",\n" +
                        "      \"study_node\": 1,\n" +
                        "      \"hasStudyed\": 0\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"study_ID\": \"S00003\",\n" +
                        "      \"study_item_ID\": \"E10100010003\",\n" +
                        "      \"ID\": \"U10001\",\n" +
                        "      \"study_createDate\": \"2017/8/22  19:47:10\",\n" +
                        "      \"study_latestStudyTime\": \"2017-08-23 19:52:24\",\n" +
                        "      \"study_nextDateTime\": \"2017-08-24 19:47:24\",\n" +
                        "      \"study_node\": 5,\n" +
                        "      \"hasStudyed\": 0\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";
                DataUtil.sendPost(testString,getActivity());
//                Toast.makeText(getActivity(), "return: ",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }



    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
//        if(mRequestQueue!=null){
//            mRequestQueue.cancelAll(REQUESTTAG);
//        }


    }
}
