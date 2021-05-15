package com.example.displaycontacts.ui;

import android.content.Intent;
import android.os.Build;
import android.text.TextDirectionHeuristic;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.displaycontacts.R;
import com.example.displaycontacts.databinding.ActivityViewContactBinding;
import com.example.displaycontacts.databinding.TextRowItemBinding;
import com.example.displaycontacts.model.Contact;

import java.util.ArrayList;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Contact> localDataSet;
    private ItemClickListener listener;
    private static LifecycleOwner lifecycleOwner;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private TextRowItemBinding binding;

        public TextRowItemBinding getBinding() {
            return binding;
        }

        public ViewHolder(TextRowItemBinding binding, View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.binding = binding;
            textView = (TextView) view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }


    public ListAdapter(ItemClickListener listener,LifecycleOwner lifecycleOwner) {
        localDataSet = new ArrayList<>();
        this.listener = listener;
        this.lifecycleOwner = lifecycleOwner;
    }

    public static LifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        TextRowItemBinding binding = TextRowItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        View view = binding.getRoot();
        binding.setLifecycleOwner(lifecycleOwner);

        return new ViewHolder(binding,view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String name = localDataSet.get(position).getName();
        viewHolder.getBinding().setName(name);
        viewHolder.getBinding().setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(name, localDataSet.get(position).getPhoneNumber());
            }
        });
        //
        if(name.matches("^(?=.*[0-9])[- +()0-9]+$")) {
            viewHolder.getTextView().setTextDirection(View.TEXT_DIRECTION_LTR);
        } else {
            viewHolder.getTextView().setTextDirection(View.TEXT_DIRECTION_INHERIT);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public void addContacts(List<Contact> newContacts) {
        localDataSet.addAll(newContacts);
        notifyDataSetChanged();
    }

}