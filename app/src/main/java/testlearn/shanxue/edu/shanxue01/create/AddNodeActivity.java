package testlearn.shanxue.edu.shanxue01.create;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.getbase.floatingactionbutton.FloatingActionButton;
import testlearn.shanxue.edu.shanxue01.R;

public class AddNodeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AddNode.activity";

    private EditText etMomo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_node);
        drawActionButton();
        etMomo = (EditText)findViewById(R.id.etWindowMomo);


    }

    private void drawActionButton() {
        FloatingActionButton actionMomo = (FloatingActionButton) findViewById(R.id.btn_windowMomo_add);
        actionMomo.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.i(TAG,"onClick");
        switch (view.getId()){
            case R.id.btn_windowMomo_add:
                sendValue2Createfragment();

                finish();
        }
    }

    private void sendValue2Createfragment() {
        Bundle bundle = new Bundle();
        bundle.putString("text",etMomo.getText().toString());
        Log.i(TAG,"etMomo is : " + etMomo.getText().toString());
    }
}
