package testlearn.shanxue.edu.shanxue01.create;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.PopupMenu;
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

    private MyDBHelper myDBHelper;
    private SQLiteDatabase dbRead, dbWrite;
    private List<MomoModel> momoModelList = new ArrayList<>();
    private UserInfoModle userInfoModle = new UserInfoModle();
    private Intent intent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.i(TAG, "onCreate");

        myDBHelper = new MyDBHelper(getActivity());
        dbRead = myDBHelper.getReadableDatabase();
        dbWrite = myDBHelper.getWritableDatabase();

        momoModelList = myDBHelper.loadMomo2Model(dbRead,momoModelList);

        SharedPreferences preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        userInfoModle.setNickName(preferences.getString("nickname", "no name"));
        userInfoModle.setID(preferences.getString("ID", "0"));
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

                holder.itemID.setText(String.valueOf(momoModelList.get(position).getMomo_ID()));
                holder.itemMomo.setText(momoModelList.get(position).getMomo_text());
                holder.itemHint.setText(momoModelList.get(position).getMomo_hintMain());
                holder.ibtnMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showPopup(holder, position);
                    }
                });

                CardView cardView = holder.itemView.findViewById(R.id.card_view);
                if (momoModelList.get(position).getMomo_enableFlag() == 1) {
                    cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorLightGreen));
                    holder.tvMomoNode.setText("" + momoModelList.get(position).getStudy_node());

                } else {
                    cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorLightYellow));
                    holder.tvMomoNode.setText("");

                }
//                Log.i(TAG,"(init) momo " + position + " enable flag is " + momoModelList.get(position).getMomo_enableFlag());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switchNode(view, holder, position);
//                        Log.i(TAG,"(pressed) momo " + position + " enable flag is " + momoModelList.get(position).getMomo_enableFlag());

                        myDBHelper.update(dbWrite, momoModelList.get(position));
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
                                        myDBHelper.delete(dbWrite, itemID);
                                        refresh();
                                    }
                                }).setNegativeButton("no", null).show();
                        return true;
                    }
                });
            }

            @Override
            public int getItemCount() {
                return momoModelList.size();
            }

            private void showPopup(final RecyclerAdapter.ViewHolder holder, final int position) {
                View menuItemView = holder.itemView.findViewById(R.id.card_view);

                PopupMenu popupMenu = new PopupMenu(getActivity(), menuItemView);
                MenuInflater inflater = popupMenu.getMenuInflater();

                inflater.inflate(R.menu.menu_card_momo, popupMenu.getMenu());
                Log.i(TAG, "position is: " + position);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_card_change:
                                Toast.makeText(getContext(), "card changed", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.item_card_delete:
                                myDBHelper.delete(dbWrite,Integer.parseInt(holder.itemID.getText().toString()));
                                refresh();
                                break;
                            default:
                                return false;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }

        };

        recyclerView.setAdapter(adapter);

    }

    private void drawActionButton(View view) {
        FloatingActionButton actionMomo = new FloatingActionButton(getActivity());
        FloatingActionButton actionNode = new FloatingActionButton(getActivity());


        actionMomo.setTitle("Add");
        actionMomo.setTag("momo");
        actionMomo.setColorNormal(ContextCompat.getColor(getActivity(), R.color.colorWhite));
        actionMomo.setColorPressed(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        actionMomo.setId(1);
        actionMomo.setIcon(R.drawable.ic_add);
        actionMomo.setOnClickListener(this);


        actionNode.setTitle("Long Momo");
        actionNode.setTag("node");
        actionNode.setColorNormal(ContextCompat.getColor(getActivity(), R.color.colorWhite));
        actionNode.setColorPressed(ContextCompat.getColor(getActivity(), R.color.colorPrimary));

        actionNode.setId(2);
        actionNode.setIcon(R.drawable.ic_create_gray);
        actionNode.setOnClickListener(this);


        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) view.findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(actionMomo);
        menuMultipleActions.addButton(actionNode);


        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(getResources().getColor(R.color.white));

    }

    private void switchNode(View view, RecyclerAdapter.ViewHolder holder, int position) {

        // to Study
        if (momoModelList.get(position).getMomo_enableFlag() == 0) {

            momoModelList.get(position).setMomo_enableFlag(1);
            momoModelList.get(position).setStudy_node(Math.max(0, momoModelList.get(position).getStudy_node()));

            CardView cardView = holder.itemView.findViewById(R.id.card_view);
            cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorLightGreen));


            holder.tvMomoNode.setText("" + momoModelList.get(position).getStudy_node());

            Log.i(TAG, "Entry " + position + " enable_flag is: " + momoModelList.get(position).getMomo_enableFlag());
            Snackbar.make(view, "Entry " + position + " in calcular!",
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } else {
            //out study
            momoModelList.get(position).setMomo_enableFlag(0);
            momoModelList.get(position).setStudy_node(-1);

            CardView cardView = holder.itemView.findViewById(R.id.card_view);
            cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorLightYellow));
            holder.tvMomoNode.setText("");

            Log.i(TAG, "Entry " + position + " enable_flag is: " + momoModelList.get(position).getMomo_enableFlag());
            Snackbar.make(view, "Entry " + position + " out calcular!",
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case 1:
                myDBHelper.addMomo(view,dbWrite,etMomoText,etMomoHint,node,userInfoModle);
                refresh();
                break;
            case 2:
                addNode();
                break;
        }
    }


    private void addNode() {
        intent = new Intent(getActivity(), AddNodeActivity.class);

        startActivity(intent);

    }

    private void refresh() {
        myDBHelper.loadMomo2Model(dbRead,momoModelList);
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
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("text") != null) {
                String text = bundle.getString("text");
                myDBHelper.addMomo(dbWrite, text,node,userInfoModle);
                Log.i(TAG, "text is " + text);
            }
        } else {
            Log.i(TAG, "text is null");
        }

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
}
