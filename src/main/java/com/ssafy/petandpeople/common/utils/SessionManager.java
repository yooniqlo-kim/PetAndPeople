package com.ssafy.petandpeople.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionManager {

    public static Long getUserKeyFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // 세션체크 로직
        return (long) session.getAttribute("userKey");
    }

}
