package redeye.ghostofwar.philamlife.Classes.Home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import redeye.ghostofwar.philamlife.Classes.ServicesOffered.services_fulldetails;
import redeye.ghostofwar.philamlife.R;

public class datamine_adapter extends RecyclerView.Adapter<datamine_adapter.AllcurrentHolder>{

    private Context context;
    private List<home_datamine_costructor> home_datamine_costructor;
    public Integer count = 0;
    public  static String ipaddress;

    public datamine_adapter(Context context, List<home_datamine_costructor> home_datamine_costructor)
    {
        this.context = context;
        this.home_datamine_costructor = home_datamine_costructor;


    }

    @Override
    public AllcurrentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_datamine_, null);
        AllcurrentHolder holder = new AllcurrentHolder(view);
        int width = parent.getMeasuredWidth()/2;
        view.setMinimumWidth(width);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return holder;
    }
    @Override
    public void onBindViewHolder(final AllcurrentHolder holder, int position) {

        final home_datamine_costructor feed_required_settergetter = home_datamine_costructor.get(position);
        holder.title.setText(feed_required_settergetter.brief());
        Uri profilePicUri = Uri.parse(feed_required_settergetter.image());
        Glide.with(context)
                .load(profilePicUri)
                .into(holder.adsimage);
        holder.full.setText(feed_required_settergetter.price());
        holder.productname.setText(feed_required_settergetter.product());
        holder.micro.setText(feed_required_settergetter.micro());


    }
    Integer rowindex = 0;
    @Override
    public int getItemCount() {
        return home_datamine_costructor.size();
    }
    public class AllcurrentHolder extends RecyclerView.ViewHolder{

        TextView title,productname;
        ImageView adsimage;
        Button micro,full;
        public AllcurrentHolder(final View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            adsimage = itemView.findViewById(R.id.adsimage);
            micro = itemView.findViewById(R.id.micro);
            full = itemView.findViewById(R.id.full);
            productname = itemView.findViewById(R.id.productname);

        }
    }

}
