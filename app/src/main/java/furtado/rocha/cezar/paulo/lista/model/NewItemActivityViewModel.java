package furtado.rocha.cezar.paulo.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    // Guardando o endereco URI da foto escolhida pelo usuario
    Uri selectPhotoLocation = null;

    // Metodo para obter a lista de itens
    public Uri getSelectPhotoLocation() {
        return selectPhotoLocation;
    }

    // Metodo para colocar o endereco URI dentro do ViewModel
    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
