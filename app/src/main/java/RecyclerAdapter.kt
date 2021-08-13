import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.bedu.modastoreapp.R

class RecyclerAdapter (var Product:MutableList<Product>,val context: Context):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val product = Product.get(position)
        holder.bind(product, context)
    }

    override fun getItemCount(): Int {
        return Product.size
    }

    open class ViewHolder(open var view: View) : RecyclerView.ViewHolder(view) {
        //obteniendo las referencias a las Views
        val productName = view.findViewById(R.id.product) as TextView
        //val description = view.findViewById(R.id.tvDescription) as TextView
        val price = view.findViewById(R.id.price) as TextView
        val image = view.findViewById(R.id.ProductImage) as ImageView

        //"atando" los datos a las Views
        open fun bind(product: Product, context: Context){
            productName.text = product.productName
            //description.text = product.description
            price.text = product.price
            image.setImageResource(product.image)

            itemView.setOnClickListener { v ->
                Toast.makeText( v.context, "CLICKK", Toast.LENGTH_SHORT).show()
            }

        }

        open fun bind(item: Items, context: Context) {}
    }

}