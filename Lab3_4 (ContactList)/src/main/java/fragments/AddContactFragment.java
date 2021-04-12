package fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import ua.opu.contactlist.Contact;
import ua.opu.contactlist.DataViewModel;
import ua.opu.contactlist.R;

import static android.app.Activity.RESULT_OK;

public class AddContactFragment extends Fragment {

    private static final int IMAGE_CAPTURE_REQUEST_CODE = 7777;

    private ImageView profileImage;     // элемент UI для фотографии контакта
    private Uri uri;                    // путь к файлу с фотографией
    private DataViewModel viewModel;


    public AddContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // проверяем было ли сделано фото
        if (requestCode == IMAGE_CAPTURE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // получаем сделанное фото
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                // меняем ImageView с изображением контакта
                profileImage.setImageBitmap(imageBitmap);

                String filename = "contact_" + System.currentTimeMillis() + ".png";

                File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), filename);
                FileOutputStream fileOutputStream;
                try {
                    fileOutputStream = new FileOutputStream(outputFile);
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

                    // сохраняем путь к файлу в формате Uri
                    uri = Uri.fromFile(outputFile);

                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // получаем объект ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        // получаем ссылки на элементы UI
        profileImage = view.findViewById(R.id.profile_image);
        EditText contactName = view.findViewById(R.id.name_et);      // поле имени контакта
        EditText contactEmail = view.findViewById(R.id.email_et);    // поле почты контакта
        EditText contactPhone = view.findViewById(R.id.phone_et);    // поле номера телефона контакта
        Button addButton = view.findViewById(R.id.button_add);       // кнопка добавления контакта
        Button photoButton = view.findViewById(R.id.button_camera);  // кнопка для фото
        Button cancelButton = view.findViewById(R.id.button_cancel); // кнопка возврата

        // вешаем слушатели на кнопки

        addButton.setOnClickListener(v -> {
            // проверяем чтоб все поля были заполнены
            if (contactName.getText().toString().equals("")) {
                Toast.makeText(requireContext(), "Name field is empty", Toast.LENGTH_SHORT).show();
            } else if (contactEmail.getText().toString().equals("")) {
                Toast.makeText(requireContext(), "Email field is empty", Toast.LENGTH_SHORT).show();
            } else if (contactPhone.getText().toString().equals("")) {
                Toast.makeText(requireContext(), "Phone field is empty", Toast.LENGTH_SHORT).show();
            } else {
                String name = contactName.getText().toString();
                String email = contactEmail.getText().toString();
                String phone = contactPhone.getText().toString();

                // Добавляем новый контакт в список
                Contact contact = new Contact(name, email, phone, uri);
                viewModel.addContact(contact);

                getActivity().onBackPressed();
            }
        });

        photoButton.setOnClickListener(v -> {
            // запрашиваем ОС открыть камеру, чтобы сделать фото
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            try {
                startActivityForResult(takePictureIntent, IMAGE_CAPTURE_REQUEST_CODE);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(requireContext(), "Error while trying to open camera app", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(v -> getActivity().onBackPressed());
    }
}