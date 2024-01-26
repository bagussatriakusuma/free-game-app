package com.example.challengechapter6.presentation.main.bookmark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter6.data.local.entity.BookmarkEntity
import com.example.challengechapter6.databinding.ListItemBookmarkBinding

class BookmarkAdapter(private val onClick: OnClickListener) :
    RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<BookmarkEntity>() {
        override fun areItemsTheSame(oldItem: BookmarkEntity, newItem: BookmarkEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BookmarkEntity, newItem: BookmarkEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitData(value: List<BookmarkEntity>?) = differ.submitList(value)

    interface OnClickListener {
        fun onClickItem(data: BookmarkEntity)
    }

    inner class ViewHolder(private val binding: ListItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BookmarkEntity) {
            Glide.with(binding.root)
                .load(data.thumbnail)
                .into(binding.ivThumbnail)
            binding.tvTitle.text = data.title
            binding.tvGenre.text = data.genre

            binding.root.setOnClickListener {
                onClick.onClickItem(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolder(ListItemBookmarkBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
