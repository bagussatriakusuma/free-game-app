package com.example.challengechapter7.presentation.main.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter7.databinding.ListItemScreenshotsBinding
import com.example.domain.model.main.ScreenshotsGames

class ScreenshotsAdapter(private val onClick: OnClickListener): RecyclerView.Adapter<ScreenshotsAdapter.ViewHolder>() {

    private val diffCallBack = object: DiffUtil.ItemCallback<ScreenshotsGames>(){
        override fun areItemsTheSame(oldItem: ScreenshotsGames, newItem: ScreenshotsGames): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ScreenshotsGames, newItem: ScreenshotsGames): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    fun submitData(value: List<ScreenshotsGames>?) = differ.submitList(value)

    interface OnClickListener {
        fun onClickItem (data: ScreenshotsGames)
    }

    inner class ViewHolder(private val binding: ListItemScreenshotsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (data: ScreenshotsGames){

            Glide.with(binding.root)
                .load(data.image)
                .into(binding.ivScreenshots)
            binding.root.setOnClickListener {
                onClick.onClickItem(data)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotsAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolder(ListItemScreenshotsBinding.inflate(inflate,parent,false))
    }

    override fun onBindViewHolder(holder: ScreenshotsAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}