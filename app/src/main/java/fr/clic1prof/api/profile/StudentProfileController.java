package fr.clic1prof.api.profile;

import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.models.user.SchoolLevel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface StudentProfileController extends ProfileController {

    @GET("student/profile")
    Call<StudentProfile> getProfile();

    @PUT("student/school-level")
    Call<Void> updateSchoolLevel(@Body SchoolLevel level);
}
