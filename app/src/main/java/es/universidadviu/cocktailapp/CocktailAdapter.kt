package es.universidadviu.cocktailapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.universidadviu.cocktailapp.models.Cocktail

class CocktailAdapter  (var mContext: Context, private val cocktails: List<Cocktail>): RecyclerView.Adapter<CocktailViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cocktail,parent,false)
        return CocktailViewHolder(mContext, view)
    }

    override fun getItemCount() = cocktails.size

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.render(cocktails[position])
    }

}
