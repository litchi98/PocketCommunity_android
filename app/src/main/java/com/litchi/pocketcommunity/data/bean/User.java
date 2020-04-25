package com.litchi.pocketcommunity.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public static final int ROLE_MANAGER = 1;
    public static final int ROLE_STANDARD = 2;

    private int id;
    private String name;
    private String password;
    private String gender;
    private String telNumber;
    private String identificationId;
    private int identificationImageId;
    private int contractImageId;
    private int avatarImageId;
    private int roleId;

    public User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        password = in.readString();
        gender = in.readString();
        telNumber = in.readString();
        identificationId = in.readString();
        identificationImageId = in.readInt();
        contractImageId = in.readInt();
        avatarImageId = in.readInt();
        roleId = in.readInt();
    }

    public User() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(gender);
        dest.writeString(telNumber);
        dest.writeString(identificationId);
        dest.writeInt(identificationImageId);
        dest.writeInt(contractImageId);
        dest.writeInt(avatarImageId);
        dest.writeInt(roleId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getIdentificationId() {
        return identificationId;
    }

    public void setIdentificationId(String identificationId) {
        this.identificationId = identificationId;
    }

    public int getIdentificationImageId() {
        return identificationImageId;
    }

    public void setIdentificationImageId(int identificationImageId) {
        this.identificationImageId = identificationImageId;
    }

    public int getContractImageId() {
        return contractImageId;
    }

    public void setContractImageId(int contractImageId) {
        this.contractImageId = contractImageId;
    }

    public int getAvatarImageId() {
        return avatarImageId;
    }

    public void setAvatarImageId(int avatarImageId) {
        this.avatarImageId = avatarImageId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
