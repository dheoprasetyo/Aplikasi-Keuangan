package com.destiny.pos.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.destiny.pos.InputActivity;
import com.destiny.pos.Model.Model;
import com.destiny.pos.R;
import com.destiny.pos.SharedPreferance.DB_Helper;
import com.destiny.pos.UtamaActivity;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Model> dataList;
    private Context mContext;
    private RecyclerView mRecyclerV;
    DB_Helper dbHelper;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView deskripsi,tanggal,tipe,kategori,jumlah,keterangan,jenis;
        public Button delete,update;
        public LinearLayout linearJenis;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            deskripsi=(TextView)v.findViewById(R.id.tvDeskripsi);
            tanggal=(TextView)v.findViewById(R.id.tvTanggal);
            tipe=(TextView)v.findViewById(R.id.tvTipe);
            kategori=(TextView)v.findViewById(R.id.tvKategori);
            jumlah=(TextView)v.findViewById(R.id.tvJumlah);
            jenis=(TextView)v.findViewById(R.id.tvJenis);
            linearJenis=(LinearLayout)v.findViewById(R.id.linearJenis);
            keterangan=(TextView)v.findViewById(R.id.tvKeterangan);
            delete=(Button)v.findViewById(R.id.btnDelete);
            update=(Button)v.findViewById(R.id.btnUpdate);
        }
    }

    public void add(int position, Model model) {
        dataList.add(position, model);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public DataAdapter(List<Model> myDataset, Context context, RecyclerView recyclerView) {
        dataList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.list_data, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Model model = dataList.get(position);
        holder.deskripsi.setText(model.getDeskripsi());
        holder.tanggal.setText(model.getTanggal());
        holder.tipe.setText(model.getTipe());
        holder.kategori.setText(model.getKategori());
        holder.jumlah.setText(model.getJumlah());
        holder.keterangan.setText(model.getKeterangan());
        if (model.getDeskripsi().equals("Pribadi")){
            holder.linearJenis.setVisibility(View.GONE);
        }else{
            holder.jenis.setText(model.getJenis());
        }
        dbHelper = new DB_Helper(mContext);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteData(model.getId(),mContext);
                Toast.makeText(mContext, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, UtamaActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(mContext, InputActivity.class);
                goInput.putExtra("IO","Update");
                goInput.putExtra("ID",model.getId());
                goInput.putExtra("DESKRIPSI",model.getDeskripsi());
                goInput.putExtra("TANGGAL",model.getTanggal());
                goInput.putExtra("TIPE",model.getTipe());
                goInput.putExtra("KATEGORI",model.getKategori());
                goInput.putExtra("JUMLAH",model.getJumlah());
                goInput.putExtra("JENIS",model.getJenis());
                goInput.putExtra("KETERANGAN",model.getKeterangan());
                mContext.startActivities(new Intent[]{goInput});
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataList.size();
    }



}
