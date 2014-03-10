package com.application.healthnow;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VitalSignsFragment extends Fragment{
	
	public VitalSignsFragment() { }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_vitalsigns, container, false);
		 
		return rootView;
	}
}