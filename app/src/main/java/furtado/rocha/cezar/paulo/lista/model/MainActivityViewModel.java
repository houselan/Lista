package furtado.rocha.cezar.paulo.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    // Guardando a lista de itens cadastrados
    List<MyItem> itens = new ArrayList<>();

    // Obtencao da lista de itens
    public List<MyItem> getItens() {
        return itens;
    }
}
