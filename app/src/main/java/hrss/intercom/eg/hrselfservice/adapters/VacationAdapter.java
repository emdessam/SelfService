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
import hrss.intercom.eg.hrselfservice.api.LstVacHst;

/**
 * Created by Emad.Essam on 11/14/2016.
 */

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.ViewHolder> {


    Context mContext;
    String TAG = "PermissionAdapter";

    ArrayList<LstVacHst> requestsHistory;

    public VacationAdapter(Context context, ArrayList<LstVacHst> lstVacHstArrayList) {

        this.mContext = context;


        this.requestsHistory = lstVacHstArrayList;


    }

    @Override
    public VacationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.vac_item_history, null);
        ViewHolder viewHolder = new ViewHolder(view);
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VacationAdapter.ViewHolder holder, int position) {
        LstVacHst lst = requestsHistory.get(position);

        holder.name.setText(lst.getVacName());
        holder.startDate.setText(lst.getStartDate().toString());
        holder.endDate.setText(lst.getEndDate().toString());
        String sta = lst.getStatus().toString();
        if (sta.equalsIgnoreCase("0")) {

            sta = "Accepted";
        } else {
            sta = "Rejected";
        }

        holder.status.setText(sta);
    }

    @Override
    public int getItemCount() {
        return (null != requestsHistory ? requestsHistory.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView startDate;
        protected TextView status;
        protected TextView endDate;

        public ViewHolder(View view) {

            super(view);
            this.name = (TextView) view.findViewById(R.id.vac_name);
            this.startDate = (TextView) view.findViewById(R.id.vac_start_date);
            this.status = (TextView) view.findViewById(R.id.vac_status);
            this.endDate = (TextView) view.findViewById(R.id.vac_end_date);
        }


    }
}
