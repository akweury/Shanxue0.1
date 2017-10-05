package testlearn.shanxue.edu.shanxue01.create;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import testlearn.shanxue.edu.shanxue01.R;
import testlearn.shanxue.edu.shanxue01.control.MyDBHelper;
import testlearn.shanxue.edu.shanxue01.models.MomoModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CreateFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "CreateFragment";

    private int node = -1;

    private EditText etMomoText;
    private EditText etMomoHint;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    MyDBHelper myDBHelper;
    private SQLiteDatabase dbRead,dbWrite;
    private List<MomoModel> momoModelList = new ArrayList<>();
    private UserInfoModle userInfoModle = new UserInfoModle();

    private boolean flag1 = false;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");

        myDBHelper = new MyDBHelper(getActivity());
        dbRead = myDBHelper.getReadableDatabase();
        dbWrite = myDBHelper.getWritableDatabase();

        loadMomo2Model();

        SharedPreferences preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        userInfoModle.setNickName(preferences.getString("nickname", "no name"));
        userInfoModle.setID(preferences.getString("ID", "0"));
    }

    private void loadMomo2Model() {

        Cursor cursor = null;
        cursor = dbRead.query("entry",null,null,null,null,null,null );
        momoModelList.clear();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            MomoModel momoModel = new MomoModel();

            //TODO: momoModel.setMomo_ID(cursor.getPosition()+1);
            momoModel.setMomo_ID(cursor.getInt(cursor.getColumnIndex(MyDBHelper.ID)));
            momoModel.setMomo_type(cursor.getString(cursor.getColumnIndex(MyDBHelper.TYPE)));
            momoModel.setMomo_label(cursor.getString(cursor.getColumnIndex(MyDBHelper.LABEL)));
            momoModel.setMomo_text(cursor.getString(cursor.getColumnIndex(MyDBHelper.TEXT)));
            momoModel.setMomo_hintMain(cursor.getString(cursor.getColumnIndex(MyDBHelper.HINT_MAIN)));
            momoModel.setMomo_study_node(cursor.getInt(cursor.getColumnIndex(MyDBHelper.STUDY_NODE)));
            momoModel.setMomo_enableFlag(cursor.getInt(cursor.getColumnIndex(MyDBHelper.ENABLE_FLAG)));
            momoModel.setMomo_hintOthers(cursor.getString(cursor.getColumnIndex(MyDBHelper.HINT_OTHERS)));
            momoModel.setMomo_creator(cursor.getString(cursor.getColumnIndex(MyDBHelper.CREATOR)));
            momoModel.setMomo_createDateTime(cursor.getString(cursor.getColumnIndex(MyDBHelper.CREATE_DATETIME)));
            momoModel.setMomo_updateTime(cursor.getString(cursor.getColumnIndex(MyDBHelper.UPDATE_TIME)));
            momoModel.setMomo_latestStudyTime(cursor.getString(cursor.getColumnIndex(MyDBHelper.LATEST_STUDY_TIME)));
            momoModel.setMomo_log(cursor.getString(cursor.getColumnIndex(MyDBHelper.LOG)));

            momoModelList.add(momoModel);
        }
        Log.i(TAG,"loadMomo2Model: " + momoModelList.size());


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_create, container, false);
        Log.i(TAG, "onCreatView");

        drawRecyclerView(view);
        drawEditMomo(view);
        //https://github.com/futuresimple/android-floating-action-button
        drawActionButton(view);

        return view;
    }

    private void drawEditMomo(View view) {
        etMomoText = (EditText) view.findViewById(R.id.etMomoText);
        etMomoHint = (EditText) view.findViewById(R.id.etMomoHint);
    }

    private void drawRecyclerView(final View view) {
        recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_momo, parent, false);

                RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(v);

                return viewHolder;
            }

            @Override
            public void onBindViewHolder(final RecyclerAdapter.ViewHolder holder, final int position) {

                holder.itemID.setText( String.valueOf(momoModelList.get(position).getMomo_ID()));
                holder.itemMomo.setText(momoModelList.get(position).getMomo_text());
                holder.itemHint.setText(momoModelList.get(position).getMomo_hintMain());

                CardView cardView = holder.itemView.findViewById(R.id.card_view);
                if (momoModelList.get(position).getMomo_enableFlag()==1)
                    cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorLightGreen));
                else {
                    cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorLightYellow));
                }
//                Log.i(TAG,"(init) momo " + position + " enable flag is " + momoModelList.get(position).getMomo_enableFlag());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switchNode(view,holder,position);
//                        Log.i(TAG,"(pressed) momo " + position + " enable flag is " + momoModelList.get(position).getMomo_enableFlag());

                        myDBHelper.update(dbWrite,momoModelList.get(position));
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        new AlertDialog.Builder(getActivity()).setTitle("coution!").setMessage("delete or not")
                                .setPositiveButton("yes", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        int itemID = Integer.parseInt(holder.itemID.getText().toString());
                                        myDBHelper.delete(dbWrite,itemID);
//                                        dbWrite.delete("entry","_id=?",new String[]{itemID+""});
                                        refresh();
                                    }
                                }).setNegativeButton("no",null).show();
                        return true;
                    }
                });
            }

            @Override
            public int getItemCount() {
                return momoModelList.size();
            }
        };

        recyclerView.setAdapter(adapter);

    }

    private void drawActionButton(View view) {
        FloatingActionButton actionMomo = new FloatingActionButton(getActivity());
        FloatingActionButton actionNode = new FloatingActionButton(getActivity());


        actionMomo.setTitle("Momo");
        actionMomo.setTag("momo");
        actionMomo.setId(1);
        actionMomo.setIcon(R.drawable.ic_add_circle);
        actionMomo.setOnClickListener(this);


        actionNode.setTitle("Node");
        actionNode.setTag("node");
        actionNode.setId(2);
        actionNode.setIcon(R.drawable.ic_timelapse);
        actionNode.setOnClickListener(this);


        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) view.findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(actionMomo);
        menuMultipleActions.addButton(actionNode);


        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(getResources().getColor(R.color.white));

    }

    private void switchNode(View view, RecyclerAdapter.ViewHolder holder, int position) {
        if (momoModelList.get(position).getMomo_enableFlag()==0){
            momoModelList.get(position).setMomo_enableFlag(1);
            CardView cardView =  holder.itemView.findViewById(R.id.card_view);
            cardView.setCardBackgroundColor(Color.parseColor("#8BC34A"));


            Log.i(TAG,"Entry " + position + " enable_flag is: " + momoModelList.get(position).getMomo_enableFlag());
            Snackbar.make(view, "Entry " + position + " in calcular!",
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }else {
            momoModelList.get(position).setMomo_enableFlag(0);

            CardView cardView =  holder.itemView.findViewById(R.id.card_view);
            cardView.setCardBackgroundColor(Color.parseColor("#d2eddf41"));

            Log.i(TAG,"Entry " + position + " enable_flag is: " + momoModelList.get(position).getMomo_enableFlag());
            Snackbar.make(view, "Entry " + position + " out calcular!",
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case 1:
                addMomo();
                refresh();
                break;
            case 2:
                break;
        }
    }

    private void addMomo() {
        String hint = etMomoHint.getText().toString().trim();
        String text = etMomoText.getText().toString().trim();
        if(hint.matches("") && !text.matches("")){
            etMomoHint.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            etMomoText.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(getActivity(),"Please input the hint!",Toast.LENGTH_SHORT).show();
        }else if(text.matches("")&&!hint.matches("")){
            etMomoHint.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
            etMomoText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(getActivity(),"Please input the Text!",Toast.LENGTH_SHORT).show();
        }else if(hint.matches("")&&text.matches("")){
            etMomoText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            etMomoHint.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(getActivity(),"Please input the Text&Hint!",Toast.LENGTH_SHORT).show();
        }else {
            etMomoHint.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
            etMomoText.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);

            myDBHelper.instert(dbWrite,etMomoText,etMomoHint,node,userInfoModle);
        }
    }

    private void refresh() {
        loadMomo2Model();
        adapter.notifyDataSetChanged();
    }






    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");


    }

//    private void addMomo(SQLiteDatabase dbWrite, EditText etMomoText, EditText etMomoHint, UserInfoModle userInfoModle) {
//        TimeUtil timeUtil = new TimeUtil();
//        ContentValues values = new ContentValues();
//        values.put("_type","");
//        values.put("_label","");
//        values.put("_text", this.etMomoText.getText().toString());
//        values.put("_hint_main", this.etMomoHint.getText().toString());
//        values.put("_study_node", node);
//        values.put("_enable_flag", 0);
//        values.put("_hint_others", "");
//        values.put("_thecreator", this.userInfoModle.getNickName());
//        values.put("_create_datetime", timeUtil.getCurrentTime());
//        values.put("_update_time", timeUtil.getCurrentTime());
//        values.put("_latestStudyTime", timeUtil.getCurrentTime());
//        values.put("_mylog", " log: " + timeUtil.getCurrentTime() + "\n");
//        long result = this.dbWrite.insert("entry",null,values);
//        if (result == -1) {
//            Toast.makeText(getActivity(), "Some error occured while inserting", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getActivity(), "Data inserted successfully : " + result, Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_card_momo, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
