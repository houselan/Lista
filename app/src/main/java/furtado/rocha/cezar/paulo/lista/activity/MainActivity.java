package furtado.rocha.cezar.paulo.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import furtado.rocha.cezar.paulo.lista.R;
import furtado.rocha.cezar.paulo.lista.adapter.MyAdapter;
import furtado.rocha.cezar.paulo.lista.model.MainActivityViewModel;
import furtado.rocha.cezar.paulo.lista.model.MyItem;
import furtado.rocha.cezar.paulo.lista.util.Util;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1;
    MyAdapter myAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Botao FAB
        FloatingActionButton fabAddItem = findViewById((R.id.fabAddNewItem));

        //Ouvidor de cliques
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // metodo com Intent explicito para navegar para NewItemActivity
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                //metodo que assume que a Activity NewItemActivity vai retornar com dados para essa Activity
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        // Obtencao do RecyclerView
        RecyclerView rvItens = findViewById(R.id.rvItens);

        // Obtencao do ViewModel que se refere a MainActivity (MainActivityViewlModel)
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        // A lista de itens e obtida a partir do ViewModel e repassada para o Adapter
        List<MyItem> itens = vm.getItens();

        // Criacao do MyAdapter
        myAdapter = new MyAdapter(this,itens);
        // O myAdapter e colocado no RecyclerView
        rvItens.setAdapter(myAdapter);

        // Indica que o tamanho entre os itens da lista nao varia
        rvItens.setHasFixedSize(true);

        // Criacao de um gerenciador de layout do tipo linear e
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // O LayoutManager e colocado no RecyclerView
        rvItens.setLayoutManager(layoutManager);

        // Um divisor que separa cada item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        // O divisor e colocado no RecyclerView
        rvItens.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) { // Verificacao se as condicoes de retorno foram cumpridas
            if (resultCode == Activity.RESULT_OK) {
                // Instancia para guardar dados do item
                MyItem myItem = new MyItem();
                //Obtencao dos dados retornados
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                Uri selectedPhotoURI = data.getData();


                try {
                    // Funcao que carrega a imagem e guarda dentro de um Bitmap, criando assim uma copia da imagem original
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoURI, 100, 100);
                    // O bitmap e guardado dentro de um objeto do tipo MyItem
                    myItem.photo = photo;
                }
                // Caso o arquivo de imagem nao seja encontrado a excecao e disparada
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // Obtencao do ViewModel
                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
                // Obtencao da lista de itens que o ViewModel possui
                List<MyItem> itens = vm.getItens();

                // Adicao do item a lista de itens
                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }
}