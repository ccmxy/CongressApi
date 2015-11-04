package com.example.epicodus.congressapp;

/**
 * Created by Guest on 11/3/15.
 */
public class CongressDetails {
    private  String mLastName;
    private  String mFirstName;
    private  String mGender;
    private  String mParty;
    private  String mAge;

    public String getLastName() {
        return mLastName;
    }

    public CongressDetails(String firstName, String lastName, String gender, String party, String age)
    {
        mLastName = lastName;
        mFirstName = firstName;
        mGender = gender;
        mParty = party;
        mAge = age;
    }
    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getParty() {
        return mParty;
    }

    public void setParty(String party) {
        mParty = party;
    }

    public String getAge() {
        return mAge;
    }

    public void setAge(String age) {
        mAge = age;
    }



}
