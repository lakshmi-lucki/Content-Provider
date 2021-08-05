package com.pd.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvQueryResult;

  //  predefined columns in DB of contacts
    private String[] mColProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
    };
    // private String check = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + "= 'Ebi Brother'";
    private String orderby = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        tvQueryResult = (TextView) findViewById( R.id.tv );
        ContentResolver contentResolver = getContentResolver();
        //queries
        Cursor cursor = contentResolver.query( ContactsContract.Contacts.CONTENT_URI, mColProjection, null, null, orderby );

        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder stringBuilderQueryResult = new StringBuilder( " " );
            while (cursor.moveToNext()) {
                stringBuilderQueryResult.append( cursor.getString( 0 ) + " ," +  cursor.getString( 1 ) + " ," + cursor.getString( 2 ) );
            }
            tvQueryResult.setText( stringBuilderQueryResult.toString() );
        } else {
            tvQueryResult.setText( "No Contacts in Device" );

        }
    }
}