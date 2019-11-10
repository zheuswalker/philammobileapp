package redeye.ghostofwar.philamlife.Classes.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;

import java.util.List;

import redeye.ghostofwar.philamlife.Classes.ServicesOffered.ProductsOffered;
import redeye.ghostofwar.philamlife.R;


public class home_services_content_adapter extends RecyclerView.Adapter<home_services_content_adapter.AllcurrentHolder>{

    private Context context;
    private List<home_services_content_constructors> home_services_content_constructors;
    public Integer count = 0;
    public  static String ipaddress;

    public home_services_content_adapter(Context context, List<home_services_content_constructors> home_services_content_constructors)
    {
        this.context = context;
        this.home_services_content_constructors = home_services_content_constructors;


    }

    @Override
    public AllcurrentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_services_offered_template, null);
        AllcurrentHolder holder = new AllcurrentHolder(view);
        int width = parent.getMeasuredWidth();
        view.setMinimumWidth(width);
        return holder;
    }
    @Override
    public void onBindViewHolder(final AllcurrentHolder holder, int position) {

        final home_services_content_constructors feed_required_settergetter = home_services_content_constructors.get(position);
        holder.notifiername.setText(feed_required_settergetter.pso_service_name());
        holder.activitycontent.setText(feed_required_settergetter.pso_service_desc());
        holder.serviceview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsOffered.class);
                intent.putExtra("ProductName",holder.notifiername.getText());
                context.startActivity(intent);
            }
        });


        Uri profilePicUri = Uri.parse("https://server.sympies.net/philamserver/PhilamServer/iconic/"+feed_required_settergetter.pso_icon());
        Glide.with(context)
                .load(profilePicUri)
                .into(holder.serviceicon);



    }
    Integer rowindex = 0;
    @Override
    public int getItemCount() {
        return home_services_content_constructors.size();
    }
    public class AllcurrentHolder extends RecyclerView.ViewHolder{

        TextView activitycontent;
        ImageView serviceicon;
        TextView notifiername;
        CardView serviceview;
        public AllcurrentHolder(final View itemView) {
            super(itemView);
            notifiername= itemView.findViewById(R.id.notifiername);
            activitycontent = itemView.findViewById(R.id.activitycontent);
            serviceicon = itemView.findViewById(R.id.serviceicon);
            serviceview = itemView.findViewById(R.id.serviceview);

        }
    }

}
