package id.ac.politeknikharber.wisata.home

import com.google.firebase.database.*
import id.ac.politeknikharber.wisata.ObjectWisata



object IsiData {
    val ref = FirebaseDatabase.getInstance().getReference("wisata")
    val dataObject: ArrayList<ObjectWisata>
    get() {
        val data:ArrayList<ObjectWisata> = arrayListOf()
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(i in snapshot.children){
                        val destinasi = i.getValue(ObjectWisata::class.java)
                        if(destinasi != null){
                            data.add(destinasi)
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return data
    }
}