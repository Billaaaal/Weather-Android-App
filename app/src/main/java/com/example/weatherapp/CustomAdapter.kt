import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.ItemsViewModel
import com.example.weatherapp.R

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }



    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text
        holder.textViewTime.text = ItemsViewModel.time

        Log.i("PSG", ItemsViewModel.icon)

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






    }



    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageview)
        var textView: TextView = itemView.findViewById(R.id.textView)
        var textViewTime: TextView = itemView.findViewById(R.id.TimeContainer)
        var he = itemView.setBackgroundColor(Color.TRANSPARENT)

    }

}