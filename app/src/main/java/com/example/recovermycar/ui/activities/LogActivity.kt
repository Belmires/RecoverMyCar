package com.example.recovermycar.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recovermycar.R
import com.example.recovermycar.ui.activities.NavActivity
import com.example.recovermycar.ui.sql.DataBaseHelper
import kotlinx.android.synthetic.main.activity_log.*

class LogActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DataBaseHelper

    private val activity = this@LogActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        initObjects()

        btnEntrar.setOnClickListener {
            logar()
        }
        btnCadastrarUsuario.setOnClickListener {
            val intent = Intent(this@LogActivity, CadastrarActivity::class.java).apply {

            }
            startActivity(intent)

        }
    }

    private fun logar(){
        val user = editTextName.text
        val senha = editTextSenha.text

        if (user.isEmpty()){
            Toast.makeText(applicationContext, "Usuário não preenchido", Toast.LENGTH_SHORT).show()
        } else if (senha.isEmpty()){
            Toast.makeText(applicationContext, "Senha não preenchida", Toast.LENGTH_SHORT).show()
        } else if (databaseHelper!!.checkUser(user!!.toString().trim { it <= ' ' }, senha!!.toString().trim { it <= ' ' })) {

            val accountsIntent = Intent(activity, NavActivity::class.java)
            accountsIntent.putExtra("EMAIL", user!!.toString().trim { it <= ' ' })

            Toast.makeText(applicationContext, "Logado", Toast.LENGTH_SHORT).show()
            limparCampos()
            startActivity(accountsIntent)
        }else{
            Toast.makeText(applicationContext, "Usuário não cadastrado", Toast.LENGTH_SHORT).show()
        }


    }

    /**
     * Este método é para inicializar objetos a serem usados
     */
    private fun initObjects() {
        databaseHelper = DataBaseHelper(activity)
    }

    private fun limparCampos() {
         editTextName.text.clear()
         editTextSenha.text.clear()
    }
}