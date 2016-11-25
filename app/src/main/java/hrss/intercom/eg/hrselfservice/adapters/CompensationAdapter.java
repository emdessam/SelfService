package hrss.intercom.eg.hrselfservice.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hrss.intercom.eg.hrselfservice.R;
import hrss.intercom.eg.hrselfservice.api.LstCompHst;


/**
 * Created by Emad.Essam on 11/14/2016.
 */

public class CompensationAdapter extends RecyclerView.Adapter<CompensationAdapter.ViewHolder> {


    Context mContext;
    String TAG = "PermissionAdapter";

    ArrayList<LstCompHst> requestsHistory;

    public CompensationAdapter(Context context, ArrayList<LstCompHst> lstCompHstArrayList) {

        this.mContext = context;


        this.requestsHistory = lstCompHstArrayList;


    }

    @Override
    public CompensationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.comp_item_history, null);
        ViewHolder viewHolder = new ViewHolder(view);
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CompensationAdapter.ViewHolder holder, int position) {
        LstCompHst lst = requestsHistory.get(position);

        holder.remark.setText(lst.getRemarks());
        holder.startDate.setText(lst.getStartDate().toString());
        String sta = lst.getStatus().toString();
        if (sta.equalsIgnoreCase("0")) {

            sta = "Accepted";
        } else {
            sta = "Rejected";
        }

        holder.status.setText(sta);
        holder.days.setText(lst.getNoOfDays().toString() + "  Days");

    }

    @Override
    public int getItemCount() {
        return (null != requestsHistory ? requestsHistory.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView remark;
        protected TextView startDate;
        protected TextView status;
        protected TextView days;


        public ViewHolder(View view) {

            super(view);
            this.remark = (TextView) view.findViewById(R.id.comp_remarks);
            this.startDate = (TextView) view.findViewById(R.id.comp_date);
            this.status = (TextView) view.findViewById(R.id.comp_status);
            this.days = (TextView) view.findViewById(R.id.comp_days);

        }


    }
}
