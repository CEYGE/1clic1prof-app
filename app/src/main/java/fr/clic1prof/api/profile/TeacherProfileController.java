package fr.clic1prof.api.profile;

import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.models.profile.modifier.SpecialityModifier;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface TeacherProfileController extends ProfileController {

    @GET("teacher/profile")
    Call<TeacherProfile> getProfile();

    @PUT("teacher/profile/studies")
    Call<String> updateStudies(@Body String studies);

    @PUT("teacher/profile/description")
    Call<String> updateDescription(@Body String description);

    @PUT("teacher/profile/speciality")
    Call<SpecialityModifier> updateSpeciality(SpecialityModifier modifier);
}
