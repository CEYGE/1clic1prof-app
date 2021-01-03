package fr.clic1prof.repositories.profile.v2;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;

import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.models.profile.modifier.PasswordModifier;
import fr.clic1prof.util.DataListener;

public interface ProfileRepository<T extends Profile> {

    void getProfile(DataListener<T> listener);

    void getProfilePicture(DataListener<Bitmap> listener);

    void updateFirstName(String firstName, DataListener<Void> listener);

    void updateLastName(String lastName, DataListener<Void> listener);

    void updatePassword(PasswordModifier modifier, DataListener<Void> listener);

    void updatePicture(Bitmap bitmap, DataListener<Void> listener);

    void deleteProfilePicture(DataListener<Void> listener);
}
