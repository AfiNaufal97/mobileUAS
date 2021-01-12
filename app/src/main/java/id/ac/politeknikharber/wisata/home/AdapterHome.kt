package id.ac.politeknikharber.wisata.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.politeknikharber.wisata.ObjectWisata
import id.ac.politeknikharber.wisata.R

class AdapterHome(private val data:ArrayList<ObjectWisata>): RecyclerView.Adapter<AdapterHome.MyDestinasi>() {
    class MyDestinasi(itemView: View):RecyclerView.ViewHolder(itemView){
        val kota:TextView = itemView.findViewById(R.id.tv_item_kota)
        val destinasi:TextView = itemView.findViewById(R.id.tv_item_kota)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDestinasi {
       return MyDestinasi(LayoutInflater.from(parent.context).inflate(R.layout.item_destinasi, parent, false))
    }

    override fun onBindViewHolder(holder: MyDestinasi, position: Int) {
        val isi = data[position]
        holder.kota.text = isi.kota
        holder.destinasi.text = isi.namaDes
    }

    override fun getItemCount() = data.size
}