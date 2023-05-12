package furtado.rocha.cezar.paulo.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import furtado.rocha.cezar.paulo.lista.R;
import furtado.rocha.cezar.paulo.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // Obtencao do ViewlModel que se refere a NewItemActivity (NewItemActivityViewModel)
        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);

        // Obtencao do URI guardado no ViewModel
        Uri selectPhotoLocation = vm.getSelectPhotoLocation();

        // Condicao que e executada se o usuario ja tiver escolhido uma imagem antes de rotacionar a a tela
        if (selectPhotoLocation != null) {
            // ImageView setado na tela
            ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
            imvfotoPreview.setImageURI(selectPhotoLocation);
        }

        // Obtencao do ImageButton e definicao do ouvidor de cliques nele
        ImageButton imgCl = findViewById(R.id.imbCl);
        imgCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Execucao da abertura da galeria para escolher a foto
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT); // Intent implicito para abrir documento
                photoPickerIntent.setType("image/*"); // Busca por apenas documentos com qualquer tipo de imagem
                startActivityForResult(photoPickerIntent,PHOTO_PICKER_REQUEST); // Execucao do Intent com a imagem selecionada como resultado
            }
        });

        // Obtemos o botao e colocamos o ouvidor de cliques nele
        Button btnAddItem = findViewById(R.id.btnAddItem);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtencao da imagem guardada no ViewModel
                Uri photoSelected = vm.getSelectPhotoLocation();
                //Verificacao se o campo de foto esta vazio
                if (photoSelected == null) {
                    // Se o campo estiver vazio uma mensagem de erro e exibida
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                //Verificacao se o campo de titulo esta vazio
                if (title.isEmpty()) {
                    // Se o campo estiver vazio uma mensagem de erro e exibida
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                 EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                //Verificacao se o campo de descricao esta vazio
                if (description.isEmpty()) {
                    // Se o campo estiver vazio uma mensagem de erro e exibida
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                //Retorno de dados para a Activity que a chamou
                Intent i = new Intent();
                i.setData(photoSelected);
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK, i);
                //Finalizacao da Activity
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) { // Verificacao se o requestCode e o mesmo do que o fornecido
            if (resultCode == Activity.RESULT_OK) { // Verificacao se o resultCode e um codigo de sucesso
                //Obtencao do endereco para acessar a imagem
                Uri photoSelected = data.getData();
                //Obtencao do ImageVIew
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);

                //Exibicao da foto
                imvfotoPreview.setImageURI(photoSelected);

                // Obtencao do ViewlModel
                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
                // Colocando o endereço URI da imagem escolhida no ViewModel
                vm.setSelectPhotoLocation(photoSelected);
            }
        }
    }


}