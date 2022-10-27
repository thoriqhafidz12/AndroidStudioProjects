package com.example.uts_katon.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_katon.API.APIRequestData;
import com.example.uts_katon.API.RetroServer;
import com.example.uts_katon.Activity.UbahData;
import com.example.uts_katon.Activity.market;
import com.example.uts_katon.Model.DataModel;
import com.example.uts_katon.Model.ResponseModel;
import com.example.uts_katon.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listdata;
    private int noBarang;
    private List<DataModel> listBarang;


    public AdapterData(Context ctx, List<DataModel> listdata) {
        this.ctx = ctx;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listdata.get(position);
        holder.tvNo.setText(String.valueOf(dm.getNo()));
        holder.tvNama.setText(dm.getNama());
        holder.tvJenis.setText(dm.getJenis());
        holder.tvUkuran.setText(dm.getUkuran());
        holder.tvHarga.setText(dm.getHarga());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvNo, tvNama, tvJenis, tvUkuran,tvHarga;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvNo = itemView.findViewById(R.id.tv_no);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvJenis = itemView.findViewById(R.id.tv_jenis);
            tvUkuran = itemView.findViewById(R.id.tv_ukuran);
            tvHarga = itemView.findViewById(R.id.tv_harga);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih Operasi yang akan diakukan");
                    dialogPesan.setCancelable(true);
                    noBarang = Integer.parseInt(tvNo.getText().toString());

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteData();
                            dialogInterface.dismiss();
                            Handler hand = new Handler();
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((market)ctx).retrieveData();
                                }
                            },500);
                            ((market)ctx).retrieveData();
                        }

                        private void deleteData() {
                            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                            Call<ResponseModel> hapusData = ardData.ardDeleteData(noBarang);
                            hapusData.enqueue(new Callback<ResponseModel>() {
                                @Override
                                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                    int kode = response.body().getKode();
                                    String pesan = response.body().getPesan();

                                    Toast.makeText(ctx, "Kode :"+kode+"| Pesan :"+pesan, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ResponseModel> call, Throwable t) {
                                    Toast.makeText(ctx, "Gagal Terhubung Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();
                            dialogInterface.dismiss();
                        }
                    });

                    dialogPesan.show();
                    return false;
                }
            });
        }
        private void getData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> ambilData = ardData.ardGetData(noBarang);
            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listBarang = response.body().getData();

                    int varNoBarang = listBarang.get(0).getNo();
                    String varNamaBarang = listBarang.get(0).getNama();
                    String varJenisBarang = listBarang.get(0).getJenis();
                    String varUkuranBarang = listBarang.get(0).getUkuran();
                    String varHargaMusik = listBarang.get(0).getHarga();

                    Intent kirim = new Intent(ctx, UbahData.class);
                    kirim.putExtra("xNo",varNoBarang);
                    kirim.putExtra("xJenis",varNamaBarang);
                    kirim.putExtra("xMerk",varJenisBarang);
                    kirim.putExtra("xTipe",varUkuranBarang);
                    kirim.putExtra("xHarga",varHargaMusik);
                    ctx.startActivity(kirim);

                    Toast.makeText(ctx, "Kode :"+kode+"| Pesan :"+pesan+"| Data : "+varNamaBarang+"|"+varJenisBarang+"|"+varUkuranBarang+"|"+"|"+varHargaMusik, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Terhubung Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
