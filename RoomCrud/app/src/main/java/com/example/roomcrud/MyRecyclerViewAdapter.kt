package com.example.roomcrud

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcrud.databinding.ListItemBinding
import com.example.roomcrud.db.Subscriber

class MyRecyclerViewAdapter(private val clickListener:(Subscriber)->Unit): RecyclerView.Adapter<MyViewholder>() {

    private val subscribersList = ArrayList<Subscriber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val  layoutInflater = LayoutInflater.from(parent.context)
        val binding:ListItemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)

        return  MyViewholder(binding)
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(subscribersList[position],clickListener)
    }

    fun setList(subscribers: List<Subscriber>){
        subscribersList.clear()
        subscribersList.addAll(subscribers)
    }

}

class MyViewholder(val binding:ListItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(subscriber: Subscriber, clickListener:(Subscriber)->Unit){
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItemLayout.setOnClickListener{
            clickListener(subscriber)
        }
    }
}