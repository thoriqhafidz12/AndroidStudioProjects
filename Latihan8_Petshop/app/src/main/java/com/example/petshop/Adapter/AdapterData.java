package com.example.petshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.Model.DataModel;
import com.example.petshop.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private Context context;
    private List<DataModel> listData;

    public AdapterData(Context context, List<DataModel> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);
        holder.tvId.setText(dm.getId());
        holder.tvNamaBrg.setText(dm.getNama_barang());
        holder.tvHargaBrg.setText(dm.getHarga_barang());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvId, tvNamaBrg, tvHargaBrg;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNamaBrg = itemView.findViewById(R.id.tv_namabrg);
            tvHargaBrg = itemView.findViewById(R.id.tv_hargabrg);
        }
    }
}
