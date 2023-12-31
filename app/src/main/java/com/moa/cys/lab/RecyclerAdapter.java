package com.moa.cys.lab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moa.cys.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
	
	private List<String> mData;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;
	
	// data is passed into the constructor
	RecyclerAdapter(Context context, List<String> data) {
		this.mInflater = LayoutInflater.from(context);
		this.mData = data;
	}
	
	// inflates the row layout from xml when needed
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = mInflater.inflate(R.layout.recycler_adapter, parent, false);
		return new ViewHolder(view);
	}
	
	// binds the data to the TextView in each row
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		
		String value = mData.get(position);
		holder.myTextView.setText(value);
		holder.btnValue.setText(value);
	}
	
	@Override
	public int getItemCount() {
		return mData.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView myTextView;
		Button btnValue;
		
		ViewHolder(View itemView) {
			super(itemView);
			myTextView = itemView.findViewById(R.id.tvAnimalName);
			btnValue = itemView.findViewById(R.id.btnValue);
			itemView.setOnClickListener(this);
		}
		
		@Override
		public void onClick(View view) {
			if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
		}
	}
	
	// convenience method for getting data at click position
	String getItem(int id) {
		return mData.get(id);
	}
	
	// allows clicks events to be caught
	void setClickListener(ItemClickListener itemClickListener) {
		this.mClickListener = itemClickListener;
	}
	
	// parent activity will implement this method to respond to click events
	public interface ItemClickListener {
		void onItemClick(View view, int position);
	}
}