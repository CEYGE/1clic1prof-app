package fr.clic1prof.activities.dashboard;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.clic1prof.R;

public class CreationEventActivity extends AppCompatActivity {

    private TextView date_begin;
    private TextView date_end;
    private TextView frequency;
    private TextView reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_event);

        date_begin = findViewById(R.id.editTextDateBegin);
        date_end = findViewById(R.id.editTextDateEnd);
        frequency = findViewById(R.id.textViewFrequency);
        reminder = findViewById(R.id.textViewReminder);

        listenerTime();
        setListenerFrequency();
        setListenerReminder();
        setListenerBack();
        setListenerCreate();
        // TODO Sliding gesture to change page, create navigation to activity instead of intent ?

    }

    /**
     * Create listener that close the activity when clicking to the back arrow
     */
    public void setListenerBack(){
        LinearLayout layout = findViewById(R.id.header_event);
        layout.setOnClickListener(v -> finish());
    }

    /**
     * Create listener when clikcing to create button
     */
    public void setListenerCreate(){
        Button button = findViewById(R.id.buttonCreate);
        button.setOnClickListener(v -> finish());
    }

    /**
     * Create listeners for the frequency attribute. onClick display the bottom sheet
     * and onCheckedChanged change the textview when a frequency is selected.
     */
    public void setListenerFrequency(){
        LinearLayout layout = findViewById(R.id.layout_frequency);
        layout.setOnClickListener((View.OnClickListener) v -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CreationEventActivity.this,R.style.BottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getApplicationContext().getApplicationContext())
                    .inflate(
                            R.layout.bottom_sheet_frequency,
                            (LinearLayout)findViewById(R.id.bottomSheetContainer)
                    );

            RadioGroup radio_button = bottomSheetView.findViewById(R.id.radio_frequency);
            radio_button.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group, checkedId) -> {
                int selectedId = radio_button.getCheckedRadioButtonId();
                RadioButton button = (RadioButton) bottomSheetView.findViewById(selectedId);
                frequency.setText(button.getText());
            });

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });

    }

    /**
     * Create listeners for the reminder attribute. onClick display the bottom sheet
     * and onCheckedChanged change the textview when a frequency is selected.
     */
    public void setListenerReminder(){
        LinearLayout layout = findViewById(R.id.layout_reminder);
        layout.setOnClickListener((View.OnClickListener) v -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CreationEventActivity.this,R.style.BottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getApplicationContext().getApplicationContext())
                    .inflate(
                            R.layout.bottom_sheet_reminder,
                            (LinearLayout)findViewById(R.id.bottomSheetContainer)
                    );

            RadioGroup radio_button = bottomSheetView.findViewById(R.id.radio_reminder);
            radio_button.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group, checkedId) -> {
                int selectedId = radio_button.getCheckedRadioButtonId();
                RadioButton button = (RadioButton) bottomSheetView.findViewById(selectedId);
                reminder.setText(button.getText());
            });

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });

    }


    public void listenerTime(){
        date_begin.setOnClickListener(v -> showDateTimeDialog(date_begin));
        date_end.setOnClickListener(v -> showDateTimeDialog(date_end));
    }



    /**
     * Showing date/time dialog then changing in the TextView parameter
     * @param date_time
     */
    private void showDateTimeDialog(final TextView date_time) {
        final Calendar calendar= Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener= (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

            TimePickerDialog.OnTimeSetListener timeSetListener= (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd MMM yy, HH:mm");

                date_time.setText(simpleDateFormat.format(calendar.getTime()));
            };
            new TimePickerDialog(CreationEventActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
        };
        new DatePickerDialog(CreationEventActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

}