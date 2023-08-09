package io.devbits.gocart.onboarding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.devbits.gocart.onboarding.databinding.LayoutOnboardingItemBinding
import io.devbits.gocart.onboarding.ui.model.OnboardingItem

class OnboardingItemAdapter :
    ListAdapter<OnboardingItem, OnboardingItemAdapter.OnboardingItemViewHolder>(ONBOARDING_ITEM_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class OnboardingItemViewHolder private constructor(
        private val binding: LayoutOnboardingItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(onboardingItem: OnboardingItem) {
            val drawable =
                AppCompatResources.getDrawable(binding.root.context, onboardingItem.imageRes)
            binding.ivOnboardingImage.setImageDrawable(drawable)

            binding.tvOnboardingTitle.text = onboardingItem.title
            binding.tvOnboardingDescription.text = onboardingItem.description
        }

        companion object {
            fun create(parent: ViewGroup): OnboardingItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding =
                    LayoutOnboardingItemBinding.inflate(inflater, parent, false)
                return OnboardingItemViewHolder(binding)
            }
        }
    }

    companion object {
        private val ONBOARDING_ITEM_DIFF = object : DiffUtil.ItemCallback<OnboardingItem>() {
            override fun areItemsTheSame(
                oldItem: OnboardingItem,
                newItem: OnboardingItem
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: OnboardingItem,
                newItem: OnboardingItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}