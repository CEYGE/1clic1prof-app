package fr.clic1prof.abstractviews;


import android.widget.CalendarView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import fr.clic1prof.R;

public abstract class AgendaFragment extends AbstractFragment {
    Long date;
    //TODO add specific method for agenda here


    @Override
    public void onResume() {
        super.onResume();
        TextView title = getActivity().findViewById(R.id.titleFragment);
        setCalendarListener();
        title.setText(this.getString(R.string.agenda));
    }


    public void setCalendarListener(){
        CalendarView cv = getActivity().findViewById(R.id.calendarView);
        TextView textView = getActivity().findViewById(R.id.textViewDate);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE d MMMM yyyy", Locale.FRANCE);
                String selectedDates = sdf.format(new Date(year-1900, month,dayOfMonth));
                textView.setText(selectedDates.toUpperCase(Locale.FRANCE));
            }
        });
    }
}
