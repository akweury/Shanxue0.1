package testlearn.shanxue.edu.shanxue01.book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import testlearn.shanxue.edu.shanxue01.R;


public class BookFragment extends Fragment {
    private static final String TAG = "BookFragment";

    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book,container,false);
        Log.i(TAG,"onCreatView");
        btnTEST = (Button) view.findViewById(R.id.btnTest);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON BookFragment",Toast.LENGTH_SHORT).show();
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

    }
}
