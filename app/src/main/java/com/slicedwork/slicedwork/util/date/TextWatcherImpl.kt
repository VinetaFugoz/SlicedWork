package com.slicedwork.slicedwork.util.date

import android.text.Editable
import android.text.TextWatcher
import java.util.*

object TextWatcherImpl : TextWatcher {

    var current = ""
    private const val ddmmyyyy = "DDMMYYYY"
    private val cal: Calendar = Calendar.getInstance()

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.toString() != current) {
            var clean = s.toString().replace("[^\\d.]", "");
            val cleanC = current.replace("[^\\d.]", "");

            val cl = clean.length;
            var sel = cl;
            for (i in 2 until (cl and (6)) step 2) {
                sel++
            }
            //Fix for pressing delete next to a forward slash
            if (clean.equals(cleanC)) sel--;

            if (clean.length < 8) {
                clean += ddmmyyyy.substring(clean.length);
            } else {
                //This part makes sure that when we finish entering numbers
                //the date is correct, fixing it otherwise
                var day = Integer.parseInt(clean.substring(0, 2));
                var mon = Integer.parseInt(clean.substring(2, 4));
                var year = Integer.parseInt(clean.substring(4, 8));

                if (mon > 12) mon = 12;
                cal.set(Calendar.MONTH, mon - 1);

                year = if (year < 1900) 1900 else if (year > 2100) 2100 else year

                cal.set(Calendar.YEAR, year);
                // ^ first set year for the line below to work correctly
                //with leap years - otherwise, date e.g. 29/02/2012
                //would be automatically corrected to 28/02/2012

                day =
                    if (day > cal.getActualMaximum(Calendar.DATE)) cal.getActualMaximum(Calendar.DATE) else day
                clean = String.format("%02d%02d%02d", day, mon, year);
            }

            clean = String.format(
                "%s/%s/%s", clean.substring(0, 2),
                clean.substring(2, 4),
                clean.substring(4, 8)
            );

            sel = if (sel < 0) 0 else sel;
            current = clean;
        }
    }
    override fun afterTextChanged(s: Editable?) {}
}