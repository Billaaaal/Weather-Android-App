import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.SecondItemsViewModel

class SecondCustomAdapter(private val mList: List<SecondItemsViewModel>) : RecyclerView.Adapter<SecondCustomAdapter.SecondViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_layout_2, parent, false)

        return SecondViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class

        Log.i("TESTEE", ItemsViewModel.icon)

        when(ItemsViewModel.icon){
            "01"-> holder.imageView.setImageResource(R.drawable.ic_sun)
            "02"-> holder.imageView.setImageResource(R.drawable.ic_fewclouds)
            "03"-> holder.imageView.setImageResource(R.drawable.ic_clouds)
            "04"-> holder.imageView.setImageResource(R.drawable.ic_clouds)
            "09"-> holder.imageView.setImageResource(R.drawable.ic_rain)
            "10"-> holder.imageView.setImageResource(R.drawable.ic_sun)
            "11"-> holder.imageView.setImageResource(R.drawable.ic_thunder)
            "13"-> holder.imageView.setImageResource(R.drawable.ic_rain)
            "50"-> holder.imageView.setImageResource(R.drawable.ic_clouds)

            else -> {
                holder.imageView.setImageResource(R.drawable.ic_fewclouds)

            }


        }


        // sets the text to the textview from our itemHolder class
        holder.TempContainer.text = ItemsViewModel.temp
        holder.DateContainer.text = ItemsViewModel.date


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class SecondViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        var TempContainer: TextView = itemView.findViewById(R.id.TempContainer)
        var DateContainer: TextView = itemView.findViewById(R.id.DateContainer)

        var he = itemView.setBackgroundColor(Color.TRANSPARENT)
    }
}
