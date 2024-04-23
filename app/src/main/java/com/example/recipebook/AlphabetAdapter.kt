package com.example.recipebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlphabetAdapter(private val alphabetList: List<String>, private val listener: OnAlphabetClickListener) : RecyclerView.Adapter<AlphabetAdapter.AlphabetViewHolder>() {

    interface OnAlphabetClickListener {
        fun onAlphabetClick(alphabet: String)
    }

    inner class AlphabetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val alphabetTextView: TextView = itemView.findViewById(android.R.id.text1)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(alphabet: String) {
            alphabetTextView.text = alphabet
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val alphabet = alphabetList[position]
                listener.onAlphabetClick(alphabet)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphabetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return AlphabetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlphabetViewHolder, position: Int) {
        val currentItem = alphabetList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = alphabetList.size
}
