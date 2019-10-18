package com.example.pcdashboard.Dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Presenter.EditCommentPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IEditCommentView;

import static com.example.pcdashboard.Manager.IScreenManager.COMMENT_DIALOG;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDialog extends DialogFragment implements IEditCommentView, View.OnClickListener {
    private ScreenManager screenManager;
    private EditCommentPresenter presenter;
    private EditText etInput;
    private ImageButton ibEdit;
    private ImageButton ibCancel;

    public EditDialog() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.UpdateInfoDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_edit, container, false);
        initialize(view);
        setCancelable(false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onResume() {
        presenter.setEditView(this);
        presenter.addEditListener();
        presenter.onInit();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setEditView(null);
        presenter.removeEditListener();
        super.onPause();
    }

    private void initialize(View view) {
        screenManager=ScreenManager.getInstance();
        presenter=new EditCommentPresenter(getContext());
        etInput = view.findViewById(R.id.et_input_edit_dialog);
        ibEdit = view.findViewById(R.id.ib_edit_edit_dialog);
        ibCancel = view.findViewById(R.id.ib_cancel_edit_dialog);
        ibEdit.setOnClickListener(this);
        ibCancel.setOnClickListener(this);
    }

    @Override
    public void onInit(PostComment postComment) {
        etInput.setText(postComment.getContent());
    }

    @Override
    public void onCheckFailure() {
        CustomToast.makeText(getContext(), "Nội dung không được trống", CustomToast.LENGTH_SHORT, CustomToast.WARNING).show();
    }

    @Override
    public void onSuccess() {
        CustomToast.makeText(getContext(), "Thành công", CustomToast.LENGTH_SHORT,CustomToast.SUCCESS).show();
        screenManager.openDialog(COMMENT_DIALOG,null);
        dismiss();
    }

    @Override
    public void onFailure() {
        CustomToast.makeText(getContext(), "Thất bại\nVui lòng thử lại", CustomToast.LENGTH_SHORT,CustomToast.FAILURE).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_edit_edit_dialog:
                presenter.onCheck(etInput.getText().toString().trim());
                break;
            case R.id.ib_cancel_edit_dialog:
                screenManager.openDialog(COMMENT_DIALOG,null);
                dismiss();
                break;
        }
    }
}
