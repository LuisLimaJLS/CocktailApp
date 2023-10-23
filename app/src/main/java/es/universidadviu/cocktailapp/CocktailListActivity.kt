package es.universidadviu.cocktailapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class CocktailListActivity : AppCompatActivity() {

    //Componentes
    private lateinit var tvWelcome: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_list)

        //Inicializacion de componentes
        initComponents()
        initListener()

        //Recibimos el nombre
        val name:String=intent.extras?.getString("name").orEmpty()
        if(name.isNotEmpty()){
            val welcome: String = getString(R.string.welcome_string,name)
            tvWelcome.text = welcome
        }
    }

    private fun initComponents(){
        tvWelcome = findViewById(R.id.tvWelcome)
    }

    private fun initListener(){
        //botones
        /*btnStart.setOnClickListener{
            val intent = Intent(this,CocktailListActivity::class.java)
            intent.putExtra("name",txtName.text.toString())
            startActivity(intent)
        }*/
    }
}