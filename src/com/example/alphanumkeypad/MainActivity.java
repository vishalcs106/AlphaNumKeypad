package com.example.alphanumkeypad;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	Button button1, button2,button3, button4, button5, button6, button7, button8, button9, button10, button11, button12;
	EditText message;
	String messageString = "";
	boolean countDownEnds = false;
	int sameKeyTapCount = 1;
	int previousButtonID = 0;
	boolean isLowerCase = true;
	boolean isSameKey = false;
	KeyTimer timer = new KeyTimer(2000, 1000);
	boolean isSpacePressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        message = (EditText) findViewById(R.id.editText1);
        
        View.OnLongClickListener longListener = new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Button button = (Button) v;
                messageString +=  button.getText().toString().substring(0, 1);
    			message.append(button.getText().toString().substring(0, 1));
    			message.setSelection(message.getText().length());
                return true;
            }
        };
        
        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Button button = (Button) v;
                if(!isSameAsPrevious(button.getId()) || countDownEnds) {
                	if(isLowerCase)
                		messageString +=  button.getText().toString().substring(1, 2);
                	else
                		messageString +=  button.getText().toString().substring(1, 2).toUpperCase();
                	timer = new KeyTimer(2000, 1000);
                	timer.start();
                	sameKeyTapCount = 1;
                	isSameKey = false;
                }
                else {
                	String messStr = messageString.substring(0, messageString.length()-1);
                	String btnStr = button.getText().toString();
                	
                	if(sameKeyTapCount == btnStr.length()-1)
                		sameKeyTapCount = 0;
                	int keyCount = sameKeyTapCount++;
                	String str = btnStr.substring(keyCount+1);
                	try {
                		if(isLowerCase)
                		messageString = messStr + str.substring(0, 1);
                		else
                			messageString = messStr + str.substring(0, 1).toUpperCase();
                		}
                	catch(Exception e) {
                		e.printStackTrace();
                	}
                }
                
                if(!isSameAsPrevious(button.getId()))
                {
                	timer = new KeyTimer(2000, 1000);
                	timer.start();
                }
                message.setText(messageString);
    			message.setSelection(message.getText().length());
    			previousButtonID = button.getId();
            }
        };
        
        View.OnClickListener caseListener = new View.OnClickListener() {
            public void onClick(View v) {
            	isLowerCase = !isLowerCase;
			}
        };
        
        View.OnClickListener clearListener = new View.OnClickListener() {
            public void onClick(View v) {
            	if(!messageString.equals(""))
            	messageString = messageString.substring(0, messageString.length()-1);
                message.setText(messageString);
                message.setSelection(message.getText().length());
            }
        };
        
        View.OnClickListener spaceListener = new View.OnClickListener() {
            public void onClick(View v) {
            	messageString += " ";
            	message.append(" ");
                message.setSelection(message.getText().length());
            }
        };
        
               
        button1.setOnLongClickListener((OnLongClickListener) longListener);
        button2.setOnLongClickListener((OnLongClickListener) longListener);
        button3.setOnLongClickListener((OnLongClickListener) longListener);
        button4.setOnLongClickListener((OnLongClickListener) longListener);
        button5.setOnLongClickListener((OnLongClickListener) longListener);
        button6.setOnLongClickListener((OnLongClickListener) longListener);
        button7.setOnLongClickListener((OnLongClickListener) longListener);
        button8.setOnLongClickListener((OnLongClickListener) longListener);
        button9.setOnLongClickListener((OnLongClickListener) longListener);
        button10.setOnLongClickListener((OnLongClickListener) longListener);
        button11.setOnLongClickListener((OnLongClickListener) longListener);
        button12.setOnLongClickListener((OnLongClickListener) longListener);
        
        button1.setOnClickListener((OnClickListener) clickListener);
        button2.setOnClickListener((OnClickListener) clickListener);
        button3.setOnClickListener((OnClickListener) clickListener);
        button4.setOnClickListener((OnClickListener) clickListener);
        button5.setOnClickListener((OnClickListener) clickListener);
        button6.setOnClickListener((OnClickListener) clickListener);
        button7.setOnClickListener((OnClickListener) clickListener);
        button8.setOnClickListener((OnClickListener) clickListener);
        button9.setOnClickListener((OnClickListener) clickListener);
        button10.setOnClickListener(caseListener);
        button11.setOnClickListener(spaceListener);
        button12.setOnClickListener(clearListener);
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
    
    Boolean isSameAsPrevious(int buttonID) {
    	if(buttonID == previousButtonID )
    		{isSameKey = true;
    		return true;
    		}
    	else { isSameKey = false;
    		return false;
    	}
    	}
    
    public class KeyTimer extends CountDownTimer {
        public KeyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
            countDownEnds = false;
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
        	//if(!isSameKey)
        	countDownEnds = true;
        }

		@Override
		public void onTick(long arg0) {
			// TODO Auto-generated method stub
			
		}
    }
}


