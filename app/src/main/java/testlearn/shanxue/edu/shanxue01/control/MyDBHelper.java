package testlearn.shanxue.edu.shanxue01.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import testlearn.shanxue.edu.shanxue01.models.MomoModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;

import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "MyDBHelper";


    private static final String DBNAME = "shanxue.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "entry";

    public static final String ID = "_id";
    public static final String TYPE = "_type";
    public static final String LABEL = "_label";
    public static final String TEXT = "_text";
    public static final String HINT_MAIN = "_hint_main";
    public static final String ENABLE_FLAG = "_enable_flag";
    public static final String HINT_OTHERS = "_hint_others";
    public static final String CREATOR = "_thecreator";
    public static final String LOG = "_mylog";


    public static final String CREATE_DATETIME = "_create_datetime";
    public static final String STUDY_NODE = "_study_node";
    public static final String STUDY_NEXT_DATETIME = "_study_next_datetime";
    public static final String LATEST_STUDY_TIME = "_latestStudyTime";
    public static final String HAS_STUDYED = "_has_studyed";




    public MyDBHelper(Context context) {

        super(context, DBNAME, null, VERSION);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.i(TAG, "onOpen");
        super.onOpen(db);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "create a new Table");
        String queryTable = " CREATE TABLE " + TABLE_NAME +
                " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TYPE + " TEXT, " +
                LABEL + " TEXT, " +
                TEXT + " TEXT NOT NULL, " +
                HINT_MAIN + " TEXT, " +
                HINT_OTHERS + " TEXT, " +
                STUDY_NODE + " INTEGER " +
                ENABLE_FLAG + " INTEGER " +
                CREATOR + " TEXT " +
                CREATE_DATETIME + " TEXT " +
                STUDY_NEXT_DATETIME + " TEXT " +
                LATEST_STUDY_TIME + " TEXT " +
                HAS_STUDYED + " INTEGER " +
                LOG + " TEXT " +
                " )";
        String queryTable2 = "create table " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, _type TEXT, _label TEXT, _text TEXT, _hint_main TEXT, " +
                "_study_node INTEGER, _enable_flag INTEGER,  _hint_others TEXT, _thecreator TEXT, " +
                "_create_datetime TEXT, _study_next_datetime TEXT, _latestStudyTime TEXT, _has_studyed INTEGER, _mylog TEXT)";
        try {
            sqLiteDatabase.execSQL(queryTable2);
            Log.i(TAG, "create succeed!");
        } catch (SQLException e) {
            Log.i(TAG, "create failed");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void insert(SQLiteDatabase dbWrite,String text,String hint, int node, UserInfoModle userInfoModle) {

        TimeUtil timeUtil = new TimeUtil();
        ContentValues values = new ContentValues();

        values.put(TYPE,"");
        values.put(LABEL,"");
        values.put(TEXT,text);
        values.put(HINT_MAIN, hint);
        values.put(STUDY_NODE, node);
        values.put(ENABLE_FLAG, 0);
        values.put(HINT_OTHERS, "");
        values.put(CREATOR, userInfoModle.getNickName());
        values.put(CREATE_DATETIME, timeUtil.getCurrentTime());
        values.put(STUDY_NEXT_DATETIME, timeUtil.getCurrentTime());
        values.put(LATEST_STUDY_TIME, timeUtil.getCurrentTime());
        values.put(HAS_STUDYED,0);
        values.put(LOG, " log: " + timeUtil.getCurrentTime() + "\n");

        long result = dbWrite.insert("entry",null,values);
        Log.i(TAG,result + " row(s) is insterted");
    }


    public void update(SQLiteDatabase dbWrite, MomoModel momoModel) {
        ContentValues values = new ContentValues();

        values.put(TYPE, momoModel.getMomo_type());
        values.put(LABEL, momoModel.getMomo_label());
        values.put(TEXT, momoModel.getMomo_text());
        values.put(HINT_MAIN, momoModel.getMomo_hintMain());
        values.put(STUDY_NODE, momoModel.getStudy_node());
        values.put(ENABLE_FLAG, momoModel.getMomo_enableFlag());
        values.put(HINT_OTHERS, momoModel.getMomo_hintOthers());
        values.put(STUDY_NEXT_DATETIME, momoModel.getStudy_nextDateTime());
        values.put(LATEST_STUDY_TIME, momoModel.getStudy_latestStudyTime());
        values.put(HAS_STUDYED,momoModel.getHasStudyed());
        values.put(LOG, momoModel.getMomo_log());

        String where = ID + " = " + momoModel.getMomo_ID();

//        Log.i(TAG,"(mydbhelper)momoModel enable flag is : " + momoModel.getMomo_text());
        int result = dbWrite.update(TABLE_NAME, values, where, null);
        Log.i(TAG,result + " row(s) updated");
    }

    public void delete(SQLiteDatabase dbWrite, int id) {

        String where = ID + " = " + id;
        int result = dbWrite.delete(TABLE_NAME, where, null);
        Log.i(TAG,result + " row(s) deleted.");
    }

    public List<MomoModel> loadMomo2Model(SQLiteDatabase dbRead, List<MomoModel> momoModelList) {

        Cursor cursor = null;
        cursor = dbRead.query("entry", null, null, null, null, null, null);
        momoModelList.clear();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            MomoModel momoModel = new MomoModel();

            //TODO: momoModel.setMomo_ID(cursor.getPosition()+1);
            momoModel.setMomo_ID(cursor.getInt(cursor.getColumnIndex(ID)));
            momoModel.setMomo_type(cursor.getString(cursor.getColumnIndex(TYPE)));
            momoModel.setMomo_label(cursor.getString(cursor.getColumnIndex(LABEL)));
            momoModel.setMomo_text(cursor.getString(cursor.getColumnIndex(TEXT)));
            momoModel.setMomo_hintMain(cursor.getString(cursor.getColumnIndex(HINT_MAIN)));
            momoModel.setMomo_enableFlag(cursor.getInt(cursor.getColumnIndex(ENABLE_FLAG)));
            momoModel.setMomo_hintOthers(cursor.getString(cursor.getColumnIndex(HINT_OTHERS)));
            momoModel.setMomo_creator(cursor.getString(cursor.getColumnIndex(CREATOR)));
            momoModel.setMomo_log(cursor.getString(cursor.getColumnIndex(LOG)));

            momoModel.setStudy_node(cursor.getInt(cursor.getColumnIndex(STUDY_NODE)));
            momoModel.setStudy_creatorDate(cursor.getString(cursor.getColumnIndex(CREATE_DATETIME)));
            momoModel.setStudy_nextDateTime(cursor.getString(cursor.getColumnIndex(STUDY_NEXT_DATETIME)));
            momoModel.setStudy_latestStudyTime(cursor.getString(cursor.getColumnIndex(LATEST_STUDY_TIME)));
            momoModel.setHasStudyed(cursor.getInt(cursor.getColumnIndex(HAS_STUDYED)));

            momoModelList.add(momoModel);
        }
        Log.i(TAG, "loadMomo2Model size: " + momoModelList.size());
        return momoModelList;


    }

    public void addMomo(View view, SQLiteDatabase dbWrite, TextView etMomoText, TextView etMomoHint, int node, UserInfoModle userInfoModle) {
        Log.i(TAG,"addMomo");
        String hint = etMomoHint.getText().toString().trim();
        String text = etMomoText.getText().toString().trim();

//        if (hint.matches("") && !text.matches("")) {
//            etMomoHint.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
//            etMomoText.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
//            Toast.makeText(getActivity(), "Please input the hint!", Toast.LENGTH_SHORT).show();
//        } else if (text.matches("") && !hint.matches("")) {
//            etMomoHint.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
//            etMomoText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
//            Toast.makeText(getActivity(), "Please input the Text!", Toast.LENGTH_SHORT).show();
//        } else if (hint.matches("") && text.matches("")) {
//            etMomoText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
//            etMomoHint.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
//            Toast.makeText(getActivity(), "Please input the Text&Hint!", Toast.LENGTH_SHORT).show();
//        } else {
//            etMomoHint.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
//            etMomoText.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
//
//            myDBHelper.insert(dbWrite, etMomoText, etMomoHint, node, userInfoModle);
//        }

        Log.i(TAG,"hint :" + hint);
        Log.i(TAG,"text :" + text);
        etMomoText.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        etMomoHint.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        boolean flag = true;
        if (text.matches("")) {
            etMomoText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            flag = false;
        }
        if (hint.matches("")) {
            etMomoHint.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            flag = false;
        }
        if (flag) {
            insert(dbWrite, text, hint, node, userInfoModle);
        }
    }

    public void addMomo(SQLiteDatabase dbWrite, String text, int node, UserInfoModle userInfoModle) {
        if (!text.matches("")) {
            insert(dbWrite, text, "", node, userInfoModle);
        }
    }

//    public Cursor getAllRecords() {
//
////        myDB.query(TABLE_NAME,null,null,null,null,null,null);
//
//        String query = "SELECT * FROM " + TABLE_NAME;
//        Cursor cursor = myDB.rawQuery(query, null);
//        return cursor;
//    }

}
