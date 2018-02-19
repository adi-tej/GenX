package com.genx.evelyn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
@Document(collection = "user")
public class User {
    @Id
    @Field("user_id")
    private String userId;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    private String role;
    private String email;
    private String mobile;
    @Field("profile_image")
    private String profileImage;
    @Field("profile_description")
    private String profileDescription;
    private String status;


    /**
     * Gets firstName.
     * @return Value of firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets new profileDescription.
     * @param profileDescription New value of profileDescription.
     */
    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    /**
     * Gets profileImage.
     * @return Value of profileImage.
     */
    public String getProfileImage() {
        return profileImage;
    }

    /**
     * Sets new email.
     * @param email New value of email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets new lastName.
     * @param lastName New value of lastName.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets new mobile.
     * @param mobile New value of mobile.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Gets mobile.
     * @return Value of mobile.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets new profileImage.
     * @param profileImage New value of profileImage.
     */
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * Gets email.
     * @return Value of email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets new userId.
     * @param userId New value of userId.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets profileDescription.
     * @return Value of profileDescription.
     */
    public String getProfileDescription() {
        return profileDescription;
    }

    /**
     * Gets userId.
     * @return Value of userId.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets status.
     * @return Value of status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets new firstName.
     * @param firstName New value of firstName.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets lastName.
     * @return Value of lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets new status.
     * @param status New value of status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets new role.
     * @param role New value of role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets role.
     * @return Value of role.
     */
    public String getRole() {
        return role;
    }
}
