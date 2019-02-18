package com.unionbankassociation.custom_dialogs;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.unionbankassociation.R;
import com.unionbankassociation.adapters.CustomPlaceAdapter;
import com.unionbankassociation.databinding.DialogSelectPlaceBinding;
import com.unionbankassociation.interfaces.DialogCallBack;
import com.unionbankassociation.models.ContactUSListBean;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomIPlaceDialog extends Dialog {

    private Context context;
    private ArrayList<ContactUSListBean> mList, newList;
    private DialogSelectPlaceBinding mbinding;
    private DialogCallBack dialogCallBack;

    public CustomIPlaceDialog(Context context, ArrayList<ContactUSListBean> mList, DialogCallBack dialogCallBack) {
        super(context);
        this.context = context;
        this.mList = mList;
        this.dialogCallBack = dialogCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mbinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_select_place, null, false);
        setContentView(mbinding.getRoot());
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setUpList();
    }

    private void setUpList() {
        setList(mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mbinding.rvSelectPlace.setLayoutManager(linearLayoutManager);
        CustomPlaceAdapter adapter = new CustomPlaceAdapter(context, newList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = (String) v.getTag();
                dialogCallBack.onSubmit(title);
                dismiss();
            }
        });
        mbinding.rvSelectPlace.setAdapter(adapter);
    }

    private void setList(ArrayList<ContactUSListBean> mList) {
        newList = new ArrayList<>();
        HashMap<String, ContactUSListBean> mNewList = new HashMap<>();
        for (int i = 0; i < mList.size(); i++) {
            mNewList.put(mList.get(i).getPlace(), mList.get(i));
        }
        newList.addAll(mNewList.values());
    }
}
