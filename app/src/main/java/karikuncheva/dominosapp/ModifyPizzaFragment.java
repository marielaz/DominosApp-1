package karikuncheva.dominosapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyPizzaFragment extends Fragment {

    interface ModifyCommunicator {
        public void modifyPrice(double sum);
    }

    List<RadioButton> pizza_type_bnts = new ArrayList<RadioButton>();
    Button small_bnt, med_bnt, large_bnt;
    RadioButton trad_bnt, ital_bnt, thin_bnt;
    static Pizza pizza;
    //    private Pizza.Size size;
//    private Pizza.Type type;
    RecyclerView recyclerView;
    private double tempSizeChecked;
    static double sum;
    private double small;
    private double midd;
    private ModifyCommunicator mc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_modify, container, false);

        if (getActivity().getIntent().getExtras() != null) {
            Bundle b = getActivity().getIntent().getExtras();
            if (b.getSerializable("pizza") != null) {
                pizza = (Pizza) b.getSerializable("pizza");
                pizza.size = pizza.getSize();
                pizza.type = pizza.getType();
            }
        }

        mc = (ModifyCommunicator) getActivity();
        tempSizeChecked = pizza.getPrice();
        sum = tempSizeChecked;
        small = pizza.getPrice() - 1.50;
        midd = pizza.getPrice() - 1.00;

        recyclerView = (RecyclerView) v.findViewById(R.id.ingredients_recycler_view);
        ModifyAdapter adapter = new ModifyAdapter(getActivity(), pizza,  mc);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false);
        trad_bnt = (RadioButton) v.findViewById(R.id.trad_bnt);
        ital_bnt = (RadioButton) v.findViewById(R.id.ital_bnt);
        thin_bnt = (RadioButton) v.findViewById(R.id.thin_bnt);

        pizza_type_bnts.add(trad_bnt);
        pizza_type_bnts.add(ital_bnt);
        pizza_type_bnts.add(thin_bnt);

        small_bnt = (Button) v.findViewById(R.id.small);
        med_bnt = (Button) v.findViewById(R.id.med);
        large_bnt = (Button) v.findViewById(R.id.large);

        trad_bnt.setChecked(true);
        large_bnt.setPressed(true);

        small_bnt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                small_bnt.setPressed(true);
                pizza.setSize(Product.Size.SMALL);
                med_bnt.setPressed(false);
                large_bnt.setPressed(false);

                sum -= tempSizeChecked;
                sum += small;
                tempSizeChecked = small;
                mc.modifyPrice(sum);
                return true;
            }

        });
        med_bnt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                med_bnt.setPressed(true);
                pizza.setSize(Product.Size.MEDIUM);
                small_bnt.setPressed(false);
                large_bnt.setPressed(false);

                sum -= tempSizeChecked;
                sum += midd;
                tempSizeChecked = midd;
                mc.modifyPrice(sum);
                return true;
            }
        });
        large_bnt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                large_bnt.setPressed(true);
                if (pizza.size != Product.Size.LARGE) {
                    pizza.setSize(Product.Size.LARGE);
                }
                small_bnt.setPressed(false);
                med_bnt.setPressed(false);

                sum -= tempSizeChecked;
                sum += pizza.getPrice();
                tempSizeChecked = pizza.getPrice();
                mc.modifyPrice(sum);
                return true;
            }
        });

        for (RadioButton button : pizza_type_bnts) {
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) processRadioButtonClick(buttonView);
                }

            });
        }
        if (trad_bnt.isChecked()) {
            pizza.setType(Product.Type.TRADITIONAL);

        } else if (ital_bnt.isChecked()) {
            pizza.setType(Product.Type.ITALIAN_STYLE);
        } else {
            pizza.setType(Product.Type.THIN_AND_CRISPY);
        }

        return v;
    }

    private void processRadioButtonClick(CompoundButton buttonView) {
        for (RadioButton button : pizza_type_bnts) {
            if (button != buttonView) button.setChecked(false);
        }
    }

}