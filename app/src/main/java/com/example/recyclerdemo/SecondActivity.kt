package com.example.recyclerdemo

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerdemo.snaphelper.EdgeSnapHelperActivity
import com.example.recyclerdemo.snaphelper.LinearSnapHelperActivity
import com.example.recyclerdemo.snaphelper.PagerSnapHelperActivity

/**
 * @author 李开开 <a href="likaikai5566@126.com">Email</a>
 * @date 2023/06/25 10:37 PM
 */
class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)

        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.title = intent.getStringExtra("title")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val items = intent.getStringArrayExtra("item_list") as Array<String>

        val listView = findViewById<ListView>(R.id.itemsView)
        listView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)

        listView.onItemClickListener = AdapterView.OnItemClickListener {
                adapterView, view, position, id ->
            run {
                val it = Intent()
                var component: ComponentName =
                    ComponentName(this, LinearSnapHelperActivity::class.java)
                if (view is TextView) {
                    it.putExtra("title", view.text)
                    component = when {
                        TextUtils.equals(view.text, "LinearSnapHelper")
                        -> ComponentName(this, LinearSnapHelperActivity::class.java)
                        TextUtils.equals(view.text, "PagerSnapHelper")
                        -> ComponentName(this, PagerSnapHelperActivity::class.java)
                        TextUtils.equals(view.text, "CustomSnapHelper")
                        -> ComponentName(this, EdgeSnapHelperActivity::class.java)
                        else -> ComponentName(this, LinearSnapHelperActivity::class.java)
                    }
                }
                it.component = component
                startActivity(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}