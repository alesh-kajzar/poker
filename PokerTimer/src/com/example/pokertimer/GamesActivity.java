package com.example.pokertimer;
import java.util.List;
import java.util.Random;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;///asi zbytecny <--
import android.widget.ListView;
import android.widget.Toast;

public class GamesActivity extends ListActivity {
  private GamesDataSource datasource;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    datasource = new GamesDataSource(this);
    datasource.open();

    List<Game> values = datasource.getAllGames();

    // Use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<Game> adapter = new ArrayAdapter<Game>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
    
    //my code
    
    ActionBar ab = getActionBar();
    ab.setDisplayShowHomeEnabled(false);
    ab.setDisplayShowTitleEnabled(false);
    
    ListView list = (ListView)findViewById(R.id.list);
    ///registerForContextMenu(list);                            <--------
	/*ListView lv = (ListView)findViewById(R.id.list); 
	lv.setClickable(false);
	lv.setOnItemClickListener(new OnItemClickListener()
	{
		 @Override
		 public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
	    { 
	        makeToast();
	    }
	});*/
	

	/*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
		   public void onItemClick(AdapterView parentView, View childView, int position, long id) {  
			   //makeToast();
		        }
		        public void onNothingClick(AdapterView parentView) {  

		        }  
		      });  
	
	*/
	
	//Toast.makeText(this, view.getId(), Toast.LENGTH_LONG).show();
  }
  
  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    if (v.getId()==R.id.list) {
      AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
      menu.setHeaderTitle("zblo");
      String[] menuItems =  new String[] { "mTitleRaw" };
      for (int i = 0; i<menuItems.length; i++) {
        menu.add(Menu.NONE, i, i, menuItems[i]);
      }
    }
  }
  
  //Shows top menu - Officially its called action bar
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.games, menu);
      
      return true;
  }
  
  protected void startSettingsActivity(){
  Intent intent = new Intent(this, SettingsActivity.class);
  //intent.putExtra(AgeDisplayerActivity.AGE, age);
  startActivity(intent);
  }
  
  protected void startAddGameActivity(){
	  Intent intent = new Intent(this, AddGameActivity.class);
	  //intent.putExtra(AgeDisplayerActivity.AGE, age);
	  startActivity(intent);
	  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

      switch (item.getItemId()) {
	      case R.id.menu_add:    
	    	  startAddGameActivity();
	          break;
	      case R.id.menu_search:
	    	  ///
	    	  break;
	      case R.id.menu_settings:
	    	  startSettingsActivity();
	    	  break;
	      default:
	          return super.onOptionsItemSelected(item);
      }
      return true;
  }
  
  public void makeToast(){
	  Toast.makeText(this, "Ahoj", Toast.LENGTH_LONG).show();
	  
  }
  
 /* public void onHold(View view) {
	  
	  Toast.makeText(this, view.getId(), Toast.LENGTH_LONG).show();
	  
  }*/

  // Will be called via the onClick attribute
  // of the buttons in main.xml
  
  public void onClick(View view) {
    @SuppressWarnings("unchecked")
    ArrayAdapter<Game> adapter = (ArrayAdapter<Game>) getListAdapter();
    //Game game = null;
    switch (view.getId()) {
    	case R.id.menu_add:
    		Toast.makeText(this, "P�idat hru", Toast.LENGTH_LONG).show();
    	break;
    	
    	case R.id.menu_settings:
    		Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show();
    	break;
    	
    	/*case R.id.menu_search:
    		Toast.makeText(this, "P�idat hru", Toast.LENGTH_LONG).show();
    	break;*/
    /*case R.id.add:
    	///zde se bude volat prepinani kontextu na nove okno nebo dialog, kde ziskame informace o vytvarene hre
      String newGameName = getName();//new String[] { "Cool", "Very nice", "Hate it" };
     if(newGameName.equals(""))
     {
    	 Toast.makeText(this, "Zadejte nazev noveho typu hry", Toast.LENGTH_LONG).show();
     }
     else
     {
      // Save the new game to the database
      game = datasource.createGame(newGameName);
      adapter.add(game);
     }
      break;
    case R.id.delete:
      if (getListAdapter().getCount() > 0) {
        game = (Game) getListAdapter().getItem(0);
        datasource.deleteGame(game);
        adapter.remove(game);
      }
      break;*/

    	
    }
    adapter.notifyDataSetChanged();
  }

  @Override
  protected void onResume() {
    datasource.open();
    super.onResume();
  }

  @Override
  protected void onPause() {
    datasource.close();
    super.onPause();
  }

} 