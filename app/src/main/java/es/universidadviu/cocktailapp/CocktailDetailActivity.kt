package es.universidadviu.cocktailapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView

class CocktailDetailActivity : AppCompatActivity() {

    //Componentes
    private lateinit var tvIdDrink: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_detail)
        initComponents()
        initListener()

        //llamada al api

        getCocktails("lookup.php?i=11007")

        val idDrink:String=intent.extras?.getString("idDrink").orEmpty()
        if(idDrink.isNotEmpty()){
            tvIdDrink.text = idDrink
        }
    }

    private fun getCocktails(s: String) {

    }

    private fun initComponents(){
        tvIdDrink = findViewById(R.id.tvIdDrink)
    }

    private fun initListener(){
    }
}
