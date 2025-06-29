package com.jobportal.Job.Portal.utility;

public class OtpData {

    public static String getFormattedOtp(String otp) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>Your OTP Code</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      background-color: #f4f4f4;\n" +
                "      padding: 20px;\n" +
                "    }\n" +
                "    .email-container {\n" +
                "      background-color: #ffffff;\n" +
                "      padding: 30px;\n" +
                "      max-width: 500px;\n" +
                "      margin: 0 auto;\n" +
                "      border-radius: 10px;\n" +
                "      box-shadow: 0 0 5px rgba(0,0,0,0.1);\n" +
                "    }\n" +
                "    .header {\n" +
                "      text-align: center;\n" +
                "      color: #333;\n" +
                "    }\n" +
                "    .otp {\n" +
                "      font-size: 32px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: center;\n" +
                "      color: #1a73e8;\n" +
                "      margin: 20px 0;\n" +
                "    }\n" +
                "    .footer {\n" +
                "      margin-top: 30px;\n" +
                "      font-size: 12px;\n" +
                "      color: #999;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"email-container\">\n" +
                "    <h2 class=\"header\">Your One-Time Password (OTP)</h2>\n" +
                "    <p>Hello,</p>\n" +
                "    <p>Use the following OTP to complete your verification process:</p>\n" +
                "    <div class=\"otp\">" + otp + "</div>\n" +  // Dynamic OTP insertion
                "    <p>This OTP is valid for 10 minutes. Please do not share it with anyone.</p>\n" +
                "    <p>If you did not request this, please ignore this email.</p>\n" +
                "    <div class=\"footer\">\n" +
                "      © 2025 SparksVision. All rights reserved.<br>\n" +
                "      Need help? Contact support@sparksvision.com\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
