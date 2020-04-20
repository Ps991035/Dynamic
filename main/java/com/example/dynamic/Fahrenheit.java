package com.example.dynamic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fahrenheit extends Fragment {
    private FragmentBListener listener;
    private EditText editText;
    private Button buttonOk;

    public interface FragmentBListener {
        void onInputBSent(String input);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fahrenheit, container, false);

        editText = v.findViewById(R.id.edit_text);
        buttonOk = v.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                listener.onInputBSent(input);
            }
        });

        return v;
    }

    boolean validInput(){
        try{
            Double.parseDouble(editText.getText().toString());
            return true;
        }
        catch (Exception e){
            editText.requestFocus();
            if(editText.getText().toString().length()==0) {
                editText.setError("Please enter some number");
            }
            else
                editText.setError("Please enter valid number");
            return false;
        }
    }

    public void clearData() {
        editText.setText("");
    }

    public void convert(String newText) {
        try{
            double data = Double.parseDouble(newText);
            double res = 1.8*data+32;
            editText.setText(""+res);
        }
        catch (Exception e){
            editText.requestFocus();
            if(newText.length()==0) {
                editText.setError("Please enter some number");
            }
            else
                editText.setError("Please enter valid number");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentBListener) {
            listener = (FragmentBListener) context;
        }
        else {
            Toast.makeText(context, "Error occured !!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
