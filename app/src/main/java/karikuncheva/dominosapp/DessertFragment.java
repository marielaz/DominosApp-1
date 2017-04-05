package karikuncheva.dominosapp;

/**
 * Created by Mariela Zviskova on 10.3.2017 г..
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.User;

public class DessertFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dessert, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_dessert);

        User user = (User) getArguments().getSerializable("user");
        DessertCustomAdapter adapter = new DessertCustomAdapter(getActivity(), Shop.getInstance().getDesserts(), user);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return v;

    }
}
