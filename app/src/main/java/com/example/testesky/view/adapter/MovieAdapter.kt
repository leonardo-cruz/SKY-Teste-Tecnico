import kotlinx.android.synthetic.main.movies_list.view.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testesky.R
import com.example.testesky.model.MovieResult
import com.squareup.picasso.Picasso

class MovieAdapter(
    var movieList: MutableList<MovieResult>,
    val clickListener: (MovieResult) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(movieList[position])
        holder.itemView.setOnClickListener { clickListener(movieList[position]) }
    }

    fun updateList(list: MutableList<MovieResult>) {
        this.movieList.removeAll(movieList)
        this.movieList = list
        notifyDataSetChanged()
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(movie: MovieResult) {
            Picasso.get().load(movie.cover_url).into(itemView.movieImage)
            itemView.movieName.text = movie.title
        }

    }

}