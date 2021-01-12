package id.ac.politeknikharber.wisata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DestinasiAdapter(val dCtx:Context, val layoutResId:Int, val desList:List<ObjectWisata>):ArrayAdapter<ObjectWisata>(dCtx, layoutResId, desList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup):View {
        val layout = LayoutInflater.from(dCtx).inflate(layoutResId, null)

        val tv_des = layout.findViewById<TextView>(R.id.tv_item_nama)
        val tv_kota = layout.findViewById<TextView>(R.id.tv_item_kota)
        val destinasi = desList[position]

        tv_des.text = destinasi.namaDes
        tv_kota.text = destinasi.kota

        return layout
    }
}