import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.bedu.modastoreapp.R

class RecyclerAdapterOrder(var items:MutableList<Items>, val context: Context):
    RecyclerView.Adapter<RecyclerAdapterOrder.ViewHolderOrder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderOrder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderOrder(layoutInflater.inflate(R.layout.card_order, parent, false))
    }


    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolderOrder(override var view: View) : RecyclerAdapter.ViewHolder(view) {

        val idCompra = view.findViewById(R.id.idCompra) as TextView
        val orderPrice = view.findViewById(R.id.OrderPrice) as TextView
        val num = view.findViewById(R.id.num) as TextView
        val image1 = view.findViewById(R.id.image1) as ImageView
        val image2=view.findViewById(R.id.image2) as ImageView
        val image3 =view.findViewById(R.id.image3)as ImageView

       override fun bind(item:Items,context:Context){

          idCompra.text=item.id
          orderPrice.text=item.orderprice
          num.text=item.itemNum
           image1.setImageResource(item.image1)
           image2.setImageResource(item.image2)
            image3.setImageResource(item.image3)

           itemView.setOnClickListener { v ->
               Toast.makeText( v.context, "CLICKK", Toast.LENGTH_SHORT).show()
           }
        }
    }
    override fun onBindViewHolder(holder: ViewHolderOrder, position: Int) {
        val items = items.get(position)
        holder.bind(items, context)
    }
}