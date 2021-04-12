package fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.opu.contactlist.Contact;
import ua.opu.contactlist.ContactsAdapter;
import ua.opu.contactlist.DataViewModel;
import ua.opu.contactlist.R;

public class ContactsFragment extends Fragment implements ContactsAdapter.DeleteItemListener {

    private RecyclerView mRecyclerView;
    private ContactsAdapter adapter;
    private DataViewModel viewModel;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDeleteItem(int position) {
        viewModel.removeContact(position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        viewModel.getData().observe(getViewLifecycleOwner(), contacts -> adapter.notifyDataSetChanged());

        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        adapter = new ContactsAdapter(viewModel.getData().getValue(), this);
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.fab).setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.container, AddContactFragment.class, null)
                    .addToBackStack("add_contact")
                    .commit();
        });
    }
}