package ua.opu.contactlist;

import android.net.Uri;

// обычный POJO класс, сущность для хранения данных о контакте
public class Contact {

    private String name;
    private String email;
    private String phone;
    private Uri imageUri;

    public Contact(String name, String email, String phone, Uri imageUri) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
