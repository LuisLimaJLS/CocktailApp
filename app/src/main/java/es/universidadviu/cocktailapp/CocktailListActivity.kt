package es.universidadviu.cocktailapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.universidadviu.cocktailapp.api.ApiService
import es.universidadviu.cocktailapp.api.CocktailResponse
import es.universidadviu.cocktailapp.models.Cocktail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CocktailListActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    //Componentes
    private lateinit var tvWelcome: TextView
    private lateinit var svSearchCocktails: SearchView
    private lateinit var clListCocktails: ConstraintLayout

    //Recycler canales
    private lateinit var rvCocktails: RecyclerView
    private lateinit var cocktailAdapter: CocktailAdapter

    private val cocktailsInit= mutableListOf<Cocktail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_list)

        //Inicializacion de componentes
        initComponents()
        initListener()
        initUI()
        getCocktails("search.php?f=m")

        //Recibimos el nombre
        val name:String=intent.extras?.getString("name").orEmpty()
        if(name.isNotEmpty()){
            val welcome: String = getString(R.string.welcome_string,name)
            tvWelcome.text = welcome
        }
    }

    private fun initComponents(){
        tvWelcome = findViewById(R.id.tvWelcome)
        svSearchCocktails = findViewById(R.id.svSearchCocktails)
        rvCocktails = findViewById(R.id.rvCocktails)
        clListCocktails = findViewById(R.id.clListCocktails)
    }

    private fun initListener(){
        //botones
        /*btnStart.setOnClickListener{
            val intent = Intent(this,CocktailListActivity::class.java)
            intent.putExtra("name",txtName.text.toString())
            startActivity(intent)
        }*/
    }
    //Incializacion de RecyclerViews
    private fun initUI(){
        cocktailAdapter = CocktailAdapter(cocktailsInit)
        rvCocktails.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvCocktails.adapter=cocktailAdapter
        svSearchCocktails.setOnQueryTextListener(this)
    }

    //Funcion de peticion APIRest
    private fun getCocktailsRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getCocktails(url: String){
        CoroutineScope(Dispatchers.IO).launch{
            val call = getCocktailsRetrofit().create(ApiService::class.java).getAllCocktails(url)
            val cocktailsResponse: CocktailResponse? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val cocktailsData = cocktailsResponse?.cocktails ?: emptyList()
                    cocktailsInit.clear()
                    cocktailsInit.addAll(cocktailsData)
                    //Repinta RecyclerView
                    cocktailAdapter.notifyDataSetChanged()
                } else {
                    showError()
                }
                hideKeyboard()
            }
        }
    }

    private fun hideKeyboard(){
        val imm:InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(clListCocktails.windowToken,0)
    }

    private fun showError(){
        Toast.makeText(this, "Error en la API", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(data: String?): Boolean {
        if(!data.isNullOrEmpty()){
            getCocktails("search.php?s="+(data.lowercase()))
        }
        return true
    }

    override fun onQueryTextChange(data: String?): Boolean {
        return true
    }

}