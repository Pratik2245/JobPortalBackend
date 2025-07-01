package com.jobportal.Job.Portal.utility;

public class OtpData {

    public static String getFormattedOtp(String otp, String name) {
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
                "      max-width: 550px;\n" +
                "      margin: 0 auto;\n" +
                "      border-radius: 12px;\n" +
                "      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);\n" +
                "      border-top: 6px solid #1a73e8;\n" +
                "    }\n" +
                "    .header {\n" +
                "      text-align: center;\n" +
                "      color: #1a73e8;\n" +
                "      font-size: 24px;\n" +
                "      font-weight: bold;\n" +
                "    }\n" +
                "    .content {\n" +
                "      margin-top: 20px;\n" +
                "      color: #333;\n" +
                "      font-size: 16px;\n" +
                "    }\n" +
                "    .otp-box {\n" +
                "      background-color: #f0f8ff;\n" +
                "      border: 2px dashed #1a73e8;\n" +
                "      padding: 20px;\n" +
                "      font-size: 28px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: center;\n" +
                "      color: #1a73e8;\n" +
                "      margin: 30px 0;\n" +
                "      border-radius: 10px;\n" +
                "    }\n" +
                "    .footer {\n" +
                "      margin-top: 40px;\n" +
                "      font-size: 12px;\n" +
                "      color: #888;\n" +
                "      text-align: center;\n" +
                "      line-height: 1.6;\n" +
                "    }\n" +
                "    .footer a {\n" +
                "      color: #1a73e8;\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"email-container\">\n" +
                "    <div class=\"header\">Your One-Time Password (OTP)</div>\n" +
                "    <div class=\"content\">\n" +
                "      <p>Hello <strong>" + name + "</strong>,</p>\n" +
                "      <p>Use the following One-Time Password (OTP) to complete your verification process:</p>\n" +
                "      <div class=\"otp-box\">" + otp + "</div>\n" +
                "      <p>This OTP is valid for <strong>10 minutes</strong>. Please do not share it with anyone.</p>\n" +
                "      <p>If you did not request this, you can safely ignore this email.</p>\n" +
                "    </div>\n" +
                "    <div class=\"footer\">\n" +
                "      &copy; 2025 SparksVision. All rights reserved.<br>\n" +
                "      SparksVision Pvt. Ltd., Pune, India<br>\n" +
                "      Need help? <a href=\"mailto:support@sparksvision.com\">Contact our support team</a>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
