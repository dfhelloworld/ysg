// Generated code from Butter Knife. Do not modify!
package com.ysg.yashige.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ysg.yashige.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WelcomeADActivity_ViewBinding implements Unbinder {
  private WelcomeADActivity target;

  @UiThread
  public WelcomeADActivity_ViewBinding(WelcomeADActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WelcomeADActivity_ViewBinding(WelcomeADActivity target, View source) {
    this.target = target;

    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
    target.skipText = Utils.findRequiredViewAsType(source, R.id.skip, "field 'skipText'", TextView.class);
    target.userText = Utils.findRequiredViewAsType(source, R.id.use, "field 'userText'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WelcomeADActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewPager = null;
    target.skipText = null;
    target.userText = null;
  }
}
