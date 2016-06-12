package com.bobomee.blogdemos.dialog_fragment;

import android.app.Dialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.bobomee.android.common.util.StringUtil;
import com.bobomee.blogdemos.R;
import com.bobomee.blogdemos.base.BaseDialogFragment;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bobomee on 16/1/10.
 */
public class BottomDialogFragment extends BaseDialogFragment {

    private List<ListItem> datas = new ArrayList<>();

    protected Dialog dialog;

    @Override
    public View initFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {

        TypedArray imgs = getResources().obtainTypedArray(R.array.bottom_list);

        for (int i = 0; i < imgs.length(); ++i) {

            String content = StringUtil.getRandomString();
            // index ,defaultValue
            datas.add(new ListItem(imgs.getResourceId(i, R.mipmap.perm_group_system_tools), content));
        }

        imgs.recycle();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BottomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_bottom_layout, null);

        initView(view);

        builder.setView(view);

        dialog = builder.create();

        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        return dialog;
    }

    private void initView(View view) {
        ListView listView = (ListView) view.findViewById(android.R.id.list);

        listView.setAdapter(new QuickAdapter<ListItem>(baseA, R.layout.dialog_bottom_item, datas) {
            @Override
            protected void convert(BaseAdapterHelper helper, ListItem item) {
                helper.setImageResource(android.R.id.icon,item.resId);
                helper.setText(android.R.id.text1, item.content);
            }
        });
    }
}
