package com.aiday.loader;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;

import com.aiday.constants.Key;
import com.aiday.preference.PrefHelper;

public class ContactLoader extends Thread {

    private Context mContext;

    ContactLoader(Context context) {
        this.mContext = context;
    }

    @Override
    public void run() {

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        long timeCheck = PrefHelper.getInstance().loadLong(Key.LAST_UPDATED_CONTACT, (long) -1);

        ContentResolver contentResolver = mContext.getContentResolver();

        String[] PROJECTION = new String[] {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION, ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP + ">?",
                new String[]{String.valueOf(timeCheck)}, null);

        if (cursor != null && cursor.getCount() > 0) {
            try {
                final int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                final int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                final int colHasNumber = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
                final int colTimeStamp = cursor.getColumnIndex(ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP);

                String name, number;
                while (cursor.moveToNext()) {
                    if (Integer.parseInt(cursor.getString(colHasNumber)) > 0) {
                        name = cursor.getString(nameIndex);
                        number = cursor.getString(numberIndex);

                        String timeStamp = cursor.getString(colTimeStamp);
                        if (Long.parseLong(timeStamp) > timeCheck) {
                            timeCheck = Long.parseLong(timeStamp);
                            PrefHelper.getInstance().saveState(Key.LAST_UPDATED_CONTACT, timeCheck);
                        }
                    }
                }
            } finally {
                cursor.close();
            }
        }
    }
}
