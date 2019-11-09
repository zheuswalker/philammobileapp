package redeye.ghostofwar.philamlife.Classes.Wallet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;

import redeye.ghostofwar.philamlife.Classes.ServicesOffered.ProductsOffered;
import redeye.ghostofwar.philamlife.R;


public class wallet_cashprocess_adapter extends RecyclerView.Adapter<wallet_cashprocess_adapter.AllcurrentHolder>{

    private Context context;
    private List<wallet_cashprocess_constructors> wallet_cashprocess_constructors;
    public Integer count = 0;
    public  static String ipaddress;

    public wallet_cashprocess_adapter(Context context, List<wallet_cashprocess_constructors> wallet_cashprocess_constructors)
    {
        this.context = context;
        this.wallet_cashprocess_constructors = wallet_cashprocess_constructors;


    }

    @Override
    public AllcurrentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_wallet_cashprocess_adapter, null);
        AllcurrentHolder holder = new AllcurrentHolder(view);
        int width = parent.getMeasuredWidth();
        view.setMinimumWidth(width);
        return holder;
    }
    @Override
    public void onBindViewHolder(final AllcurrentHolder holder, int position) {

        final wallet_cashprocess_constructors feed_required_settergetter = wallet_cashprocess_constructors.get(position);
        holder.dateprocessed.setText(feed_required_settergetter.pw_processdate());
        holder.cashin.setText("Money Transferred : "+feed_required_settergetter.pw_processedmoney());

        if(feed_required_settergetter.pw_processtype().equals("0")) {
            holder.type.setText("CASH IN");
            holder.slide.setImageDrawable(context.getDrawable(R.drawable.slidedown));
            holder.slide.setRotation(180);
        }
        else
            holder.type.setText("CASH OUT");
        holder.topup.setText("TopUp :"+feed_required_settergetter.pw_processfee());



    }
    Integer rowindex = 0;
    @Override
    public int getItemCount() {
        return wallet_cashprocess_constructors.size();
    }
    public class AllcurrentHolder extends RecyclerView.ViewHolder{

        TextView dateprocessed,type;
        ImageView slide;
        TextView topup,cashin;
        CardView serviceview;
        public AllcurrentHolder(final View itemView) {
            super(itemView);
            topup= itemView.findViewById(R.id.topup);
            dateprocessed = itemView.findViewById(R.id.dateprocessed);
            slide = itemView.findViewById(R.id.slide);
            serviceview = itemView.findViewById(R.id.serviceview);
            cashin= itemView.findViewById(R.id.cashin);
            type = itemView.findViewById(R.id.type);

        }
    }

}
