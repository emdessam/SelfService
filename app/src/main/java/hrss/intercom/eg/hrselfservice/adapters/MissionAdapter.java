package hrss.intercom.eg.hrselfservice.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hrss.intercom.eg.hrselfservice.R;
import hrss.intercom.eg.hrselfservice.api.LstMisHst;

/**
 * Created by Emad.Essam on 11/14/2016.
 */

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolder> {

    Context mContext;
    String TAG = "MissionAdapter";
    ArrayList<LstMisHst> requestsHistory;

    public MissionAdapter(Context context, ArrayList<LstMisHst> lstMisHstArrayList) {

        this.mContext = context;


        this.requestsHistory = lstMisHstArrayList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.mis_item_history, null);
        ViewHolder viewHolder = new ViewHolder(view);
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LstMisHst lst = requestsHistory.get(position);
        String sta = lst.getStatus().toString();
        if (sta.equalsIgnoreCase("0")) {

            sta = "Accepted";
        } else {
            sta = "Rejected";
        }
        String misT = lst.getMisType().toString();
        if (misT.equalsIgnoreCase("S")) {

            misT = "Partial Mission";
        } else {

            misT = "Multiple Mission";

        }
        holder.remark.setText(lst.getRemarks());
        holder.startDate.setText(lst.getStartDate());
        holder.endDate.setText(lst.getEndDate());
        holder.status.setText(sta);
        holder.duration.setText(lst.getDuration() + "  Days");
        holder.misType.setText(misT);

    }

    @Override
    public int getItemCount() {
        return (null != requestsHistory ? requestsHistory.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView remark;
        protected TextView status;
        protected TextView endDate;
        protected TextView duration;
        protected TextView misType;

        protected TextView startDate;

        public ViewHolder(View view) {

            super(view);
            this.status = (TextView) view.findViewById(R.id.mis_status);
            this.endDate = (TextView) view.findViewById(R.id.mis_end_date);
            this.duration = (TextView) view.findViewById(R.id.mis_duration);
            this.misType = (TextView) view.findViewById(R.id.mis_type);
            this.remark = (TextView) view.findViewById(R.id.mis_remarks);
            this.startDate = (TextView) view.findViewById(R.id.mis_start_date);

        }


    }
}
//    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
//    CustomViewHolder viewHolder = new CustomViewHolder(view);