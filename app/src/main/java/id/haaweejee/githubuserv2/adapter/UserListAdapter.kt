package id.haaweejee.githubuserv2.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.haaweejee.githubuserv2.DetailActivity
import id.haaweejee.githubuserv2.databinding.ItemCardLayoutBinding
import id.haaweejee.githubuserv2.model.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ListViewHolder>() {

    private val listUser = ArrayList<User>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClick (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(user: List<User>){
        listUser.clear()
        listUser.addAll(user)
        notifyDataSetChanged()
        Log.d("TAG", "cek item masuk $listUser")
    }

    inner class ListViewHolder(private val binding: ItemCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun bind(user: User){
            binding.root.setOnClickListener { onItemClickCallback?.onItemClicked(user) }
            binding.tvUsername.text = user.login
            Glide.with(itemView.context)
                .load(user.avatar_url)
                .centerCrop()
                .apply(RequestOptions().override(60,60))
                .into(binding.ivAvatar)
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
    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemClickCallback{
        fun onItemClicked(data: User)
    }
}

