package id.haaweejee.githubuserv2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.haaweejee.githubuserv2.DetailActivity
import id.haaweejee.githubuserv2.databinding.ItemCardLayoutBinding
import id.haaweejee.githubuserv2.model.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ListViewHolder>() {

    private val listUser = ArrayList<User>()

    fun setData(user: List<User>){
        listUser.clear()
        listUser.addAll(user)
    }

    inner class ListViewHolder(private val binding: ItemCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun bind(user: User){
            binding.tvUsername.text = user.username
            binding.ivAvatar.setImageResource(user.avatar)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListAdapter.ListViewHolder {
        val view = ItemCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListAdapter.ListViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            val moveDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            moveDetail.putExtra(DetailActivity.EXTRA_USER_DATA, user)
            holder.itemView.context.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int = listUser.size
}

interface onCardClickListener{
    //TODO: This interface gonna use later when we need more action at itemList
}