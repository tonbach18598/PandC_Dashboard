package com.example.pcdashboard.View;

public interface ILoginView {
    void onInput();
    void showLoadingDialog();
    void onLoginSuccess();
    void onCheckFailure();
    void onLoginFailure();
}
