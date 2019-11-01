package com.example.dummy.aigentech.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dummy.aigentech.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageDialogFragment extends DialogFragment {

    public static final String TAG = "Message_Dialog";
    EditText etName,etMsg;
    Button btnCreateAd;

    public MessageDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.msg_dialog, container, false);
        etName = view.findViewById(R.id.etName);
        etMsg = view.findViewById(R.id.etMsg);
        btnCreateAd = view.findViewById(R.id.btnCreateAd);
        btnCreateAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString()!=null && !etName.getText().toString().isEmpty() &&
                etMsg.getText().toString()!=null && !etMsg.getText().toString().isEmpty()) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"developer@aigen.tech"});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Enquiry Contact Name:" + etName.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, etMsg.getText().toString());
                    emailIntent.setType("message/rfc822");
                    startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
                }
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

}
