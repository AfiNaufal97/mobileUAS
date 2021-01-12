package id.ac.politeknikharber.wisata.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import id.ac.politeknikharber.wisata.DestinasiAdapter
import id.ac.politeknikharber.wisata.MainActivity
import id.ac.politeknikharber.wisata.ObjectWisata
import id.ac.politeknikharber.wisata.R
import id.ac.politeknikharber.wisata.home.IsiData.ref

class Home : AppCompatActivity() {
    lateinit var btn_logout: Button
    lateinit var auth: FirebaseAuth

    lateinit var rv:RecyclerView
    lateinit var adapterData:RecyclerView.Adapter<*>
    lateinit var viewManager:LinearLayoutManager
    private lateinit var destinationList:MutableList<ObjectWisata>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        auth = FirebaseAuth.getInstance()
        btn_logout = findViewById(R.id.btn_logout)
        rv = findViewById(R.id.rv_tampilan_awal)


        destinationList = mutableListOf()


        btn_logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        viewManager = LinearLayoutManager(this)



        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(i in snapshot.children){
                        val destinasi = i.getValue(ObjectWisata::class.java)
                        if (destinasi != null) {
                            destinationList.add(destinasi)
                        }
                    }

                    adapterData = AdapterHome()

                   rv.apply {
                       layoutManager = viewManager
                       adapter = adapterData
                   }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}