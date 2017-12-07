// Generated code from Butter Knife. Do not modify!
package com.ysg.yashige.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ysg.yashige.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotificationWebViewActivity_ViewBinding implements Unbinder {
  private NotificationWebViewActivity target;

  @UiThread
  public NotificationWebViewActivity_ViewBinding(NotificationWebViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NotificationWebViewActivity_ViewBinding(NotificationWebViewActivity target, View source) {
    this.target = target;

    target.finishIV = Utils.findRequiredViewAsType(source, R.id.finish_iV, "field 'finishIV'", ImageView.class);
    target.titleTV = Utils.findRequiredViewAsType(source, R.id.title_tV, "field 'titleTV'", TextView.class);
    target.notifiWV = Utils.findRequiredViewAsType(source, R.id.notifi_wV, "field 'notifiWV'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationWebViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.finishIV = null;
    target.titleTV = null;
    target.notifiWV = null;
  }
}
