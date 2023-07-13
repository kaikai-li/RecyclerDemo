package com.example.recyclerdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.title = "RecyclerView Features"
        
        val listView = findViewById<ListView>(R.id.listView)
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                run {
                    val it = Intent(this, SecondActivity::class.java)
                    var items = emptyArray<String>()
                    if (view is TextView) {
                        it.putExtra("title", view.text)
                        items = when {
                            TextUtils.equals(view.text, "SnapHelper") ->
                                this.resources.getStringArray(R.array.snap_items)
                            else -> emptyArray<String>()
                        }
                    }
                    it.putExtra("item_list", items)
                    startActivity(it)
                }
            }
    }
}