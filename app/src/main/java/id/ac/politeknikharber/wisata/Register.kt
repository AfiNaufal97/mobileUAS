package id.ac.politeknikharber.wisata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import id.ac.politeknikharber.wisata.home.Home

class Register : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var btn_reg: Button
    lateinit var email: EditText
    lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        btn_reg = findViewById(R.id.btn_register)
        email = findViewById(R.id.email_reg)
        pass = findViewById(R.id.password_reg)

        btn_reg.setOnClickListener {
            register()
        }
    }

    fun register() {
        if (email.text.toString().isEmpty()) {
            email.error = "tolong masukan email"
            email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Email TIdak valid"
            email.requestFocus()
            return
        }

        if (pass.text.toString().isEmpty()) {
            pass.error = "Tolong masukan password"
            pass.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
            .addOnCompleteListener(this){task ->
                if (!task.isSuccessful) {
                    Toast.makeText(this, "gagal register", Toast.LENGTH_SHORT).show()
                }else {
                    startActivity(Intent(this, Home::class.java))
                    finish()
                }
            }
    }
}