// Generated code from Butter Knife. Do not modify!
package com.ysg.yashige.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.barteksc.pdfviewer.PDFView;
import com.ysg.yashige.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PDFViewActivity_ViewBinding implements Unbinder {
  private PDFViewActivity target;

  @UiThread
  public PDFViewActivity_ViewBinding(PDFViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PDFViewActivity_ViewBinding(PDFViewActivity target, View source) {
    this.target = target;

    target.pdfView = Utils.findRequiredViewAsType(source, R.id.pdfView, "field 'pdfView'", PDFView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PDFViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pdfView = null;
  }
}
