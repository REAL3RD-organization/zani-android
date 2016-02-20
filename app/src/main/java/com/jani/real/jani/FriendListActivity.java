package com.jani.real.jani;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JeongMinCha on 2016. 2. 3..
 */

public class FriendListActivity extends Activity {

    private DBHelper              helper;

    private ListView              list_friends;
    private ListViewAdapter       adapter_list_friends;
    private Button                btn_add_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freind_list);

        helper = new DBHelper(this, null, null, 0);

        adapter_list_friends = new ListViewAdapter(this);

        list_friends = (ListView) findViewById(R.id.list_view_friends);
        list_friends.setAdapter(adapter_list_friends);
        list_friends.setOnItemClickListener(onClickListItem);

        List<String> friendList = helper.getFriendList();
        for (int i = 0; i < friendList.size(); i++) {
            adapter_list_friends.addItem(getResources().getDrawable(R.drawable.ic_launcher), friendList.get(i));
        }

        btn_add_friend = (Button) findViewById(R.id.btn_add_friend);
        btn_add_friend.setOnClickListener(addFriendListener);
    }

    private Button.OnClickListener addFriendListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_add_friend) {
                goToAddFriendActivity();
            }
        }
    };

    private void goToAddFriendActivity() {
        Intent intent = new Intent(FriendListActivity.this, AddFriendActivity.class);
        startActivity(intent);
    }

    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    };

    private class ListViewAdapter extends BaseAdapter {
        private Context context = null;
        private ArrayList<ListData> list_datas = new ArrayList<ListData>();

        public ListViewAdapter(Context ctxt) {
            super();
            context = ctxt;
        }

        @Override
        public int getCount() {
            return list_datas.size();
        }

        @Override
        public Object getItem(int position) {
            return list_datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_item, null);

                holder.image = (ImageView) convertView.findViewById(R.id.mImage);
                holder.name = (TextView) convertView.findViewById(R.id.mName);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            ListData data = list_datas.get(position);

            if (data.icon != null) {
                holder.image.setVisibility(View.VISIBLE);
                holder.image.setImageDrawable(data.icon);
            } else {
                holder.image.setVisibility(View.GONE);
            }

            holder.name.setText(data.name);

            return convertView;
        }

        public void addItem(Drawable icon, String name) {
            ListData data = new ListData();
            data.icon = icon;
            data.name = name;
            list_datas.add(data);
        }
    }
}