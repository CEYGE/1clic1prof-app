package fr.clic1prof.repositories.profile;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;

import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.models.profile.modifier.PasswordModifier;

public interface ProfileRepository<T extends Profile> {

    LiveData<T> getProfile();

    LiveData<String> updateFirstName(String firstName);

    LiveData<String> updateLastName(String lastName);

    LiveData<Boolean> updatePassword(PasswordModifier modifier);

    LiveData<Bitmap> updatePicture(Bitmap bitmap);
}
