package com.litchi.pocketcommunity.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Proposal implements Parcelable {
    public static final int STATE_TO_BE_CONFIRMED = 0;
    public static final int STATE_TO_BE_PROCESSED = 1;
    public static final int STATE_PROCESSING = 2;
    public static final int STATE_FINISH = 3;

    private Integer id;

    private Integer proposerId;

    private Integer currentProcessorId;

    private Integer state;

    private String title;

    private String content;

    private Date proposeDate;

    protected Proposal(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            proposerId = null;
        } else {
            proposerId = in.readInt();
        }
        if (in.readByte() == 0) {
            currentProcessorId = null;
        } else {
            currentProcessorId = in.readInt();
        }
        if (in.readByte() == 0) {
            state = null;
        } else {
            state = in.readInt();
        }
        title = in.readString();
        content = in.readString();
        proposeDate = new Date(in.readLong());
    }

    public static final Creator<Proposal> CREATOR = new Creator<Proposal>() {
        @Override
        public Proposal createFromParcel(Parcel in) {
            return new Proposal(in);
        }

        @Override
        public Proposal[] newArray(int size) {
            return new Proposal[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProposerId() {
        return proposerId;
    }

    public void setProposerId(Integer proposerId) {
        this.proposerId = proposerId;
    }

    public Integer getCurrentProcessorId() {
        return currentProcessorId;
    }

    public void setCurrentProcessorId(Integer currentProcessorId) {
        this.currentProcessorId = currentProcessorId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getProposeDate() {
        return proposeDate;
    }

    public void setProposeDate(Date proposeDate) {
        this.proposeDate = proposeDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStateText() {
        switch (this.state){
            case STATE_TO_BE_PROCESSED:
                return "待处理";
            case STATE_PROCESSING:
                return "处理中";
            case STATE_TO_BE_CONFIRMED:
                return "待确认";
            case STATE_FINISH:
                return "已关闭";
            default:
                return "ERROR";
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (proposerId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(proposerId);
        }
        if (currentProcessorId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currentProcessorId);
        }
        if (state == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(state);
        }
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeLong(proposeDate.getTime());
    }
}
