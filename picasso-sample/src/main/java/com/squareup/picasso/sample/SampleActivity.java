package com.squareup.picasso.sample;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.squareup.picasso.Picasso;

public class SampleActivity extends Activity {
  private SampleAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    StrictMode.setThreadPolicy(
        new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());

    adapter = new SampleAdapter(this);

    ListView lv = new ListView(this);
    lv.setAdapter(adapter);

    setContentView(lv);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    menu.add("Debugging")
        .setCheckable(true)
        .setChecked(Picasso.with(this).isDebugging())
        .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
          @Override public boolean onMenuItemClick(MenuItem item) {
            item.setChecked(!item.isChecked());
            Picasso.with(SampleActivity.this).setDebugging(item.isChecked());
            adapter.notifyDataSetChanged();
            return true;
          }
        });
    return true;
  }
}
