package com.example.recyclerdemo.snaphelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdemo.R

/**
 * @author 李开开 <a href="likaikai5566@126.com">Email</a>
 * @date 2023/07/04 11:03 PM
 */
class PagerSnapHelperActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_snaphelper)

        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.title = intent.getStringExtra("title")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = Adapter(
            listOf(
                "XiaoMing", "CuiHua", "ErGou",
                "DaZhuang", "ZhuZi", "PangZi", "XiaoDong",
                "XiaoLong", "DaMing", "GouDan", "DaZhu",
                "ChunSheng", "LiLi", "Change", "Cheng", "Bi", "Ya", "Di"
            )
        )
        recyclerView.adapter = adapter
        PagerSnapHelper().attachToRecyclerView(recyclerView)
    }

    class Adapter(val names: List<String>): RecyclerView.Adapter<Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.page_snaper_item, parent, false)
            return Holder(view)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(names[position])
        }

        override fun getItemCount(): Int {
            return names.size
        }
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private lateinit var name: TextView
        init {
            name = itemView.findViewById(android.R.id.text1)
        }

        fun bind(value: String) {
            name.text = value
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