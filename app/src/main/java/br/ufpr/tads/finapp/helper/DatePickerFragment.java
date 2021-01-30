package br.ufpr.tads.finapp.helper;


import android.app.DatePickerDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private TextView textView;
    private boolean withHour;
    private FragmentManager fragmentManager;

    public DatePickerFragment(TextView textView, boolean withHour, FragmentManager supportFragmentManager) {
        this.textView = textView;
        this.withHour = withHour;
        this.fragmentManager = supportFragmentManager;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        if (withHour){
            DialogFragment timerFragment = new TimePickerFragment(textView, year, month, day);
            timerFragment.show(this.fragmentManager, "timePicker");
        } else {
            month++;
            String d = String.valueOf(day);
            String m = String.valueOf(month);
            if (day < 10)
                d = "0"+day;
            if (month < 10)
                m = "0"+month;

            this.textView.setText(d+"/"+m+"/"+year);
        }

    }

}
