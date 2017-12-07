// Generated code from Butter Knife. Do not modify!
package com.ysg.yashige.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ysg.yashige.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LaunchActivity_ViewBinding implements Unbinder {
  private LaunchActivity target;

  @UiThread
  public LaunchActivity_ViewBinding(LaunchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LaunchActivity_ViewBinding(LaunchActivity target, View source) {
    this.target = target;

    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
    target.text = Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LaunchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image = null;
    target.text = null;
  }
}
