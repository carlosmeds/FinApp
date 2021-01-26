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
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView t = (TextView)getActivity().findViewById(R.id.hourTransaction);
        String h = String.valueOf(hourOfDay);
        String m = String.valueOf(minute);
        if (hourOfDay < 10)
            h = "0" + h;

        if (minute < 10)
            m = "0" + m;

        t.setText(h + ":"+ m);
    }

}

