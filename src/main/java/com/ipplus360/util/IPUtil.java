package com.ipplus360.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 辉太郎 on 2017/2/5.
 */
public class IPUtil {

    public static long ipToLong(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            long ipv4 = ByteBuffer.wrap(address.getAddress()).order(ByteOrder.BIG_ENDIAN).getInt() & 0xffffffffL;

            return ipv4;
        } catch (Exception e) {
            return -1;
        }

    }

    public static String ipFromLong(Long ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(String.valueOf(ipAddress));
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }

    }

    public static boolean isIPv4(String ipAddress){
        InetAddress address;
        try {
            address = InetAddress.getByName(ipAddress);
            if(address instanceof Inet4Address){
                return true;
            }
            else {
                //Returning null if ipAddress is undefined
                return false;
            }
        } catch (UnknownHostException e) {
            //Returning null if ipAddress is undefined
            return false;
        }
    }

    public static String get24Subnet(String address) {
        return address.substring(0, address.lastIndexOf("."));
    }

    public static boolean isFormatValid(String ip) {
        boolean isValidAddressFormat;
        String ipv4PatternString = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
        Pattern ipv4Pattern = Pattern.compile(ipv4PatternString);
//        String ipv6PatternString = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
//        Pattern ipv6Pattern = Pattern.compile(ipv6PatternString);

        if (!ipv4Pattern.matcher(ip).matches()) {
            isValidAddressFormat = false;
        } else {
            isValidAddressFormat = true;
        }
        return isValidAddressFormat;
    }

    //获取用户端IP
    public static String getUserIP(HttpServletRequest request) {

        try {

            if (request != null) {

                String ip = request.getHeader("X-Forwarded-For");

                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getHeader("Proxy-Client-IP");
                    }
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getHeader("WL-Proxy-Client-IP");
                    }
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getHeader("HTTP_CLIENT_IP");
                    }
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                    }
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getRemoteAddr();
                    }
                } else if (ip.length() > 15) {
                    String[] ips = ip.split(",");
                    for (int index = 0; index < ips.length; index++) {
                        String strIp = ips[index];
                        if (!("unknown".equalsIgnoreCase(strIp))) {
                            ip = strIp;
                            break;
                        }
                    }
                }
                return ip;

            } else {
                return "";
            }
        } catch(Exception e) {
            return "";
        }
    }
}
