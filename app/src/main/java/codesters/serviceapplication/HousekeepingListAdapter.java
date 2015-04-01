package codesters.serviceapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static codesters.serviceapplication.R.layout;

/**
 * Created by RAVI on 23-Mar-15.
 */
public class HousekeepingListAdapter extends RecyclerView.Adapter<HousekeepingListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    List<HousekeepingItem> data;
    private Context context;

    private int expandedPosition = -1;

    public HousekeepingListAdapter(Context context, List<HousekeepingItem> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout.housekeepint_item_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) view.getTag();
                HousekeepingItem housekeepingItem = data.get(holder.getPosition());
                // Check for an expanded view, collapse if you find one
                if (expandedPosition >= 0) {
                    int prev = expandedPosition;
                    notifyItemChanged(prev);
                }
                // Set the current position to "expanded"
                expandedPosition = holder.getPosition();
                notifyItemChanged(expandedPosition);
               // Toast.makeText(context, "Clicked: " + foodItem.FoodTitle, Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HousekeepingItem housekeepingItem = data.get(position);
        holder.title.setText(housekeepingItem.HousekeepingTitle);
        holder.icon.setImageResource(housekeepingItem.HousekeepingIconId);

        if (position == expandedPosition) {
            if(holder.expandableArea.getVisibility()==View.VISIBLE){
                holder.expandableArea.setVisibility(View.GONE);
            }else{
                holder.expandableArea.setVisibility(View.VISIBLE);
            }
        } else {
            holder.expandableArea.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView title;
        LinearLayout expandableArea;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.hoskeepingListText);
            icon = (ImageView) itemView.findViewById(R.id.houskeepingListIcon);
            title.setBackgroundResource(R.drawable.shape);
            expandableArea = (LinearLayout) itemView.findViewById(R.id.llExpandArea);
        }
    }
}
