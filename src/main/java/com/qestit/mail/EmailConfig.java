/*
 * Copyright (c) 2023 QESTIT.
 * QesteniumX - QESTIT
 */

package com.qestit.mail;

import static com.qestit.constants.FrameworkConstants.REPORT_TITLE;

/**
 * Data for Sending email after execution
 */
public class EmailConfig {

    //Remember to create an app password (App Password) for Gmail before sending
    //If you use Hosting's email, it's normal
    //Enable Override Report and Send mail in config file => src/test/resources/config/config.properties
    //OVERRIDE_REPORTS=yes
    //send_email_to_users=yes

    public static final String SERVER = "smtp.gmail.com";
    public static final String PORT = "465";

    public static final String FROM = "";
    public static final String PASSWORD = "";

    public static final String[] TO = {"khalilhany76@gmail.com"};
    public static final String SUBJECT = REPORT_TITLE;
}