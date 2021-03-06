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
import hrss.intercom.eg.hrselfservice.api.LstPerHst;

/**
 * Created by Emad.Essam on 11/14/2016.
 */

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.ViewHolder> {


    Context mContext;
    String TAG = "PermissionAdapter";

    ArrayList<LstPerHst> requestsHistory;

    public PermissionAdapter(Context context, ArrayList<LstPerHst> lstPerHstArrayList) {
        this.mContext = context;


        this.requestsHistory = lstPerHstArrayList;


    }

    @Override
    public PermissionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.per_item_history, null);
        ViewHolder viewHolder = new ViewHolder(view);
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PermissionAdapter.ViewHolder holder, int position) {
        LstPerHst lst = requestsHistory.get(position);

        holder.name.setText(lst.getPerEnName());
        holder.startDate.setText(lst.getStartDate());
        String sta = lst.getStatus().toString();
        if (sta.equalsIgnoreCase("0")) {

            sta = "Accepted";
        } else {
            sta = "Rejected";
        }

        holder.status.setText(sta);

        holder.endDate.setText(lst.getEndDate());
        holder.duration.setText(lst.getDuration() + "  Days");

    }

    @Override
    public int getItemCount() {
        return (null != requestsHistory ? requestsHistory.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView status;
        protected TextView duration;
        protected TextView name;
        protected TextView endDate;
        protected TextView startDate;

        public ViewHolder(View view) {

            super(view);
            this.name = (TextView) view.findViewById(R.id.per_name);
            this.startDate = (TextView) view.findViewById(R.id.per_start_date);
            this.status = (TextView) view.findViewById(R.id.per_status);
            this.endDate = (TextView) view.findViewById(R.id.per_end_date);
            this.duration = (TextView) view.findViewById(R.id.per_duration);
        }


    }
}
