package com.example.watersystem.personal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watersystem.R;
import com.example.watersystem.personal.PersonalFragment;
import com.example.watersystem.personal.type.PersonalCenterItemType;
import com.example.watersystem.personal.type.PersonalNameType;
import com.example.watersystem.personal.type.PersonalNewVersionType;
import com.example.watersystem.personal.type.PersonalPhoneNumberType;

import java.util.Collections;
import java.util.List;

public class PersonalCenterItemAdapter extends RecyclerView.Adapter<PersonalCenterItemAdapter.PersonalCenterItemHolder> {

    private PersonalFragment personalFragment;
    private List<PersonalCenterItemType> data;
    private OnItemClickLister onItemClickLister;

    public PersonalCenterItemAdapter(PersonalFragment personalFragment, List<PersonalCenterItemType> mData) {
        this.personalFragment = personalFragment;
        if (mData == null) {
            data = Collections.emptyList();
        } else {
            data = mData;
        }
    }

    @NonNull
    @Override
    public PersonalCenterItemAdapter.PersonalCenterItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_personal_center_item, parent, false);
        PersonalCenterItemHolder holder = new PersonalCenterItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalCenterItemAdapter.PersonalCenterItemHolder holder, int position) {
        PersonalCenterItemType personalCenterItemType = data.get(position);

        // 设置手机号眼睛icon可见性
        if (personalCenterItemType.getItemType() == PersonalCenterItemType.PERSONAL_CENTER_ITEM_PHONE) {
            PersonalPhoneNumberType personalPhoneNumberType = (PersonalPhoneNumberType) personalCenterItemType;
            holder.itemPhoneView.setVisibility(View.VISIBLE);
            holder.itemPhoneView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((PersonalPhoneNumberType) personalCenterItemType).isPlain()) {
                        holder.itemPhoneView.setImageResource(R.drawable.login_psd_off);
                        personalPhoneNumberType.setPlain(false);
                    } else {
                        holder.itemPhoneView.setImageResource(R.drawable.login_psd_on);
                        personalPhoneNumberType.setPlain(true);
                    }
                    personalFragment.onPhoneStatusClick();
                }
            });
        }

        // 设置版本更新icon可见性
        if (personalCenterItemType.getItemType() == PersonalCenterItemType.PERSONAL_CENTER_NEW_VERSION) {
            PersonalNewVersionType personalNewVersionType = (PersonalNewVersionType) personalCenterItemType;
            holder.versionNew.setVisibility(View.VISIBLE);
            holder.itemArrowView.setVisibility(personalNewVersionType.isShow() ? View.VISIBLE : View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickLister != null) {
                        onItemClickLister.onItemClicked(personalNewVersionType);
                    }
                }
            });
        }

        if (personalCenterItemType.getItemType() == PersonalCenterItemType.PERSONAL_CENTER_ITEM_NAME) {
            PersonalNameType personalNameType = (PersonalNameType) personalCenterItemType;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickLister != null) {
                        onItemClickLister.onItemClicked(personalNameType);
                    }
                }
            });
        }

        holder.itemKeyView.setText(personalCenterItemType.getItemKey());
        holder.itemValueView.setText(personalCenterItemType.getItemValue());

    }

    public void setData(List<PersonalCenterItemType> mData) {
        if (mData == null || mData.isEmpty()) {
            if (data != null) {
                data.clear();
            }
        } else {
            data = mData;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PersonalCenterItemHolder extends RecyclerView.ViewHolder {

        private TextView itemKeyView;
        private TextView itemValueView;
        private ImageView versionNew;
        private ImageView itemPhoneView;
        private ImageView itemArrowView;

        public PersonalCenterItemHolder(@NonNull View itemView) {
            super(itemView);

            itemKeyView = itemView.findViewById(R.id.personal_item_key);
            itemValueView = itemView.findViewById(R.id.personal_item_value);
            versionNew = itemView.findViewById(R.id.personal_version_new);
            itemPhoneView = itemView.findViewById(R.id.personal_item_phone);
            itemArrowView = itemView.findViewById(R.id.personal_item_arrow);

        }
    }

    public void setShowNewEditionView(boolean isShow) {
        if (data != null && !data.isEmpty()) {
            for (PersonalCenterItemType itemType : data) {
                if (itemType.getItemType() == PersonalCenterItemType.PERSONAL_CENTER_NEW_VERSION) {
                    PersonalNewVersionType editionType = (PersonalNewVersionType) itemType;
                    editionType.setShow(isShow);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public interface OnItemClickLister {
        void onItemClicked(PersonalCenterItemType type);
    }
}
