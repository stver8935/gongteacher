package com.study.gongteacher.ui.custom.confirmdialog;

import android.view.View;

public interface ConfirmDialogNavigator {
    void onPositiveButton(View.OnClickListener listener);
    void onNegativeButton(View.OnClickListener listener);

}
