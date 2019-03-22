package com.tekit.software.mystonecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tekit.software.mystonecalculator.Model.StoneDimention;
import com.tekit.software.mystonecalculator.Singleton.StoneSingleton;
import com.tekit.software.mystonecalculator.Util.StoneSortingComparator;
import com.tekit.software.mystonecalculator.sql.DataBaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getName();

    ArrayList<StoneDimention> listOfCustomers = new ArrayList<StoneDimention>();


    ImageView imageViewCancel;

    EditText editTextNoNug;

    //@BindView(R.id.et_height_nug)
    EditText editTextHeightNug;

    //@BindView(R.id.et_length_nug)
    EditText editTextLengthNug;

    //@BindView(R.id.tv_sq_foot)
    TextView textViewSqFoot;


    @BindView(R.id.total_nug)
    TextView textViewTotalNu;

    @BindView(R.id.total_sq_foot)
    TextView textViewTotalSqFoot;

    @BindView(R.id.layout_container)
    LinearLayout linearLayoutContainer;

    @BindView(R.id.btn_add_row)
    Button buttonAddRow;

    @BindView(R.id.btn_sort)
    Button buttonSort;

    @BindView(R.id.addInHashMap)
    Button buttonAddInHashMap;

    @BindView(R.id.getHash)
    Button buttonGetHash;


    private DataBaseAdapter dataBaseAdapter;




    private ArrayList<ArrayList<StoneDimention>> arrayLists;

    public static Float roundOf(Float f) {
        String s = String.valueOf(f);
        if (s.contains(".")) {
            String[] split = s.split("\\.");
            String decimal = split[1];
            return Float.valueOf(decimal) > 50 ? Float.valueOf(split[0]) + 1 : Float.valueOf(split[0]);

        } else {
            return f;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        dataBaseAdapter = new DataBaseAdapter(this);
        buttonAddRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRow();
            }
        });
        addRow();

        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    addDataToList();
            }
        });


        buttonAddInHashMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseAdapter.addTrackingNoConstraint(listOfCustomers);
                StoneSingleton.getInstance().setArrayList(listOfCustomers);
              listOfCustomers.clear();
              linearLayoutContainer.removeAllViews();

              }
        });

        buttonGetHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ArrayList<StoneDimention>> listArrayList = StoneSingleton.getInstance().getListArrayList();
                for (ArrayList<StoneDimention> list : listArrayList) {
                    for (StoneDimention stoneDimention : list) {

                        sortData(stoneDimention);
                        Log.e("sortStoneDimention", "" + stoneDimention);


                    }


                }
            }
        });


    }

    private void addRow() {
        if(checkRowIsEmpty() ) {
            int i = linearLayoutContainer.getChildCount();
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            final View view = layoutInflater.inflate(R.layout.row_calculator_data, null);
            TextView textView = view.findViewById(R.id.tv_no);

            editTextNoNug = view.findViewById(R.id.et_no_nug);
            editTextLengthNug = view.findViewById(R.id.et_length_nug);
            editTextHeightNug = view.findViewById(R.id.et_height_nug);
            textViewSqFoot = view.findViewById(R.id.tv_sq_foot);
            imageViewCancel = view.findViewById(R.id.iv_cancel);

            editTextHeightNug.addTextChangedListener(new MyTextWatcher(view));
            editTextLengthNug.addTextChangedListener(new MyTextWatcher(view));
            editTextNoNug.addTextChangedListener(new MyTextWatcher(view));

            imageViewCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    editTextNoNug = view.findViewById(R.id.et_no_nug);
                    textViewSqFoot = view.findViewById(R.id.tv_sq_foot);
                    textViewTotalSqFoot.setText(String.valueOf(Float.valueOf(checkEmpty1(textViewTotalSqFoot.getText().toString())) - (Float.valueOf(checkEmpty1(textViewSqFoot.getText().toString())))));
                    textViewTotalNu.setText(String.valueOf(Float.valueOf(checkEmpty1(textViewTotalNu.getText().toString())) - (Float.valueOf(checkEmpty1(editTextNoNug.getText().toString())))));
                    linearLayoutContainer.removeView(view);

                }
            });

            textView.setText(String.valueOf(i++));
            linearLayoutContainer.addView(view);
            view.setTag(String.valueOf(i++));
        }else {
            Toast.makeText(this,"Row is already inserted ",Toast.LENGTH_SHORT).show();
        }
    }

    private String checkEmpty(String value) {
        return value.isEmpty() ? "1" : value;
    }

    private String checkEmpty1(String value) {
        return value.isEmpty() ? "0" : value;
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            textViewSqFoot = view.findViewById(R.id.tv_sq_foot);
            editTextNoNug = view.findViewById(R.id.et_no_nug);
            textViewTotalSqFoot.setText("");
            Log.e(TAG, " Anurag "+" before text changed : " +"charSequence "+ charSequence.toString());
            Log.e(TAG, " Anurag before text changed : start " + start + " before " + before + " count " + count);
            Log.e("Anurag", "Number of Nug before : " + editTextNoNug.getText().toString());
            Log.e("Anurag", "Total No of Nug before " + textViewTotalNu.getText().toString());
            if (editTextNoNug.getText().hashCode() == charSequence.hashCode()) {
                textViewTotalNu.setText(String.valueOf(Float.valueOf(checkEmpty1(textViewTotalNu.getText().toString())) - Float.valueOf(checkEmpty1(charSequence.toString()))));
                // textViewTotalSqFoot.setText(String.valueOf(Float.valueOf(checkEmpty(textViewTotalSqFoot.getText().toString()))-Float.valueOf(checkEmpty(textViewSqFoot.getText().toString()))));
                Log.e("Anurag", "in If Number of Nug befor: " + editTextNoNug.getText().toString());
                Log.e("Anurag", "in If Total No of Nug before " + textViewTotalNu.getText().toString());
            }
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
            Log.e(TAG, "Anurag" + " on text changed : "+ " charSequence " + charSequence.toString());
            Log.e(TAG, "Anurag "+ "on text changed : start " + start + " count " + count + " after " + after);

            editTextNoNug = view.findViewById(R.id.et_no_nug);
            editTextLengthNug = view.findViewById(R.id.et_length_nug);
            editTextHeightNug = view.findViewById(R.id.et_height_nug);
            textViewSqFoot = view.findViewById(R.id.tv_sq_foot);


            if (editTextNoNug.getText().hashCode() == charSequence.hashCode()) {
                textViewSqFoot.setText(String.valueOf(Float.valueOf(checkEmpty(charSequence.toString())) * Float.valueOf(checkEmpty(editTextHeightNug.getText().toString())) * Float.valueOf(checkEmpty(editTextLengthNug.getText().toString()))));
              //  Log.e("Anurag", "No of Nug : " + textViewSqFoot.getText().toString());
                Log.e("Anurag", "Number of Nug : " + editTextNoNug.getText().toString());
                textViewTotalNu.setText(String.valueOf(Float.valueOf(checkEmpty1(charSequence.toString())) + Float.valueOf(checkEmpty1(textViewTotalNu.getText().toString()))));
                Log.e("Anurag", "Total No of Nug " + textViewTotalNu.getText().toString());

            } else if (editTextHeightNug.getText().hashCode() == charSequence.hashCode()) {
                textViewSqFoot.setText(String.valueOf(Float.valueOf(checkEmpty(charSequence.toString())) * Float.valueOf(checkEmpty(editTextLengthNug.getText().toString())) * Float.valueOf(checkEmpty(editTextNoNug.getText().toString()))));
                Log.e(TAG, "height of Nug : " + textViewSqFoot.getText().toString());
                Log.e(TAG, "heigth1 of Nug : " + charSequence.toString());
            } else if (editTextLengthNug.getText().hashCode() == charSequence.hashCode()) {
                textViewSqFoot.setText(String.valueOf(Float.valueOf(checkEmpty(charSequence.toString())) * Float.valueOf(checkEmpty(editTextNoNug.getText().toString())) * Float.valueOf(checkEmpty(editTextHeightNug.getText().toString()))));
                Log.e(TAG, "Length of Nug : " + textViewSqFoot.getText().toString());
                Log.e(TAG, "Length1 of Nug : " + charSequence.toString());
            }
            if (!editTextNoNug.getText().toString().isEmpty() && !editTextHeightNug.getText().toString().isEmpty() && !editTextLengthNug.getText().toString().isEmpty()) {
                textViewSqFoot.setText(String.valueOf(Float.valueOf(textViewSqFoot.getText().toString())));
                Log.e(TAG, "All Empty " + textViewSqFoot.getText().toString());

            }

           /* if (!editTextNoNug.getText().toString().isEmpty() && !editTextHeightNug.getText().toString().isEmpty() && !editTextLengthNug.getText().toString().isEmpty()) {
                textViewSqFoot.setText(String.valueOf(roundOf(Float.valueOf(textViewSqFoot.getText().toString()))));
            }
              */


        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.e(TAG, "after text changed : editable " + editable.toString());
            if (editTextLengthNug.getText().toString().isEmpty() && editTextHeightNug.getText().toString().isEmpty() && editTextNoNug.getText().toString().isEmpty()) {
                textViewSqFoot.setText("");
            }

            int n = linearLayoutContainer.getChildCount();
            Log.e(TAG, "Total Child Count :" + n);
            View view = null;
            textViewTotalSqFoot.setText("");
            for (int j = 0; n > j; j++) {
                Log.e(TAG, "For Loop count :" + j);
                view = linearLayoutContainer.getChildAt(j);
                LinearLayout linearLayout = view.findViewById(R.id.layout_row);
                TextView textView = linearLayout.findViewById(R.id.tv_sq_foot);
                EditText editText = linearLayout.findViewById(R.id.et_no_nug);
                textViewTotalSqFoot.setText(String.valueOf(Float.valueOf(checkEmpty1(textViewTotalSqFoot.getText().toString())) + Float.valueOf(checkEmpty1(textView.getText().toString()))));
                Log.e(TAG, "Total Sq Foot : " + textViewTotalSqFoot.getText().toString());
                // textViewTotalNu.setText(String.valueOf(Float.valueOf(checkEmpty1(textViewTotalNu.getText().toString()))+Float.valueOf(checkEmpty1(editText.getText().toString()))));
            }
        }
    }


    private void addDataToList(){
        listOfCustomers.clear();
        int n = linearLayoutContainer.getChildCount();
        Log.e(TAG, "Total Child Count :" + n);
        View view = null;

        for (int j = 0; n > j; j++) {
            Log.e(TAG, "For Loop count :" + j);
            view = linearLayoutContainer.getChildAt(j);
            LinearLayout linearLayout = view.findViewById(R.id.layout_row);
            editTextNoNug = linearLayout.findViewById(R.id.et_no_nug);
            editTextLengthNug = linearLayout.findViewById(R.id.et_length_nug);
            editTextHeightNug = linearLayout.findViewById(R.id.et_height_nug);
            textViewSqFoot = linearLayout.findViewById(R.id.tv_sq_foot);
            listOfCustomers.add(new StoneDimention(editTextNoNug.getText().toString(),editTextLengthNug.getText().toString(),editTextHeightNug.getText().toString(),textViewSqFoot.getText().toString()));

        }

        sortStoneDimention();


    }


    private boolean checkRowIsEmpty(){
        int n = linearLayoutContainer.getChildCount();
        if(n!=0) {
            View view = linearLayoutContainer.getChildAt(n-1);
            LinearLayout linearLayout = view.findViewById(R.id.layout_row);
            editTextNoNug = linearLayout.findViewById(R.id.et_no_nug);
            editTextLengthNug = linearLayout.findViewById(R.id.et_length_nug);
            editTextHeightNug = linearLayout.findViewById(R.id.et_height_nug);
            textViewSqFoot = linearLayout.findViewById(R.id.tv_sq_foot);
            if (editTextLengthNug.getText().toString().isEmpty() || editTextHeightNug.getText().toString().isEmpty() || editTextNoNug.getText().toString().isEmpty()) {
                return false;
            }else return true;

        }else return true;


    }
    private void sortStoneDimention(){
        Collections.sort(listOfCustomers,
                new StoneSortingComparator());

        // after Sorting: iterate using enhanced for-loop
        System.out.println("\n\nAfter Sorting: iterate using"
                + " enhanced for-loop\n");
        linearLayoutContainer.removeAllViews();
        for(StoneDimention stoneDimention: listOfCustomers) {

            sortData(stoneDimention);
            Log.e("sortStoneDimention", ""+stoneDimention);


        }
    }

    private void sortData(StoneDimention stoneDimention){

        int i = linearLayoutContainer.getChildCount();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View view = layoutInflater.inflate(R.layout.row_calculator_data, null);
        TextView textView = view.findViewById(R.id.tv_no);

        editTextNoNug = view.findViewById(R.id.et_no_nug);
        editTextLengthNug = view.findViewById(R.id.et_length_nug);
        editTextHeightNug = view.findViewById(R.id.et_height_nug);
        textViewSqFoot = view.findViewById(R.id.tv_sq_foot);
        imageViewCancel = view.findViewById(R.id.iv_cancel);

        editTextNoNug.setText(stoneDimention.getNoOfNug());
        editTextHeightNug.setText(stoneDimention.getHeightOfNug());
        editTextLengthNug.setText(stoneDimention.getLengthOfNug());
        textViewSqFoot.setText(stoneDimention.getSqureFeet());

        editTextHeightNug.addTextChangedListener(new MyTextWatcher(view));
        editTextLengthNug.addTextChangedListener(new MyTextWatcher(view));
        editTextNoNug.addTextChangedListener(new MyTextWatcher(view));

        imageViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                editTextNoNug = view.findViewById(R.id.et_no_nug);
                textViewSqFoot = view.findViewById(R.id.tv_sq_foot);
                textViewTotalSqFoot.setText(String.valueOf(Float.valueOf(checkEmpty1(textViewTotalSqFoot.getText().toString()))-(Float.valueOf(checkEmpty1(textViewSqFoot.getText().toString())))));
                textViewTotalNu.setText(String.valueOf(Float.valueOf(checkEmpty1(textViewTotalNu.getText().toString()))-(Float.valueOf(checkEmpty1(editTextNoNug.getText().toString())))));
                linearLayoutContainer.removeView(view);

            }
        });

        textView.setText(String.valueOf(i++));
        linearLayoutContainer.addView(view);
        view.setTag(String.valueOf(i++));
    }


}
