package es.universidadviu.cocktailapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
            val intent = Intent(this,CocktailListActivity::class.java)
            intent.putExtra("name",txtName.text.toString())
            startActivity(intent)
        }
    }
}