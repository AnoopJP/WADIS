package com.phoenix.wdis.util;

public class CTApiConstants
{

    /*
     * ErrorMessages
     */

    public static final String kNetworkErrorMessage =
        "No Internet connection detected. Please try again later.";
    public static final String kNetworkTimeoutErrorMessage =
        "Connection timed out. Please try again later.";
    public static final String kInternalServerErrorMessage =
        "Internal Server Error. Please try again later.";
    public static final String kAuthenticationErrorMessage =
        "Authentication Error. Please try again later.";
    public static final String kApiErrorMessage = "Web Service Error. Please try again later.";
    public static final String kBadRequestErrorMessage =
        "API Request Error. Please try again later.";

    // Network Constants

    public static final String kNetworkErrorExceptionKey = "101";
    public static final String kTimeOutErrorExceptionKey = "102";
    public static final String kInternalServerErrorExceptionKey = "103";
    public static final String kAuthenticationErrorExceptionKey = "104";
    public static final String kBadRequestErrorExceptionKey = "105";
    public static final int kNetworkErrorExceptionResponseKey = 101;
    public static final int kTimeOutErrorExceptionResponseKey = 102;
    public static final int kInternalServerExceptionResponseKey = 103;
    public static final int kAuthenticationExceptionResponseKey = 104;

    public static final String kSuccessMsgKey = "Success";
    public static final String kNetworkError = "No Network";
    public static final String kApiError = "API Error";
    public static final String kApiUnauthMsgKey = "UnAuthorized";
    public static final String kApiFailedMsgKey = "Failure";
    public static final String kResponseBeanKey = "responseBean";
    public static final String kErrorMsgKey = "errorMessages";
    public static final String KResponseMessagesKey = "responseMessages";

    // HTTP Status Code Constants

    public static final String kHttpStatusKey = "HttpStatus";

    // Request Time out param

    public static final int kRequestTimeOutInMills = 20000;

    // Test Urls

    // public static final String kCTBaseUrl = "http://115.249.121.214:8080/";
    // public static final String kCTBaseUrl = "http://115.249.121.221:8080/";
    // public static final String kCTBaseUrl = "http://115.249.121.211:8080/";
    // public static final String kCTBaseUrl = "http://115.249.76.217:8080/";
    public static final String kCTBaseUrl = "http://115.249.76.219:8080/";

    public static final String kCTLoginUrl = "doLogin";
    public static final String kCTUpdateCarePlanUrl = "carePlan";
    public static final String kCTMedicationUrl = "medication";
    public static final String kCTFetchCCListUrl = "fetchCCList";
    public static final String kCTFetchPCGServicesUrl = "fetchPCGServices";
    public static final String kCTFetchMedSessionListUrl = "fetchMedSessionInfo";
    public static final String kCTUpdateMedicationSession = "updateMedSession";
    public static final String kCTFetchMedicationListUrl = "fetchMedicationList";
    public static final String kCTFetchPCGProfileUrl = "fetchPCGProfile";
    public static final String kCTFetchFamilyProfileUrl = "fetchFamilyProfile";
    public static final String kCTFetchLookUpDataUrl = "fetchLookupData";
    public static final String kCTFetchAlertGroupUrl = "fetchAlertGroups";
    public static final String kCTFetchCalendartasksUrl = "fetchCalendarTasks";

}
