package com.example.apiinandroid.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apiinandroid.databinding.ItemsViewsBinding
import com.example.apiinandroid.models.DataModelClassItem

class DataAdapter(private val context: Context, private var list: List<DataModelClassItem>):
RecyclerView.Adapter<DataAdapter.ViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsViewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val model: DataModelClassItem = list.get(position)
        //Set data into custom itemsViews
        holder.tvId.text = list[position].id.toString()
        holder.tvTitle.text = list[position].title.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder (binding: ItemsViewsBinding): RecyclerView.ViewHolder(binding.root){
        val tvId = binding.tvId
        val tvTitle = binding.tvTitle

    }
}