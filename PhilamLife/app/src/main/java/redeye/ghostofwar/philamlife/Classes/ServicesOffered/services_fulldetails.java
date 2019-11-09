package redeye.ghostofwar.philamlife.Classes.ServicesOffered;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import redeye.ghostofwar.philamlife.Classes.Configs.EndPoints;
import redeye.ghostofwar.philamlife.R;


public class services_fulldetails extends AppCompatActivity {
    String prodname;
    SimpleExoPlayerView exopostwithcomment ;
    SimpleExoPlayer exoPlayer;


    public static Context context;

    public static ImageView prodimage;
    public static TextView prodprice, proddesc, prodrate, prodnamet,market_product_seller;
    String sellername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_fulldetails);
        exopostwithcomment = findViewById(R.id.exoplayer);

        prodname = getIntent().getStringExtra("ProductName");
            prodname = prodname.substring(prodname.lastIndexOf(":") + 1);
            prodnamet = findViewById(R.id.market_product_view_name);
            prodnamet.setText(prodname);
            prodprice = findViewById(R.id.market_product_view_price);
            proddesc = findViewById(R.id.market_product_view_desc);
            market_product_seller = findViewById(R.id.market_product_seller);
            context = getApplicationContext();

            Toolbar back = findViewById(R.id.back);
            setSupportActionBar(back);
            back.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            ImageView top = findViewById(R.id.backtop);
            final ScrollView sc = findViewById(R.id.fulldetailsscroll);
            top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sc.fullScroll(View.FOCUS_UP);
                }
            });
            new getProdDetails(context).execute(prodname);


    }

    public class getProdDetails extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context ctx;
        getProdDetails(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected String doInBackground(String... params) {

            String reference = "https://server.sympies.net/api/getPhilProducts.php";
            String servicename = params[0];
            String data ="";
            try {
                URL url = new URL(reference);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);


                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                data = URLEncoder.encode("servicename","UTF-8")+"="+ URLEncoder.encode(servicename,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

proddesc.setText(result);

            JSONObject feedcontentvalues = null;

            try {
                feedcontentvalues = new JSONObject(result);

                try {
                    JSONArray feedvalues = feedcontentvalues.getJSONArray("products");
                    for (int i=0; i < feedvalues.length(); i++)
                    {
                        JSONObject feedarray = feedvalues.getJSONObject(i);
                        String ppo_productname= feedarray.getString("ppo_productname");
                        String ppo_issueage = feedarray.getString("ppo_issueage");
                        String ppo_coverage = feedarray.getString("ppo_coverage");
                        String ppo_productdesc = feedarray.getString("ppo_productdesc");

                        proddesc.setText(Html.fromHtml(ppo_productdesc));
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            proddesc.setText(Html.fromHtml(ppo_productdesc, Html.FROM_HTML_MODE_LEGACY));
                        } else {
                            proddesc.setText(Html.fromHtml(ppo_productdesc));
                        }
                        prodnamet.setText(ppo_productname);
                        prodprice.setText("Issue Age: "+ppo_issueage);
                        market_product_seller.setText("Life Insurance Coverage  : "+ ppo_coverage);
                    }
                    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                    TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

                    exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
                    Uri uri = Uri.parse("https://server.sympies.net/philamserver/PhilamServer/philam.mp4");
                    DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
                    ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                    MediaSource mediaSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null);
                    exopostwithcomment.setPlayer(exoPlayer);
                    exoPlayer.prepare(mediaSource);
                    exopostwithcomment.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
                    exoPlayer.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                    exopostwithcomment.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
                    exopostwithcomment.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }


}
