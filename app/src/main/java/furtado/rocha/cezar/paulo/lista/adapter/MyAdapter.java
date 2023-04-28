package furtado.rocha.cezar.paulo.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import furtado.rocha.cezar.paulo.lista.R;
import furtado.rocha.cezar.paulo.lista.activity.MainActivity;
import furtado.rocha.cezar.paulo.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {

    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position);

        View v = holder.itemView;

        ImageView imvphoto = v.findViewById(R.id.imvPhoto);
        imvphoto.setImageURI(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvdesc = v.findViewById((R.id.tvDesc));
        tvdesc.setText(myItem.description);
    }

    public int getItemCount() {
        return itens.size();
    }
}
