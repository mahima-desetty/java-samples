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

import com.google.api.services.classroom.model.Topic;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

// Unit test class for List Topics classroom snippet
public class TestListTopics extends BaseTest {

  @Test
  public void testListTopics() throws IOException {
    CreateTopic.createTopic(testCourse.getId());
    List<Topic> listTopics = ListTopics.listTopics(testCourse.getId());
    Assert.assertNotNull("Topics could not be retrieved.", listTopics);
    Assert.assertFalse("No topics were retrieved.", listTopics.size() == 0);
  }
}
