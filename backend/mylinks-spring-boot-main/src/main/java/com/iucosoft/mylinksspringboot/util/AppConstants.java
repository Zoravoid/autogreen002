package com.iucosoft.mylinksspringboot.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    public static class Messages {
        public static final String CATEGORY_REMOVED_SUCCESSFULLY_FROM_GROUP = "Category removed successfully from group.";
        public static final String MEMBER_ALREADY_IN_GROUP = "Member already in group.";
        public static final String MEMBER_REMOVED_FROM_GROUP_SUCCESSFULLY = "Member removed from group successfully.";
        public static final String LINK_REMOVED_FROM_CATEGORY_TEMPLATE = "Link with id = %s removed from category with id = %s";
    }

    public static class Fields {
        public static final String ID = "id";
        public static final String TITLE = "title";
    }
}
