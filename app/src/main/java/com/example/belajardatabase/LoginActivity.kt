package com.example.belajardatabase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("SesiLogin", Context.MODE_PRIVATE)

        val sudahLogin = sharedPref.getBoolean("IS_LOGGED_IN", false)

        if (sudahLogin) {

            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }


        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()

            if (username.isNotEmpty()) {

                with(sharedPref.edit()) {
                    putBoolean("IS_LOGGED_IN", true)
                    putString("USERNAME", username)
                    apply()
                }

                Toast.makeText(this, "Halo, $username!", Toast.LENGTH_SHORT).show()


                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}