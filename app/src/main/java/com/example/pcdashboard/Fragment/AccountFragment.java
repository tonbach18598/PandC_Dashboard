package com.example.pcdashboard.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Presenter.AccountPresenter;
import com.example.pcdashboard.Presenter.IAccountPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IAccountView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements IAccountView {
private IAccountPresenter iAccountPresenter;
private TextView tvLogOut;
ScreenManager screenManager;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account, container, false);
        tvLogOut = view.findViewById(R.id.tv_logout_account);
        screenManager = ScreenManager.getInstance();

        IAccountPresenter iAccountPresenter;
        iAccountPresenter=new AccountPresenter(this);
        setPresenter(iAccountPresenter);
        return view;
    }

    @Override
    public void setPresenter(IAccountPresenter iAccountPresenter) {
        this.iAccountPresenter=iAccountPresenter;
    }

    @Override
    public void onClickRow() {

    }

    @Override
    public void onDestroy() {
        iAccountPresenter.onDestroy();
        super.onDestroy();
    }
}
