package br.ufpr.tads.finapp.helper;


import android.app.DatePickerDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import br.ufpr.tads.finapp.R;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private TextView textView;

    public DatePickerFragment(TextView textView) {
        this.textView = textView;
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
