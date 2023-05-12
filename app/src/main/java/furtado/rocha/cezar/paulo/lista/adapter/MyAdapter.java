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
        //Inflador que sera usado para ler o arquivo xml e entao criar os elementos de interface
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { // Preencher a UI com os dados de um item
        // Recepcao da posicao para indicar o item correto
        MyItem myItem = itens.get(position);

        View v = holder.itemView;

        // Preenchimento da UI com a imagem
        ImageView imvphoto = v.findViewById(R.id.imvPhoto);
        imvphoto.setImageBitmap(myItem.photo);

        // Preenchimento da UI com o titulo
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        // Preenchimento da UI com a descricao
        TextView tvdesc = v.findViewById((R.id.tvDesc));
        tvdesc.setText(myItem.description);
    }

    // Retorno do tamanho da lista dos itens
    public int getItemCount() {
        return itens.size();
    }
}
