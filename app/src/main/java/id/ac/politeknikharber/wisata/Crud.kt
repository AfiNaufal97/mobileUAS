package id.ac.politeknikharber.wisata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

class Crud : AppCompatActivity() {

    lateinit var btn_simpan:Button
    lateinit var destinasi:EditText
    lateinit var kota:EditText
    private lateinit var ref:DatabaseReference
    private lateinit var destinationList:MutableList<ObjectWisata>
    private lateinit var listView :ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
        ref = FirebaseDatabase.getInstance().getReference("wisata")
        btn_simpan = findViewById(R.id.btn_tambahData)
        destinasi = findViewById(R.id.edt_destinasi)
        kota = findViewById(R.id.edt_kota)
        listView = findViewById(R.id.lv_read_data)


        destinationList = mutableListOf()

        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(i in snapshot.children){
                        val destinasi = i.getValue(ObjectWisata::class.java)
                        if (destinasi != null) {
                            destinationList.add(destinasi)
                        }
                    }

                    val adapter = DestinasiAdapter(applicationContext, R.layout.item_destinasi, destinationList)
                    listView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        btn_simpan.setOnClickListener {
            fungsiTambah()
        }
    }

    fun fungsiTambah(){
        if(destinasi.text.toString().isNotEmpty() && kota.text.toString().isNotEmpty()){
            val wisataId = ref.push().key
            val dataWisata = ObjectWisata(wisataId, destinasi.text.toString(), kota.text.toString())


            if(wisataId != null){
                ref.child(wisataId).setValue(dataWisata).addOnCompleteListener {
                    Toast.makeText(this, "berhasil di tambhakan", Toast.LENGTH_SHORT).show()
                }
            }


        }else{
            destinasi.error = "Tidak boleh koosng"
            kota.error = "Tidak boleh kosong"
            return
        }
    }


}