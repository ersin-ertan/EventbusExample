package com.nullcognition.eventbusexample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);
   }


   @Override
   public boolean onCreateOptionsMenu(Menu menu){
	  // Inflate the menu; this adds items to the action bar if it is present.
	  getMenuInflater().inflate(R.menu.menu_main, menu);
	  return true;
   }

   int backstackCountName = 0;

   @Override
   public boolean onOptionsItemSelected(MenuItem item){
	  // Handle action bar item clicks here. The action bar will
	  // automatically handle clicks on the Home/Up button, so long
	  // as you specify a parent activity in AndroidManifest.xml.
	  int id = item.getItemId();

	  //noinspection SimplifiableIfStatement
	  if(id == R.id.action_send_event){
		 de.greenrobot.event.EventBus.getDefault().post(new EventIncrement(3));
		 return true;
	  }
	  if(id == R.id.action_add_fragment){
		 getFragmentManager().beginTransaction().add(R.id.container, PlaceholderFragment.newInstance()).addToBackStack(Integer.toString(++ backstackCountName)).commit();
		 return true;
	  }

	  return super.onOptionsItemSelected(item);
   }

   @Override
   public void onBackPressed(){
	  super.onBackPressed();
	  if(backstackCountName != 0){
		 -- backstackCountName;
	  }
   }

   /**
	* A placeholder fragment containing a simple view.
	*/
   public static class PlaceholderFragment extends Fragment {

	  private static int ID = 0; // might want to 0 the number or save the fragment instance states when exiting, since this is persisent but the fragments are not
	  private final  int id = ++ ID;

	  int value = 0;

	  android.widget.TextView textViewId    = null;
	  android.widget.TextView textViewValue = null;

	  public PlaceholderFragment(){
	  }

	  public void onEvent(com.nullcognition.eventbusexample.EventIncrement inEventIncrement){

		 if((textViewId != null) && (textViewValue != null)){
			textViewValue.setText(Integer.toString(value += inEventIncrement.getIncrementAmount()));

		 }
	  }

	  public static com.nullcognition.eventbusexample.MainActivity.PlaceholderFragment newInstance(){

		 PlaceholderFragment placeholderFragment = new com.nullcognition.eventbusexample.MainActivity.PlaceholderFragment();
		 de.greenrobot.event.EventBus.getDefault().register(placeholderFragment);

		 return placeholderFragment;
	  }

	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		 View rootView = inflater.inflate(R.layout.fragment_main, container, false);

		 try{
			textViewId = (android.widget.TextView)rootView.findViewById(com.nullcognition.eventbusexample.R.id.textViewId);
			textViewValue = (android.widget.TextView)rootView.findViewById(com.nullcognition.eventbusexample.R.id.textViewValue);
		 }
		 catch(Exception e){
			e.printStackTrace();
			android.util.Log.e(getClass().getSimpleName(), "onCreateView findViewById = null");
		 }

		 textViewId.setText(Integer.toString(id));

		 return rootView;
	  }
   }
}
