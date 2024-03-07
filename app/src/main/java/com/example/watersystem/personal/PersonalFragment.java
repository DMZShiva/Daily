package com.example.watersystem.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watersystem.R;
import com.example.watersystem.map.RouteDetailFragment;
import com.example.watersystem.personal.adapter.PersonalCenterItemAdapter;
import com.example.watersystem.personal.dialog.PersonalLogoutDialog;
import com.example.watersystem.personal.type.PersonalCenterItemType;
import com.example.watersystem.personal.type.PersonalNameType;
import com.example.watersystem.personal.type.PersonalNewVersionType;
import com.example.watersystem.personal.type.PersonalPhoneNumberType;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PersonalFragment extends Fragment {

    private PersonalCenterItemAdapter adapter;
    private RecyclerView recyclerView;
    private TextView logOut;
    private List<PersonalCenterItemType> dataList = new ArrayList<>();

    // 默认当前手机号为加密状态
    private boolean isPlain = false;

    private String phoneNum = "15718807588";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_personal, container, false);

        recyclerView = view.findViewById(R.id.personal_recycler_view);
        logOut = view.findViewById(R.id.personal_logout);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToLogin();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new PersonalCenterItemAdapter(this, null);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        phoneNumber();
        addData();
        adapter.setData(dataList);

        return view;
    }

    public void onPhoneStatusClick() {
        if (isPlain) {
            isPlain = false;
        }

    }

    private void phoneNumber() {

    }



    private void addData() {

        StringBuilder sb =new StringBuilder();
        if(!TextUtils.isEmpty(phoneNum) && phoneNum.length() > 6 ){

            for (int i = 0; i < phoneNum.length(); i++) {
                char c = phoneNum.charAt(i);

                if (i >= 3 && i <= 6) {
                    sb.append('*');

                } else {
                    sb.append(c);

                }
            }
        }

        dataList.add(new PersonalNameType(getString(R.string.personal_name), "Shiva"));
        dataList.add(new PersonalNameType(getString(R.string.personal_id), "1102381023"));
        dataList.add(new PersonalNameType(getString(R.string.personal_password), "1.0.0"));
        dataList.add(new PersonalPhoneNumberType(getString(R.string.personal_phone), sb.toString()));
        dataList.add(new PersonalNewVersionType(getString(R.string.personal_version), "1.0.0", true));
    }

    public void jumpToLogin() {
        PersonalLogoutDialog personalLogoutDialog = new PersonalLogoutDialog();
        personalLogoutDialog.show(getChildFragmentManager(), "logout");
    }
}
