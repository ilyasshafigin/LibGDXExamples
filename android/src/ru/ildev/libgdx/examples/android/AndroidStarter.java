package ru.ildev.libgdx.examples.android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ru.ildev.libgdx.examples.utils.GdxExamples;

/**
 * @author Ilyas Shafigin
 * @version 1.0
 * @since 26.02.15
 */
public class AndroidStarter extends ListActivity {

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> testNames = GdxExamples.getNames();
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testNames));
    }

    @Override
    protected void onListItemClick (ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Object o = this.getListAdapter().getItem(position);
        String testName = o.toString();

        Bundle bundle = new Bundle();
        bundle.putString("example", testName);
        Intent intent = new Intent(this, GdxExampleActivity.class);
        intent.putExtras(bundle);

        this.startActivity(intent);
    }
}
