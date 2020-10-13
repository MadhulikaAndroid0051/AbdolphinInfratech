package com.example.abdolphininfratech.Fragement;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.abdolphininfratech.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UnpaidReportSearchFragment extends Fragment {
    EditText et_from_date,et_to_date,et_fromID,et_ToID;
    Button btn_search;
    private DatePickerDialog mDatePickerDialog,getmDatePickerDialog;
    String title="Unpaid Income Report";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unpadi_report_serch_frgment, container, false);

        //EditText for Date
        btn_search=view.findViewById(R.id.btn_search);
        et_from_date = view.findViewById(R.id.from_date);
        et_to_date = view.findViewById(R.id.to_date);
        et_fromID=view.findViewById(R.id.search_fromID);
        et_ToID=view.findViewById(R.id.search_toID);


        setDateTimeField();
        setToDateTimeField();
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
        String ToId=et_ToID.getText().toString().trim();
        String FromID=et_fromID.getText().toString().trim();
        Fragment mFragment = null;
        Bundle bundle=new Bundle();
        bundle.putString("From_Date",From_Date);
        bundle.putString("To_Date",To_Date);
        bundle.putString("ToID",ToId);
        bundle.putString("FromID",FromID);
        mFragment = new UnpaidncomeReportFragment();
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
