package content.content;

import java.util.Date;

import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract.Contacts.Data;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv=(TextView)findViewById(R.id.textView1);
		StringBuffer mybuffer=new StringBuffer();
		ContentResolver resolver=getContentResolver();
		Cursor mycursor=resolver.query(CallLog.Calls.CONTENT_URI,null,null,null,null);
		int key_num=mycursor.getColumnIndex(CallLog.Calls.NUMBER);
		int key_duration=mycursor.getColumnIndex(CallLog.Calls.DURATION);
		int key_date=mycursor.getColumnIndex(CallLog.Calls.DATE);
		int key_type=mycursor.getColumnIndex(CallLog.Calls.TYPE);
		while(mycursor.moveToNext())
		{
			String myphonenumber=mycursor.getString(key_num);
			String myphoneduration=mycursor.getString(key_duration);
			String myphonedateid=mycursor.getString(key_date);
			String myphonetype=mycursor.getString(key_type);
			Date mydate=new Date(Long.valueOf(myphonedateid));
			int i=Integer.parseInt(myphonetype);
			String iom=null;
			switch(i)
			{
			case CallLog.Calls.INCOMING_TYPE:
			iom="Incoming_call";
			break;
			case CallLog.Calls.MISSED_TYPE:
			iom="Missed_call";
			break;
			case CallLog.Calls.OUTGOING_TYPE:
			iom="Outgoing_call";
			break;
			
			}
			mybuffer.append("My Phone number is/n"+myphonenumber+"\n Duration is "+myphoneduration+"\n Call type is/n"+iom+"\n Call Date/n"+mydate);
			
			
		}
		tv.setText(mybuffer);
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
