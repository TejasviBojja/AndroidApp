package com.example.speechrecorder;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailActivity extends ActionBarActivity {
	
	EditText emailAddressField;
	public String eAddress;
	public String finalEmailAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
		
		sendEmailOnClick();
		cancelEmailOnClick();
		
	}

	private void cancelEmailOnClick() {
		// TODO Auto-generated method stub
		Button tocancelemailbtn=(Button)findViewById(R.id.cancelEmail);
		tocancelemailbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
	}

	private void sendEmailOnClick() {
		// TODO Auto-generated method stub
		Button tosendemailbtn=(Button)findViewById(R.id.sendEmail);
		tosendemailbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				emailAddressField = (EditText)findViewById(R.id.emailIDField);
				eAddress = emailAddressField.getText().toString();
				finalEmailAddress = eAddress;
				//"mailto:" +
				
				Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
				
				sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { finalEmailAddress });
				sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Recorded Audio Attachment");
				sendEmailIntent.putExtra(Intent.EXTRA_TEXT, "This is the audio that was recorded by the Application");
				sendEmailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+"/storage/sdcard/audiorecorder.3gpp"));
				
				sendEmailIntent.setData(Uri.parse(finalEmailAddress));
				sendEmailIntent.setType("Audio/3gp");
				
				
				try {
			         startActivity(Intent.createChooser(sendEmailIntent, "Send mail..."));
			         finish();
			         Log.i("Finished sending email...", "");
			      } catch (android.content.ActivityNotFoundException ex) {
			         Toast.makeText(EmailActivity.this,"There is no email client installed.", Toast.LENGTH_SHORT).show();
			      }
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
