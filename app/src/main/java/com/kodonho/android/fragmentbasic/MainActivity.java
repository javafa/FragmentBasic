package com.kodonho.android.fragmentbasic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kodonho.android.fragmentbasic.dummy.DummyContent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private static final String TAG = "MainActivity";
    public static ArrayList<ListData> datas = new ArrayList<>();
    public static int position = -1;

    Fragment listFragment;
    FragmentTwo detailFragement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0; i< 20;i++){
            ListData data = new ListData();
            data.title = i+ " Item Title";
            data.contents = makeDetails(i);
            datas.add(data);
        }

        listFragment =  new FragmentOne(); //ItemFragment.newInstance(1);
        // 프래그먼트로 값넘기기
//        Bundle args = new Bundle();
//        args.putString("key","값");
//        listFragment.setArguments(args);

        detailFragement = new FragmentTwo();

        setFragment();
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.\n" +
                    "DE Modfastails inform ation here.\n" +
                    "a dfas dfre detailsadfdsformation here.\n" +
                    "SsSsadfg fd fsadrsadfails information here.\n" +
                    "K adfdsafore detaiasfdstion fsdae.\n" +
                    "Mofdsa fdetadfsds ifsdfation here.");
        }
        return builder.toString();
    }

    public void setFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, listFragment);
        transaction.commit();
    }

    public void goDetail(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, detailFragement);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.i(TAG,"==========item="+item.content);
        detailFragement.setData(item);
        goDetail();

    }
}
