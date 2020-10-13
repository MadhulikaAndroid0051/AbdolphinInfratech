package com.example.abdolphininfratech.Fragement;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.abdolphininfratech.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PayoutRequestReportSearchFragment extends Fragment {


    EditText et_from_date,et_to_date;
    Button btn_search;
    Spinner spinner_status;
    String[] search_status = { "All", "Pending",
            "Approved", "Declined"};
    String SpinnerValue;
    String title="PayOut Request Report";

    private DatePickerDialog mDatePickerDialog,getmDatePickerDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payout_request_report_search_fragment, container, false);

        //EditText for Date
        et_from_date=view.findViewById(R.id.from_date);
        et_to_date=view.findViewById(R.id.to_date);

        spinner_status=view.findViewById(R.id.spinner_status);

        btn_search=view.findViewById(R.id.btn_search);
        final List<String> categories = new ArrayList<String>();
        categories.add("All");
        categories.add("Pending");
        categories.add("Approved");
        categories.add("Declined");
        ArrayAdapter<String> adp = new ArrayAdapter<String>
                (getContext(),android.R.layout.simple_dropdown_item_1line,categories);
        spinner_status.setAdapter(adp);

        spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==1)
                {
                    String selectedValue =adapterView.getItemAtPosition(i).toString();

                   // SpinnerValue   =adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(getContext(),SpinnerValue+ "", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getContext(), "Plz Select Block", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        et_from_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDatePickerDialog.show();
                return false;
            }
        });
        et_to_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                getmDatePickerDialog.show();
                return false;
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendData();
            }
        });

        setDateTimeField();
        setToDateTimeField();
        return view;
    }
    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);

                et_from_date.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private void setToDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        getmDatePickerDialog= new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();

                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                final Date startDate = newDate.getTime();
                String toDate = sd.format(startDate);

                et_to_date.setText(toDate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        getmDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    public void SendData()
    {
        String From_Date=et_from_date.getText().toString().trim();
        String To_Date=et_to_date.getText().toString().trim();
        Fragment mFragment = null;
        Bundle bundle=new Bundle();
        bundle.putString("From_Date",From_Date);
        bundle.putString("To_Date",To_Date);
        bundle.putString("SpinnerValue","Approved");
        mFragment = new PayOutRequestReportFragment();
        mFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,mFragment).addToBackStack("login").commit();


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(title);

    }

}
