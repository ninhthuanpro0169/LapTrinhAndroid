package com.example.travelgood;

import android.annotation.SuppressLint;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.travelgood.adapter.DulichAdapter;
import com.example.travelgood.adapter.LoaidlAdapter;
import com.example.travelgood.model.Dulich;
import com.example.travelgood.model.Loaidl;
import com.example.travelgood.ultil.CheckConnection;
import com.example.travelgood.ultil.Server;
import com.example.travelgood.ultil.Server0;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.travelgood.R.id.drawerlayout;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbarmanhinhchinh;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DulichAdapter dulichAdapter;
    DrawerLayout drawerLayout;
    ArrayList<Loaidl> mangloaidl;
    LoaidlAdapter loaidlAdapter;
    int id=0;
    String tenloaidulich="";
    String hinhanhloaidulich="";
    ArrayList<Dulich> mangdulich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
            GetDuLieuLoaidl();
            GetDuLieuDLHot();
            ActionViewFipper();
            ActionBar();
        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra lại kết nối");
            finish();
        }
    }

    private void GetDuLieuLoaidl() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server0.DuongdanLoaidl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response !=null){
                    for (int i=0 ; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id= jsonObject.getInt("id");
                            tenloaidulich=jsonObject.getString("tenloaidulich");
                            hinhanhloaidulich=jsonObject.getString("hinhanhloaidulich");
                            mangloaidl.add(new Loaidl(id,tenloaidulich,hinhanhloaidulich));
                            loaidlAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @SuppressLint("RestrictedApi")
    private void ActionBar(){
        setSupportActionBar(toolbarmanhinhchinh);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbarmanhinhchinh.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarmanhinhchinh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }

    private void GetDuLieuDLHot() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdandulichhot, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                 if (response != null ){
                     int ID = 0;
                     String Tendiadiem="";
                     String Diachi ="";
                     String Motadiadiem ="";
                     String Hinhanhdiadiem ="";
                     int IDDulich =0;
                     for ( int i = 0 ; i < response.length(); i++){
                         try{
                             JSONObject jsonObject = response.getJSONObject(i);
                             ID = jsonObject.getInt("id");
                             Tendiadiem = jsonObject.getString("tendiadiem");
                             Diachi = jsonObject.getString("diachi");
                             Motadiadiem=jsonObject.getString("motadiadiem");
                             Hinhanhdiadiem=jsonObject.getString("hinhanhdiadiemdulich");
                             IDDulich = jsonObject.getInt("iddulich");
                             mangdulich.add(new Dulich(ID,Tendiadiem,Diachi,Hinhanhdiadiem,Motadiadiem,IDDulich));
                             dulichAdapter.notifyDataSetChanged();

                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void ActionViewFipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://vietnamembassy-thailand.org/wp-content/uploads/2019/03/du_lich_thai_lan-1.jpg");
        mangquangcao.add("https://media.vietravel.net/Images/NewsPicture/1-du-lich-gia-dinh.jpg");
        mangquangcao.add("https://www.saigontourist.net/uploads/destination/TrongNuoc/Mientay/Nam-Du-a-tranquil-island.jpg");
        mangquangcao.add("https://www.dulichrongachau.vn/image/catalog/2018/Tour/Nam-Du/tour-nam-du-6.jpg");
        for (int i =0 ; i< mangquangcao.size() ; i++ ) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void Anhxa(){
        toolbarmanhinhchinh = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerViewmanhinhchinh = findViewById(R.id.recyclerview);
        navigationView = findViewById(R.id.navigationview);
        listViewmanhinhchinh = findViewById(R.id.listviewmanhinhchinh);
        drawerLayout=findViewById(drawerlayout);
        mangloaidl = new ArrayList<>();
        loaidlAdapter = new LoaidlAdapter(mangloaidl,getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaidlAdapter);
        mangdulich = new ArrayList<>();
        dulichAdapter = new DulichAdapter(getApplicationContext(), mangdulich);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerViewmanhinhchinh.setAdapter(dulichAdapter);


    }
}
