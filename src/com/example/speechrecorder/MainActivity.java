package com.example.speechrecorder;

import java.io.File;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	
	MediaPlayer mediaPlayer;
	MediaRecorder mediaRecorder;
	Button stopbtn;
	Button startbtn;
	Button playbtn;
	Button stopPlaybackbtn;
	private String OUTPUT_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startRecording();
        stopRecording();
        playRecording();
        stopPlayback();
        goBackLanding();
        sendEmail();
        
        stopbtn.setEnabled(false);
        playbtn.setEnabled(false);
        stopPlaybackbtn.setEnabled(false);
        
        OUTPUT_FILE=Environment.getExternalStorageDirectory()+"/audiorecorder.3gpp";
    }

	private void sendEmail() {
		// TODO Auto-generated method stub
		Button mailbtn=(Button)findViewById(R.id.sendMail);
    	mailbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent emailAudio=new Intent(MainActivity.this,EmailActivity.class);
				startActivity(emailAudio);
			}
    	});
	}

	private void goBackLanding() {
		// TODO Auto-generated method stub
		Button backbtn=(Button)findViewById(R.id.back);
    	backbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
    		
    	});
	}

	private void startRecording() {
		// TODO Auto-generated method stub
    	
    	startbtn=(Button)findViewById(R.id.start);
    	startbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sMediaRecorder();
				File outFile=new File(OUTPUT_FILE);
				
				if(outFile.exists())
					outFile.delete();
				
				mediaRecorder=new MediaRecorder();
				mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				mediaRecorder.setOutputFile(OUTPUT_FILE);
				try {
					mediaRecorder.prepare();
					mediaRecorder.start();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//startbtn.setEnabled(false);
		        stopbtn.setEnabled(true);
		        Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
				
			}

    	});
		
	}

	private void sMediaRecorder() {
		// TODO Auto-generated method stub
		if(mediaRecorder!=null)
			mediaRecorder.release();		
	}
	
    private void stopRecording() {
		// TODO Auto-generated method stub
    	stopbtn=(Button)findViewById(R.id.stop);
    	stopbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mediaRecorder!=null)
		    		mediaRecorder.stop();
				//stopbtn.setEnabled(false);
		        playbtn.setEnabled(true);
		        Toast.makeText(getApplicationContext(), "Audio recorded successfully",Toast.LENGTH_LONG).show(); 
			}
    		
    	});
    			
	}
    


	private void playRecording() {
		// TODO Auto-generated method stub
		playbtn=(Button)findViewById(R.id.play);
    	playbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v){
				// TODO Auto-generated method stub
				sMediaPlayer();
				mediaPlayer=new MediaPlayer();
				try {
					mediaPlayer.setDataSource(OUTPUT_FILE);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					mediaPlayer.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mediaPlayer.start();
				//playbtn.setEnabled(false);
		        stopPlaybackbtn.setEnabled(true);
				Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();
						    		
			}
    		
    	});
		
	}
	

	private void sMediaPlayer() {
		// TODO Auto-generated method stub
		if(mediaRecorder!=null)
		{
			try{
				mediaRecorder.release();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

	private void stopPlayback() {
		// TODO Auto-generated method stub
		stopPlaybackbtn=(Button)findViewById(R.id.stopPlayback);
    	stopPlaybackbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mediaPlayer!=null)
		    		mediaPlayer.stop();
		        Toast.makeText(getApplicationContext(), "Recorded Audio Stopped",Toast.LENGTH_LONG).show(); 
			}
    		
    	});
		
	}
	
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
