package ua.opu.contactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactHolder> {

    // константы для определения типа используемого макета элемента списка
    private static final int EMPTY_LIST_TYPE = -1;
    private static final int NON_EMPTY_LIST_TYPE = 1;

    private List<Contact> contactList;  // источник данных
    private DeleteItemListener deleteItemListener;

    class ContactHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView email;
        private TextView phone;
        private ImageView image;
        private ImageButton deleteButton;
        private DeleteItemListener deleteItemListener;

        public ContactHolder(@NonNull View itemView, DeleteItemListener deleteItemListener) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            image = itemView.findViewById(R.id.contact_image);
            deleteButton = itemView.findViewById(R.id.clear_button);
            this.deleteItemListener = deleteItemListener;
        }
    }

    public interface DeleteItemListener {
        void onDeleteItem(int position);
    }

    public ContactsAdapter(List<Contact> contactList, DeleteItemListener deleteItemListener) {
        this.contactList = contactList;
        this.deleteItemListener = deleteItemListener;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // выбираем макет в зависимости от viewType и производим inflation
        if (viewType == NON_EMPTY_LIST_TYPE) {
            v = layoutInflater.inflate(R.layout.list_contact, parent, false);
        } else {
            v = layoutInflater.inflate(R.layout.list_no_items, parent, false);
        }

        return new ContactHolder(v, deleteItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        // если список не пустой, заполняем элемент списка данными
        if (getItemViewType(position) == NON_EMPTY_LIST_TYPE) {
            Contact contact = contactList.get(position);

            holder.name.setText(contact.getName());
            holder.email.setText(contact.getEmail());
            holder.phone.setText(contact.getPhone());
            // проверяем сделано ли для контакта фото, и если нет, ставим фото по умолчанию
            if (contact.getImageUri() != null) {
                holder.image.setImageURI(contact.getImageUri());
            } else {
                holder.image.setImageResource(R.drawable.new_user_placeholder);
            }

            holder.deleteButton.setOnClickListener(v -> {
                // удаляем элемент из списка и уведомляем адаптер об изменении ситочника данных
                contactList.remove(position);
                this.notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        // если список пуст, возвращаем 1 вместо нуля, если не пуст - реальный размер списка
        return Math.max(contactList.size(), 1);
    }

    @Override
    public int getItemViewType(int position) {
        // возвращаем константу для определения типа макета для процесса inflation
        return contactList.isEmpty() ? EMPTY_LIST_TYPE : NON_EMPTY_LIST_TYPE;
    }
}

