package com.unionbankassociation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unionbankassociation.R;
import com.unionbankassociation.models.ContactUSListBean;

import java.util.ArrayList;

public class CustomPlaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ContactUSListBean> mNotificationList;
    private View.OnClickListener listener;

    public CustomPlaceAdapter(Context context, ArrayList<ContactUSListBean> mNotificationList, View.OnClickListener listener) {
        this.context = context;
        this.mNotificationList = mNotificationList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_place_view, viewGroup, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        NotificationViewHolder holder = null;
        holder = (NotificationViewHolder) viewHolder;
        holder.bind(mNotificationList.get(i));
    }

    @Override
    public int getItemCount() {
        return mNotificationList.size();
    }

    private class NotificationViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView title, tvTime;

        public NotificationViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            title.setOnClickListener(listener);
        }

        public void bind(ContactUSListBean noticData) {
            title.setText(noticData.getPlace());
            title.setTag(noticData.getPlace());
        }
    }
}
