package es.universidadviu.cocktailapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import es.universidadviu.cocktailapp.api.ApiService
import es.universidadviu.cocktailapp.api.CocktailResponse
import es.universidadviu.cocktailapp.models.Cocktail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CocktailDetailActivity : AppCompatActivity() {

    //Componentes
    private lateinit var idNameDetail: TextView
    private lateinit var idCategoryDetail: TextView
    private lateinit var idCodeDetail: TextView
    private lateinit var ivImageDetail: ImageView
    private lateinit var idIngredient1Detail: TextView
    private lateinit var idIngredient2Detail: TextView
    private lateinit var idIngredient3Detail: TextView
    private lateinit var idInstructionsDetail: TextView

    //Cocktail
    private val cocktailsInit= mutableListOf<Cocktail>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_detail)
        initComponents()
        initListener()
        val idDrink:String=intent.extras?.getString("idDrink").orEmpty()
        if(idDrink.isNotEmpty()){
            //llamada al api
            getCocktail("lookup.php?i="+idDrink)
        }
    }

    private fun initComponents(){
        idNameDetail = findViewById(R.id.idNameDetail)
        idCategoryDetail = findViewById(R.id.idCategoryDetail)
        idCodeDetail = findViewById(R.id.idCodeDetail)
        ivImageDetail = findViewById(R.id.ivImageDetail)
        idIngredient1Detail = findViewById(R.id.idIngredient1Detail)
        idIngredient2Detail = findViewById(R.id.idIngredient2Detail)
        idIngredient3Detail = findViewById(R.id.idIngredient3Detail)
        idInstructionsDetail = findViewById(R.id.idInstructionsDetail)
    }

    private fun initListener(){
    }

    //Funcion de peticion APIRest
    private fun getCocktailsRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun getCocktail(url: String){
        CoroutineScope(Dispatchers.IO).launch{
            val call = getCocktailsRetrofit().create(ApiService::class.java).getAllCocktails(url)
            val cocktailsResponse: CocktailResponse? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val cocktailsData = cocktailsResponse?.cocktails ?: emptyList()
                    cocktailsInit.clear()
                    cocktailsInit.addAll(cocktailsData)
                    //Repinta RecyclerView
                    setViewCocktail(cocktailsInit[0])
                } else {
                    showError()
                }
            }
        }
    }
    private fun showError(){
        Toast.makeText(this, "Error en la API", Toast.LENGTH_SHORT).show()
    }

    private fun setViewCocktail(cocktail: Cocktail){
        Picasso.get().load(cocktail.strDrinkThumb).into(ivImageDetail)
        idNameDetail.text = cocktail.strDrink
        idCategoryDetail.text = cocktail.strCategory
        idCodeDetail.text = cocktail.idDrink
        idIngredient1Detail.text = cocktail.strIngredient1
        idIngredient2Detail.text = cocktail.strIngredient2
        idIngredient3Detail.text = cocktail.strIngredient3
        idInstructionsDetail.text = cocktail.strInstructions
    }

}
