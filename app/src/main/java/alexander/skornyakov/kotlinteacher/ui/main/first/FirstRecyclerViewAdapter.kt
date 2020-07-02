package alexander.skornyakov.kotlinteacher.ui.main.first

import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.data.model.SectionModel
import alexander.skornyakov.kotlinteacher.data.model.StepModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FirstRecyclerViewAdapter :
    ListAdapter<SectionModel, FirstRecyclerViewAdapter.FirstViewHolder>(ModelDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(view: View, chapterId: String, header: String)
    }

    private lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class FirstViewHolder(itemView: View, val listener: OnItemClickListener? = null) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val header = itemView.findViewById<TextView>(R.id.header)
        var chapterId: String = ""
        lateinit var chapter: SectionModel

        init {
            itemView.findViewById<CardView>(R.id.first_card).setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener?.onItemClick(view!!, chapterId, chapter.header)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.main_first_rv_item, parent, false)
        val viewHolder = FirstViewHolder(view, listener)
        return viewHolder
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        val model = getItem(position)
        holder.apply {
            chapter = model
            header.text = model.header
            chapterId = model.id
        }
    }

}

class ModelDiffCallback : DiffUtil.ItemCallback<SectionModel>() {
    override fun areItemsTheSame(oldItem: SectionModel, newItem: SectionModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SectionModel, newItem: SectionModel): Boolean {
        return oldItem.header == newItem.header
    }

}
