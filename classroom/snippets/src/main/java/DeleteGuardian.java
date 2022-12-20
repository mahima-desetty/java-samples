// Copyright 2022 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


// [START classroom_delete_guardian_class]

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.ClassroomScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import java.util.Collections;

/* Class to demonstrate the use of Classroom Delete Guardian API. */
public class DeleteGuardian {
  /**
   * Delete a guardian for a specific student.
   *
   * @param studentId - the id of the student the guardian belongs to.
   * @param guardianId - the id of the guardian to delete.
   * @throws IOException - if credentials file not found.
   */
  public static void deleteGuardian(String studentId, String guardianId) throws IOException {
    /* Load pre-authorized user credentials from the environment.
     TODO(developer) - See https://developers.google.com/identity for
      guides on implementing OAuth2 for your application. */
    GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
        .createScoped(Collections.singleton(ClassroomScopes.CLASSROOM_GUARDIANLINKS_STUDENTS));
    HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
        credentials);

    // Create the classroom API client.
    Classroom service = new Classroom.Builder(new NetHttpTransport(),
        GsonFactory.getDefaultInstance(),
        requestInitializer)
        .setApplicationName("Classroom samples")
        .build();

    // [START classroom_delete_guardian_code_snippet]
    try {
      service.userProfiles().guardians().delete(studentId, guardianId)
          .execute();
    } catch (GoogleJsonResponseException e) {
      GoogleJsonError error = e.getDetails();
      if (error.getCode() == 404) {
        System.out.printf("There is no record of guardianId (%s).", guardianId);
      }
    }
    // [END classroom_delete_guardian_code_snippet]
  }
}
// [END classroom_delete_guardian_class]