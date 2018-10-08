// Generated code from Butter Knife. Do not modify!
package com.example.sevenacademy.sevendemo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatActivity_ViewBinding implements Unbinder {
  private ChatActivity target;

  private View view2131230801;

  private View view2131230802;

  @UiThread
  public ChatActivity_ViewBinding(ChatActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChatActivity_ViewBinding(final ChatActivity target, View source) {
    this.target = target;

    View view;
    target.chatField = Utils.findRequiredViewAsType(source, R.id.chatField, "field 'chatField'", EditText.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.chatList = Utils.findRequiredViewAsType(source, R.id.chatList, "field 'chatList'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.fab, "method 'sendMessage'");
    view2131230801 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendMessage();
      }
    });
    view = Utils.findRequiredView(source, R.id.fabImage, "method 'sendImage'");
    view2131230802 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendImage();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ChatActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.chatField = null;
    target.toolbar = null;
    target.chatList = null;

    view2131230801.setOnClickListener(null);
    view2131230801 = null;
    view2131230802.setOnClickListener(null);
    view2131230802 = null;
  }
}
