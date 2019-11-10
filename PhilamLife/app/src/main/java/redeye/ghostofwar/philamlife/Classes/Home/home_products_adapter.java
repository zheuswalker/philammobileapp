package redeye.ghostofwar.philamlife.Classes.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;

import redeye.ghostofwar.philamlife.Classes.ServicesOffered.services_fulldetails;
import redeye.ghostofwar.philamlife.R;

public class home_products_adapter extends RecyclerView.Adapter<home_products_adapter.AllcurrentHolder>{

    private Context context;
    private List<home_services_content_constructors> home_services_content_constructors;
    public Integer count = 0;
    public  static String ipaddress;

    public home_products_adapter(Context context, List<home_services_content_constructors> home_services_content_constructors)
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
        holder.notifiername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, services_fulldetails.class);
                intent.putExtra("ProductName",holder.notifiername.getText());
                context.startActivity(intent);
            }
        });

        int selectedColor = Color.parseColor("#b8123e");
        String td = feed_required_settergetter.pso_service_name().trim();
        if(td.indexOf(" ")>0) {
            td = td.substring(0, 1) + td.substring(td.indexOf(" "), td.indexOf(" ") + 2);
            td=td.replace(" ","");
        }
        else
            td = td.substring(0,1);
        TextDrawable drawable1 = TextDrawable.builder()
                .buildRound(td.toUpperCase(), selectedColor);
        holder.serviceicon.setImageDrawable(drawable1);


    }
    Integer rowindex = 0;
    @Override
    public int getItemCount() {
        return home_services_content_constructors.size();
    }
    public class AllcurrentHolder extends RecyclerView.ViewHolder{

        TextView activitycontent, activitydate;
        ImageView serviceicon,imotion;
        TextView notifiername;
        LinearLayout notiflayout;
        ImageView activityicon;
        RelativeLayout activityiconlayout;
        Button followback;
        public AllcurrentHolder(final View itemView) {
            super(itemView);

            serviceicon = itemView.findViewById(R.id.serviceicon);
            notifiername= itemView.findViewById(R.id.notifiername);
            activitycontent = itemView.findViewById(R.id.activitycontent);

        }
    }

}
