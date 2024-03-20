package com.example.contentmedia.ui.contact;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contentmedia.R;
import com.example.contentmedia.model.Contact;

import java.util.ArrayList;
import java.util.Random;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    ArrayList<Contact> lstContact;
    Context context;
    ContactCallback contactCallback;
    public ContactAdapter(ArrayList<Contact> lstContact){this.lstContact=lstContact;}

public void setContactCallback(ContactCallback contactCallback){
        this.contactCallback=contactCallback;
}

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View userView=inflater.inflate(R.layout.itemcontact,parent,false);
        ContactViewHolder viewHolder=new ContactViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = lstContact.get(position);
        holder.tvContactName.setText(contact.getName() + " - " + contact.getPhoneNumber());

        // Tạo màu ngẫu nhiên
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.ivPhoto.setImageResource(R.drawable.baseline_account_circle_24);
        holder.ivPhoto.setColorFilter(color);

        holder.ivCall.setImageResource(R.drawable.baseline_local_phone_24);
        holder.ivCall.setOnClickListener(v -> contactCallback.onItemClickedCall(contact.getPhoneNumber()));
    }



    @Override
    public int getItemCount() {
        return lstContact.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public final TextView tvContactName;
        public final ImageView ivPhoto;
        public final ImageView ivCall;
        public ContactViewHolder(@NonNull View itemView){
            super(itemView);
            tvContactName=itemView.findViewById(R.id.tv_contact_name);
            ivPhoto=itemView.findViewById(R.id.iv_photo);
            ivCall=itemView.findViewById(R.id.iv_call);
        }
    }
    public interface ContactCallback{
        void onItemClickedCall(String Phonenumber);
    }

}
