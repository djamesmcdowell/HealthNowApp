package com.example.dbsetup;

import com.example.dbsetup.Adapter.LoginDataBaseAdapter;
import com.example.dbsetup.Adapter.SignUPActivity;
import com.example.dbsetup.DBAdapter;
import ca.demo.databasedemo.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;

/*
 * Steps to using the DB:
 * 1. [DONE] Instantiate the DB Adapter
 * 2. [DONE] Open the DB
 * 3. [DONE] use get, insert, delete, .. to change data.
 * 4. [DONE]Close the DB
 */

/**
 * Demo application to show how to use the built-in SQL lite database.
 */
public class MainActivity extends Activity {

	DBAdapter myDb;
	Button btnSignIn, btnSignUp;
	LoginDataBaseAdapter loginDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// create a instance of SQLite Database
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter = loginDataBaseAdapter.open();

		// Get The Refference Of Buttons
		btnSignIn = (Button) findViewById(R.id.buttonSignIN);
		btnSignUp = (Button) findViewById(R.id.buttonSignUP);

		// Set OnClick Listener on SignUp button
		btnSignUp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// / Create Intent for SignUpActivity abd Start The Activity
				Intent intentSignUP = new Intent(getApplicationContext(),
						SignUPActivity.class);
				startActivity(intentSignUP);
			}
		});
	}

	// Methos to handleClick Event of Sign In Button
	public void signIn(View V) {
		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.login);
		dialog.setTitle("Login");

		// get the Refferences of views
		final EditText editTextUserName = (EditText) dialog
				.findViewById(R.id.editTextUserNameToLogin);
		final EditText editTextPassword = (EditText) dialog
				.findViewById(R.id.editTextPasswordToLogin);

		Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);

		// Set On ClickListener
		btnSignIn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// get The User name and Password
				String userName = editTextUserName.getText().toString();
				String password = editTextPassword.getText().toString();

				// fetch the Password form database for respective user name
				String storedPassword = loginDataBaseAdapter
						.getSinlgeEntry(userName);

				// check if the Stored password matches with Password entered by
				// user
				if (password.equals(storedPassword)) {
					Toast.makeText(MainActivity.this,
							"Congrats: Login Successfull", Toast.LENGTH_LONG)
							.show();
					dialog.dismiss();
				} else {
					Toast.makeText(MainActivity.this,
							"User Name or Password does not match",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		dialog.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		closeDB();
	}

	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}

	private void closeDB() {
		myDb.close();
	}

	public void onClick_ClearAll(View v) {
		// displayText("Clicked clear all!");
		myDb.deleteAll();
	}

	public void onClick_DisplayRecords(View v) {
		// displayText("Clicked display record!");

		Cursor cursor = myDb.getAllRows();
		displayRecordSet(cursor);
	}

	// Display an entire recordset to the screen.
	private void displayRecordSet(Cursor cursor) {
		// String message = "";
		// populate the message from the cursor

		// Reset cursor to start, checking to see if there's data:
		if (cursor.moveToFirst()) {
			do {
				// Process the data:
				// int id = cursor.getInt(DBAdapter.COL_ROWID);
				// String name = cursor.getString(DBAdapter.COL_NAME);
				// int studentNumber = cursor.getInt(DBAdapter.COL_STUDENTNUM);
				// String favColour = cursor.getString(DBAdapter.COL_FAVCOLOUR);

				// Append data to the message:
				// message += "id=" + id
				// +", name=" + name
				// +", #=" + studentNumber
				// +", Colour=" + favColour
				// +"\n";
			} while (cursor.moveToNext());
		}

		// Close the cursor to avoid a resource leak.
		cursor.close();

		// displayText(message);
	}
}
