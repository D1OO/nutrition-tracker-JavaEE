package net.shvdy.nutrition_tracker.dto;

import net.shvdy.nutrition_tracker.model.entity.UserProfile;

/**
 * 30.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class UserProfileDTO {

    private Long profileId;
    private String firstName;
    private String lastName;
    private UserProfile.Lifestyle lifestyle;
    private int age;
    private int height;
    private int weight;

    public UserProfileDTO(Long profileId, String firstName, String lastName,
                          UserProfile.Lifestyle lifestyle, int age, int height, int weight) {
        this.profileId = profileId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lifestyle = lifestyle;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public static UserProfileDTOBuilder builder() {
        return new UserProfileDTOBuilder();
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserProfile.Lifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(UserProfile.Lifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public static final class UserProfileDTOBuilder {
        private Long profileId;
        private String firstName;
        private String lastName;
        private UserProfile.Lifestyle lifestyle;
        private int age;
        private int height;
        private int weight;

        private UserProfileDTOBuilder() {
        }

        public UserProfileDTOBuilder profileId(Long profileId) {
            this.profileId = profileId;
            return this;
        }

        public UserProfileDTOBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserProfileDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserProfileDTOBuilder lifestyle(UserProfile.Lifestyle lifestyle) {
            this.lifestyle = lifestyle;
            return this;
        }

        public UserProfileDTOBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserProfileDTOBuilder height(int height) {
            this.height = height;
            return this;
        }

        public UserProfileDTOBuilder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public UserProfileDTO build() {
            return new UserProfileDTO(profileId, firstName, lastName, lifestyle, age, height, weight);
        }
    }
}
