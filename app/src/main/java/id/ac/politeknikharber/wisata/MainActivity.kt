package id.ac.politeknikharber.wisata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import id.ac.politeknikharber.wisata.home.Home

class MainActivity : AppCompatActivity() {
    lateinit var daftar:TextView
    lateinit var btn_login:Button
    lateinit var email:EditText
    lateinit var pass:EditText
    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        btn_login= findViewById(R.id.btn_login)
        email = findViewById(R.id.email_log)
        pass = findViewById(R.id.password_log)

        btn_login.setOnClickListener {
            masukHome()
        }

        daftar = findViewById(R.id.tv_daftar)
        daftar.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }

    fun masukHome() {
        if (email.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty()) {
            auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, Home::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Email & password tidak valid", Toast.LENGTH_SHORT).show()
                        }
                    }
        }else{
            Toast.makeText(this, "Email dan password tidak boleh kosong !!!", Toast.LENGTH_SHORT).show()
        }
    }

}