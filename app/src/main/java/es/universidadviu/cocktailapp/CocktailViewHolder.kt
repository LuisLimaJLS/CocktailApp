package es.universidadviu.cocktailapp

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.universidadviu.cocktailapp.models.Cocktail

class CocktailViewHolder(var mContext: Context, view: View) : RecyclerView.ViewHolder(view) {
    private val txtName: TextView =view.findViewById(R.id.idDrink)
    private val txtCategory: TextView =view.findViewById(R.id.idCategory)
    private val ivImage: ImageView =view.findViewById(R.id.ivStrDrinkThumb)
    private val divider: View = view.findViewById(R.id.divider)

    private val cvItem: CardView =view.findViewById(R.id.cvItem)
    fun render(cocktail: Cocktail){
        Picasso.get().load(cocktail.strDrinkThumb).into(ivImage)
        txtName.text = cocktail.strDrink
        txtCategory.text = cocktail.strCategory
        divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.component_color))
        initListener(cocktail)
    }
    private fun initListener(cocktail: Cocktail) {
        //CardView
        cvItem.setOnClickListener { startActivityDetail(cocktail) }
    }
    private fun startActivityDetail(cocktail: Cocktail){
        if (!cocktail.idDrink.isNullOrEmpty()){

            mContext.startActivity(Intent(mContext, CocktailDetailActivity::class.java).putExtra("idDrink",cocktail.idDrink))
        } else{
            showError()
        }
    }
    private fun showError(){
    }
}