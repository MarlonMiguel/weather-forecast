package com.example.trabalho.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trabalho.R;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private final List<String> cityList;

    public CityAdapter(List<String> cityList) {
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        String city = cityList.get(position);
        holder.cityName.setText(city);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;

        CityViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.city_name);
        }
    }
}
