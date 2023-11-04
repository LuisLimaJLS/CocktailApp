package es.universidadviu.cocktailapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {


    //Componentes
    private lateinit var txtName: TextInputEditText
    private lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Inicializacion de componentes
        initComponents()
        initListener()
    }
    private fun initComponents(){
        txtName = findViewById(R.id.txtNameIn)
        btnStart = findViewById(R.id.btnStart)

    }

    private fun initListener(){
        //Pasar a la lista de cocteles
        btnStart.setOnClickListener{
            startActivityList()
        }
        txtName.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.action == KeyEvent.ACTION_DOWN)) {
                startActivityList()
                return@OnKeyListener true
            }
            false
        })
    }

    private fun startActivityList(){
        if (!txtName.text.toString().isNullOrEmpty()){
            val intent = Intent(this,CocktailListActivity::class.java)
            intent.putExtra("name",txtName.text.toString())
            startActivity(intent)
        } else{
            showError()
        }

    }

    private fun showError(){
        Toast.makeText(this, "Por favor ingrese su nombre", Toast.LENGTH_SHORT).show()
    }
}
