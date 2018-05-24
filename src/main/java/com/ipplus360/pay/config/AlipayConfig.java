package com.ipplus360.pay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088021637979680";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK8UqJD+DLHom7XQh56fxq/catrSpLwrLLh/2Mo+Fc5B0ZrtFdb186M9CqSaRIFOHuBdl78bj+WSMyMlk1hJOmsTx/yWYdYPDFQ2F3f0p3tNtZ+AXHso9ga3Q05SX98cLbhQ1rze3N5RxX0zR+PLoBe9ys/1U7a7XknwNqBMXUEDAgMBAAECgYEAkvHWzOK9CvD9B2s++G6HlwB7mq9i3h+qiCuda5kSOivZ3ZsRUnrZgu9vqFlmF9TWa1+CRj6+1mKPkvctpNLJOMPjR0h9ZDxyC6dADOHFAbXtO/wdqq3u0dwQmoyHvgtUbszJqP64suj7cuOPZzE3nEN+CFFFTxQpoFLkQMiaZ5ECQQDfEUQoIHkedN07GBlPpSrTdPvle0oDjX/Ln6nftR6evPM528ubl9UaQJC7dpaMG08SZ+LH401HUDSIl2Fnmi3JAkEAyO3BL6itMQzJEYi/3YisB3MTgwFw++L1l2SOuy1HXInNxfkdR5l0RcEob7VwTNIyw634bgRMuKO4fyyDhiIuawJAD733MmHASm7y4eW8e4trb0rVMAe4XHHS7/rPkTMF+yvYel/RiaR4bIbb7+7Zhv+yX9km/vZCkOLPIkiUniwaSQJAW9kDBEwwz0ku6IsAWb5OFSGve6U/BoWbCld3P2zVQCHmJGdZ0Q7+rZw8ZcQkz5kOh5CRDYl9oLGGduvcO4aIfQJBAMHxL13cSTpzX9S/JXnuROl3L2DOmu+2xNa5l+OkSLq7j2nAFgJAxXzpkpl21nrt+JTjFjIEAYmkmVkPaORRnxs=";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "https://mall.ipplus360.com/alipay/notify";
	//public static String notify_url = "http://47.94.58.33:8080/alipay/notify";	// TODO
	//public static String notify_url = "http://test.mall.ipplus360.com/alipay/notify";	// TODO

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "https://mall.ipplus360.com/alipay/return";
	//public static String return_url = "http://47.94.58.33:8080/alipay/return";	// TODO
	//public static String return_url = "https://mall.ipplus360.com/alipay/return";	// TODO

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	//public static String log_path = "/home/aiwenmall/logs/alipay/";
	public static String log_path = "/home/mall/logs/alipay/";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
		
//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
}

