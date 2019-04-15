package com.unionbankassociation.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unionbankassociation.R;
import com.unionbankassociation.activities.PdfWebView;
import com.unionbankassociation.models.PdfData;

import java.util.ArrayList;

public class PdfAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<PdfData> mNotificationList;
    private View.OnClickListener listener;

    public PdfAdapter(Context context, ArrayList<PdfData> mNotificationList) {
        this.context = context;
        this.mNotificationList = mNotificationList;
//        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_pdf_view, viewGroup, false);
        return new PdfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PdfViewHolder holder = null;
        holder = (PdfViewHolder) viewHolder;
        holder.bind(mNotificationList.get(i));
    }

    @Override
    public int getItemCount() {
        return mNotificationList.size();
    }

    private class PdfViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView title, tvTime, tvDescription;
        private CardView mainCard;
        AppCompatImageView imageView, ivNotice;

        public PdfViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.iv_share);
            title = view.findViewById(R.id.title);
            tvTime = view.findViewById(R.id.time);
            mainCard = view.findViewById(R.id.main_card);
            ivNotice = view.findViewById(R.id.iv_notice);
            tvDescription = view.findViewById(R.id.description);

            mainCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PdfWebView.class);
                    intent.putExtra("pdf", mNotificationList.get(getAdapterPosition()).getDocumentLink());
                    intent.putExtra("TITLE", mNotificationList.get(getAdapterPosition()).getDocumentTitle());
                    context.startActivity(intent);
                }
            });

        }

        public void bind(PdfData noticData) {
            title.setText(noticData.getDocumentTitle());
            tvTime.setText(noticData.getCreatedDate());
            tvDescription.setText(noticData.getDescription());
        }
    }
}
