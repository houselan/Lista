package furtado.rocha.cezar.paulo.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

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
                //Verificacao se os campos estao vazios
                if (photoSelected == null) {
                    // Se os campos estiverem vazios uma mensagem de erro e exibida
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()) {
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
                photoSelected = data.getData();
                //Obtencao do ImageVIew e colocacao da foto para ser exibida
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                imvfotoPreview.setImageURI(photoSelected);
            }
        }
    }


}