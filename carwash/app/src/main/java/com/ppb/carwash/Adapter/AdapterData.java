package com.ppb.carwash.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ppb.carwash.Model.DataModel;
import com.ppb.carwash.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listdata;

    public AdapterData(Context ctx, List<DataModel> listdata) {
        this.ctx = ctx;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listdata.get(position);
        holder.tvId.setText(String.valueOf(dm.getId()));
        holder.tvnama.setText(dm.getNama());
        holder.tvnohp.setText(dm.getNohp());
        holder.tvalamat.setText(dm.getAlamat());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvId,tvnama, tvnohp, tvalamat;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvid);
            tvnama = itemView.findViewById(R.id.tvnama);
            tvnohp = itemView.findViewById(R.id.tvnohp);
            tvalamat = itemView.findViewById(R.id.tvalamat);

        }
    }
}
