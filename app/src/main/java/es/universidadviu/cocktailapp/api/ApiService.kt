package es.universidadviu.cocktailapp.api

import es.universidadviu.cocktailapp.models.Cocktail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getAllCocktails(@Url url:String): Response<CocktailResponse>

}