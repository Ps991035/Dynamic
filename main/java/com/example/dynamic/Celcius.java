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


public class Celcius extends Fragment {
    private FragmentAListener listener;
    private EditText editText;
    private Button buttonOk;

    public interface FragmentAListener {
        void onInputASent(String input);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.celcius, container, false);

        editText = v.findViewById(R.id.edit_text);
        buttonOk = v.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                listener.onInputASent(input);
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
                editText.setError("Please Enter some valid number");
            }
            else
                editText.setError("Please Enter some valid number");
            return false;
        }
    }

    public void clearData() {
        editText.setText("");
    }

    public void convert(String newText) {
        double data = Double.parseDouble(newText);
        double res = (data-32)/1.8;
        editText.setText(""+res);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
        }
        else {
            Toast.makeText(context, "Error Occured !!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
