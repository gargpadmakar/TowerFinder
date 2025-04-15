package com.padmakar.jetpackcomposeintroduction.utils

object AppConfig {

    // Shared Preferences Keys
    const val PAYLOAD_BUNDLE = "CellId"
    const val SHARED_PREF = "CellId_app"
    const val USER_ID = "user_id"
    const val USER_EMAIL_ID = "user_email"
    const val USER_FULL_NAME = "user_full_name"
    const val USER_PHONE = "user_phone"
    const val USER_PROFILE = "user_profile"
    const val USER_TOKEN = "user_token"
    const val USER_PACKAGE = "user_package"
    const val USER_PACKAGE_DURATION = "user_package_duration"
    const val LAST_OPENED_TIMESTAMP = "last_opened_timestamp"
    const val TOKEN_PREFIX = "Bearer "

    // Splash Timeout in milliseconds
    const val SPLASH_TIME_OUT: Long = 100

    // Push Notification Topics
    const val TOPIC_GLOBAL = "global"

    // Broadcast Receiver Intent Filters
    const val REGISTRATION_COMPLETE = "registrationComplete"
    const val PUSH_NOTIFICATION = "pushNotification"

    // Notification IDs
    const val NOTIFICATION_ID = 100
    const val NOTIFICATION_ID_BIG_IMAGE = 101

    // Firebase Shared Preferences Key
    const val SHARED_PREF_FIREBASE = "ah_firebase"
}