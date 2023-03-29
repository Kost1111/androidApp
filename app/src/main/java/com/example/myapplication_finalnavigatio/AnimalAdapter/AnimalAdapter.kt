package com.example.myapplication_finalnavigatio.AnimalAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R

class AnimalAdapter() : ListAdapter<Animal, AnimalAdapter.AnimalViewHolder>(AnimalDiffUtil()) {

    var itemClick: (animal: Animal) -> Unit = { _ -> }
    var likeClick: (animal: Animal) -> Unit = { _ -> }
    var deleteClick: (animal: Animal) -> Unit = { _ -> }

    inner class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(animal: Animal, position: Int) {

            val like: ImageView = itemView.findViewById(R.id.imageView2)

            if (animal.like) {
                like.setImageResource(R.drawable.like)
            } else {
                like.setImageResource(R.drawable.greey_like)
            }

            val imageView: ImageView = itemView.findViewById(R.id.imageView)
            Glide.with(imageView)
                .load(animal.imgURL)
                .error(R.drawable.error)
                .centerCrop()
                .into(imageView)

            val name: TextView = itemView.findViewById(R.id.textView_name)
            name.text = animal.name

            val description: TextView = itemView.findViewById(R.id.textView_Description)
            description.text = animal.description

            imageView.setOnClickListener {
                itemClick(animal)
            }
            like.setOnClickListener {
                like.setImageResource(R.drawable.like)
                animal.like = true
                likeClick(animal)
            }
            like.setOnLongClickListener {
                animal.like = false
                like.setImageResource(R.drawable.greey_like)
                deleteClick(animal)
                true
            }
        }
    }

    companion object {
        class AnimalDiffUtil : DiffUtil.ItemCallback<Animal>() {
            override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem.imgURL == newItem.imgURL
            }

            override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem.imgURL == newItem.imgURL
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = getItem(position)
        holder.onBind(animal, position)
    }
}