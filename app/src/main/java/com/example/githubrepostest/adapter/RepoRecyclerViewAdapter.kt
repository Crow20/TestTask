package com.example.githubrepostest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepostest.api.response.RepoResponse
import com.example.githubrepostest.databinding.RepoListItemBinding

class RepoRecyclerViewAdapter(val data:List<RepoResponse>, val context:Context):RecyclerView.Adapter<RepoRecyclerViewAdapter.ViewHolder>() {

    var onItemClick: ((RepoResponse) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RepoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), onItemClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(private val itemBinding:RepoListItemBinding, val onClickListener: ((RepoResponse)->Unit)?):RecyclerView.ViewHolder(itemBinding.root){

        fun bind(item:RepoResponse){
            val namePlaceHolder = "(${item.id}) ${item.name}"
            itemBinding.idName.text = namePlaceHolder
            itemBinding.owner.text = item.owner?.login
            itemBinding.description.text = item.description
            itemBinding.root.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }

    }

}