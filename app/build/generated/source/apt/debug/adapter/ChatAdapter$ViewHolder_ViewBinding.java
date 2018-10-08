// Generated code from Butter Knife. Do not modify!
package adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.sevenacademy.sevendemo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ChatAdapter.ViewHolder target;

  @UiThread
  public ChatAdapter$ViewHolder_ViewBinding(ChatAdapter.ViewHolder target, View source) {
    this.target = target;

    target.usernameText = Utils.findRequiredViewAsType(source, R.id.usernameText, "field 'usernameText'", TextView.class);
    target.dateText = Utils.findRequiredViewAsType(source, R.id.dateText, "field 'dateText'", TextView.class);
    target.contentText = Utils.findRequiredViewAsType(source, R.id.contentText, "field 'contentText'", TextView.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.myImage, "field 'imageView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChatAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.usernameText = null;
    target.dateText = null;
    target.contentText = null;
    target.imageView = null;
  }
}
