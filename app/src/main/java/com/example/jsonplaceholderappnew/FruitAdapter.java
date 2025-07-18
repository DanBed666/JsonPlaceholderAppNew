package com.example.jsonplaceholderappnew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder>
{
    List<Fruit> owoce = new ArrayList<>();

    public FruitAdapter(List<Fruit> owoce)
    {
        this.owoce = owoce;
    }

    @NonNull
    @Override
    public FruitAdapter.FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_element, parent, false);
        return new FruitViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.FruitViewHolder holder, int position)
    {
        holder.name.setText(owoce.get(position).getName());
        holder.description.setText(owoce.get(position).getDescription());
    }

    @Override
    public int getItemCount()
    {
        return owoce.size();
    }

    public static class FruitViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView description;
        public FruitViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.nazwa);
            description = itemView.findViewById(R.id.opis);
        }
    }
}
