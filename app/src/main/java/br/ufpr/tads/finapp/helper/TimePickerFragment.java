package br.ufpr.tads.finapp.helper;

import android.app.Dialog;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import br.ufpr.tads.finapp.R;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private TextView textView;
    private int year;
    private int month;
    private int day;

    public TimePickerFragment(TextView textView, int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.textView = textView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Date:
        String yyyy = String.valueOf(year);
        String dd = String.valueOf(day);
        month++;
        String MM = String.valueOf(month);

        if (day < 10)
            dd = "0"+day;
        if (month < 10)
            MM = "0"+month;

        String HH = String.valueOf(hourOfDay);
        String mm = String.valueOf(minute);
        if (hourOfDay < 10) {
            HH = "0" + HH;
        }

        if (minute < 10) {
            mm = "0" + mm;
        }

        this.textView.setText(dd+"/"+MM+"/"+yyyy+" "+HH+ ":"+mm);
    }

}

