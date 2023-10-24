package es.universidadviu.cocktailapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.universidadviu.cocktailapp.models.Cocktail

class CocktailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val txtName: TextView =view.findViewById(R.id.idDrink)
    private val txtCategory: TextView =view.findViewById(R.id.idCategory)
    private val ivImage: ImageView =view.findViewById(R.id.ivStrDrinkThumb)
    fun render(cocktail: Cocktail){
        Picasso.get().load(cocktail.strDrinkThumb).into(ivImage)
        txtName.text = cocktail.strDrink
        txtCategory.text = cocktail.strCategory
    }
}