package ua.opu.contactlist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DataViewModel extends ViewModel {

    private final MutableLiveData<List<Contact>> data = new MutableLiveData<>();

    public DataViewModel() {
        data.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<Contact>> getData() {
        return data;
    }

    public void addContact(Contact contact) {
        List<Contact> list = data.getValue();
        list.add(contact);
        data.setValue(list);
    }

    public void removeContact(int index) {
        List<Contact> list = data.getValue();
        list.remove(index);
        data.setValue(list);
    }
}
