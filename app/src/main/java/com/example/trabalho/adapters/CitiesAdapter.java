package com.example.trabalho.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trabalho.R;
import com.example.trabalho.model.Cidade;
import com.google.android.material.card.MaterialCardView;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {

    private List<Cidade> citiesList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Cidade cidade);
    }

    public CitiesAdapter(List<Cidade> citiesList, OnItemClickListener listener) {
        this.citiesList = citiesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {
        Cidade cidade = citiesList.get(position);
        holder.cityName.setText(cidade.getNome());
        holder.cardView.setOnClickListener(v -> listener.onItemClick(cidade));
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public void updateCitiesList(List<Cidade> newCitiesList) {
        citiesList = newCitiesList;
        notifyDataSetChanged();
    }

    public static class CitiesViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;
        MaterialCardView cardView;

        public CitiesViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.city_name);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
