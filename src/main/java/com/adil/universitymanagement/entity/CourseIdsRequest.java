package com.adil.universitymanagement.entity;

import java.util.List;

public class CourseIdsRequest {
        private List<Long> courseIds;

        public List<Long> getCourseIds() {
            return courseIds;
        }

        public void setCourseIds(List<Long> courseIds) {
            this.courseIds = courseIds;
        }
}


