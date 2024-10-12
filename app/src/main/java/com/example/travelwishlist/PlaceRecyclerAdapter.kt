package com.example.travelwishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface OnListItemClickedListener {
    fun onListItemClicked(place: Place)
}

class PlaceRecyclerAdapter(private val places: List<Place>,
                           private val onListItemClickedListener: OnListItemClickedListener):
    RecyclerView.Adapter<PlaceRecyclerAdapter.ViewHolder>(){

        // manages one view - one list item - sets the actual data in the view
        // nested classes - this is one
        // inner classes -
        inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view){
            fun bind(place: Place) {
                val placeNameTextView: TextView = view.findViewById(R.id.place_name)
                placeNameTextView.text = place.name

                val dateCreatedOnTextView: TextView = view.findViewById(R.id.date_place_added)
                val createdOnText = view.context.getString(R.string.created_on, place.formattedDate())
                dateCreatedOnTextView.text = createdOnText

                val mapIcon: ImageView = view.findViewById(R.id.map_icon)
                mapIcon.setOnClickListener {
                    onListItemClickedListener.onListItemClicked(place)

                }
            }
        }

    // Called by the recycler view to create as many view holders that are needed to display list on screen.  Create a ViewHolder for a specific position (combo view + data)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.place_list_item, parent, false)
        return ViewHolder(view)
    }

    // Called by the recycler view to set the data for one list item, by position.  Bind the view holder with data for a specific position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = places[position]
        holder.bind(place)
    }

    // how many items in the list?
    override fun getItemCount(): Int {
        return places.size
    }
}

