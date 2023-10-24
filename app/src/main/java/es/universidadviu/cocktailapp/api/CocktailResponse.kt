package es.universidadviu.cocktailapp.api

import com.google.gson.annotations.SerializedName
import es.universidadviu.cocktailapp.models.Cocktail

data class CocktailResponse(
    //@SerializedName("version") var version:String,
    @SerializedName("drinks") var cocktails:List<Cocktail>
)
