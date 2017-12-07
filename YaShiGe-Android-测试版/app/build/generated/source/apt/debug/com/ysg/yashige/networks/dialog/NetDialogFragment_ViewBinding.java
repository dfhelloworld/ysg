// Generated code from Butter Knife. Do not modify!
package com.ysg.yashige.networks.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ysg.yashige.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NetDialogFragment_ViewBinding implements Unbinder {
  private NetDialogFragment target;

  @UiThread
  public NetDialogFragment_ViewBinding(NetDialogFragment target, View source) {
    this.target = target;

    target.loadingTv = Utils.findRequiredViewAsType(source, R.id.loading_tv, "field 'loadingTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NetDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loadingTv = null;
  }
}
