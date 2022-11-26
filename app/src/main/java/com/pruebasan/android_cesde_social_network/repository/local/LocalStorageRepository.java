package com.pruebasan.android_cesde_social_network.repository.local;

import android.content.Context;

import com.pruebasan.android_cesde_social_network.models.Post;
import com.pruebasan.android_cesde_social_network.models.User;

public class LocalStorageRepository {
    public static User getSavedUser(Context context) {
        String id = (String) PreferencesHelper.getPreferences(context, ConstantsPreferences.keyId);
        String username = (String) PreferencesHelper.getPreferences(context, ConstantsPreferences.keyNames);
        String email = (String) PreferencesHelper.getPreferences(context, ConstantsPreferences.keyEmail);
        String phone = (String) PreferencesHelper.getPreferences(context, ConstantsPreferences.keyCellPhone);
        String petName = (String) PreferencesHelper.getPreferences(context, ConstantsPreferences.keyPetName);
        Integer petAge = (Integer) PreferencesHelper.getPreferences(context, ConstantsPreferences.keyPetAge);
        String avatarType = (String) PreferencesHelper.getPreferences(context, ConstantsPreferences.keyAvatarType);

        if(id == null || username == null || email == null || phone == null || petName == null || petAge == null || avatarType == null)
            return null;

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPetName(petName);
        user.setPetAge(petAge);
        user.setAvatarType(avatarType);

        return user;
    }

    public static void saveUser(Context context, User user) {
        PreferencesHelper.setPreferences(context, ConstantsPreferences.keyId, user.getId());
        PreferencesHelper.setPreferences(context, ConstantsPreferences.keyNames, user.getUsername());
        PreferencesHelper.setPreferences(context, ConstantsPreferences.keyEmail, user.getEmail());
        PreferencesHelper.setPreferences(context, ConstantsPreferences.keyCellPhone, user.getPhone());
        PreferencesHelper.setPreferences(context, ConstantsPreferences.keyPetName, user.getPetName());
        PreferencesHelper.setPreferences(context, ConstantsPreferences.keyPetAge, user.getPetAge());
        PreferencesHelper.setPreferences(context, ConstantsPreferences.keyAvatarType, user.getAvatarType());
    }

    public static void deleteUser(Context context) {
        PreferencesHelper.deletePreferences(context);
    }

    public static Post getSavedPost(Context context) {
        String id = (String) PreferencesHelper.getPreferences(context, ConstantsPreferences.keyPostId);
        String title = (String) PreferencesHelper.getPreferences(context, ConstantsPreferences.keyPostTitle);
        String description = (String) PreferencesHelper.getPreferences(context, ConstantsPreferences.keyPostDescription);

        if(id == null || title == null || title == null)
            return null;

        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setMessage(description);

        return post;
    }

    public static void savePost(Context context, Post post) {
        PreferencesHelper.setPreferences(context, ConstantsPreferences.keyPostId, post.getId());
        PreferencesHelper.setPreferences(context, ConstantsPreferences.keyPostTitle, post.getTitle());
        PreferencesHelper.setPreferences(context, ConstantsPreferences.keyPostDescription, post.getMessage());
    }

}
