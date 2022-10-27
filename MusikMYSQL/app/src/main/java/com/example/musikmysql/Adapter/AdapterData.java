package com.example.musikmysql.Adapter;

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

import com.example.musikmysql.API.APIRequestData;
import com.example.musikmysql.API.RetroServer;
import com.example.musikmysql.Activity.MainActivity;
import com.example.musikmysql.Activity.UbahActivity;
import com.example.musikmysql.Model.DataModel;
import com.example.musikmysql.Model.ResponseModel;
import com.example.musikmysql.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listdata;
    private int nomusik;
    private List<DataModel> listmusik;


    public AdapterData(Context ctx, List<DataModel> listdata) {
        this.ctx = ctx;
        this.listdata = listdata;
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
        DataModel dm = listdata.get(position);
        holder.tvNo.setText(String.valueOf(dm.getNo()));
        holder.tvJenis.setText(dm.getJenis());
        holder.tvMerk.setText(dm.getMerk());
        holder.tvTipe.setText(dm.getTipe());
        holder.tvSenar.setText(dm.getSenar());
        holder.tvHarga.setText(dm.getHarga());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvNo, tvJenis, tvMerk, tvTipe, tvSenar,tvHarga;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvNo = itemView.findViewById(R.id.tv_no);
            tvJenis = itemView.findViewById(R.id.tv_jenis);
            tvMerk = itemView.findViewById(R.id.tv_merk);
            tvTipe = itemView.findViewById(R.id.tv_tipe);
            tvSenar = itemView.findViewById(R.id.tv_senar);
            tvHarga = itemView.findViewById(R.id.tv_harga);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih Operasi yang akan diakukan");
                    dialogPesan.setCancelable(true);
                    nomusik = Integer.parseInt(tvNo.getText().toString());

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteData();
                            dialogInterface.dismiss();
                            Handler hand = new Handler();
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((MainActivity)ctx).retrieveData();
                                }
                            },500);
                            ((MainActivity)ctx).retrieveData();
                        }

                        private void deleteData() {
                            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                            Call<ResponseModel> hapusData = ardData.ardDeleteData(nomusik);
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
            Call<ResponseModel> ambilData = ardData.ardGetData(nomusik);
            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listmusik = response.body().getData();

                    int varNomusik = listmusik.get(0).getNo();
                    String varJenisMusik = listmusik.get(0).getJenis();
                    String varMerkMusik = listmusik.get(0).getMerk();
                    String varTipeMusik = listmusik.get(0).getTipe();
                    String varSenarMusik = listmusik.get(0).getSenar();
                    String varHargaMusik = listmusik.get(0).getHarga();

                    Intent kirim = new Intent(ctx, UbahActivity.class);
                    kirim.putExtra("xNo",varNomusik);
                    kirim.putExtra("xJenis",varJenisMusik);
                    kirim.putExtra("xMerk",varMerkMusik);
                    kirim.putExtra("xTipe",varTipeMusik);
                    kirim.putExtra("xSenar",varSenarMusik);
                    kirim.putExtra("xHarga",varHargaMusik);
                    ctx.startActivity(kirim);

                    Toast.makeText(ctx, "Kode :"+kode+"| Pesan :"+pesan+"| Data : "+varJenisMusik+"|"+varMerkMusik+"|"+varTipeMusik+"|"+varSenarMusik+"|"+varHargaMusik, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Terhubung Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
