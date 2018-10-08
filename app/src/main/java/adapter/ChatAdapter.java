package adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sevenacademy.sevendemo.Chat;
import com.example.sevenacademy.sevendemo.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends FirebaseRecyclerAdapter<Chat, ChatAdapter.ViewHolder> {


    Context context;

    public ChatAdapter(Context ctx, Class<Chat> modelClass, int modelLayout, Class<ViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        context = ctx;
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, Chat model, int position) {
        String email[] = model.getName().split("[@]");
        viewHolder.usernameText.setText(email[0]);
        viewHolder.dateText.setText(new SimpleDateFormat().format(new Date(model.getTime())));
        viewHolder.contentText.setText(model.getMessage());
        if (model.getImage() != null){
            Glide.with(context)
                    .load(model.getImage())
                    .into(viewHolder.imageView);
        }

    }

    @Override
    public void cleanup() {
        super.cleanup();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.usernameText)
        TextView usernameText;
        @BindView(R.id.dateText)
        TextView dateText;
        @BindView(R.id.contentText)
        TextView contentText;
        @BindView(R.id.myImage)
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
