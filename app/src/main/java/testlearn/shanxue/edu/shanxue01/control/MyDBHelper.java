package testlearn.shanxue.edu.shanxue01.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import testlearn.shanxue.edu.shanxue01.models.MomoModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;

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
    public static final String STUDY_NODE = "_study_node";
    public static final String ENABLE_FLAG = "_enable_flag";
    public static final String HINT_OTHERS = "_hint_others";
    public static final String CREATOR = "_thecreator";
    public static final String CREATE_DATETIME = "_create_datetime";
    public static final String UPDATE_TIME = "_update_time";
    public static final String LATEST_STUDY_TIME = "_latestStudyTime";
    public static final String LOG = "_mylog";


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
                UPDATE_TIME + " TEXT " +
                LATEST_STUDY_TIME + " TEXT " +
                LOG + " TEXT " +
                " )";
        String queryTable2 = "create table " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, _type TEXT, _label TEXT, _text TEXT, _hint_main TEXT, _study_node INTEGER, _enable_flag INTEGER,  _hint_others TEXT, _thecreator TEXT, _create_datetime TEXT, _update_time TEXT, _latestStudyTime TEXT, _mylog TEXT)";
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

    public void instert(SQLiteDatabase dbWrite, EditText etMomoText, EditText etMomoHint, int node, UserInfoModle userInfoModle) {

        TimeUtil timeUtil = new TimeUtil();
        ContentValues values = new ContentValues();

        values.put(TYPE,"");
        values.put(LABEL,"");
        values.put(TEXT,etMomoText.getText().toString());
        values.put(HINT_MAIN, etMomoHint.getText().toString());
        values.put(STUDY_NODE, node);
        values.put(ENABLE_FLAG, 0);
        values.put(HINT_OTHERS, "");
        values.put(CREATOR, userInfoModle.getNickName());
        values.put(CREATE_DATETIME, timeUtil.getCurrentTime());
        values.put(UPDATE_TIME, timeUtil.getCurrentTime());
        values.put(LATEST_STUDY_TIME, timeUtil.getCurrentTime());
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
        values.put(STUDY_NODE, momoModel.getMomo_study_node());
        values.put(ENABLE_FLAG, momoModel.getMomo_enableFlag());
        values.put(HINT_OTHERS, momoModel.getMomo_hintOthers());
        values.put(UPDATE_TIME, momoModel.getMomo_updateTime());
        values.put(LATEST_STUDY_TIME, momoModel.getMomo_latestStudyTime());
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

//    public Cursor getAllRecords() {
//
////        myDB.query(TABLE_NAME,null,null,null,null,null,null);
//
//        String query = "SELECT * FROM " + TABLE_NAME;
//        Cursor cursor = myDB.rawQuery(query, null);
//        return cursor;
//    }

}
