package com.example.pokertimer;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditRoundActivity extends Activity {
	
	private Round round;
	
	EditText textSBEdit;
	EditText textBBEdit;
	EditText textAnteEdit;
	EditText textTimeShow;
	CheckBox chckBreakEdit;
	
	/**
	 * Set Activity content
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity);
        
        ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
        
        textSBEdit = (EditText) findViewById(R.id.sb_edit);
        textBBEdit = (EditText) findViewById(R.id.bb_edit);
        textAnteEdit = (EditText) findViewById(R.id.ante_edit);
        textTimeShow = (EditText) findViewById(R.id.time_show);
        chckBreakEdit = (CheckBox) findViewById(R.id.break_edit);

        processIntent();
	}
	
	/**
	 * Processes intent
	 */
	private void processIntent(){
		Intent intent = getIntent();
		this.round = (Round) intent.getSerializableExtra("Round");
		textSBEdit.setText(this.round.getSB()+"");
		textBBEdit.setText(this.round.getBB()+"");
		textAnteEdit.setText(this.round.getAnte()+"");
		textTimeShow.setText(this.round.getTime()+"");
		chckBreakEdit.setChecked(this.round.isBreak());
		checkControls(this.round.isBreak());
	}
	
	public void onCheckboxClicked(View view) {
	    boolean checked = ((CheckBox) view).isChecked();
	    checkControls(checked);
	}
	
	public void checkControls(boolean checked){
		if(checked){
	    	textSBEdit.setText("0");
	    	textBBEdit.setText("0");
	    	textAnteEdit.setText("0");
	    	textSBEdit.setEnabled(false);
	    	textBBEdit.setEnabled(false);
	    	textAnteEdit.setEnabled(false);
	    }else{
	    	textSBEdit.setText(Integer.toString(this.round.getSB()));
	    	textBBEdit.setText(Integer.toString(this.round.getBB()));
	    	textAnteEdit.setText(Integer.toString(this.round.getAnte()));
	    	textSBEdit.setEnabled(true);
	    	textBBEdit.setEnabled(true);
	    	textAnteEdit.setEnabled(true);
	    }
	}
	
	/**
	 * Send modified blinds back to the AddGameActivity
	 */
	private void saveModifiedBlind(){
		Intent resultIntent = new Intent();
		setResult(Activity.RESULT_OK, resultIntent);
		round.setSB(Integer.parseInt(textSBEdit.getText().toString()));
		round.setBB(Integer.parseInt(textBBEdit.getText().toString()));
		round.setAnte(Integer.parseInt(textAnteEdit.getText().toString()));
		round.setTime(Integer.parseInt(textTimeShow.getText().toString()));
		round.setBreak(chckBreakEdit.isChecked());
		resultIntent.putExtra("Round", round);
		finish();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.edit_round, menu);
	    return true;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
	
	    		
	    		int itemId = item.getItemId();
	    		
	    		if(itemId == android.R.id.home){
	    				super.onBackPressed();
	    	    		return true;
	    		}else if(itemId == R.id.menu_round_ok){
	    		    	this.saveModifiedBlind();
	    		}
	    		return true;
	}
}