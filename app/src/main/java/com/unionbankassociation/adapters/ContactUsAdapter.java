package com.unionbankassociation.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;

import com.unionbankassociation.databinding.RowContactusBinding;
import com.unionbankassociation.models.ContactUSListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Adapter for searching data like country,state,city,Phone code etc
 **/
public class ContactUsAdapter extends RecyclerView.Adapter<ContactUsAdapter.ViewHolder> {
    private Context mActivity;
    private List<ContactUSListBean> list;
    private List<ContactUSListBean> filterableList;
    private ItemFilter mFilter = new ItemFilter();

    public ContactUsAdapter(List<ContactUSListBean> list, Context mActivity) {
        this.mActivity = mActivity;
        this.list = list;
        this.filterableList = list;
    }

    @Override
    public ContactUsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contactus, parent, false);
        RowContactusBinding binding = RowContactusBinding.inflate(LayoutInflater.from(mActivity), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.bind(filterableList.get(position));
    }


    @Override
    public int getItemCount() {
        return filterableList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private RowContactusBinding mBinding;

        ViewHolder(RowContactusBinding view) {
            super(view.getRoot());
            mBinding=view;
        }

        public void bind(ContactUSListBean contactUSListBean) {

            mBinding.tvName.setText(contactUSListBean.getName());
            mBinding.tvEmail.setText(contactUSListBean.getEmail());
            mBinding.tvPlace.setText(contactUSListBean.getPlace());
            mBinding.tvPortfolio.setText(contactUSListBean.getPortfolio());
            mBinding.tvMobile.setText(contactUSListBean.getMobile());
        }
    }


    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<ContactUSListBean> listData = list;

            int count = listData.size();
            final ArrayList<ContactUSListBean> nlist = new ArrayList<ContactUSListBean>();

            for (int i = 0; i < count; i++) {
                if (listData.get(i).getPlace().toLowerCase().contains(filterString)) {
                    nlist.add(listData.get(i));
                }
            }
            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterableList = (List<ContactUSListBean>) results.values;
            notifyDataSetChanged();
        }
    }


    public Filter getFilter() {
        return mFilter;
    }

}

