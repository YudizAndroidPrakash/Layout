package com.example.demoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.animation.AnimationActivity


//hold the data of person
data class DataHoldPerson(
    var name: String,
    var email: String,
    var mobileNumber: Int,
    var checked: Boolean = false
)

class TaskAdapter(
    private val myDataList: ArrayList<DataHoldPerson>
) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_of_person,
                parent, false
            )
        )
    }

    override fun getItemCount() = myDataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.icDelete!!.setOnClickListener {
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, myDataList.size)
            myDataList.removeAt(position)
        }

        holder.selected!!.setOnClickListener {
            myDataList[position].checked = !myDataList[position].checked
            notifyItemChanged(position)
        }

//        holder.selected!!.setOnCheckedChangeListener { _, isSelected ->
//
//            myDataList[position].checked = isSelected
//            if(myDataList[position].checked == isSelected){
//                notifyItemChanged(position)
//            }
        holder.bind(position, myDataList,holder)
        }



    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        private var tvName: TextView? = null
        private var tvEmail: TextView? = null
        private var tvMobile: TextView? = null
        var icDelete: ImageButton? = null
        var selected: CheckBox? = null

        init {
            ItemView.animation = AnimationUtils.loadAnimation(ItemView.context,R.anim.translate)
            tvName = ItemView.findViewById(R.id.tvPersonName)
            tvEmail = ItemView.findViewById(R.id.tvPersonEmail)
            tvMobile = ItemView.findViewById(R.id.tvPersonMob)
            icDelete = ItemView.findViewById(R.id.ibDeleted)
            selected = ItemView.findViewById(R.id.cbDataSelected)
        }

        fun bind(position: Int, myDataList: ArrayList<DataHoldPerson>, holder: ViewHolder) {


            val i = myDataList[position]
            tvName!!.text = i.name
            tvEmail!!.text = i.email
            tvMobile!!.text = i.mobileNumber.toString()

            holder.selected!!.isChecked = i.checked
        }
    }
}
