// Generated code from Butter Knife. Do not modify!
package com.ysg.yashige.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ysg.yashige.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebPageActivity_ViewBinding implements Unbinder {
  private WebPageActivity target;

  @UiThread
  public WebPageActivity_ViewBinding(WebPageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WebPageActivity_ViewBinding(WebPageActivity target, View source) {
    this.target = target;

    target.webView = Utils.findRequiredViewAsType(source, R.id.webView, "field 'webView'", WebView.class);
    target.webLoading = Utils.findRequiredViewAsType(source, R.id.web_loading, "field 'webLoading'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WebPageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.webView = null;
    target.webLoading = null;
  }
}
